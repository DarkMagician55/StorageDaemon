package com.dut.service;

import java.util.EnumMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by guotong on 16/09/20.
 */
public class StageManager {
    private static EnumMap<Stage, ThreadPoolExecutor> stages = new EnumMap<Stage, ThreadPoolExecutor>(Stage.class);
    public static final long KEEPALIVE = 60; // seconds to keep "extra" threads alive for when idle

    static
    {
        stages.put(Stage.READ, multiThreadedConfigurableStage(Stage.READ, 1));
    }

    public static ThreadPoolExecutor getStage(Stage stage)
    {
        return stages.get(stage);
    }

    private static ThreadPoolExecutor multiThreadedConfigurableStage(Stage stage, int numThreads)
    {
//        assert numThreads > 1 : "multi-threaded stages must have at least 2 threads";

        ThreadPoolExecutor tpe = new ReadThreadPoolExecutor(numThreads,
                numThreads,
                KEEPALIVE,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2),
                new NamedThreadFactory(stage.getJmxName()));
        tpe.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return tpe;
    }
}
