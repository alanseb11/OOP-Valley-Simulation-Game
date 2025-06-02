package game;

public class TimeOfDay {
    private String name;
    private Countdown duration;
    
    public TimeOfDay(String name, Countdown duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean hasPassed() {
        return duration.isExpired();
    }

    public void tick() {
        duration.decrement();
    }

    public void reset() {
        duration.resetCountdown();
    }

}
