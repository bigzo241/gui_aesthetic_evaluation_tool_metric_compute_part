package ujkz.aestheticEvaluation.metricCalculator.metrics;

import java.util.List;

public class Balance extends Metric{
    private static Balance instance;

    private Balance() {
        super();
    }

    public static Balance getInstance() {
        if (instance == null) {
            synchronized (Balance.class) {
                instance = new Balance();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        double BMvertical;
        double BMhorizontal;
        double wl = 0.0;
        double wr = 0.0;
        double wt = 0.0;
        double wb = 0.0;
        double a;

        for ( Composant composant: composantList ) {
            a = composant.getWidth()*composant.getHeight();
            if (composant.getX() + (composant.getWidth()/2) < width_frame/2)
                wl += a * ((width_frame - composant.getWidth())/2 - composant.getX());

            if (composant.getX() + (composant.getWidth()/2) > width_frame/2)
                wr += a * (composant.getX() + (composant.getWidth()-width_frame)/2);

            if (composant.getY() + (composant.getHeight()/2) < height_frame/2)
                wt += a * ((height_frame - composant.getHeight())/2 - composant.getY());

            if (composant.getY() + (composant.getHeight()/2) > height_frame/2)
                wb += a * (composant.getY() + (composant.getHeight() - height_frame)/2);
        }

        BMvertical = (wl - wr) / Math.max(Math.abs(wl), Math.abs(wr));
        BMhorizontal = (wt - wb) / Math.max(Math.abs(wt), Math.abs(wb));

        return 1 - (Math.abs(BMvertical) + Math.abs(BMhorizontal))/2.0;
    }

//    @Override
//    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
//        double BMvertical;
//        double BMhorizontal;
//        double wl = 0.0;
//        double wr = 0.0;
//        double wt = 0.0;
//        double wb = 0.0;
//
////        calcule de wl
//        for ( Composant compo : composantList ) {
//            if ( (compo.getX()+compo.getWidth() <= width_frame/2) && (compo.getY() + compo.getHeight() <= height_frame) ) {
//                wl += compo.getWidth()*compo.getHeight() + Math.sqrt( Math.pow(width_frame/2 - compo.getX() - compo.getWidth()/2,2) + Math.pow(height_frame/2 - compo.getY() - compo.getHeight()/2, 2));
//            }
//
//            if ( (compo.getX() < width_frame/2) && (compo.getX() + compo.getWidth() > width_frame/2) && (compo.getY() + compo.getHeight() <= height_frame) ) {
//                wl += (width_frame/2 - compo.getX()) * compo.getHeight() + Math.sqrt( Math.pow(width_frame/4 - compo.getX()/2, 2) + Math.pow(height_frame/2 - compo.getY() - compo.getHeight()/2, 2) );
//            }
//        }
//
////        calcule de wr
//        for ( Composant compo : composantList ) {
//            if ( compo.getX() >= width_frame/2 ) {
//                wr += compo.getWidth()*compo.getHeight() + Math.sqrt( Math.pow(width_frame/2 - compo.getX() - compo.getWidth()/2,2) + Math.pow(height_frame/2 - compo.getY() - compo.getHeight()/2, 2));
//            }
//
//            if ( (compo.getX() <= width_frame/2) && (compo.getX() + compo.getWidth() > width_frame/2) ) {
//                wr += (compo.getX() + compo.getWidth() - width_frame/2)*compo.getHeight() + Math.sqrt( Math.pow(width_frame/4 - (compo.getX() + compo.getWidth())/2, 2) + Math.pow(height_frame/2 - compo.getY() - compo.getHeight()/2, 2) );
//            }
//        }
//
//        BMvertical = (wl - wr) / Math.max(Math.abs(wl), Math.abs(wr));
//
////        calcule de wt
//        for ( Composant compo : composantList ) {
//            if ( (compo.getY()+compo.getHeight() <= height_frame/2) ) {
//                wt += compo.getWidth()*compo.getHeight() + Math.sqrt( Math.pow(width_frame/2 - compo.getX() - compo.getWidth()/2,2) + Math.pow(height_frame/2 - compo.getY() - compo.getHeight()/2, 2));
//            }
//
//            if ( (compo.getY() < height_frame/2) && (compo.getY() + compo.getHeight() > height_frame/2) ) {
//                wt += (height_frame/2 - compo.getY()) * compo.getWidth() + Math.sqrt( Math.pow(width_frame/2 - compo.getX() - compo.getWidth()/2, 2) + Math.pow(height_frame/4 - compo.getY() - compo.getHeight()/2, 2) );
//            }
//        }
//
////        calcule de wb
//        for ( Composant compo : composantList ) {
//            if ( (compo.getY() >= height_frame/2) ) {
//                wb += compo.getWidth()*compo.getHeight() + Math.sqrt( Math.pow(width_frame/2 - compo.getX() - compo.getWidth()/2,2) + Math.pow(height_frame/2 - compo.getY() - compo.getHeight()/2, 2));
//            }
//
//            if ( (compo.getY() < height_frame/2) && (compo.getY() + compo.getHeight() > height_frame/2) ) {
//                wb += (compo.getY() + compo.getHeight() - height_frame/2) * compo.getWidth() + Math.sqrt( Math.pow(width_frame/2 - compo.getX() - compo.getWidth()/2, 2) + Math.pow(height_frame/4 - (compo.getY() + compo.getHeight())/2, 2) );
//            }
//        }
//
//        BMhorizontal = (wt - wb) / Math.max(Math.abs(wt), Math.abs(wb));
//
//        return 1 - (Math.abs(BMvertical) + Math.abs(BMhorizontal))/2;
//    }
}
