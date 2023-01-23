package ujkz.aestheticEvaluation.metricCalculator.util;



import ujkz.aestheticEvaluation.metricCalculator.metrics.Composant;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Helper {
    private static Helper instance;

    private Helper() {}

    public static Helper getInstance() {
        if (instance == null) {
            synchronized (Helper.class) {
                instance = new Helper();
            }
        }
        return instance;
    }

    public static double getDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt( Math.pow(x2 -x1, 2) + Math.pow(y2 - y1, 2) );
    }

    public double getWidthLayout(List<Composant> composantList) {
        Composant compoXmin = Collections.min(composantList, Comparator.comparing(Composant::getX));
        Composant compoXmax = Collections.max(composantList, Comparator.comparingDouble(o -> o.getX() + o.getWidth()));

        return compoXmax.getX() + compoXmax.getWidth() - compoXmin.getX();
    }

    public double getHeightLayout(List<Composant> composantList) {
        Composant compoYmin = Collections.min(composantList, Comparator.comparing(Composant::getY));
        Composant compoYmax = Collections.max(composantList, Comparator.comparingDouble(o -> o.getY() + o.getHeight()));

        return compoYmax.getY() + compoYmax.getHeight() - compoYmin.getY();
    }

    public static int fact(int n) {
        if (n == 1 || n == 0)
            return 1;
        else
            return n * fact(n-1);
    }

    public static BigInteger factorial(int n) {
        BigInteger f  = new BigInteger("1");

        for (int i = 2; i <= n; i++)
            f = f.multiply(BigInteger.valueOf(i));
        return f;
    }
}
