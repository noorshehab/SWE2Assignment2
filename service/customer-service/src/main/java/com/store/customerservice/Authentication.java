package com.store.customerservice;

public abstract class Authentication {
    private String email;
    private String password;

    public Authentication() {
    }

    public Authentication(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract String execute(Customer c);

}
