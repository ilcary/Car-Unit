package com.ilCary.CarUnit.Crawler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class CrawlerSubitoModels {//TODO BISOGNA CHIUDERE LA PAGINA DEL DRIVER E MAGARI NON FARLA APRIRE PER NIENTE
    //come identificatore univico posso usare il link dell'annuncio

    public static final String url = "https://www.subito.it/annunci-italia/vendita/auto/";

    public List<String> getAllModelsOf(String make) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        //then get the page with the url we want
        driver.get(url);

        // metodo che mi permette di aspettare il corretto caricamento della pagina
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        //wait.until(driver2 -> ((JavascriptExecutor) driver2).executeScript("return document.readyState").equals("complete"));

        //accept cookies_button
        WebElement cookies_button = driver.findElement(By.id("didomi-notice-agree-button"));
        cookies_button.click();
        Thread.sleep(300);

        //seleziona la marca dalla quale prendere i modelli
        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[5]/div[2]/div/div/div")).click();
        Thread.sleep(300);
        WebElement Marca = driver.findElement(By.xpath("//*[contains(text(),'" + make.toUpperCase() + "')]"));
        Marca.click();
        Thread.sleep(300);

        //apro l'input modelli e poi lo elaboro
        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[6]/div[2]/div/div")).click();
        Thread.sleep(300);
        String modelliRaws = driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[6]/div[2]/div/div/ul")).getText();
        System.out.println("situazione "+modelliRaws);

        //ciclo i modelli del marchio dato e li salvo in un set
        Scanner scanner2 = new Scanner(modelliRaws);
        List<String> modelliAttuali = new ArrayList<>();
        while (scanner2.hasNextLine()) {
            String modello = scanner2.nextLine();
            //System.out.println("modello= " + modello);
            modelliAttuali.add(modello);
        }
        scanner2.close();
        System.out.println(modelliAttuali);
        return modelliAttuali;
    }
}
