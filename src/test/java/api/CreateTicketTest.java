package api;

import io.restassured.RestAssured;
import model.Status;
import model.Ticket;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/** Создание и проверка тикета */
public class CreateTicketTest extends BaseTest {

    @Test
    public void createTicketTest() {
        // todo: создать тикет и проверить, что он находится в системе
        Ticket ticket = createTicket(buildNewTicket(Status.OPEN, 3));
        Assert.assertNotNull(ticket.getId());
        getTicket(1);
    }

    @Test()
    protected Ticket getTicket(int id) {
        // todo: отправить HTTP запрос на получение тикета по его id

        System.out.println("Token"+login());
        Ticket ticket =  given()
                .spec(RestAssured.requestSpecification)
                .header("Authorization", "Token "+login().getToken())
                .pathParam("id", id)
                .when()
                .get("/api/tickets/{id}")
                .then()
                .statusCode(200)
                .extract()
                .as(Ticket.class);
        Assert.assertNotNull(ticket);
        return ticket;
    }
}
