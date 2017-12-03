package collections.utils;

public final class HumanReadable {

    private static int NORMALIZATION = 1000;

    private HumanReadable() {}

    public static String bytes(double bytes) {
        double kb = bytes / NORMALIZATION;
        if (kb >= 1) {
            double mb = kb / NORMALIZATION;
            if (mb >= 1) {
                double gb = mb / NORMALIZATION;
                if (gb >= 1) {
                    return gb + " GB";
                } else {
                    return mb + " MB";
                }
            } else {
                return kb + " Kb";
            }
        } else {
            return bytes + " bytes";
        }
    }


    public static String padRight(String name) {
        return String.format("%-12s", name);
    }

    public static String padLeft(String name) {
        return String.format("%12s", name);
    }

}
