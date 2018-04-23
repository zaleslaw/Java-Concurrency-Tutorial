package Chapter_0_Motivation.hardware;

/**
 * -Djava.compiler=NONE / -Xint
 */
public class DoubleTimes {
    public static void main(String[] args) {
            double res1 = 1.0;
            double res2 = 1.0;
            double res3 = 1.0;
            double res4 = 1.0;
            double res5 = 1.0;
            double res6 = 1.0;
            final long startTime = System.currentTimeMillis();
            for (int j = 0; j < 10_000_000; j++) {
                res1 *= 1.00001;
                res2 *= 1.000001;
                res3 *= 1.0000001;
                res4 *= 1.00000001;
                res5 *= 1.000000001;
                res6 *= 1.000000002;
            }
            final long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
            System.out.println(res1 + res2 + res3 + res4 + res5 + res6);
    }
}
