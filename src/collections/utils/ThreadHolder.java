package collections.utils;

public class ThreadHolder {

    private boolean done = false;

    public synchronized void setDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }
}
