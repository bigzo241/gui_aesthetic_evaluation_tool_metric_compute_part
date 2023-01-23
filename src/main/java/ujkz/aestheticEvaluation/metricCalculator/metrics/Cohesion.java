package ujkz.aestheticEvaluation.metricCalculator.metrics;


import ujkz.aestheticEvaluation.metricCalculator.util.Helper;

import java.util.List;

public class Cohesion extends Metric{
    private static Cohesion instance;
    private final Helper helper = Helper.getInstance();

    private Cohesion() {super();}

    public static Cohesion getInstance() {
        if (instance == null) {
            synchronized (Cohesion.class) {
                instance = new Cohesion();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        double CM_fl;
        double CM_lo;
        double width_layout = helper.getWidthLayout(composantList);
        double height_layout = helper.getHeightLayout(composantList);

        double t_fl = (height_layout/width_layout) * (width_frame/height_frame);
        if (t_fl <= 1)
            CM_fl = t_fl;
        else
            CM_fl = 1/t_fl;

        double t_lo, f_lo = 0.0;
        for ( Composant composant: composantList ) {
            t_lo = (composant.getWidth()/composant.getHeight()) * (width_layout/height_layout);
            if (t_lo <= 1)
                f_lo += t_lo;
            else
                f_lo += (1/t_lo);
        }
        CM_lo = f_lo/composantList.size();

        return (Math.abs(CM_fl) + Math.abs(CM_lo))/2.0;
    }
}
