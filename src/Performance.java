import gnu.trove.set.hash.THashSet;

import java.util.*;
import java.util.function.Supplier;

public class Performance {

    private static final int RUNS = 10;
    private static final int ELEMENTS = 1_000_000;

    public static void main(String[] args) {
        fill(() -> new HashSet<Integer>());
        fill(() -> new THashSet<Integer>());
        fill(() -> new ArrayList<Integer>());
        fill(() -> new ArrayDeque<Integer>());
        fill(() -> new Vector());
    }

    public static long run(Supplier<Collection<Integer>> supplier) {
        final Collection collection = supplier.get();
        long start = System.currentTimeMillis();
        for (int i=0; i<ELEMENTS; i++) {
            collection.add(i);
        }
        long stop = System.currentTimeMillis();
        return stop - start;
    }

    public static void fill(Supplier<Collection<Integer>> supplier) {
        int run = RUNS;
        int sum = 0;
        while (run > 0) {
            sum += run(supplier);
            run--;
        }
        System.out.println(supplier.get().getClass().getSimpleName() + ":\t " + (sum/RUNS) + " ms");
    }

}
