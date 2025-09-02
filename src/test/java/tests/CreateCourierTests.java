package tests;

import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.junit.Test;
import steps.CourierSteps;
import utils.BaseTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateCourierTests extends BaseTest {
    CourierSteps courierSteps = new CourierSteps();

    @Test
    public void courierCanBeCreated() {
        Courier courier = Courier.random();
        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(201).body("ok", equalTo(true));

        // cleanup
        int id = courierSteps.loginCourier(new CourierCredentials(courier.getLogin(), courier.getPassword()))
                .then().extract().path("id");
        courierSteps.deleteCourier(id);
    }
}