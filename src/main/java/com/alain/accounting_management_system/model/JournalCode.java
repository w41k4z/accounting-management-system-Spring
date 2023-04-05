package com.alain.accounting_management_system.model;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;
import orm.annotation.Column;
import orm.database.object.relation.Relation;

@Table(name = "journal_code", columnCount = 3)
public class JournalCode extends Relation<JournalCode> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "JCD", length = 6, sequence = "journal_code_seq")
    private String journalCodeID;

    @Column
    private String code;

    @Column
    private String entitled;

    // constructor
    public JournalCode() throws Exception {
        super();
    }

    // setters
    public void setJournalCodeID(String journalCodeID) {
        this.journalCodeID = journalCodeID;
    }

    public void setCode(String code) {
        if (code.length() == 2)
            this.code = code;
    }

    public void setEntitled(String entitled) {
        this.entitled = entitled;
    }

    // getters
    public String getJournalCodeID() {
        return this.journalCodeID;
    }

    public String getCode() {
        return this.code;
    }

    public String getEntitled() {
        return this.entitled;
    }
}
