
package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.Test;
import steps.OrderSteps;
import utils.BaseTest;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersListTests extends BaseTest {
    OrderSteps orderSteps = new OrderSteps();

    @Test
    @Ignore("Временно отключён из-за нестабильности стенда")
    @DisplayName("Получение списка заказов")
    @Description("Проверяем, что API возвращает список заказов")
    public void ordersListCanBeReceivedTest() {
        Response response = orderSteps.getOrdersList();

        response.then().statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}