package tests;

import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.junit.Test;
import steps.CourierSteps;
import utils.BaseTest;

import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTests extends BaseTest {
    CourierSteps courierSteps = new CourierSteps();

    @Test
    public void courierCanLogin() {
        Courier courier = Courier.random();
        courierSteps.createCourier(courier);

        Response response = courierSteps.loginCourier(new CourierCredentials(courier.getLogin(), courier.getPassword()));
        response.then().statusCode(200).body("id", notNullValue());

        int id = response.then().extract().path("id");
        courierSteps.deleteCourier(id);
    }
}