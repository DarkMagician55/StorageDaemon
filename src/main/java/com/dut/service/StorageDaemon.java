package com.dut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guo on 9/6/16.
 *
 */
public class StorageDaemon extends AbstractStorageDaemon {

    private static Logger logger = LoggerFactory.getLogger(StorageDaemon.class);

    public void startServer()
    {
        logger.info("startServer");
        //if runnable better than thread
//        ReceiveServer receiveServer = new ReceiveServer();
//        receiveServer.start();
            StageManager.getStage(Stage.READ).execute(new Runnable() {
                public void run() {
                    try {
                        logger.info("start-run");
                        Thread.sleep(1000);
                        logger.info("end-run");
                    }catch (Exception e){
                        logger.error("err");
                    }
                }
            });

        logger.info("endServer");

    }

    public static void main(String[] args)
    {
        new StorageDaemon().activate();
        System.exit(1);
    }

}
