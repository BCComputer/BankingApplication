package org.bank.entities;

public class Transactions {

private int account_id;
private String transaction_type;
private double amount;
private String description;


    public Transactions(int account_id, String transaction_type, double amount, String description) {
        this.account_id = account_id;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.description = description;
    }

    public Transactions() {

    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "account_id=" + account_id +
                ", transaction_type='" + transaction_type + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\''  + '}'+"\n";
    }
}
