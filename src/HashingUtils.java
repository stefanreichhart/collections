import org.github.jamm.MemoryMeter;

import java.util.*;

public class HashingUtils {

    private static Random random = new Random();
    private static MemoryMeter meter = new MemoryMeter();

    private static String ALPHABETH = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String generateString(int length) {
        if (length > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < random.nextInt(length); i++) {
                builder.append(ALPHABETH.charAt(random.nextInt(ALPHABETH.length())));
            }
            return builder.toString();
        } else {
            return "";
        }
    }

    private static String generateString() {
        return generateString(Hashing.MAX_STRING_LENGTH);
    }

    private static long generateId() {
        return random.nextLong();
    }

    private static NestedObject generateNestedObject(Storage store) {
        return generateNestedObject(store, random.nextInt(Hashing.MAX_DEPTH));
    }

    public static NestedObject createNestedObject(Storage store) {
        return store.registerRoot(generateNestedObject(store, Hashing.MAX_DEPTH));
    }

    public static NestedObject createNestedObject(Storage store, int depth) {
        return store.registerRoot(generateNestedObject(store, depth));
    }

    private static NestedObject generateNestedObject(Storage store, int depth) {
        NestedObject root = store.registerEach(new NestedObject());
        root.setId(generateId());
        root.setName(generateString());
        store.add(root);
        if (depth > 0) {
            for (int i = 0; i < random.nextInt(Hashing.MAX_CHILDREN); i++) {
                root.addChild(generateNestedObject(store, --depth));
            }
        }
        return root;
    }

    public static long getSizeInMemory(Object object) {
        return meter.measureDeep(object);
    }

}
