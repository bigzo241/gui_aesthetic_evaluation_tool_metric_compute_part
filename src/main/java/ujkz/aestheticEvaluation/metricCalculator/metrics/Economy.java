package ujkz.aestheticEvaluation.metricCalculator.metrics;

import java.util.List;

public class Economy extends Metric{
    private static Economy instance;

    private Economy() {super();}

    public static Economy getInstance() {
        if (instance == null) {
            synchronized (Economy.class) {
                instance = new Economy();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        return 1.0/composantList.size();
    }
}
