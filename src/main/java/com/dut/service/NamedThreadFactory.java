package com.dut.service;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is an implementation of the <i>ThreadFactory</i> interface. This
 * is useful to give Java threads meaningful names which is useful when using
 * a tool like JConsole.
 * Created by guotong on 16/09/20.
 */

public class NamedThreadFactory implements ThreadFactory
{
    protected final String id;
    private final int priority;
    protected final AtomicInteger n = new AtomicInteger(1);

    public NamedThreadFactory(String id)
    {
        this(id, Thread.NORM_PRIORITY);
    }

    public NamedThreadFactory(String id, int priority)
    {

        this.id = id;
        this.priority = priority;
    }

    public Thread newThread(Runnable runnable)
    {
        String name = id + ":" + n.getAndIncrement();
        Thread thread = new Thread(runnable, name);
        thread.setPriority(priority);
        return thread;
    }

    //add by xitong
    public String getName()
    {
        return id;
    }
}