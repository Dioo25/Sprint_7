package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import steps.OrderSteps;
import utils.BaseTest;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersListTests extends BaseTest {
    private final OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверяем, что ручка возвращает список заказов")
    public void ordersListCanBeReceived() {
        Response response = orderSteps.getOrdersList();
        response.then().statusCode(HttpStatus.SC_OK).body(notNullValue());
    }
}