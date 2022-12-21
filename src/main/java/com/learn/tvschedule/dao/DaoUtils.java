package com.learn.tvschedule.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoUtils {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TVSchedule",
                    "postgres", "pass");
        }
        return connection;
    }
}
