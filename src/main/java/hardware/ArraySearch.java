package hardware;

import java.util.Random;

/**
 * -Djava.compiler=NONE / -Xint
 */
public class ArraySearch {
    public static void main(String[] args) {
        int[] array = new int[100 * 3072/2 * 1024]; // 3072 is L3 cache on my machine
        final Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }

        int sum = 0;

        final long startTime = System.currentTimeMillis();

        sum = sumSequentialElements(array, sum);

        final long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(sum);
    }

    private static int sumSequentialElements(int[] array, int sum) {
        Random rnd = new Random(1234L);
        int rndNumber = 0;

        for (int i = 0; i < array.length; i++) {
            rndNumber = rnd.nextInt(array.length - 1);
            sum += array[i];
        }
        sum+= rndNumber;
        return sum;
    }

    private static int sumRandomElements(int[] array, int sum) {
        Random rnd = new Random(1234L);
        int rndNumber = 0;

        for (int i = 0; i < array.length; i++) {
            rndNumber = rnd.nextInt(array.length - 1);
            sum += array[rndNumber];
        }

        sum+= rndNumber;
        return sum;
    }
}
