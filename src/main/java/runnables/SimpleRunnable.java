package runnables;

import java.util.stream.IntStream;

public class SimpleRunnable {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            private int i = 0;
            public void run() {
                System.out.println(Thread.currentThread().getName() + " starting");
                for (; i < 10_000; i++) {
                    System.out.println(Thread.currentThread().getName() + " i is " + i);
                }
//            IntStream.iterate(0, x -> x + 1)
//                    .limit(10_000)
//                    .forEach(i -> System.out.println(Thread.currentThread().getName() + " i is " + i));
                System.out.println(Thread.currentThread().getName() + " ending");
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
//        t1.setDaemon(true);
//        t2.setDaemon(true);
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + " exiting main method");
    }
}
