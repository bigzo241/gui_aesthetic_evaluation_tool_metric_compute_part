package ujkz.aestheticEvaluation.metricCalculator.metrics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Simplicity extends Metric{

    private static Simplicity instance;

    private Simplicity() {super();}

    public static Simplicity getInstance() {
        if (instance == null) {
            synchronized (Simplicity.class) {
                instance = new Simplicity();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        Set<Double> vapSet = new HashSet<>();
        Set<Double> hapSet = new HashSet<>();

        for ( Composant composant : composantList ) {
            vapSet.add(composant.getX() + composant.getWidth()/2);
            hapSet.add(composant.getY() + composant.getHeight()/2);
        }

        return 3.0/(vapSet.size() + hapSet.size() + composantList.size());
    }
}
