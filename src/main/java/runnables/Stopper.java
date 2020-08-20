package runnables;

public class Stopper {
    static volatile boolean [] stop = { false };

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("Worker thread starting...");
            while (! stop[0])
                ;
            System.out.println("Worker thread ending...");
        }).start();
        System.out.println(Thread.currentThread().getName() + " worker started...");
        Thread.sleep(1_000);
        stop[0] = true;
        System.out.println("stop set to true, main exiting...");
    }
}
