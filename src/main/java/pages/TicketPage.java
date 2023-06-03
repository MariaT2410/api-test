package pages;

import io.qameta.allure.Step;
import models.Dictionaries;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/** Страница отдельного тикета (авторизированный пользователь) */
public class TicketPage extends HelpdeskBasePage {

    // поиск элемента через xpath
    private WebElement title = driver.findElement(By.xpath("//h3"));

    private WebElement queue = driver.findElement(By.xpath("//h3/followinh-sibling::text()"));

    private WebElement due_date = driver.findElement(By.xpath("//th[text()='Due Date']/following-sibling::td[1]"));

    private WebElement priority =  driver.findElement(By.xpath("//th[text()='Priority']/following-sibling::td[1]"));

    private WebElement dependencies =  driver.findElement(By.xpath("//th[text()='Dependencies']/following-sibling::td[1]"));

    private WebElement submitted_on =  driver.findElement(By.xpath("//th[text()='Submitted On']/following-sibling::td[1]"));

    private WebElement email =  driver.findElement(By.xpath("//th[text()='Submitter E-Mail']/following-sibling::td[1]"));

    private  WebElement assigned_to =  driver.findElement(By.xpath("//th[text()='Assigned To']/following-sibling::td[1]"));

    private WebElement copies_to =  driver.findElement(By.xpath("//th[text()='Copies To']/following-sibling::td[1]"));

    private  WebElement total_time_spend =  driver.findElement(By.xpath("//th[text()='Total time spent']/following-sibling::td[1]"));

    private WebElement description =  driver.findElement(By.xpath("//*[@id='ticket-description']/p"));


    /* Верстка страницы может измениться, поэтому для таблиц вместо индексов строк и столбцов лучше использовать
       более универсальные локаторы, например поиск по тексту + parent, following-sibling и другие.

       Текст тоже может измениться, но в этом случае элемент не будет найден и тест упадет,
       а ошибку можно будет легко локализовать и исправить.
       В случае изменений ячеек таблицы, локатор будет продолжать работать, но будет указывать на другой элемент,
       поведение теста при этом изменится непредсказуемым образом и ошибку будет сложно найти. */
    private WebElement dueDate = driver.findElement(By.xpath("//th[text()='Due Date']/following-sibling::td[1]"));

    // проинициализировать элементы через driver.findElement СДЕЛАНО
    //private WebElement title;
    //private WebElement queue;
    //private WebElement email;
    //private WebElement priority;
    //private WebElement description;

    @Step("Проверить значение полей на странице тикета")
    public void checkTicket(Ticket ticket) {
        // todo: добавить реализацию метода
        String  title_ = title.getText().substring(title.getText().indexOf(" ")+1).replace(" [Open]", "");
        String description_ = description.getText();
        String email_ = email.getText();
        Assert.assertTrue(title.getText().contains(ticket.getTitle()), "Не соответствует заголовок");
        Assert.assertTrue(description.getText().contains(ticket.getDescription()), "Не соответствует описание");
        Assert.assertTrue(email.getText().contains(ticket.getSubmitter_email()), "Не соответствует email");

        Assert.assertEquals(Dictionaries.getPriority(ticket.getPriority()).contains(priority.getText().replace("Priority", "")), "не соответствует приоритет");
        Assert.assertEquals(Dictionaries.getQueue(ticket.getQueue()).contains(queue.getText()), "Не соответствует очередь");
        //Assert.assertTrue(queue.getText().contains(ticket.getQueue().toString()));

    }
}
