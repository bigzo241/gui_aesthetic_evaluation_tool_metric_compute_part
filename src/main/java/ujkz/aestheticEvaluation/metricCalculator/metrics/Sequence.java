package ujkz.aestheticEvaluation.metricCalculator.metrics;

import java.util.*;

public class Sequence extends Metric{

    private static Sequence instance;

    private Sequence() {super();}

    public static Sequence getInstance() {
        if (instance == null) {
            synchronized (Sequence.class) {
                instance = new Sequence();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        double w_ul = 0.0;
        double w_ur = 0.0;
        double w_ll = 0.0;
        double w_lr = 0.0;
        SortedMap<Double, String> treemap = new TreeMap<>();
        HashMap<String, Short> hashMap = new HashMap<>();

        for ( Composant composant: composantList ) {
            if ( (composant.getX()+composant.getWidth()/2 <= width_frame/2) && (composant.getY()+composant.getHeight()/2) <= height_frame/2 ) {
                w_ul += composant.getWidth()*composant.getHeight();
            }
            if ( (composant.getX()+composant.getWidth()/2 >= width_frame/2) && (composant.getY()+composant.getHeight()/2) <= height_frame/2 ) {
                w_ur += composant.getWidth()*composant.getHeight();
            }
            if ( (composant.getX()+composant.getWidth()/2 <= width_frame/2) && (composant.getY()+composant.getHeight()/2) >= height_frame/2 ) {
                w_ll += composant.getWidth()*composant.getHeight();
            }
            if ( (composant.getX()+composant.getWidth()/2 >= width_frame/2) && (composant.getY()+composant.getHeight()/2) >= height_frame/2 ) {
                w_lr += composant.getWidth()*composant.getHeight();
            }
        }

        short q_UL = 4;
        short q_UR = 3;
        short q_LL = 2;
        short q_LR = 1;
        short v_ul = 0;
        short v_ur = 0;
        short v_ll = 0;
        short v_lr = 0;

        treemap.put(q_UL *w_ul, "w_ul");
        treemap.put(q_UR *w_ur, "w_ur");
        treemap.put(q_LL *w_ll, "w_ll");
        treemap.put(q_LR *w_lr, "w_lr");

        short i = 1;
        for ( Map.Entry<Double, String> element : treemap.entrySet() ) {
//            System.out.println("\tClé : " + element.getKey() + ", valeur : " + element.getValue());
            hashMap.put(element.getValue(), i);
            i++;
        }

        for ( Map.Entry<String, Short> element : hashMap.entrySet() ){
            if (element.getKey().equals("w_ul"))
                v_ul = element.getValue();
            if (element.getKey().equals("w_ur"))
                v_ur = element.getValue();
            if (element.getKey().equals("w_ll"))
                v_ll = element.getValue();
            if (element.getKey().equals("w_lr"))
                v_lr = element.getValue();
        }

        return 1 - (double)(Math.abs(4 - v_ul) + Math.abs(3 - v_ur) + Math.abs(2 - v_ll) + Math.abs(1 - v_lr))/8.0;
    }
}
