package main.java.model;

public class CustomTimeStructure {
    private long secondsValue = 0;

    public CustomTimeStructure() {
        secondsValue = 0;
    }

    public long getSeconds() {
        return secondsValue;
    }

    public CustomTimeStructure addHours(int hours) {
        secondsValue += hours * 3600;
        return this;
    }

    public CustomTimeStructure addMinutes(int minutes) {
        secondsValue += minutes * 60;
        return this;
    }

    public CustomTimeStructure addSeconds(int seconds) {
        secondsValue += seconds * 60;
        return this;
    }

    public void clear(int seconds) {
        secondsValue = 0;
    }

    public void setSeconds(int seconds) {
        secondsValue = seconds;
    }
}
