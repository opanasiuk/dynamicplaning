package model;

/**
 * Created by Sasha on 08.03.2016.
 */
public class Pair {
    private double start;
    private double end;

    public Pair(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public Pair() {
        this.start = 0;
        this.end = 0;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "P{" +
                 + start +
                ", " + end +
                "}";
    }
}
