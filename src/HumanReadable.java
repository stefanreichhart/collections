public class HumanReadable {

    private static int NORMALIZATION = 1000;

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
        //return bytes + " bytes";
    }
}
