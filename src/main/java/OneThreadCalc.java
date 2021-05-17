import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.LongStream;

public class OneThreadCalc {
    private final long[] numbers;
    private final int start;
    private final int end;

    private OneThreadCalc(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private OneThreadCalc(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    private long calc() {
        long result = 0;
        for(int i = start; i < end; i++) {
            result += numbers[i];
        }
        return result;
    }

    public static long start(long n) {
        long result;
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        OneThreadCalc oneThreadCalc = new OneThreadCalc(numbers);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        System.out.println("Начало вычисления: " + dateFormat.format(new Date()));
        result = oneThreadCalc.calc();
        System.out.println("Конец вычисления: " + dateFormat.format(new Date()));
        return result;
    }

}
