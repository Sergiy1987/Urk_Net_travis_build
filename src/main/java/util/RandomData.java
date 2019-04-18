package util;

import java.util.Locale;
import java.util.Random;

public class RandomData {
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase(Locale.ROOT);
    public static final String digits = "0123456789";
    public static final String alphanum = upper + lower + digits;


    public static String getRandomString(int size) throws NullPointerException {
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * alphanum.length());
            str.append(alphanum.charAt(index));
        }
        return str.toString();
    }

    public static void getRandomLongString(int size) throws NullPointerException {
        getRandomString(size);
    }
}
