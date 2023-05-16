package api;

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
        getTicket(ticket.getId());
    }

    @Test()
    protected Ticket getTicket(int id) {
        // todo: отправить HTTP запрос на получение тикета по его id
        Ticket ticket =  given()
                .pathParam("id", id)
                .when()
                    .get("/api/tickets/{id}")
                .then()
                .statusCode(200)
                .extract().body()
                .as(Ticket.class);
        Assert.assertNotNull(ticket);
        return ticket;
    }
}
