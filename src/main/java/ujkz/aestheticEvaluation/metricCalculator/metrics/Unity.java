package ujkz.aestheticEvaluation.metricCalculator.metrics;


import ujkz.aestheticEvaluation.metricCalculator.util.Helper;

import java.util.*;

public class Unity extends Metric implements Comparator<Composant>{
    private static Unity instance;
    private final Helper helper = Helper.getInstance();

    private Unity() {super();}

    public static Unity getInstance() {
        if (instance == null) {
            synchronized (Unity.class) {
                instance = new Unity();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        double UM_form;
        double UM_space;
        Set<Double> sizeList = new HashSet<>();
        double a = 0.0;
        for ( Composant composant : composantList ) {
            sizeList.add(composant.getWidth() * composant.getHeight());
            a += composant.getWidth() * composant.getHeight();
        }

        UM_form = 1 - (double)(sizeList.size()-1)/(double)composantList.size();

        UM_space = 1 - (helper.getWidthLayout(composantList)*helper.getHeightLayout(composantList) - a)/(width_frame*height_frame - a);
        return (Math.abs(UM_form) + Math.abs(UM_space))/2;
    }

    @Override
    public int compare(Composant o1, Composant o2) {
        return Double.compare(o1.getX()+ o1.getWidth(), o2.getX()+ o2.getWidth());
    }
}