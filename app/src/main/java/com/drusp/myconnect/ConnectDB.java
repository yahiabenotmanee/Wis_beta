package com.drusp.myconnect;

import com.adedom.library.Dru;

import java.sql.Connection;

public class ConnectDB {

    public static String BASE_URL = "192.168.0.145";

    public static Connection getConnection() {
        return Dru.connection(BASE_URL, "yahia", "hello2024", "wis");
    }
}
