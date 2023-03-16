package com.alain.accounting_management_system.model;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;

@Table(name = "equivalent_currency_detail", columnCount = 4)
public class EquivalentCurrencyDetail extends Relation<EquivalentCurrencyDetail> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "EQD", length = 7, sequence = "equi_curr_det_seq")
    private String equivalentCurrencyDetailID;

    @Column(name = "soc_equi_curr_id")
    private String equivalentCurrencyID;

    @Column(name = "currency_id")
    private String currencyID;

    @Column
    private Double value;

    private Currency currency;

    // constructor
    public EquivalentCurrencyDetail() throws Exception {
    }

    // setters
    public void setEquivalentCurrencyDetailID(String equivalentCurrencyDetailID) {
        this.equivalentCurrencyDetailID = equivalentCurrencyDetailID;
    }

    public void setEquivalentCurrencyID(String equivalentCurrencyID) {
        this.equivalentCurrencyID = equivalentCurrencyID;
    }

    public void setCurrencyID(String currencyID) {
        this.currencyID = currencyID;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    // getters
    public String getEquivalentCurrencyDetailID() {
        return this.equivalentCurrencyDetailID;
    }

    public String getEquivalentCurrencyID() {
        return this.equivalentCurrencyID;
    }

    public String getCurrencyID() {
        return this.currencyID;
    }

    public Double getValue() {
        return this.value;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    // methods
    public void getCurrency(DatabaseConnection connection) throws Exception {
        Currency currency = new Currency().findByPrimaryKey(connection,
                "WHERE currency_id = '" + this.getCurrencyID() + "'");
        this.setCurrency(currency);
    }

    @Override
    public EquivalentCurrencyDetail[] findAll(DatabaseConnection connection) throws Exception {
        EquivalentCurrencyDetail[] equivalentCurrencyDetails = super.findAll(connection);
        for (EquivalentCurrencyDetail equivalentCurrency : equivalentCurrencyDetails) {
            equivalentCurrency.getCurrency(connection);
        }
        return equivalentCurrencyDetails;
    }

    @Override
    public EquivalentCurrencyDetail[] findAll(DatabaseConnection connection, String spec) throws Exception {
        EquivalentCurrencyDetail[] equivalentCurrencyDetails = super.findAll(connection, spec);
        for (EquivalentCurrencyDetail equivalentCurrency : equivalentCurrencyDetails) {
            equivalentCurrency.getCurrency(connection);
        }
        return equivalentCurrencyDetails;
    }

    @Override
    public EquivalentCurrencyDetail findByPrimaryKey(DatabaseConnection connection) throws Exception {
        EquivalentCurrencyDetail equivalentCurrencyDetail = super.findByPrimaryKey(connection);
        equivalentCurrencyDetail.getCurrency(connection);
        return equivalentCurrencyDetail;
    }

    @Override
    public EquivalentCurrencyDetail findByPrimaryKey(DatabaseConnection connection, String spec) throws Exception {
        EquivalentCurrencyDetail equivalentCurrencyDetail = super.findByPrimaryKey(connection, spec);
        equivalentCurrencyDetail.getCurrency(connection);
        return equivalentCurrencyDetail;
    }
}
