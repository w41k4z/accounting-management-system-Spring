package com.alain.accounting_management_system.model;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;

@Table(name = "third_party_chart_of_account", columnCount = 4)
public class ThirdPartyChartOfAccount extends Relation<ThirdPartyChartOfAccount> {

    @PrimaryKey(column = @Column(name = "thd_pt_chrt_acc_id"), prefix = "TPCOA", length = 8, sequence = "third_party_chart_of_account_seq")
    private String thirdPartyChartOfAccountID;

    @Column(name = "chrt_acc_id")
    private String chartOfAccountID;

    private ChartOfAccount chartOfAccount;

    @Column
    private String key;

    @Column
    private String value;

    // constructor
    public ThirdPartyChartOfAccount() throws Exception {
        super();
    }

    // setters
    public void setThirdPartyChartOfAccountID(String thirdPartyChartOfAccountID) {
        this.thirdPartyChartOfAccountID = thirdPartyChartOfAccountID;
    }

    public void setChartOfAccountID(String chartOfAccountID) {
        this.chartOfAccountID = chartOfAccountID;
    }

    private void setChartOfAccount(ChartOfAccount chartOfAccount) {
        this.chartOfAccount = chartOfAccount;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // getters
    public String getThirdPartyChartOfAccountID() {
        return this.thirdPartyChartOfAccountID;
    }

    public String getChartOfAccountID() {
        return this.chartOfAccountID;
    }

    public ChartOfAccount getChartOfAccount() throws Exception {
        return this.chartOfAccount;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    // methods
    private void getChartOfAccount(DatabaseConnection connection) throws Exception {
        ChartOfAccount chartOfAccount = new ChartOfAccount().findByPrimaryKey(connection, this.getChartOfAccountID());
        this.setChartOfAccount(chartOfAccount);
    }

    @Override
    public ThirdPartyChartOfAccount[] findAll(DatabaseConnection connection) throws Exception {
        ThirdPartyChartOfAccount[] thirdPartyChartOfAccounts = super.findAll(connection);
        for (ThirdPartyChartOfAccount thirdPartyChartOfAccount : thirdPartyChartOfAccounts) {
            thirdPartyChartOfAccount.getChartOfAccount(connection);
        }
        return thirdPartyChartOfAccounts;
    }

    @Override
    public ThirdPartyChartOfAccount[] findAll(DatabaseConnection connection, String spec) throws Exception {
        ThirdPartyChartOfAccount[] thirdPartyChartOfAccounts = super.findAll(connection, spec);
        for (ThirdPartyChartOfAccount thirdPartyChartOfAccount : thirdPartyChartOfAccounts) {
            thirdPartyChartOfAccount.getChartOfAccount(connection);
        }
        return thirdPartyChartOfAccounts;
    }

    @Override
    public ThirdPartyChartOfAccount findByPrimaryKey(DatabaseConnection connection) throws Exception {
        ThirdPartyChartOfAccount thirdPartyChartOfAccount = super.findByPrimaryKey(connection);
        thirdPartyChartOfAccount.getChartOfAccount(connection);
        return thirdPartyChartOfAccount;
    }

    @Override
    public ThirdPartyChartOfAccount findByPrimaryKey(DatabaseConnection connection, String pk) throws Exception {
        ThirdPartyChartOfAccount thirdPartyChartOfAccount = super.findByPrimaryKey(connection, pk);
        thirdPartyChartOfAccount.getChartOfAccount(connection);
        return thirdPartyChartOfAccount;
    }

    @Override
    public int create(DatabaseConnection connection) throws Exception {
        // this create method is not available for this model,
        // because it needs a manual primary key
        // instead of a generated one
        return 0;
    }
}
