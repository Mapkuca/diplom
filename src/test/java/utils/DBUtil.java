package utils;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String url = System.getProperty("spring.datasource.url");
    private static final String user = System.getProperty("spring.datasource.username");
    private static final String password = System.getProperty("spring.datasource.password");

    @SneakyThrows
    private static Connection connection() {
        return DriverManager.getConnection(url, user, password);
    }
    @SneakyThrows
    public static void clearingTable(String tableName) {
        var runner = new QueryRunner();
        var codeSQL = "Delete from " + tableName + ";";
        runner.update(connection(), codeSQL);
    }


    @SneakyThrows
    public static String getPaymentStatus(String tableName) {
        System.out.println(tableName);
        var runner = new QueryRunner();
        var codeSQL = "SELECT status FROM " + tableName + ";";
        var paymentStatus = runner.query(connection(), codeSQL, new ScalarHandler<>());
        return paymentStatus.toString();
    }

    @SneakyThrows
    public static Object countOrderIfPayment() {
        var runner = new QueryRunner();
        var counterSQL = "SELECT COUNT(*) FROM order_entity WHERE payment_id = (SELECT transaction_id FROM payment_entity);";
        var count = runner.query(connection(), counterSQL, new ScalarHandler<>());
        return count;
    }

    @SneakyThrows
    public static Object countOrderIfCredit() {
        var runner = new QueryRunner();
        var counterSQL = "SELECT COUNT(*) FROM order_entity WHERE credit_id = (SELECT bank_id FROM credit_request_entity);";
        var count = runner.query(connection(), counterSQL, new ScalarHandler<>());
        return count;
    }

}
