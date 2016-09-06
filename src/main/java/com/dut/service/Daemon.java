package com.dut.service;

import java.io.IOException;

/**
 * Created by guo on 9/6/16.
 */
public interface Daemon {

    public void start() throws IOException;

    /**
     * Stop the daemon, ideally in an idempotent manner.
     */
//    public void stop();

    public void activate();

}
