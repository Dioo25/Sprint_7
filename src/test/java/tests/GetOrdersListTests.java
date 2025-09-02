package tests;

import org.junit.Test;
import steps.OrderSteps;
import utils.BaseTest;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersListTests extends BaseTest {
    OrderSteps orderSteps = new OrderSteps();

    @Test
    public void ordersListIsReturned() {
        orderSteps.getOrdersList().then().statusCode(200).body("orders", notNullValue());
    }
}