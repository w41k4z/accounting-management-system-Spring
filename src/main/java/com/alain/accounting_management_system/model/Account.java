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

    private SocietyAccount[] societyAccounts;

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

    private void setSocietyAccounts(SocietyAccount[] societyAccounts) {
        this.societyAccounts = societyAccounts;
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

    public SocietyAccount[] getSocietyAccounts() {
        return this.societyAccounts;
    }

    // method
    private void getSocietyAccounts(DatabaseConnection connection) throws Exception {
        SocietyAccount[] societyAccounts = new SocietyAccount().findAll(connection,
                "WHERE account_id='" + this.getAccountID() + "'");
        this.setSocietyAccounts(societyAccounts);
    }

    @Override
    public Account[] findAll(DatabaseConnection connection) throws Exception {
        Account[] accounts = super.findAll(connection);
        for (Account account : accounts) {
            account.getSocietyAccounts(connection);
        }
        return accounts;
    }

    @Override
    public Account[] findAll(DatabaseConnection connection, String spec) throws Exception {
        Account[] accounts = super.findAll(connection, spec);
        for (Account account : accounts) {
            account.getSocietyAccounts(connection);
        }
        return accounts;
    }

    @Override
    public Account findByPrimaryKey(DatabaseConnection connection) throws Exception {
        Account account = super.findByPrimaryKey(connection);
        account.getSocietyAccounts(connection);
        return account;
    }

    @Override
    public Account findByPrimaryKey(DatabaseConnection connection, String pk) throws Exception {
        Account account = super.findByPrimaryKey(connection, pk);
        account.getSocietyAccounts(connection);
        return account;
    }

    public static Account authenticate(DatabaseConnection connection, String email, String password) throws Exception {
        String spec = "WHERE email='" + email + "' AND password='" + password + "'";
        Account[] accounFound = new Account().findAll(connection, spec);
        return accounFound.length > 0 ? accounFound[0] : null;
    }
}
