package org.example.banking;

import java.util.Random;

public class BankingApp {
    public static void main(String[] args){
        Bank bank = new Bank();

        bank.addAccount(new Account("A1", 15000));
        bank.addAccount(new Account("A2", 32000));
        bank.addAccount(new Account("A3", 63000));

        Random random = new Random();
        for (int i = 1; i <= 20; i++){
            String fromAccountId = "A" + (random.nextInt(3) + 1);
            String toAccountId = "A" + (random.nextInt(3) + 1);
            double amount = random.nextInt(500) + 100;
            bank.executeTransaction(new Transaction("T" + i, fromAccountId, toAccountId, amount));
        }
        bank.shutdown();
        System.out.println("\nFinal Account Balances:");
        bank.printAccounts();
        System.out.println("\nAll Transactions:");
        bank.printTransactions();
    }
}
