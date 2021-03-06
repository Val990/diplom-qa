package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbGenerator {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.login");
    private static String password = System.getProperty("db.password");

    @SneakyThrows
    public static void cleanData() {
        var runner = new QueryRunner();
        var cleanOrder = "DELETE FROM order_entity";
        var cleanPayment = "DELETE FROM payment_entity";
        var cleanRequest = "DELETE FROM credit_request_entity";

        try (
                var connection = DriverManager.getConnection(url, user, password);
        ) {
            runner.update(connection, cleanOrder);
            runner.update(connection, cleanPayment);
            runner.update(connection, cleanRequest);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static String getStatus(String query) {
        var runner = new QueryRunner();
        var status = "";
        try (
                var connection = DriverManager.getConnection(url, user, password);
        ) {
            status = runner.query(connection, query, new ScalarHandler<>());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        var statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    @SneakyThrows
    public static String getCreditStatus() {
        var statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }
}
