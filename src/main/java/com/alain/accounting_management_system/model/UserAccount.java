package com.alain.accounting_management_system.model;

import java.sql.Date;

import orm.annotation.Column;
import orm.annotation.PrimaryKey;
import orm.annotation.Table;
import orm.database.object.relation.Relation;
import orm.database.connection.DatabaseConnection;

@Table(name = "user_account", columnCount = 8)
public class UserAccount extends Relation<UserAccount> {

    @PrimaryKey(column = @Column(name = "id"), prefix = "ACC", length = 7, sequence = "account_seq")
    private String accountID;

    @Column
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String password;

    private SocietyAccount[] societyAccounts;

    // constructor
    public UserAccount() throws Exception {
        super();
    }

    // setters
    public void setAccountID(String account_id) {
        this.accountID = account_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void setSocietyAccounts(SocietyAccount[] societyAccounts) {
        this.societyAccounts = societyAccounts;
    }

    // getters
    public String getAccountID() {
        return accountID;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPassword() {
        return this.password;
    }

    public SocietyAccount[] getSocietyAccounts() {
        return this.societyAccounts;
    }

    // method
    private void getSocietyAccounts(DatabaseConnection connection) throws Exception {
        SocietyAccount[] societyAccounts = new SocietyAccount().findAll(connection,
                "WHERE account_id='" + this.getAccountID() + "'");
        this.setSocietyAccounts(societyAccounts);
    }

    @Override
    public UserAccount[] findAll(DatabaseConnection connection) throws Exception {
        UserAccount[] accounts = super.findAll(connection);
        for (UserAccount account : accounts) {
            account.getSocietyAccounts(connection);
        }
        return accounts;
    }

    @Override
    public UserAccount[] findAll(DatabaseConnection connection, String spec) throws Exception {
        UserAccount[] accounts = super.findAll(connection, spec);
        for (UserAccount account : accounts) {
            account.getSocietyAccounts(connection);
        }
        return accounts;
    }

    @Override
    public UserAccount findByPrimaryKey(DatabaseConnection connection) throws Exception {
        UserAccount account = super.findByPrimaryKey(connection);
        account.getSocietyAccounts(connection);
        return account;
    }

    @Override
    public UserAccount findByPrimaryKey(DatabaseConnection connection, String pk) throws Exception {
        UserAccount account = super.findByPrimaryKey(connection, pk);
        account.getSocietyAccounts(connection);
        return account;
    }

    public static UserAccount authenticate(DatabaseConnection connection, String email, String password)
            throws Exception {
        String spec = "WHERE email='" + email + "' AND password='" + password + "'";
        UserAccount[] accounFound = new UserAccount().findAll(connection, spec);
        return accounFound.length > 0 ? accounFound[0] : null;
    }
}
