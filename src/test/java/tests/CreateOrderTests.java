package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Order;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import steps.OrderSteps;
import utils.BaseTest;

import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTests extends BaseTest {
    private final OrderSteps orderSteps = new OrderSteps();
    private Integer track;

    @After
    public void tearDown() {
        if (track != null) {
            // отменяем заказ — чтобы не оставлять тестовые данные
            orderSteps.cancelOrder(track);
            track = null;
        }
    }

    @Test
    @DisplayName("Создание заказа без цвета")
    @Description("Можно создать заказ без указания цвета")
    public void orderCanBeCreatedWithoutColorTest() {
        Order order = Order.random();
        Response response = orderSteps.createOrder(order);
        response.then().statusCode(HttpStatus.SC_CREATED).body("track", notNullValue());
        track = response.then().extract().path("track");
    }

    @Test
    @DisplayName("Создание заказа с цветом BLACK")
    @Description("Можно создать заказ с цветом BLACK")
    public void orderCanBeCreatedWithColorBlackTest() {
        Order order = Order.randomWithColors(new String[]{"BLACK"});
        Response response = orderSteps.createOrder(order);
        response.then().statusCode(HttpStatus.SC_CREATED).body("track", notNullValue());
        track = response.then().extract().path("track");
    }

    @Test
    @DisplayName("Создание заказа с цветом GREY")
    @Description("Можно создать заказ с цветом GREY")
    public void orderCanBeCreatedWithColorGreyTest() {
        Order order = Order.randomWithColors(new String[]{"GREY"});
        Response response = orderSteps.createOrder(order);
        response.then().statusCode(HttpStatus.SC_CREATED).body("track", notNullValue());
        track = response.then().extract().path("track");
    }

    @Test
    @DisplayName("Создание заказа с цветами BLACK и GREY")
    @Description("Можно создать заказ сразу с двумя цветами")
    public void orderCanBeCreatedWithTwoColorsTest() {
        Order order = Order.randomWithColors(new String[]{"BLACK", "GREY"});
        Response response = orderSteps.createOrder(order);
        response.then().statusCode(HttpStatus.SC_CREATED).body("track", notNullValue());
        track = response.then().extract().path("track");
    }
}