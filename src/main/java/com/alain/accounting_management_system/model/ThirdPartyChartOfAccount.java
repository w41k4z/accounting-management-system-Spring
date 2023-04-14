package com.alain.accounting_management_system.model;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;

import java.sql.SQLException;
import java.util.List;

import orm.annotation.Column;
import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;
import orm.utilities.Treatment;

@Table(name = "third_party_chart_of_account", columnCount = 4)
public class ThirdPartyChartOfAccount extends Relation<ThirdPartyChartOfAccount> {

    @PrimaryKey(column = @Column(name = "thd_pt_chrt_acc_id"), prefix = "TPCOA", length = 8, sequence = "third_party_chart_of_account_seq")
    private String thirdPartyChartOfAccountID;

    @Column
    private String key;

    @Column(name = "society_id")
    private String societyID;

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

    public void setKey(String key) {
        this.key = key;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // getters
    public String getThirdPartyChartOfAccountID() {
        return this.thirdPartyChartOfAccountID;
    }

    public String getKey() {
        return this.key;
    }

    public String getSocietyID() {
        return this.societyID;
    }

    public String getValue() {
        return this.value;
    }

    // methods
    /*
     * First element of the list must be the the column name
     */
    public static void insertImportedData(DatabaseConnection connection, List<List<String>> data, String societyID)
            throws Exception {
        String[] columnName = data.get(0).toArray(new String[data.get(0).size()]);
        for (int i = 1; i < data.size(); i++) {
            ThirdPartyChartOfAccount toCreate = new ThirdPartyChartOfAccount();
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
