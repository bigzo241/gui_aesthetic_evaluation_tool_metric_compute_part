package ujkz.aestheticEvaluation.metricCalculator.metrics;

import java.util.List;

public class Equilibrium extends Metric{
    private static Equilibrium instance;

    private Equilibrium() {super();}

    public static Equilibrium getInstance() {
        if (instance == null) {
            synchronized (Equilibrium.class) {
                instance = new Equilibrium();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        double EM_x;
        double EM_y;
        double q1 = 0.0;
        double q2 = 0.0;
        double q3 = 0.0;

//        calcule de EM_x
        for ( Composant composant : composantList ) {
            q2 += composant.getWidth()*composant.getHeight();
            q1 += composant.getWidth()*composant.getHeight()*(composant.getX() + (composant.getWidth()/2) - width_frame/2);
            q3 += composant.getWidth()*composant.getHeight()*(composant.getY() + (composant.getHeight()/2) - height_frame/2);
        }

        EM_x = 2 * (q1/(composantList.size()*width_frame*q2));

        //        calcule de EM_y
        EM_y = 2 * (q3/(composantList.size()*height_frame*q2));

        return 1 - (Math.abs(EM_x) + Math.abs(EM_y))/2.0;
    }
}