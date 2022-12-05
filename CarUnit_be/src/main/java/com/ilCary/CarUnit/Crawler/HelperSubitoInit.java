package com.ilCary.CarUnit.Crawler;


import com.ilCary.CarUnit.models.Make;
import com.ilCary.CarUnit.models.Modello;
import com.ilCary.CarUnit.services.MakeService;
import com.ilCary.CarUnit.services.ModelloService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class HelperSubitoInit implements CommandLineRunner {
//questa classe serve a prendere i modelli di tutte le auto e salvarli sul database
    @Autowired
    MakeService makeService;

    @Autowired
    ModelloService modelloService;

    // ("//*[contains(@class, 'nomeclasse')]")

    String[] BRANDS = {"abarth", "ac", "acm", "aiways", "aixam", "alfa romeo", "alpina-bmw", "alpine", "amg", "apal"
            , "ariel", "aro", "asia motors", "aston martin", "austin rover", "autobianchi", "auverland", "bellier",
            "bentley", "bertone", "biagini", "bmw", "boxel", "bugatti", "buick", "cadillac", "carletti", "casalini",
            "caterham", "chatenet", "chevrolet", "chrysler", "citroen", "citycar", "cmc (carletti)", "corvette",
            "cupra", "dacia", "daewoo", "daihatsu", "daimler", "dallara", "de la chapelle", "de tomaso", "dfsk",
            "dodge", "donkervoort", "dr", "ds", "ducati energia", "effedi", "eli", "emc", "epocar", "evo", "feab",
            "ferrari", "fiat", "fisker", "ford", "fso", "gem", "ginetta", "giotti victoria", "giottiline", "goupil",
            "great wall motor", "grecav", "green company", "haval", "honda", "hummer", "hyundai", "iato", "ineos",
            "infiniti", "innocenti", "iso", "isuzu", "italcar", "iveco", "jaguar", "jdm", "jeep", "kia", "lada",
            "lamborghini", "lancia", "land rover", "lexus", "ligier", "lotus", "luaz (volin)", "lynk&co", "mahindra"
            , "marcos", "martin motors", "maruti", "maserati", "maybach", "mazda", "mazzanti", "mazzieri", "mclaren"
            , "mega", "melex", "mercedes", "meta", "mg", "mia electric", "micro vett", "microcar", "middlebridge",
            "militem", "minauto", "mini", "mitsubishi", "moke", "moretti", "morgan", "mpm motors", "mustang",
            "nanocar", "nissan", "nissan spagna", "noble", "oltcit", "omai", "opel", "oto melara", "p.g.o.",
            "pagani", "panther", "peugeot", "piaggio", "polestar", "pontiac", "porsche", "puma italia", "qvale",
            "rayton fissore", "regis", "renault", "rolls royce", "romeo ferraris", "saab", "saleen", "santana",
            "savel-erad", "seat", "seca", "secma", "seres", "shuanghuan", "skoda", "smart", "ssangyong", "start lab"
            , "suzuki", "talbot", "tasso", "tata", "tazzari ev", "tesla", "today sunshine", "torpedo", "town life",
            "toyota", "tvr", "uaz", "umm", "valentini", "venturi", "volga", "volkswagen", "volkswagen messico",
            "volvo", "xev", "xindayang", "yugo", "zaz", "zd"};


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


    public void test() throws InterruptedException {
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


        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[5]/div[2]/div/div/div")).click();
//        Thread.sleep(500);
//        WebElement Marca = driver.findElement(By.xpath("//*[contains(text(),'" + subitoMercedes.getMarca().toUpperCase() + "')]"));
//        Marca.click();

        //pagina iniziale che mi prende la lista dei marchi auto
        String initialPage = driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[5]/div[2]/div/div/ul")).getText().toLowerCase();
        System.out.println(initialPage);

        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[5]/div[2]/div/div/div")).click();
        boolean go = false;
        // Scanner scanner = new Scanner(initialPage);

        //  while (scanner.hasNextLine())
        for (String make : BRANDS) {
            // String make = scanner.nextLine();
            if (make.equals("ds"))
                continue;
            //facciamo partire il loop solo dopo la riga "tutti"
//            if (make.equals("tutti")) {
//                System.out.println("questo ciclo viene saltato perchè line è uguale a " + make);
//                go = true;
//                continue;
//            }

            // process the line (MARCHIO)
            // Thread.sleep(500);

            System.out.println("make= " + make);

            //se ancora non è arrivato la riga tutti vai alla prossima
//            if (!go) {
//                System.out.println("salta la riga perche il marchio è ancora= " + make);
//                continue;
//            }
            if (make.equals("altro")) {
                System.out.println("il ciclo è finito perchè siamo arrivati alla line= " + make);
                break;
            }
            System.out.println("inizia il ciclo perchè il marchio è= " + make);
            //mi selezioni il marchio
            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[5]/div[2]/div/div/div")).click();
            Thread.sleep(1000);

//            if (make.toUpperCase().equals("BIRO'")) {
//                WebElement Marca = driver.findElement(By.xpath("//*[@id=\"/car/brand__option--58\"]"));
//                Thread.sleep(2000);
//                Marca.click();
//            } else {
            WebElement Marca = driver.findElement(By.xpath("//*[contains(text(),'" + make.toUpperCase() + "')]"));
            Thread.sleep(2000);
            Marca.click();
            //     }


            Make m = Make.builder().nome(make.toUpperCase()).build();
            m = makeService.save(m); // salvo direttamente anche nella variabile
            Thread.sleep(500);
            //adesso per ogni marchio prendiamo ogni modello e lo salviamo
            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[6]/div[2]/div/div")).click();//apro l'input modelli e poi lo elaboro
            String modelliRaws = driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[6]/div[2]/div/div/ul")).getText().toLowerCase();
            System.out.println(modelliRaws);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[6]/div[2]/div/div")).click();//chiudo l'input modelli
            Scanner scanner2 = new Scanner(modelliRaws);
            Set<Modello> modelloAttuali = new HashSet<>();
            while (scanner2.hasNextLine()) {
                String modello = scanner2.nextLine();
                System.out.println("modello= " + modello);

                Modello mod = Modello.builder().name(modello).make(m).build();
                modelloAttuali.add(mod);
            }
            modelloService.saveAll(modelloAttuali);
            scanner2.close();


        }
        // scanner.close();


    }

    public void acceptCookies(WebDriver driver) {
// usare il contains nel xpath per selezionare l'input
        if (driver.findElement(By.xpath("//*[contains(text(),'Ok')]")).isDisplayed())
            driver.findElement(By.xpath("//*[contains(text(),'Ok')]")).click();

        if (driver.findElement(By.xpath("//*[contains(text(),'Accetto')]")).isDisplayed())
            driver.findElement(By.xpath("//*[contains(text(),'Accetto')]")).click();

    }

    public String valueIndex(String value, String[] arr) {
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                k = i;
                break;
            }
        }
        return Integer.toString(k);
    }
}
