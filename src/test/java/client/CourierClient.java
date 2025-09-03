package client;

import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String COURIER_PATH = "/api/v1/courier";

    public Response create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(COURIER_PATH);
    }

    public Response login(CourierCredentials credentials) {
        return given()
                .header("Content-type", "application/json")
                .body(credentials)
                .post(COURIER_PATH + "/login");
    }

    public Response delete(int courierId) {
        return given()
                .delete(COURIER_PATH + "/" + courierId);
    }

    public Response deleteWithoutId() {
        return given()
                .delete(COURIER_PATH + "/");
    }
}
