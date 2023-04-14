package com.alain.accounting_management_system.connection;

import orm.database.connection.DatabaseConnection;
import orm.database.connection.PostgresConnection;

import java.sql.SQLException;

public class AppDBCon extends PostgresConnection {

    public AppDBCon() {
    }

    public AppDBCon(String url, String user, String password) throws SQLException {
        super(url, user, password);
    }

    public DatabaseConnection defaultConnection() throws SQLException {
        return new AppDBCon("jdbc:postgresql://localhost:5432/accounting_management_system", "walker", "w41k4z!");
    }
}
