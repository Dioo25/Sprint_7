package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Базовый класс для всех тестов.
 * Настраивает RestAssured и интеграцию с Allure.
 */
public abstract class BaseTest {

    @BeforeClass
    public static void setUpRestAssured() {
        // Базовый URL стенда (можно переопределить -Dbase.url=http://...)
        String baseUrl = System.getProperty("base.url", "http://qa-scooter.praktikum-services.ru");
        RestAssured.baseURI = baseUrl;

        // Фильтр для записи шагов/запросов/ответов в Allure
        RestAssured.filters(new AllureRestAssured());

        // Логировать запрос/ответ только если упал ассерт — удобно для отладки
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Куда Allure будет складывать результаты (mvn allure:serve их подхватит)
        System.setProperty("allure.results.directory", "target/allure-results");
    }
}