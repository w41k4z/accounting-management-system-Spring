package com.alain.accounting_management_system.utils;

public class MoneyFormatter {

    public static String display(Double money) {
        if (money == null || money == 0) {
            return "0,00";
        }
        StringBuilder moneyString = new StringBuilder(String.format("%.2f", money).replace(".", ","));
        int start = moneyString.indexOf(",") != -1 ? moneyString.indexOf(",") - 1 : moneyString.length();
        for (int i = start, j = 1; i >= 0; i--, j++) {
            if (j % 3 == 0) {
                moneyString.insert(i, " ");
            }
        }
        return moneyString.toString();
    }
}
