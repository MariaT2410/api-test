package pages;

import elements.MainMenu;
import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/** Страница создания тикета */
public class CreateTicketPage extends HelpdeskBasePage {

    //  добавить элементам локтаоры через @FindBy СДЕЛАНО
    @FindBy(xpath = "//*[@id ='id_queue']")
    private WebElement selectQueue;

    @FindBy(xpath = "//*[@id ='id_title']")
    private WebElement inputProblem;

    @FindBy(xpath = "//*[@id ='id_body']")
    private WebElement inputDescription;

    @FindBy(xpath = "//*[@id ='id_priority']")
    private WebElement inputPriority;

    //  добавить остальные поля формы

    @FindBy(xpath = "//*[@id ='id_due_date']")
    private WebElement inputDue_date;

    @FindBy(xpath = "//*[@id ='id_attachment']")
    private WebElement inputAttachment;

    @FindBy(xpath = "//*[@id ='id_submitter_email']")
    private WebElement inputSubmitter_email;

    @FindBy(xpath = "//*[@id ='id_assigned_to']")
    private WebElement id_assigned_to;

    @FindBy(xpath = "//*[@id ='content-wrapper']/div/div/div/div[2]/form/button")
    private WebElement submitTicketButton;

    //  проинициализировать элементы???


    @Step("Создать тикет")
    public CreateTicketPage createTicket(Ticket ticket) {

        setInputProblem(ticket.getTitle());
        // заполнить остальные поля формы
        setInputQueue(ticket.getQueue());
        setInputDescription(ticket.getDescription());
        setInputPriority(ticket.getPriority());
        setInputEmail(ticket.getSubmitter_email());

        clickOnSubmitButton();
        return this;
    }

    @Step("Ввести имя проблемы: {text}")
    private void setInputProblem(String text) {
        inputProblem.sendKeys(text);
    }
    @Step("Ввести очередь: {queue}")
    private void setInputQueue(Integer queue) {
        new Select(selectQueue).selectByValue(String.valueOf(queue));
    }
    @Step("Ввести очередь: {description}")
    private void setInputDescription(String description) {
        inputDescription.sendKeys(description);
    }
    @Step("Ввести очередь: {priority}")
    private void setInputPriority(Integer priority) {
        new Select(inputPriority).selectByValue(String.valueOf(priority));
    }
    @Step("Ввести имя проблемы: {text}")
    private void setInputEmail(String text) {
        inputSubmitter_email.sendKeys(text);
    }


    @Step("Нажать на кнопку создания тикета")
    private void clickOnSubmitButton() {
        submitTicketButton.click();
    }
}
