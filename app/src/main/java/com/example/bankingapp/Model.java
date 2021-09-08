package com.example.bankingapp;

public class Model {
    String name,email,name_sender,name_reciever,transaction_status;
    Integer balance;
    public Model(String name,String email,Integer balance){
        this.name= name;
        this.balance= balance;
        this.email=email;
    }
    public Model(String name_sender,String name_reciever,Integer balance,String transaction_status){
        this.name_sender = name_sender;
        this.balance = balance;
        this.name_reciever = name_reciever;
        this.transaction_status = transaction_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getName_sender() {
        return name_sender;
    }

    public String getName_reciever() {
        return name_reciever;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setName_sender(String name_sender) {
        this.name_sender = name_sender;
    }

    public void setName_reciever(String name_reciever) {
        this.name_reciever = name_reciever;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }
}
