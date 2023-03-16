package com.alain.accounting_management_system.model;

import orm.annotation.Column;
import orm.annotation.PrimaryKey;
import orm.annotation.Table;
import orm.database.object.relation.Relation;
import orm.database.connection.DatabaseConnection;

@Table(columnCount = 3)
public class Account extends Relation<Account> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "ACC", length = 7, sequence = "account_seq")
    private String accountID;

    @Column
    private String email;

    @Column
    private String password;

    // constructor
    public Account() throws Exception {
        super();
    }

    // setters
    public void setAccountID(String account_id) {
        this.accountID = account_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // getters
    public String getAccountID() {
        return accountID;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    // method
    public static Account authenticate(DatabaseConnection connection, String email, String password) throws Exception {
        String spec = "WHERE email='" + email + "' AND password='" + password + "'";
        return new Account().findAll(connection, spec)[0];
    }
}
