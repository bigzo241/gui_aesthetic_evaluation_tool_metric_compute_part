package ujkz.aestheticEvaluation.metricCalculator.metrics;

import java.util.List;

public abstract class Metric {
    private static String nom;
    private static String description;

    public Metric() {}
    public Metric(String nom, String description) {
        nom = nom;
        description = description;
    }

    public abstract Double computeMetric(List<Composant> composantList, Double width_frame, Double height_frame);

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Metric.nom = nom;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Metric.description = description;
    }

    public String toString() {
        return Metric.nom + "\n \t" + Metric.description;
    }
}
