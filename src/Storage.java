import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;

import java.util.*;

public class Storage<T extends NestedObject> {

    private final Statistics<T> stats;

    private final Map<Long, T> hashMap;
    private final THashMap<Long, T> thashMap;
    private final Map<Long, T> treeMap;
    private final Set<T> hashSet;
    private final THashSet<T> thashSet;
    private final List<T> arrayList;
    private final ArrayDeque<T> arrayDeque;
    private final Vector vector;

    public Storage() {
        stats = new Statistics<>();

        hashMap = new HashMap<>();
        thashMap = new THashMap<>();
        treeMap = new TreeMap<>();
        hashSet = new HashSet<>();
        thashSet = new THashSet<>();
        arrayList = new ArrayList<>();
        arrayDeque = new ArrayDeque<>();
        vector = new Vector();
    }

    public void add(T object) {
        hashMap.put(object.getId(), object);
        thashMap.put(object.getId(), object);
        treeMap.put(object.getId(), object);
        hashSet.add(object);
        thashSet.add(object);
        arrayList.add(object);
        arrayDeque.add(object);
        vector.add(object);
    }

    public T registerRoot(T object) {
        return stats.addRoot(object);
    }

    public T registerEach(T object) {
        return stats.addEach(object);
    }

    public void printDetails() {
        stats.print();
        System.out.println();
        printDetails(hashMap, hashMap.size());
        printDetails(thashMap, thashMap.size());
        printDetails(treeMap, treeMap.size());
        printDetails(hashSet, hashSet.size());
        printDetails(thashSet, thashSet.size());
        printDetails(arrayList, arrayList.size());
        printDetails(arrayDeque, arrayDeque.size());
        printDetails(vector, vector.size());
        System.out.println();
        System.out.println("Storage - Total: " + HumanReadable.bytes(HashingUtils.getSizeInMemory(this)));
    }

    private void printDetails(Object collection, int size) {
        try {
            double sizeCollection = HashingUtils.getSizeInMemory(collection);
            double overheadCollection = sizeCollection - stats.getTotal();
            double percentualOverheadCollection = Math.round(overheadCollection / sizeCollection * 100);
            String lossEncountered = size == stats.getCountEach() ? "" : " ERROR( " + size + "!=" + stats.getCountEach() + ")";

            System.out.println(collection.getClass().getSimpleName() + lossEncountered +  " - Total: " + HumanReadable.bytes(sizeCollection) + "; Total Overhead: " + HumanReadable.bytes(overheadCollection) + "; Overhead: " + percentualOverheadCollection + "%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void printSummary() {
        System.out.println("... Root=" + stats.getCountRoot() + ", Each=" + stats.getCountEach());
    }


}
