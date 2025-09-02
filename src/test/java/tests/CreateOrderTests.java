package tests;

import model.Order;
import org.junit.Test;
import steps.OrderSteps;
import utils.BaseTest;

import java.util.Arrays;

import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTests extends BaseTest {
    OrderSteps orderSteps = new OrderSteps();

    @Test
    public void orderCanBeCreatedWithColors() {
        Order order = new Order("Иван", "Иванов", "Москва", "4", "+79999999999",
                5, "2025-09-05", "Позвонить", Arrays.asList("BLACK", "GREY"));
        orderSteps.createOrder(order).then().statusCode(201).body("track", notNullValue());
    }
}