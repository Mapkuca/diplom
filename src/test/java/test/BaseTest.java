package test;

import data.URL;
import org.junit.jupiter.api.BeforeEach;
import utils.DBUtil;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    @BeforeEach
    public void setting() {
        open(URL.getUrl());
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("credit_request_entity");
        DBUtil.clearingTable("payment_entity");
    }
}
