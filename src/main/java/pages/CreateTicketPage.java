package pages;

import elements.MainMenu;
import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Страница создания тикета */
public class CreateTicketPage extends HelpdeskBasePage {

    //  добавить элементам локтаоры через @FindBy СДЕЛАНО
    @FindBy(xpath = "//*[@id ='id_queue']")
    private WebElement selectQueue;

    @FindBy(xpath = "//*[@id ='id_title']")
    private WebElement inputProblem;

    @FindBy(xpath = "//*[@id ='id_body']")
    private WebElement description;

    @FindBy(xpath = "//*[@id ='id_priority']")
    private WebElement priority;

    //  добавить остальные поля формы

    @FindBy(xpath = "//*[@id ='id_due_date']")
    private WebElement due_date;

    @FindBy(xpath = "//*[@id ='id_attachment']")
    private WebElement attachment;

    @FindBy(xpath = "//*[@id ='id_submitter_email']")
    private WebElement submitter_email;

    @FindBy(xpath = "//*[@id ='id_assigned_to']")
    private WebElement id_assigned_to;

    @FindBy(xpath = "//*[@id ='content-wrapper']/div/div/div/div[2]/form/button")
    private WebElement submitTicketButton;

    // todo: проинициализировать элементы???


    @Step("Создать тикет")
    public CreateTicketPage createTicket(Ticket ticket) {

        setInputProblem(ticket.getTitle());
        setInputProblem();
        // заполнить остальные поля формы
        ticket.setDue_date();
        ticket.setDescription();
        clickOnSubmitButton();
        return this;
    }

    @Step("Ввести имя проблемы: {text}")
    public void setInputProblem(String text) {
        inputProblem.sendKeys(text);
    }

    @Step("Нажать на кнопку создания тикета")
    public void clickOnSubmitButton() {
        submitTicketButton.click();
    }
}
