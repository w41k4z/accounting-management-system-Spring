package com.alain.accountingmanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alain.accounting_management_system.connection.AppDBCon;
import com.alain.accounting_management_system.model.Account;

import orm.database.connection.DatabaseConnection;

@SpringBootTest
class AccountingManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		try {
			DatabaseConnection connection = new AppDBCon().defaultConnection();
			Account account = Account.authenticate(connection, "admin@gmail.com", "12345678");
			System.out.println(account.getSocietyAccounts()[0].getSociety().getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
