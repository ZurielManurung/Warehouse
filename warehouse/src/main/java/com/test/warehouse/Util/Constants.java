package com.test.warehouse.Util;

import java.util.Date;

public class Constants {
    public static long getTimestamp(){
        return new Date().getTime() / 1000L;
    }
}
