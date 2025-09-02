package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String COURIER_PATH = "/api/v1/courier/";

    @Step("Создать курьера")
    public Response createCourier(Object courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH);
    }

    @Step("Авторизовать курьера")
    public Response loginCourier(Object creds) {
        return given()
                .header("Content-type", "application/json")
                .body(creds)
                .when()
                .post(COURIER_PATH + "login");
    }

    @Step("Удалить курьера")
    public Response deleteCourier(int courierId) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_PATH + courierId);
    }
}
