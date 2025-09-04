package steps;

import client.CourierClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

public class CourierSteps {
    private final CourierClient courierClient = new CourierClient();

    @Step("Шаг: создать курьера")
    public Response createCourier(Courier courier) {
        return courierClient.createCourier(courier);
    }

    @Step("Шаг: логин курьера")
    public Response loginCourier(CourierCredentials credentials) {
        return courierClient.loginCourier(credentials);
    }

    @Step("Шаг: удалить курьера")
    public Response deleteCourier(int id) {
        return courierClient.deleteCourier(id);
    }

    @Step("Шаг: попытка удалить без id")
    public Response deleteCourierWithoutId() {
        return courierClient.deleteCourierWithoutId();
    }
}
