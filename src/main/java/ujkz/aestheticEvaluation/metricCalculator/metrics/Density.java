package ujkz.aestheticEvaluation.metricCalculator.metrics;

import java.util.List;

public class Density extends Metric{
    private static Density instance;

    private Density() {super();}

    public static Density getInstance() {
        if (instance == null) {
            synchronized (Density.class) {
                instance = new Density();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        double a = 0.0;
        for ( Composant composant : composantList ) {
            a+= composant.getWidth()*composant.getHeight();
        }
        return 1 - 2*Math.abs(0.5 - a/(width_frame*height_frame));
    }
}
