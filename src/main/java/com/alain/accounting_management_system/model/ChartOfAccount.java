package com.alain.accounting_management_system.model;

import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;
import orm.annotation.PrimaryKey;

import java.util.List;

import orm.annotation.Column;
import orm.annotation.Table;

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

    private ThirdPartyChartOfAccount[] thirdPartyChartOfAccounts;

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

    private void setThirdPartyChartOfAccounts(ThirdPartyChartOfAccount[] thirdPartyChartOfAccounts) {
        this.thirdPartyChartOfAccounts = thirdPartyChartOfAccounts;
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

    public ThirdPartyChartOfAccount[] getThirdPartyChartOfAccounts() {
        return this.thirdPartyChartOfAccounts;
    }

    // methods
    private void getThirdPartyChartOfAccounts(DatabaseConnection connection) throws Exception {
        ThirdPartyChartOfAccount[] thirdPartyChartOfAccounts = new ThirdPartyChartOfAccount().findAll(connection,
                "WHERE chrt_acc_id = '" + this.getAccountNumberID() + "'");
        this.setThirdPartyChartOfAccounts(thirdPartyChartOfAccounts);
    }

    @Override
    public ChartOfAccount[] findAll(DatabaseConnection connection) throws Exception {
        ChartOfAccount[] chartOfAccounts = super.findAll(connection);
        for (ChartOfAccount chartOfAccount : chartOfAccounts) {
            chartOfAccount.getThirdPartyChartOfAccounts(connection);
        }
        return chartOfAccounts;
    }

    @Override
    public ChartOfAccount[] findAll(DatabaseConnection connection, String spec) throws Exception {
        ChartOfAccount[] chartOfAccounts = super.findAll(connection, spec);
        for (ChartOfAccount chartOfAccount : chartOfAccounts) {
            chartOfAccount.getThirdPartyChartOfAccounts(connection);
        }
        return chartOfAccounts;
    }

    @Override
    public ChartOfAccount findByPrimaryKey(DatabaseConnection connection) throws Exception {
        ChartOfAccount chartOfAccount = super.findByPrimaryKey(connection);
        chartOfAccount.getThirdPartyChartOfAccounts(connection);
        return chartOfAccount;
    }

    @Override
    public ChartOfAccount findByPrimaryKey(DatabaseConnection connection, String pk) throws Exception {
        ChartOfAccount chartOfAccount = super.findByPrimaryKey(connection, pk);
        chartOfAccount.getThirdPartyChartOfAccounts(connection);
        return chartOfAccount;
    }

    @Override
    public int create(DatabaseConnection connection) throws Exception {
        // this create method is not available for this model,
        // because it needs a manual primary key
        // instead of a generated one
        return 0;
    }

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
    public static void insertImportedData(DatabaseConnection connection, List<List<String>> data) throws Exception {
        String[] columnName = data.get(0).toArray(new String[data.get(0).size()]);
        for (int i = 0; i < data.size(); i++) {
            ChartOfAccount toCreate = new ChartOfAccount();
            for (int j = 0; j < columnName.length; j++) {
                // toCreate.get
            }
        }
    }
}
