package com.dut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guo on 9/6/16.
 */
public class StorageDaemon extends AbstractStorageDaemon {

    private static Logger logger = LoggerFactory.getLogger(StorageDaemon.class);

    public void startServer()
    {
        logger.info("startServer");

    }

    public static void main(String[] args)
    {
        new StorageDaemon().activate();
    }

}
