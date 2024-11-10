package com.cp.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class AppLog {


    private static Log log;
    private AppLog(){

    }
    public static Log getInstance() {
        if (log == null) {
            log = LogFactory.getLog("Logging");
        }
        return log;
    }

}
