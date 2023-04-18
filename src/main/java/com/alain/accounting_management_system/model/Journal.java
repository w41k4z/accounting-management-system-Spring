package com.alain.accounting_management_system.model;

import orm.annotation.Table;
import orm.annotation.PrimaryKey;

import java.sql.Date;
import java.util.List;

import orm.annotation.Column;
import orm.database.connection.DatabaseConnection;
import orm.database.object.relation.Relation;
import orm.database.object.view.View;
import orm.utilities.Treatment;

@Table(columnCount = 11)
public class Journal extends Relation<Journal> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "JRN", length = 8, sequence = "journal_seq")
    private String journalID;

    @Column(name = "journal_date")
    private Date journalDate;

    @Column(name = "society_id")
    private String societyID;

    @Column(name = "piece_number")
    private Integer pieceNumber;

    @Column(name = "part_reference")
    private String partReference;

    @Column(name = "general_account")
    private String generalAccount;

    @Column(name = "third_party_account")
    private String thirdPartyAccount;

    @Column
    private String entitled;

    @Column
    private String label;

    @Column
    private Double debit;

    @Column
    private Double credit;

    // constructor
    public Journal() throws Exception {
        super();
    }

    // setters
    public void setJournalID(String journalID) {
        this.journalID = journalID;
    }

    public void setJournalDate(Date journalDate) {
        this.journalDate = journalDate;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public void setPieceNumber(Integer pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public void setPartReference(String partReference) {
        this.partReference = partReference;
    }

    public void setGeneralAccount(String generalAccount) {
        this.generalAccount = generalAccount;
    }

    public void setThirdPartyAccount(String thirdPartyAccount) {
        this.thirdPartyAccount = thirdPartyAccount;
    }

    public void setEntitled(String entitled) {
        this.entitled = entitled;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    // getters
    public String getJournalID() {
        return this.journalID;
    }

    public Date getJournalDate() {
        return this.journalDate;
    }

    public String getSocietyID() {
        return this.societyID;
    }

    public Integer getPieceNumber() {
        return this.pieceNumber;
    }

    public String getPartReference() {
        return this.partReference;
    }

    public String getGeneralAccount() {
        return this.generalAccount;
    }

    public String getThirdPartyAccount() {
        return this.thirdPartyAccount;
    }

    public String getEntitled() {
        return this.entitled;
    }

    public String getLabel() {
        return this.label;
    }

    public Double getDebit() {
        return this.debit;
    }

    public Double getCredit() {
        return this.credit;
    }

    // methods
    public static boolean isBalanced(Journal[] journals) {
        double debit = 0;
        double credit = 0;
        for (Journal journal : journals) {
            debit += journal.getDebit() == null ? 0 : journal.getDebit();
            credit += journal.getCredit() == null ? 0 : journal.getCredit();
        }
        return debit == credit;
    }

    public static Journal[] getGeneralLedger(DatabaseConnection connection, String society_id)
            throws Exception {
        return new View<Journal>("general_ledger", Journal.class).findAll(connection,
                "WHERE society_id = '" + society_id + "'");
    }

    /*
     * First element of the list must be the the column name
     */
    public static void insertImportedData(DatabaseConnection connection, List<List<String>> data, String societyID)
            throws Exception {
        // first element of the list is the column name
        Journal[] journals = new Journal[data.size() - 1];
        String[] columnName = data.get(0).toArray(new String[data.get(0).size()]);
        for (int i = 1; i < data.size(); i++) {
            Journal toCreate = new Journal();
            toCreate.setSocietyID(societyID);
            for (int j = 0; j < columnName.length; j++) {
                Treatment.setObjectFieldValue(toCreate, data.get(i).get(j), toCreate.getColumn(columnName[j].trim()));
            }
            journals[i - 1] = toCreate;
        }
        if (isBalanced(journals)) {
            for (Journal journal : journals) {
                journal.create(connection);
            }
        } else {
            throw new Exception("The journal is not balanced");
        }
    }
}
