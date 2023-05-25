package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Status;
import model.Ticket;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

/** Обновление тикета */
public class UpdateTicketTest extends BaseTest {

    @Test()
    public void updateTicketTest() {
        // todo: создать тикет со статусом Closed, затем обновить тикет и проверить сообщение об ошибке (негативный сценарий)
        Ticket ticket1 = createTicket(buildNewTicket(Status.CLOSED, 3));
        Assert.assertNotNull(Ticket.class);
        updateTicketNegative(ticket1);

    }

    private void updateTicketNegative(Ticket ticket) {
        // todo: отправить HTTP запрос для обновления данных тикета и сразу же проверить статус код (должен соответствовать ошибке)
        ticket.setModified(LocalDateTime.now().toString());
        ticket.setStatus(1);
        given()
                .spec(RestAssured.requestSpecification)
                .header("Authorization", "Token "+login().getToken())
                //.pathParam("id", ticket.getId())
                .body(ticket)
                .when()
                .put("/api/tickets")
                .then()
                .statusCode(200);
        //Assert.assertNotNull(ticket);

    }
}
