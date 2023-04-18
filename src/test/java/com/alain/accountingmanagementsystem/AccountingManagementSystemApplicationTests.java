package com.alain.accountingmanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alain.accounting_management_system.connection.AppDBCon;
import com.alain.accounting_management_system.model.UserAccount;
import com.alain.accounting_management_system.utils.MoneyFormatter;

import orm.database.connection.DatabaseConnection;

@SpringBootTest
class AccountingManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		try {
			System.out.println(MoneyFormatter.display(15.));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
