/*
 * @author yangjiewei
 * @date 2020/11/12 16:10
 */
package com.yjiewei.entity;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer id;
    private String username;
    private Double money;

    public Account() {
    }

    public Account(String username, Double money) {
        this.username = username;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", money=" + money +
                '}';
    }
}
