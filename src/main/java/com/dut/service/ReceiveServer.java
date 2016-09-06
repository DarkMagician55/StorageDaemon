package com.dut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * Created by guo on 9/6/16.
 *
 */
public class ReceiveServer extends Thread {

    private static Logger logger = LoggerFactory.getLogger(ReceiveServer.class);

    public ReceiveServer()
    {

    }

    public ReceiveServer(InetAddress listenAddr, int listenPort)
    {

    }

    public void run()
    {
        logger.info("Thread-run");
    }

}
