package test;

import data.DataHelper;
import org.junit.jupiter.api.Test;
import utils.ApiUtils;

public class APITest {
    @Test
    public void shouldPayWithApprovedCardAPI() {
        ApiUtils.shouldSendRequestApprovedCard();
    }

    @Test
    public void shouldPayWithDeclinedCardAPI() {
        ApiUtils.shouldSendRequestDeclinedCard();
    }

    @Test
    public void shouldCreditWithApprovedCardAPI() {
        ApiUtils.shouldSendCreditRequestApprovedCard();
    }

    @Test
    public void shouldCreditWithDeclinedCardAPI() {
        ApiUtils.shouldSendCreditRequestDeclinedCard();
    }
}
