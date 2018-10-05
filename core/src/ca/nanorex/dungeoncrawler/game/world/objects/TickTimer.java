package ca.nanorex.dungeoncrawler.game.world.objects;

public class TickTimer implements Comparable<TickTimer> {

    private int setTime;
    private int remainingTime;

    public TickTimer() {}

    public void update() {
        if (remainingTime > 0)
            remainingTime--;
    }

    public void start() {
        start(setTime);
    }

    public void start(int time) {
        setTime = time;
        remainingTime = time;
    }

    public boolean expired() {
        return remainingTime == 0;
    }

    public int compareTo(TickTimer o) {
        return remainingTime < o.remainingTime ? -1 : remainingTime > o.remainingTime ? 1 : 0;
    }
}
