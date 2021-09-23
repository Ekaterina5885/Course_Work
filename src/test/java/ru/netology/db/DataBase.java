package ru.netology.db;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

    @SneakyThrows
    private static Connection getConnection() {
        String url = System.getProperty("dbUrl");
        String user = System.getProperty("dbUser");
        String password = System.getProperty("dbPassword");
        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static String getDebitCardTransactionStatus() {

        var debitStatusSQL = "SELECT status FROM payment_entity WHERE id is not null;";
        var conn = getConnection();
        var countStmt = conn.createStatement();
        var result = countStmt.executeQuery(debitStatusSQL);

        if (result.next()) {
            return result.getString("status");
        }
        return null;
    }

    @SneakyThrows
    public static String getCreditCardTransactionStatus() {

        var creditStatusSQL = "SELECT status FROM credit_request_entity WHERE id is not null;";
        var conn = getConnection();
        var countStmt = conn.createStatement();
        var result = countStmt.executeQuery(creditStatusSQL);

        if (result.next()) {
            return result.getString("status");
        }
        return null;
    }

    @SneakyThrows
    public static String getOrderTransactionDebitCard() {

        var paymentIDSQL = "SELECT * FROM payment_id FROM order_entity WHERE id is not null";
        var conn = getConnection();
        var countStmt = conn.createStatement();
        var result = countStmt.executeQuery(paymentIDSQL);

        if (result.next()) {
            return result.getString("payment_id");
        }
        return null;
    }

    @SneakyThrows
    public static String getOrderTransactionCreditCard() {

        var paymentIDSQL = "SELECT * FROM  credit_id FROM order_entity WHERE id is not null;";
        var conn = getConnection();
        var countStmt = conn.createStatement();
        var result = countStmt.executeQuery(paymentIDSQL);

        if (result.next()) {
            return result.getString("credit_id");
        }
        return null;
    }
}