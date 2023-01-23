package ujkz.aestheticEvaluation.metricCalculator.metrics;


import ujkz.aestheticEvaluation.metricCalculator.util.Helper;

import java.util.*;

public class Symmetry extends Metric{
    private static Symmetry instance;

    private Symmetry() {super();}

    public static Symmetry getInstance() {
        if (instance == null) {
            synchronized (Symmetry.class) {
                instance = new Symmetry();
            }
        }
        return instance;
    }

    @Override
    public Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame) {
        double[] X = {0.0, 0.0, 0.0, 0.0};
        double[] Y = {0.0, 0.0, 0.0, 0.0};
        double[] H = {0.0, 0.0, 0.0, 0.0};
        double[] B = {0.0, 0.0, 0.0, 0.0};
        double[] O = {0.0, 0.0, 0.0, 0.0};
        double[] R = {0.0, 0.0, 0.0, 0.0};

        double compo_center_x, compo_center_y;

        for ( Composant composant : composantList ) {
            compo_center_x = composant.getX()+composant.getWidth()/2;
            compo_center_y = composant.getY()+composant.getHeight()/2;
            boolean b = (compo_center_x - width_frame / 2) != 0.0 && (compo_center_y - height_frame / 2) != 0.0;
            double abs = Math.abs((compo_center_y - height_frame / 2) / (compo_center_x - width_frame / 2));

            // Upper-left
            if ( (compo_center_x <= width_frame/2) && (compo_center_y <= height_frame/2) ) {
                if ( compo_center_x != width_frame/2 )
                    X[0] += Math.abs(compo_center_x - width_frame/2);
                if ( compo_center_y != height_frame/2)
                    Y[0] += Math.abs(compo_center_y - height_frame/2);
                H[0] += composant.getHeight();
                B[0] += composant.getWidth();
                if (b)
                    O[0] += abs;
                R[0] += Helper.getDistance(compo_center_x, width_frame/2, compo_center_y, height_frame/2);
            }
            // Upper-right
            if ( (compo_center_x >= width_frame/2) && (compo_center_y <= height_frame/2) ) {
                if ( compo_center_x != width_frame/2 )
                    X[1] += Math.abs(compo_center_x - width_frame/2);
                if ( compo_center_y != height_frame/2)
                    Y[1] += Math.abs(compo_center_y - height_frame/2);
                H[1] += composant.getHeight();
                B[1] += composant.getWidth();
                if (b)
                    O[1] += abs;
                R[1] += Helper.getDistance(compo_center_x, width_frame/2, compo_center_y, height_frame/2);
            }
            // Lower-left
            if ( (compo_center_x <= width_frame/2) && (compo_center_y) >= height_frame/2 ) {
                if ( compo_center_x != width_frame/2 )
                    X[2] += Math.abs(compo_center_x - width_frame/2);
                if ( compo_center_y != height_frame/2)
                    Y[2] += Math.abs(compo_center_y - height_frame/2);
                H[2] += composant.getHeight();
                B[2] += composant.getWidth();
                if (b)
                    O[2] += abs;
                R[2] += Helper.getDistance(compo_center_x, width_frame/2, compo_center_y, height_frame/2);
            }
            // Lower-right
            if ( (compo_center_x >= width_frame/2) && (compo_center_y) >= height_frame/2 ) {
                if ( compo_center_x != width_frame/2 )
                    X[3] += Math.abs(compo_center_x - width_frame/2);
                if ( compo_center_y != height_frame/2)
                    Y[3] += Math.abs(compo_center_y - height_frame/2);
                H[3] += composant.getHeight();
                B[3] += composant.getWidth();
                if (b)
                    O[3] += abs;
                R[3] += Helper.getDistance(compo_center_x, width_frame/2, compo_center_y, height_frame/2);
            }

        }

        double[] X_p = new double[4];
        double[] Y_p = new double[4];
        double[] H_p = new double[4];
        double[] B_p = new double[4];
        double[] O_p = new double[4];
        double[] R_p = new double[4];

        for ( int i = 0;i<X.length;i++ ) {
            X_p[i] = (X[i] - Arrays.stream(X).min().getAsDouble()) / (Arrays.stream(X).max().getAsDouble() - Arrays.stream(X).min().getAsDouble());
            Y_p[i] = (Y[i] - Arrays.stream(Y).min().getAsDouble()) / (Arrays.stream(Y).max().getAsDouble() - Arrays.stream(Y).min().getAsDouble());
            H_p[i] = (H[i] - Arrays.stream(H).min().getAsDouble()) / (Arrays.stream(H).max().getAsDouble() - Arrays.stream(H).min().getAsDouble());
            B_p[i] = (B[i] - Arrays.stream(B).min().getAsDouble()) / (Arrays.stream(B).max().getAsDouble() - Arrays.stream(B).min().getAsDouble());
            O_p[i] = (O[i] - Arrays.stream(O).min().getAsDouble()) / (Arrays.stream(O).max().getAsDouble() - Arrays.stream(O).min().getAsDouble());
            R_p[i] = (R[i] - Arrays.stream(R).min().getAsDouble()) / (Arrays.stream(R).max().getAsDouble() - Arrays.stream(R).min().getAsDouble());
        }

        double SYM_v = (Math.abs(X_p[0] - X_p[1]) + Math.abs(X_p[2] - X_p[3]) + Math.abs(Y_p[0] - Y_p[1]) + Math.abs(Y_p[2] - Y_p[3])
                + Math.abs(H_p[0] - H_p[1]) + Math.abs(H_p[2] - H_p[3]) + Math.abs(B_p[0] - B_p[1]) + Math.abs(B_p[2] -B_p[3])
                + Math.abs(O_p[0] - O_p[1]) + Math.abs(O_p[2] - O_p[3]) + Math.abs(R_p[0] - R_p[1]) + Math.abs(R_p[2] -R_p[3]))/12.0;

        double SYM_h = (Math.abs(X_p[0] - X_p[2]) + Math.abs(X_p[1] - X_p[3]) + Math.abs(Y_p[0] - Y_p[2]) + Math.abs(Y_p[1] - Y_p[3])
                + Math.abs(H_p[0] - H_p[2]) + Math.abs(H_p[1] - H_p[3]) + Math.abs(B_p[0] - B_p[2]) + Math.abs(B_p[1] -B_p[3])
                + Math.abs(O_p[0] - O_p[2]) + Math.abs(O_p[1] - O_p[3]) + Math.abs(R_p[0] - R_p[2]) + Math.abs(R_p[1] -R_p[3]))/12.0;

        double SYM_r = (Math.abs(X_p[0] - X_p[3]) + Math.abs(X_p[1] - X_p[2]) + Math.abs(Y_p[0] - Y_p[3]) + Math.abs(Y_p[1] - Y_p[2])
                + Math.abs(H_p[0] - H_p[3]) + Math.abs(H_p[1] - H_p[2]) + Math.abs(B_p[0] - B_p[3]) + Math.abs(B_p[1] -B_p[2])
                + Math.abs(O_p[0] - O_p[3]) + Math.abs(O_p[1] - O_p[2]) + Math.abs(R_p[0] - R_p[3]) + Math.abs(R_p[1] -R_p[2]))/12.0;

        return 1.0 - (Math.abs(SYM_v) + Math.abs(SYM_h) + Math.abs(SYM_r))/3.0;
    }
}
