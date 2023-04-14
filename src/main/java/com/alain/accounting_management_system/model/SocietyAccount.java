package com.alain.accounting_management_system.model;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;

@Table(name = "society_account", columnCount = 4)
public class SocietyAccount extends Relation<SocietyAccount> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "SAC", length = 8, sequence = "society_account_seq")
    private String societyAccountID;

    @Column(name = "account_id")
    private String accountID;

    @Column(name = "society_id")
    private String societyID;

    private Society society;

    @Column
    private String password;

    // constructor
    public SocietyAccount() throws Exception {
        super();
    }

    // setters
    public void setSocietyAccountID(String society_account_id) {
        this.societyAccountID = society_account_id;
    }

    public void setAccountID(String account_id) {
        this.accountID = account_id;
    }

    public void setSocietyID(String society_id) {
        this.societyID = society_id;
    }

    private void setSociety(Society society) {
        this.society = society;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // getters
    public String getSocietyAccountID() {
        return societyAccountID;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getSocietyID() {
        return this.societyID;
    }

    public Society getSociety() {
        return this.society;
    }

    public String getPassword() {
        return this.password;
    }

    // methods
    private void getSociety(DatabaseConnection connection) throws Exception {
        Society society = new Society().findByPrimaryKey(connection, this.getSocietyID());
        this.setSociety(society);
    }

    @Override
    public SocietyAccount[] findAll(DatabaseConnection connection) throws Exception {
        SocietyAccount[] societyAccounts = super.findAll(connection);
        for (SocietyAccount societyAccount : societyAccounts) {
            societyAccount.getSociety(connection);
        }
        return societyAccounts;
    }

    @Override
    public SocietyAccount[] findAll(DatabaseConnection connection, String spec) throws Exception {
        SocietyAccount[] societyAccounts = super.findAll(connection, spec);
        for (SocietyAccount societyAccount : societyAccounts) {
            societyAccount.getSociety(connection);
        }
        return societyAccounts;
    }

    @Override
    public SocietyAccount findByPrimaryKey(DatabaseConnection connection) throws Exception {
        SocietyAccount societyAccount = super.findByPrimaryKey(connection);
        societyAccount.getSociety(connection);
        return societyAccount;
    }

    @Override
    public SocietyAccount findByPrimaryKey(DatabaseConnection connection, String pk) throws Exception {
        SocietyAccount societyAccount = super.findByPrimaryKey(connection, pk);
        societyAccount.getSociety(connection);
        return societyAccount;
    }
}
