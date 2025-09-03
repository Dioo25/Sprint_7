package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;
import utils.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteCourierTests extends BaseTest {
    private final CourierSteps courierSteps = new CourierSteps();
    private Integer courierId;

    @Before
    public void setUp() {
        Courier courier = Courier.random();
        courierSteps.createCourier(courier);

        Response loginResponse = courierSteps.loginCourier(
                new CourierCredentials(courier.getLogin(), courier.getPassword())
        );
        courierId = loginResponse.then().extract().path("id");
    }

    @After
    public void tearDown() {
        if (courierId != null) {
            courierSteps.deleteCourier(courierId);
            courierId = null;
        }
    }

    @Test
    @DisplayName("Удалить курьера по id")
    @Description("Проверяем, что курьера можно удалить по id и возвращается ok: true")
    public void courierCanBeDeletedTest() {
        Response response = courierSteps.deleteCourier(courierId);
        response.then().statusCode(HttpStatus.SC_OK).body("ok", equalTo(true));
        courierId = null; // чтобы не удалять дважды в @After
    }

    @Test
    @DisplayName("Удаление курьера без указания id")
    @Description("Попытка удалить курьера без id — сервер возвращает 404 и сообщение Not Found.")
    public void cannotDeleteCourierWithoutIdTest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/api/v1/courier/");

        response.then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                // более гибкая проверка: строка содержит "Not Found"
                .body("message", containsString("Not Found"));
    }

    @Test
    @DisplayName("Удаление курьера с несуществующим id")
    @Description("Попытка удаления курьера с несуществующим id возвращает 404 и сообщение")
    public void cannotDeleteCourierWithInvalidIdTest() {
        int fakeId = 99999999;
        Response response = courierSteps.deleteCourier(fakeId);

        response.then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                // проверяем по containsString, чтобы точка в конце не ломала тест
                .body("message", containsString("Курьера с таким id нет"));
    }
}
