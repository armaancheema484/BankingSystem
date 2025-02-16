package org.example.banking;

import java.util.*;
import java.util.concurrent.*;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public void executeTransaction(Transaction transaction) {
        executorService.submit(() -> {
            try {
                Account fromAccount = accounts.get(transaction.getFromAccountId());
                Account toAccount = accounts.get(transaction.getToAccountId());

                if (fromAccount == null || toAccount == null) {
                    throw new InvalidTransactionException("Invalid account in transaction " + transaction.getTransactionId());
                }

                fromAccount.withdraw(transaction.getAmount());
                toAccount.deposit(transaction.getAmount());

                transactions.add(transaction);
                System.out.println("Transaction " + transaction.getTransactionId() + " completed.");
            } catch (InsufficientFundsException | InvalidTransactionException e) {
                System.err.println("Transaction " + transaction.getTransactionId() + " failed: " + e.getMessage());
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public void printAccounts() {
        accounts.values().forEach(account ->
                System.out.println("Account " + account.getAccountId() + " balance: " + account.getBalance()));
    }

    public void printTransactions() {
        transactions.forEach(transaction ->
                System.out.println("Transaction " + transaction.getTransactionId() + ": " +
                        transaction.getFromAccountId() + " -> " + transaction.getToAccountId() + " $" + transaction.getAmount()));
    }
}