package com.example.marco.myemotion;

/**
 * Created by marco on 21/03/18.
 */

public class Registrazione  {
    private String x;
    private String y;
    private String timestamp;

    public Registrazione(String x, String y, String timestamp) {
        this.x = x;
        this.y = y;
        this.timestamp = timestamp;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Registrazione{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
