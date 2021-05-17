import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;


public class MultyThreadCalc extends RecursiveTask<Long> {
   private final long[] numbers;
   private final int start;
   private final int end;
   public static final int threadsNum = Runtime.getRuntime().availableProcessors();

   private MultyThreadCalc(long[] numbers) {
       this(numbers, 0, numbers.length);
   }

   private MultyThreadCalc(long[] numbers, int start, int end) {
       this.numbers = numbers;
       this.start = start;
       this.end = end;
   }

    @Override
    protected Long compute() {
        int length = end - start;
        if(length <= threadsNum) {
                return add();
        }
        MultyThreadCalc multyThreadCalcTask1 = new MultyThreadCalc(numbers, start, start + length / 2);
        multyThreadCalcTask1.fork();
        MultyThreadCalc multyThreadCalcTask2 = new MultyThreadCalc(numbers, start + length / 2, end);

        Long secondTaskResult = multyThreadCalcTask2.compute();
        Long firstTaskResult = multyThreadCalcTask1.join();

        return firstTaskResult + secondTaskResult;
   }

   private long add() {
       long result = 0;

       for(int i = start; i < end; i++) {
           result += numbers[i];
       }

       return result;
   }

   public static long start(long n) {
       long result;
       ForkJoinPool pool = new ForkJoinPool();
       long[] numbers = LongStream.rangeClosed(1, n).toArray();
       ForkJoinTask<Long> task = new MultyThreadCalc(numbers);
       DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
       System.out.println("Начало вычисления: " + dateFormat.format(new Date()));
       result = pool.invoke(task);
       System.out.println("Конец вычисления: " + dateFormat.format(new Date()));
       return result;
   }
}
