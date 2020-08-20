package runnables;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        // if a is the starting of a thread, and b is the first action of that thread
        // hb(a,b)
        BlockingQueue<int[]> queue = new ArrayBlockingQueue<>(10);
        new Thread(() -> {
            System.out.println("Producer starting");
            try {
                for (int i = 0; i < 10_000; i++) {
                    int[] data = {0, i};
                    if (i < 100) {
                        Thread.sleep(1);
                    }
                    data[0] = i;
                    if (i == 5_000) {
                        data[0] = -1;
                    }
                    queue.put(data);
                    data = null;
                }
            } catch (InterruptedException ie) {
                System.out.println("Shutdown requested...");
            }
            System.out.println("Producer finished");
        }).start();
        Thread cons = new Thread(() -> {
            System.out.println("Consumer starting");
            try {
                for (int i = 0; i < 10_000; i++) {
                    int[] data = queue.take();
                    if (data[0] != data[1] || data[0] != i) {
                        System.out.println("****** ERROR at index " + i
                                + " data[0] = " + data[0] + " data[1] = " + data[1]);
                    }
                    if (i > 9_900) {
                        Thread.sleep(1);
                    }
                }
            } catch (InterruptedException ie) {
                // if a interrupts a thread and b is the action of a thread noticing is is interrupted
                // hb(a,b)
                System.out.println("Shutdown requested...");
            }
            System.out.println("Consumer finished");
        });
        cons.start();
        System.out.println("Producer and consumer started");
        // "determine that cons has finished" cons.wait(), cons.isAlive()
        // if a is the last action of a thread and b is another thread determining
        // the first thread is dead hb(a,b)
    }
}
