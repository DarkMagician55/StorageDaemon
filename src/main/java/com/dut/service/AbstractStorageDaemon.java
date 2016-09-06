package com.dut.service;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by guo on 9/6/16.
 */
public abstract class AbstractStorageDaemon implements Daemon {

    private static Logger logger = LoggerFactory.getLogger(AbstractStorageDaemon.class);

    static
    {
        String config = System.getProperty("log4j2.configuration", "log4j2.xml");
        URL configLocation = null;

        // test if we load right classpath.
        configLocation = AbstractStorageDaemon.class.getClassLoader().getResource(config);
        if (configLocation == null)
            throw new RuntimeException("Couldn't figure out log4j configuration.");
        //TODO confirm conf path is right
        LogManager.getLogger(AbstractStorageDaemon.class).info("Logging initialized");
    }

    protected volatile boolean isRunning = false;

    protected abstract void startServer();

    public void start()
    {
        if (!isRunning)
        {
            startServer();
            isRunning = true;
        }
    }

    public void activate()
    {
        try
        {

            if (System.getProperty("cassandra-foreground","true").equals("false"))
            {
                System.out.close();
                System.err.close();
            }

            start();
        } catch (Throwable e)
        {
            String msg = "Exception encountered during startup.";
            logger.error(msg, e);

            // try to warn user on stdout too, if we haven't already detached
            System.out.println(msg);
            e.printStackTrace();

            System.exit(3);
        }
    }

}
