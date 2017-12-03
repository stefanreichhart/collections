package collections.runners;

import collections.utils.HumanReadable;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.function.Supplier;

public class RunPerformance {

  private static final int RUNS = 100;
  private static final int RETRY = 5;
  private static final int[] ELEMENTS = {1, 5, 10, 25, 50, 75, 100, 250, 500, 750, 1_000, 2_500, 5_000, 7_500, 10_000, 50_000, 100_000, 250_000, 500_000, 750_000, 1_000_000, 2_000_000, 3_000_000, 5_000_000};

  public static void main(String[] args) {
    for (int element : ELEMENTS) {
      System.out.print("\t" + element);
    }
    reportPerformanceMap(() -> new HashMap<Integer, Integer>());
    reportPerformanceMap(() -> new THashMap<Integer, Integer>());
    reportPerformanceMap(() -> new TreeMap<Integer, Integer>());
    reportPerformanceCollection(() -> new HashSet<Integer>());
    reportPerformanceCollection(() -> new THashSet<Integer>());
    reportPerformanceCollection(() -> new ArrayList<Integer>());
    reportPerformanceCollection(() -> new ArrayDeque<Integer>());
    reportPerformanceCollection(() -> new Vector());
  }

  public static void reportPerformanceMap(Supplier<Map<Integer, Integer>> supplier) {
    System.out.println();
    System.out.print(supplier.get().getClass().getSimpleName());
    for (int i = 0; i < ELEMENTS.length; i++) {
      int elements = ELEMENTS[i];
      int run = RUNS;
      long sum = 0;
      while (run > 0) {
        sum += applyPut(elements, supplier, RETRY);
        run--;
      }
      System.out.print("\t" + (sum / RUNS));
    }
  }

  public static long applyPut(int elements, Supplier<Map<Integer, Integer>> supplier, int retry) {
    final Map<Integer, Integer> collection = supplier.get();
    final long start = System.nanoTime();
    for (int i = 0; i < elements; i++) {
      collection.put(i, i);
    }
    return System.nanoTime() - start;
  }

  public static void reportPerformanceCollection(Supplier<Collection<Integer>> supplier) {
    System.out.println();
    System.out.print((supplier.get().getClass().getSimpleName()));
    for (int i = 0; i < ELEMENTS.length; i++) {
      int elements = ELEMENTS[i];
      int run = RUNS;
      long sum = 0;
      while (run > 0) {
        sum += applyAdd(elements, supplier, RETRY);
        run--;
      }
      System.out.print("\t" + (sum / RUNS));
    }
  }

  public static long applyAdd(int elements, Supplier<Collection<Integer>> supplier, int retry) {
    final Collection collection = supplier.get();
    final long start = System.nanoTime();
    for (int i = 0; i < elements; i++) {
      collection.add(i);
    }
    return System.nanoTime() - start;
  }

  private static String padRight(String text) {
    return HumanReadable.padRight(text);
  }

}
