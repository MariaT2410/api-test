package pages;

import elements.MainMenu;
import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

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
    public void createTicket(Ticket ticket) {
        ////CreateTicketPage
        try {


            setInputProblem(ticket.getTitle());
            // заполнить остальные поля формы
            setInputQueue(ticket.getQueue());
        }catch (NullPointerException e){}
            setDue(ticket.getDue_date());
            setInputDescription(ticket.getDescription());
            setInputPriority(ticket.getPriority());
            setInputEmail(ticket.getSubmitter_email());

            clickOnSubmitButton();


    }

    @Step("Ввести имя проблемы: {text}")
    private void setInputProblem(String text) {
        //try {
        inputProblem.sendKeys(text);
        //}catch (NullPointerException e){}
    }
    @Step("Ввести очередь: {queue}")
    private void setInputQueue(Integer queue) {
       // try {
            new Select(selectQueue).selectByValue(String.valueOf(queue));
       // }catch (NullPointerException e){}
    }
    @Step("Ввести описание: {description}")
    private void setInputDescription(String description) {
        //try {
        inputDescription.sendKeys(description);
       // }catch (NullPointerException e){}
    }
    @Step("Ввести приоритет: {priority}")
    private void setInputPriority(Integer priority) {
        //try {
        new Select(inputPriority).selectByValue(String.valueOf(priority));
        //}catch (NullPointerException e){}
    }
    @Step("Ввести email: {email}")
    private void setInputEmail(String text) {
       // try {
        inputSubmitter_email.sendKeys(text);
        //}catch (NullPointerException e){}
    }

    public void setKbitem(int number) {
        inputAttachment.sendKeys(String.valueOf(number));
    }

    public void setAssigned_to(String text){
        id_assigned_to.sendKeys(text);
    }

    public void setDue(String text) {
        //try {

        inputDue_date.click();
        List<WebElement> call = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr/td/a"));
        for( WebElement a : call){
            if(a.getText().equals(text)){
                a.click();
            }
        }
        //}catch (NullPointerException e){}
    }

    @Step("Нажать на кнопку создания тикета")
    private void clickOnSubmitButton() {
        try {

            submitTicketButton.click();
        }catch (NullPointerException e){}
    }
}
