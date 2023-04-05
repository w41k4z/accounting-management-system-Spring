package com.alain.accounting_management_system.model;

import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;
import orm.utilities.Treatment;
import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.annotation.Table;

import java.sql.SQLException;
import java.util.List;

@Table(name = "chart_of_account", columnCount = 4)
public class ChartOfAccount extends Relation<ChartOfAccount> {

    private final static int ACCOUNT_NUMBER_LENGTH = 5;

    @PrimaryKey(column = @Column(name = "id"), prefix = "COA", length = 8, sequence = "chart_of_account_seq")
    private String accountNumberID;

    @Column(name = "account_number")
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
    public void setAccountNumberID(String accountNumberID) {
        this.accountNumberID = accountNumberID;
    }

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
    public String getAccountNumberID() {
        return this.accountNumberID;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getSocietyID() {
        return this.societyID;
    }

    public String getEntitled() {
        return this.entitled;
    }

    // methods
    public String accountNumberRectification(String accountNumber) throws Exception {
        StringBuilder validAccountNumber = new StringBuilder(accountNumber);
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH - accountNumber.length(); i++) {
            validAccountNumber.append("0");
        }
        return validAccountNumber.toString();
    }

    /*
     * First element of the list must be the the column name
     */
    public static void insertImportedData(DatabaseConnection connection, List<List<String>> data, String societyID)
            throws Exception {
        String[] columnName = data.get(0).toArray(new String[data.get(0).size()]);
        for (int i = 1; i < data.size(); i++) {
            ChartOfAccount toCreate = new ChartOfAccount();
            toCreate.setSocietyID(societyID);
            for (int j = 0; j < columnName.length; j++) {
                Treatment.setObjectFieldValue(toCreate, data.get(i).get(j), toCreate.getColumn(columnName[j]));
            }
            try {
                toCreate.create(connection);
            } catch (SQLException e) { // catching duplicate key
                if (e.getSQLState().equals("23505")) {
                    continue;
                } else {
                    throw e; // for other errors
                }
            }
        }
    }
}
