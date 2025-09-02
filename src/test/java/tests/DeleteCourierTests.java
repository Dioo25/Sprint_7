package tests;

import model.Courier;
import model.CourierCredentials;
import org.junit.Test;
import steps.CourierSteps;
import utils.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class DeleteCourierTests extends BaseTest {
    CourierSteps courierSteps = new CourierSteps();

    @Test
    public void courierCanBeDeleted() {
        Courier courier = Courier.random();
        courierSteps.createCourier(courier);
        int id = courierSteps.loginCourier(new CourierCredentials(courier.getLogin(), courier.getPassword()))
                .then().extract().path("id");

        courierSteps.deleteCourier(id).then().statusCode(200).body("ok", equalTo(true));
    }
}
