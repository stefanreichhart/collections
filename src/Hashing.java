public class Hashing {

    public static final int COUNT = 100000;

    public static final int MAX_DEPTH = 4;
    public static final int MAX_CHILDREN = 10;
    public static final int MAX_STRING_LENGTH = 0;

    public static void main(String[] args) throws InterruptedException {

        final Storage<NestedObject> store = new Storage<>();
        final ThreadHolder holder = new ThreadHolder();

        System.out.println("Generating data ...");
        Thread supervise = new Thread(createSuperviser(holder, store));
        supervise.start();
        generateData(COUNT, holder, store);
        supervise.join();

        System.out.println();
        System.out.println("Gathering statistics ...");
        store.printDetails();

    }

    private static void generateData(final int count, final ThreadHolder holder, final Storage store) {
        for (int i = 0; i < count; i++) {
            HashingUtils.createNestedObject(store);
        }
        holder.setDone();
    }

    private static Runnable createSuperviser(final ThreadHolder holder, final Storage store) {
        return () -> {
            while (!holder.isDone()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                store.printSummary();
            }
        };
    }

}
