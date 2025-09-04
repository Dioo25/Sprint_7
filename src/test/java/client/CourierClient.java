package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String COURIER_PATH = "/api/v1/courier";

    @Step("Создать курьера (API)")
    public Response createCourier(Object courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH);
    }

    @Step("Авторизация курьера (API)")
    public Response loginCourier(Object credentials) {
        return given()
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post(COURIER_PATH + "/login");
    }

    @Step("Удалить курьера по id (API)")
    public Response deleteCourier(int id) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_PATH + "/" + id);
    }

    @Step("Попытаться удалить курьера без id (API)")
    public Response deleteCourierWithoutId() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_PATH);
    }
}
