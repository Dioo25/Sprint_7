package steps;

import client.CourierClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

public class CourierSteps {
    private final CourierClient courierClient = new CourierClient();

    @Step("Создание курьера")
    public Response createCourier(Courier courier) {
        return courierClient.create(courier);
    }

    @Step("Логин курьера")
    public Response loginCourier(CourierCredentials credentials) {
        return courierClient.login(credentials);
    }

    @Step("Удаление курьера по id")
    public Response deleteCourier(int courierId) {
        return courierClient.delete(courierId);
    }

    @Step("Удаление курьера без id")
    public Response deleteCourierWithoutId() {
        return courierClient.deleteWithoutId();
    }
}
