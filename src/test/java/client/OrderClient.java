package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderClient {
    private static final String ORDER_PATH = "/api/v1/orders/";

    @Step("Создать заказ")
    public Response createOrder(Object order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDER_PATH);
    }

    @Step("Принять заказ")
    public Response acceptOrder(int courierId, int orderId) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("courierId", courierId)
                .when()
                .put(ORDER_PATH + "accept/" + orderId);
    }

    @Step("Получить заказ по треку")
    public Response getOrderByTrack(int track) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("t", track)
                .when()
                .get(ORDER_PATH + "track");
    }

    @Step("Получить список заказов")
    public Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(ORDER_PATH);
    }

    @Step("Отменить заказ по треку")
    public Response cancelOrder(int track) {
        // По API: отмена заказа по параметру track — делаем POST/PUT на /orders/cancel с телом {"track": track}
        return given()
                .header("Content-type", "application/json")
                .body(Map.of("track", track))
                .when()
                .put(ORDER_PATH + "cancel");
    }
}