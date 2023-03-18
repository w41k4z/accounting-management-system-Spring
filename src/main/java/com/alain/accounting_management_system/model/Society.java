package com.alain.accounting_management_system.model;

import java.sql.Date;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;

@Table(columnCount = 13)
public class Society extends Relation<Society> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "SOC", length = 8, sequence = "society_seq")
    private String societyID;

    @Column
    private String name;

    @Column(name = "ceo")
    private String societyCEO;

    @Column
    private String object;

    @Column
    private String address;

    @Column
    private String headquarters;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "tin")
    private String taxIdentificationNumber;

    @Column(name = "stn")
    private String statisticalNumber;

    @Column(name = "crgn")
    private String commercialRegisterNumber;

    @Column
    private String status;

    @Column(name = "stdtacpd")
    private Date startDateOfAccountingPeriod;

    @Column(name = "accounting_currency")
    private String accountingCurrency;

    private Currency currency;

    private EquivalentCurrency equivalentCurrency;

    // constructor
    public Society() throws Exception {
        super();
    }

    // setters
    public void setSocietyID(String society_id) {
        this.societyID = society_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSocietyCEO(String societyCEO) {
        this.societyCEO = societyCEO;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public void setStatisticalNumber(String statisticalNumber) {
        this.statisticalNumber = statisticalNumber;
    }

    public void setCommercialRegisterNumber(String commercialRegisterNumber) {
        this.commercialRegisterNumber = commercialRegisterNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartDateOfAccountingPeriod(Date startDateOfAccountingPeriod) {
        this.startDateOfAccountingPeriod = startDateOfAccountingPeriod;
    }

    public void setAccountingCurrency(String accountingCurrency) {
        this.accountingCurrency = accountingCurrency;
    }

    private void setCurrency(Currency currency) {
        this.currency = currency;
    }

    private void setEquivalentCurrency(EquivalentCurrency equivalentCurrency) {
        this.equivalentCurrency = equivalentCurrency;
    }

    // getters
    public String getSocietyID() {
        return this.societyID;
    }

    public String getName() {
        return this.name;
    }

    public String getSocietyCEO() {
        return this.societyCEO;
    }

    public String getObject() {
        return this.object;
    }

    public String getAddress() {
        return this.address;
    }

    public String getHeadquarters() {
        return this.headquarters;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getTaxIdentificationNumber() {
        return this.taxIdentificationNumber;
    }

    public String getStatisticalNumber() {
        return this.statisticalNumber;
    }

    public String getCommercialRegisterNumber() {
        return this.commercialRegisterNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public Date getStartDateOfAccountingPeriod() {
        return this.startDateOfAccountingPeriod;
    }

    public String getAccountingCurrency() {
        return this.accountingCurrency;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public EquivalentCurrency getEquivalentCurrency() {
        return this.equivalentCurrency;
    }

    // methods
    private void getCurrency(DatabaseConnection connection) throws Exception {
        Currency currency = new Currency().findByPrimaryKey(connection, this.getAccountingCurrency());
        this.setCurrency(currency);
    }

    private void getEquivalentCurrency(DatabaseConnection connection) throws Exception {
        EquivalentCurrency equivalentCurrency = new EquivalentCurrency().findAll(connection,
                "WHERE society_id = '" + this.getSocietyID() + "'")[0];
        this.setEquivalentCurrency(equivalentCurrency);
    }

    @Override
    public Society[] findAll(DatabaseConnection connection) throws Exception {
        Society[] societies = super.findAll(connection);
        for (Society society : societies) {
            society.getCurrency(connection);
            society.getEquivalentCurrency(connection);
        }
        return societies;
    }

    @Override
    public Society[] findAll(DatabaseConnection connection, String spec) throws Exception {
        Society[] societies = super.findAll(connection, spec);
        for (Society society : societies) {
            society.getCurrency(connection);
            society.getEquivalentCurrency(connection);
        }
        return societies;
    }

    @Override
    public Society findByPrimaryKey(DatabaseConnection connection) throws Exception {
        Society society = super.findByPrimaryKey(connection);
        society.getCurrency(connection);
        society.getEquivalentCurrency(connection);
        return society;
    }

    @Override
    public Society findByPrimaryKey(DatabaseConnection connection, String pk) throws Exception {
        Society society = super.findByPrimaryKey(connection, pk);
        society.getCurrency(connection);
        society.getEquivalentCurrency(connection);
        return society;
    }
}
