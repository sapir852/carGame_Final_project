public class Save {


    private String name;
    private int score = 0;
    private double lat = 0.0;
    private double lon = 0.0;

    public Save() { }

    public String getName() {
        return name;
    }

    public Save setName(String name) {
        this.name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Save setScore(int score) {
        this.score = score;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Save setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Save setLon(double lon) {
        this.lon = lon;
        return this;
    }
}
