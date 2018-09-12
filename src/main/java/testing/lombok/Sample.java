package testing.lombok;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.val;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static testing.lombok.Sample.DataTools.createDate;
import static testing.lombok.Sample.DataTools.createLastName;
import static testing.lombok.Sample.DataTools.createName;

/**
 * @author rick.
 */
public class Sample {

    public static void main(String ... args) throws IOException {
        SamplePojo sample = new SamplePojo("first value", "second value", "third value", 4);
        System.out.println(String.format("Printing the toString for the pojo %s", sample));
        testNotNull(new SamplePojo());
        try {
            testNotNull(null);
        } catch (NullPointerException e) { }
        testHashable();
    }

    private static void testHashable() {
        val map = new HashMap<HashableItem, Integer>();
        val reverseMap = new HashMap<Integer, HashableItem>();
        for (int i = 0; i < 10; i++) {
            val key = new HashableItem(createName(i), createLastName(i), createDate(i), i);
            map.put(key, i);
            reverseMap.put(i, key);
        }
        val randomNumber = new Random().nextInt(10);
        val key = new HashableItem(createName(randomNumber), randomNumber);
        System.out.println(String.format("Getting number data (%s) from object key: %s", map.get(key), key));
    }



    private static void testNotNull(@NonNull SamplePojo sample) {
        System.out.println(
                String.format("Printing out something like '%s' out of nothing at all: %s", sample.getField1(), sample.toString()));
    }

    private void trySomethingWithStreams() throws IOException {
        @Cleanup InputStream ir =  new FileInputStream(new File("/home/rick/.zshrc"));
        @Cleanup OutputStream out = new FileOutputStream(new File("/home/rick/.zshrc-copied-using-java"));
        byte[] b = new byte[10000];
        while (true) {
            int r = ir.read(b);
            if (r == -1) {
                break;
            }
            out.write(b, 0, r);
        }
    }

    static class DataTools {
        static String createName(int i) {
            return createString("Name%s", i);
        }

        static String createLastName(int i) {
            return createString("LastName%s", i);
        }

        static String createString(String base, int i) {
            return String.format(base, i);
        }

        static Date createDate(int i) {
            return new Date(System.currentTimeMillis() + i);
        }
    }
}