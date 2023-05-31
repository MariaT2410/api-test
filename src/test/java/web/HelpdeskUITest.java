package web;

import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;
    private Ticket ticket;

    @BeforeClass
    public void setup() throws IOException {
        loadProperties();
        setupDriver();
    }

    @Step("Загрузить конфигурацилнные файлы")
    private void loadProperties() throws IOException {
        // Читаем конфигурационные файлы в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));

    }

    @Step("Создать экземпляр драйвера")
    private void setupDriver() {
        // Создание экземпляра драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //System.setProperty("webdriver.http.factory", "jdk-http-client");
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        AbstractPage.setDriver(driver);
    }

    @Test
    public void createTicketTest() {
        // todo: шаги тест-кейса

        MainPage mainPage = new MainPage();
        // Предусловие: открыта главная страница сайта https://at-sandbox.workbench.lanit.ru/
        driver.get("https://at-sandbox.workbench.lanit.ru/");
        CreateTicketPage newTicket = PageFactory.initElements(driver,CreateTicketPage.class);

        //NewTicket в гл меню
        mainPage.mainMenu().clickOnNewTicketButton();
        ticket = buildNewTicket();
        //Создать Ticket
        newTicket.createTicket(ticket);


        //Log in
        LoginPage loginPage = new LoginPage();
        mainPage.mainMenu().clickOnLogInButton();
        //Авторизация
        loginPage.login(System.getProperty("user"), System.getProperty("password"));


        // Найти созданный Ticket
        mainPage.mainMenu().searchTicket(ticket);
        mainPage.mainMenu().clickOnGoButton();

    }

    private Ticket buildNewTicket() {
        Ticket ticket = new Ticket();
        // todo: заполнить поля тикета
        ticket.setTitle("title");
        ticket.setDescription("test");
        ticket.setDue_date("31.05.2023");
        ticket.setPriority(2);
        ticket.setQueue(1);
        ticket.setSubmitter_email("aaa@mail.ru");
        return ticket;
    }

    @AfterTest
    public void close() {
        if (driver != null) {
            // Закрываем одно текущее окно браузера
            driver.close();
            // Закрываем все открытые окна браузера, завершаем работу браузера, освобождаем ресурсы
            driver.quit();
        }
    }
}
