CREATE DATABASE accounting_management_system OWNER walker;

CREATE SEQUENCE currency_seq;
CREATE SEQUENCE account_seq;
CREATE SEQUENCE society_seq;
CREATE SEQUENCE soc_equi_curr_seq;
CREATE SEQUENCE equi_curr_det_seq;
CREATE SEQUENCE society_account_seq;
CREATE SEQUENCE chart_of_account_seq;
CREATE SEQUENCE third_party_chart_of_account_seq;

CREATE TABLE currency (
    id VARCHAR(5) PRIMARY KEY,
    label VARCHAR(3) NOT NULL UNIQUE
);
CREATE TABLE account (
    id VARCHAR(7) PRIMARY KEY,
    email VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(16) NOT NULL
);
CREATE TABLE society (
    id VARCHAR(8) PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    ceo VARCHAR(40) NOT NULL,
    object TEXT NOT NULL,
    address TEXT NOT NULL,
    headquarters VARCHAR(25) NOT NULL,
    creation_date DATE NOT NULL,
    tin VARCHAR(25), -- tax identification number
    stn VARCHAR(25), -- statistical number
    crgn VARCHAR(25), -- commercial register number
    status VARCHAR(25), 
    stdtacpd DATE NOT NULL, -- start date of accounting period
    accounting_currency VARCHAR(5) REFERENCES currency(id)
);
CREATE TABLE society_equivalent_currency (
    id VARCHAR(6) PRIMARY KEY,
    society_id VARCHAR(8) REFERENCES society(id) UNIQUE
);
CREATE TABLE equivalent_currency_detail (
    id VARCHAR(7) PRIMARY KEY,
    soc_equi_curr_id VARCHAR(6) REFERENCES society_equivalent_currency(id),
    currency_id VARCHAR(5) REFERENCES currency(id),
    value REAL NOT NULL
);
CREATE TABLE society_account (
    id VARCHAR(8) PRIMARY KEY,
    account_id VARCHAR(7) REFERENCES account(id),
    society_id VARCHAR(8) REFERENCES society(id),
    password VARCHAR(16) NOT NULL
);
CREATE TABLE chart_of_account (
    id VARCHAR(8) PRIMARY KEY,
    account_number VARCHAR(5) NOT NULL, -- is unique for each society
    society_id VARCHAR(7) REFERENCES society(id),
    entitled VARCHAR(50)
);
ALTER TABLE chart_of_account
  ADD CONSTRAINT uq_chart_of_account UNIQUE(account_number, society_id);
CREATE TABLE third_party_chart_of_account (
    thd_pt_chrt_acc_id VARCHAR(8) PRIMARY KEY,
    chrt_acc_id VARCHAR(8) REFERENCES chart_of_account(id),
    key VARCHAR(5) NOT NULL, -- is unique for each society
    value VARCHAR(25) NOT NUll
); 