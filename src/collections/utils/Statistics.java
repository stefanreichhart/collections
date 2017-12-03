package collections.utils;

public class Statistics<T extends NestedObject> {

    private long countRoot = 0;
    private long countEach = 0;
    private double avg = 0;
    private double total = 0;

    public T addRoot(T nestedObject) {
        synchronized (Statistics.class) {
            long sizeInMemory = Generator.getSizeInMemory(nestedObject);
            total += sizeInMemory;
            countRoot++;
            avg = total / countRoot;
            return nestedObject;
        }
    }

    public T addEach(T nestedObject) {
        synchronized (Statistics.class) {
            countEach++;
            return nestedObject;
        }
    }

    public void print() {
        System.out.println("Count Root: " + countRoot);
        System.out.println("Count Each: " + countEach);
        System.out.println("Avg Size: " + HumanReadable.bytes(avg));
        System.out.println("Total Size: " + HumanReadable.bytes(total));
    }

    public long getCountRoot() {
        synchronized (Statistics.class) {
            return countRoot;
        }
    }


    public long getCountEach() {
        synchronized (Statistics.class) {
            return countEach;
        }
    }

    public double getTotal() {
        synchronized (Statistics.class) {
            return total;
        }
    }

}
