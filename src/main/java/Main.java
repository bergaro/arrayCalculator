
public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Onew thread task - " + OneThreadCalc.start(200_000_000L));
        Thread.sleep(100);
        System.out.println("ForkJoinTask - " + MultyThreadCalc.start(200_000_000L));

    }
}
