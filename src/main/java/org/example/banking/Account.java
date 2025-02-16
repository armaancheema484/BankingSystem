package org.example.banking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account
{
    private String accountId;
    private double balance;
    private Lock lock = new ReentrantLock();

    public Account(String accountId, double balance){
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId(){
        return accountId;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        lock.lock();
        try {
            if (balance < amount){
                throw new InsufficientFundsException("Insufficient funds in account" + accountId);
            }
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }
}
