package com.example.marco.myemotion;

/**
 * Created by marco on 21/03/18.
 */

public class Registrazione  {
    private double x;
    private double y;
    private long timestamp;

    public Registrazione(double x, double y, long timestamp) {
        this.x = x;
        this.y = y;
        this.timestamp = timestamp;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Registrazione{" +
                "x=" + x +
                ", y=" + y +
                ", timestamp=" + timestamp +
                '}';
    }
}
