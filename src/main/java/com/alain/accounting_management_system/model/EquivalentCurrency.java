package com.alain.accounting_management_system.model;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;

@Table(name = "society_equivalent_currency", columnCount = 2)
public class EquivalentCurrency extends Relation<EquivalentCurrency> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "EQC", length = 6, sequence = "soc_equi_curr_seq")
    private String equivalentCurrencyID;

    @Column(name = "society_id")
    private String societyID;

    private EquivalentCurrencyDetail[] equivalentCurrencyDetails;

    // constructor
    public EquivalentCurrency() throws Exception {
        super();
    }

    // setters
    public void setEquivalentCurrencyID(String equivalentCurrencyID) {
        this.equivalentCurrencyID = equivalentCurrencyID;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    private void setEquivalentCurrencyDetails(EquivalentCurrencyDetail[] equivalentCurrencyDetails) {
        this.equivalentCurrencyDetails = equivalentCurrencyDetails;
    }

    // getters
    public String getEquivalentCurrencyID() {
        return equivalentCurrencyID;
    }

    public String getSocietyID() {
        return societyID;
    }

    public EquivalentCurrencyDetail[] getEquivalentCurrencyDetails() {
        return equivalentCurrencyDetails;
    }

    // methods
    private void getEquivalentCurrencyDetails(DatabaseConnection connection) throws Exception {
        EquivalentCurrencyDetail[] equivalentCurrencyDetails = new EquivalentCurrencyDetail().findAll(connection,
                "WHERE soc_equi_curr_id = '" + this.getEquivalentCurrencyID() + "'");
        this.setEquivalentCurrencyDetails(equivalentCurrencyDetails);
    }

    @Override
    public EquivalentCurrency[] findAll(DatabaseConnection connection) throws Exception {
        EquivalentCurrency[] equivalentCurrencies = super.findAll(connection);
        for (EquivalentCurrency equivalentCurrency : equivalentCurrencies) {
            equivalentCurrency.getEquivalentCurrencyDetails(connection);
        }
        return equivalentCurrencies;
    }

    @Override
    public EquivalentCurrency[] findAll(DatabaseConnection connection, String spec) throws Exception {
        EquivalentCurrency[] equivalentCurrencies = super.findAll(connection, spec);
        for (EquivalentCurrency equivalentCurrency : equivalentCurrencies) {
            equivalentCurrency.getEquivalentCurrencyDetails(connection);
        }
        return equivalentCurrencies;
    }

    @Override
    public EquivalentCurrency findByPrimaryKey(DatabaseConnection connection) throws Exception {
        EquivalentCurrency equivalentCurrency = super.findByPrimaryKey(connection);
        equivalentCurrency.getEquivalentCurrencyDetails(connection);
        return equivalentCurrency;
    }

    @Override
    public EquivalentCurrency findByPrimaryKey(DatabaseConnection connection, String pk) throws Exception {
        EquivalentCurrency equivalentCurrency = super.findByPrimaryKey(connection, pk);
        equivalentCurrency.getEquivalentCurrencyDetails(connection);
        return equivalentCurrency;
    }
}
