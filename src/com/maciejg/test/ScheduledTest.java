package com.maciejg.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledTest {

    public static void testCancel() throws Exception {
        final ScheduledThreadPoolExecutor EXECUTOR = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
        ScheduledFuture f1 = EXECUTOR.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Im alive 1");
            }
        }, 0, 1, TimeUnit.SECONDS);
        ScheduledFuture f2 = EXECUTOR.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Im alive 2");
            }
        }, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(10000);
        f1.cancel(true);
        System.out.println("f1 cancel");
        Thread.sleep(10000);
        f2.cancel(false);
        System.out.println("f2 cancel");
        Thread.sleep(10000);
    }
}
