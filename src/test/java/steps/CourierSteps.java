package steps;

import client.CourierClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

public class CourierSteps {
    CourierClient courierClient = new CourierClient();

    @Step("Создать курьера")
    public Response createCourier(Courier courier) {
        return courierClient.createCourier(courier);
    }

    @Step("Авторизовать курьера")
    public Response loginCourier(CourierCredentials creds) {
        return courierClient.loginCourier(creds);
    }

    @Step("Удалить курьера")
    public Response deleteCourier(int id) {
        return courierClient.deleteCourier(id);
    }
}
