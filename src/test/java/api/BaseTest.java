package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AuthToken;
import model.Status;
import model.Ticket;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


//import org.json.JSONObject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

import static io.restassured.RestAssured.given;

/** Абстрактный класс, содержащий общие для всех тестов методы */
public abstract class BaseTest {

    @BeforeClass
    public void prepare() throws IOException {
        // todo: загрузить в системные переменные "base.uri" из "config.properties"
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));

        String baseUri = System.getProperty("base.uri");
        if (baseUri == null || baseUri.isEmpty()) {
            throw new RuntimeException("В файле \"config.properties\" отсутствует значение \"base.uri\"");
        }

        // todo: подготовить глобальные преднастройки для запросов
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://at-sandbox.workbench.lanit.ru") // задаём базовый адрес каждого ресурса
                .addHeader("base.uri", System.getProperty("base.uri"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        //Здесь задаётся фильтр, позволяющий выводить содержание ответа
        RestAssured.filters(new ResponseLoggingFilter());
    }


    @Test()
    protected AuthToken login() {
        // todo: отправить запрос на получения токена, используя учетные данные из "config.properties"
        AuthToken authToken1 = new AuthToken();
        authToken1.setUsername("admin");
        authToken1.setPassword("adminat");

        Response response = given()
                .spec(RestAssured.requestSpecification)
                .auth().preemptive().basic("admin", "adminat")
                .body(authToken1)
                .when().post("/api/login")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("OAuth Response - " + response.getBody().asString());
        Assert.assertNotNull(response);
        authToken1.setToken(response.getBody().asString());

        String jsonString = new com.google.gson.Gson().toJson(authToken1);
        System.out.println(authToken1.getToken());
        return authToken1;
    }

    @Test()
    protected Ticket buildNewTicket(Status status, int priority) {
        // todo: создать объект с тестовыми данными
        Ticket newTicket = new Ticket();
        newTicket.setId(new Random().nextInt(500000));
        newTicket.setStatus(status.getCode());
        newTicket.setPriority(priority);
        newTicket.setTitle("Title"+ newTicket.getId().toString());
        newTicket.setQueue(2);
        newTicket.setCreated(LocalDateTime.now().toString());
        newTicket.setModified(LocalDateTime.now().toString());
        if (login() == null){newTicket.setSubmitter_email("name@mail.ru");}
        System.out.println(login());
        return newTicket;
    }

    @Test()
    protected Ticket createTicket(Ticket ticket) {
        // todo: отправить HTTP запрос для создания тикета
        Ticket ticket1 = given()
                .spec(RestAssured.requestSpecification)
                .body(ticket)
                .when().post("/api/tickets")
                .then().statusCode(201)
                .extract()
                .as(Ticket.class);
        return ticket1;
    }
}
