package com.ilCary.CarUnit.Crawler;

import com.ilCary.CarUnit.models.Make;
import com.ilCary.CarUnit.models.Modello;
import com.ilCary.CarUnit.services.MakeService;
import com.ilCary.CarUnit.services.ModelloService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.Duration;
import java.util.*;

@Component
public class ModelsScraper implements CommandLineRunner {

    @Autowired
    MakeService makeService;

    @Autowired
    ModelloService modelloService;

    // ("//*[contains(@class, 'nomeclasse')]")


    //salvarmi le cordinate del punto e poi dico al driover di fare un click
    final static String url = "https://www.subito.it/annunci-italia/vendita/auto/";


    @Override
    public void run(String... args) throws Exception {

       // test();
    }

    public WebElement waitElementToBeVisible(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
        return element;
    }


    public void test() throws InterruptedException, IOException {
        // System.setProperty("webdriver.chrome.driver", "src/main/resources/static/asset/browser_drivers/chromedriver.exe");
        //set the driver for chrome browser
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
//*[@id="filters-container"]/div/div[11]/div[2]/div/div/div
        //then get the page with the url we want
        driver.get(url);
        WebElement cookies_button = driver.findElement(By.id("didomi-notice-agree-button"));
        cookies_button.click();
        Thread.sleep(2000);


        // metodo che mi permette di aspettare il corretto caricamento della pagina
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(driver2 -> ((JavascriptExecutor) driver2).executeScript("return document.readyState").equals("complete"));



//        Thread.sleep(500);
//        WebElement Marca = driver.findElement(By.xpath("//*[contains(text(),'" + subitoMercedes.getMarca().toUpperCase() + "')]"));
//        Marca.click();

        //pagina iniziale che


        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[5]/div[2]/div/div/div")).click();
        Thread.sleep(2000);

        List<String> modelliAbarth = new ArrayList<String>();

        driver.findElement(By.xpath("//*[@id=\"/car/brand__option--36\"]")).click();//*[@id="filters-container"]/div/div[6]/div[2]/div/div/ul
        Thread.sleep(2000);
        String initialPage = driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[6]/div[2]/div/div/ul")).getText().toLowerCase();
        System.out.println("abarth? "+initialPage);
//        Scanner scanner = new Scanner(initialPage);
//        Scanner scanneMarchi = new Scanner(initialPage);


    }

}

