package ujkz.aestheticEvaluation.metricCalculator.metrics;


import ujkz.aestheticEvaluation.metricCalculator.util.Helper;

import java.math.BigInteger;
import java.util.List;

public class Homogeneity extends Metric{

    private static Homogeneity instance;

    private Homogeneity() {super();}

    public static Homogeneity getInstance() {
        if (instance == null) {
            synchronized (Homogeneity.class) {
                instance = new Homogeneity();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        int n_ul = 0;
        int n_ur = 0;
        int n_ll = 0;
        int n_lr = 0;
        int n = composantList.size();

        double compo_center_x, compo_center_y;

        for ( Composant composant: composantList ) {
            compo_center_x = composant.getX()+composant.getWidth()/2;
            compo_center_y = composant.getY()+composant.getHeight()/2;
            if ( (compo_center_x <= width_frame/2) && (compo_center_y) <= height_frame/2 ) {
                n_ul += 1;
            }
            if ( (compo_center_x >= width_frame/2) && (compo_center_y) <= height_frame/2 ) {
                n_ur += 1;
            }
            if ( (compo_center_x <= width_frame/2) && (compo_center_y) >= height_frame/2 ) {
                n_ll += 1;
            }
            if ( (compo_center_x >= width_frame/2) && (compo_center_y) >= height_frame/2 ) {
                n_lr += 1;
            }
        }

        BigInteger fn = Helper.factorial(n/4);
        BigInteger fn_ul = Helper.factorial(n_ul);
        BigInteger fn_ur = Helper.factorial(n_ur);
        BigInteger fn_ll = Helper.factorial(n_ll);
        BigInteger fn_lr = Helper.factorial(n_lr);

        fn = fn.pow(4);
        BigInteger hom = fn.divide(fn_ul.multiply(fn_ur).multiply(fn_ll).multiply(fn_lr));
        return hom.doubleValue();
    }
}
