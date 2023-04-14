package com.alain.accounting_management_system.model;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.database.object.relation.Relation;

@Table(columnCount = 2)
public class Currency extends Relation<Currency> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "CUR", length = 5, sequence = "currency_seq")
    private String currencyID;

    @Column
    private String label; // EUR, USD, GBP, etc.

    // constructor
    public Currency() throws Exception {
        super();
    }

    // setters
    public void setCurrencyID(String currency_ID) {
        this.currencyID = currency_ID;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // getters
    public String getCurrencyID() {
        return this.currencyID;
    }

    public String getLabel() {
        return this.label;
    }
}
