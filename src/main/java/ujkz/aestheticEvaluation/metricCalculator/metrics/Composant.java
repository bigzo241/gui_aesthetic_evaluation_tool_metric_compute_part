package ujkz.aestheticEvaluation.metricCalculator.metrics;

public class Composant {
    private int id;
    private Double x;
    private Double y;
    private Double width;
    private Double height;
    private String type;

    public Composant() {}

    public Composant(int id, Double x, Double y, Double width, Double height, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void affich() {
        System.out.println();
        System.out.println("\tId: " + this.getId());
        System.out.println("\tX: " + this.getX());
        System.out.println("\tY: " + this.getY());
        System.out.println("\tWidth: " + this.getWidth());
        System.out.println("\tHeight: " + this.getHeight());
        System.out.println("\tType: " + this.getType());
    }
}
