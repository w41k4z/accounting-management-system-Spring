package com.alain.accounting_management_system.model;

import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;

import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.annotation.Table;

@Table(name = "chart_of_account", columnCount = 3)
public class ChartOfAccount extends Relation<ChartOfAccount> {

    private final static int ACCOUNT_NUMBER_LENGTH = 5;

    @PrimaryKey(column = @Column(name = "account_number"))
    private String accountNumber;

    @Column(name = "society_id")
    private String societyID;

    @Column
    private String entitled;

    // constructor
    public ChartOfAccount() throws Exception {
        super();
    }

    // setters
    public void setAccountNumber(String accountNumber) throws Exception {
        if (accountNumber == null || accountNumber.trim().length() == 0
                || accountNumber.trim().length() > ACCOUNT_NUMBER_LENGTH) {
            throw new Exception("ERROR: The account number is invalid");
        }
        this.accountNumber = this.accountNumberRectification(accountNumber.trim());
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public void setEntitled(String entitled) {
        this.entitled = entitled.trim();
    }

    // getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSocietyID() {
        return societyID;
    }

    public String getEntitled() {
        return entitled;
    }

    // methods
    public String accountNumberRectification(String accountNumber) throws Exception {
        StringBuilder validAccountNumber = new StringBuilder(accountNumber);
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH - accountNumber.length(); i++) {
            validAccountNumber.append("0");
        }
        return validAccountNumber.toString();
    }

    // override
    @Override
    public int create(DatabaseConnection connection) throws Exception {
        // this create method is not available for this model,
        // because it needs a manual primary key
        // instead of a generated one
        return 0;
    }
}
