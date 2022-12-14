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

    String[] BRANDS = {"Abarth","Ac","Acm","Aiways","Aixam","Alfa romeo","Alpina-bmw","Alpine","Amg","Apal","Ariel","Aro",
            "Asia motors","Aston martin","Austin rover","Autobianchi","Auverland","Bellier","Bentley","Bertone","Biagini",
            "Bmw","Boxel","Bugatti","Buick","Cadillac","Carletti","Casalini","Caterham","Chatenet","Chevrolet","Chrysler",
            "Citroen","Citycar","Cmc (carletti)","Corvette","Cupra","Dacia","Daewoo","Daihatsu","Daimler","Dallara","De la chapelle",
            "De tomaso","Dfsk","Dodge","Donkervoort","Dr","Ds","Ducati energia","Effedi","Eli","Emc","Epocar","Evo","Feab","Ferrari",
            "Fiat","Fisker","Ford","Fso","Gem","Ginetta","Giotti victoria","Giottiline","Goupil","Great wall motor","Grecav","Green company",
            "Haval","Honda","Hummer","Hyundai","Iato","Ineos","Infiniti","Innocenti","Iso","Isuzu","Italcar","Iveco","Jaguar","Jdm","Jeep",
            "Kia","Lada","Lamborghini","Lancia","Land rover","Lexus","Ligier","Lotus","Luaz (volin)","Lynk&co","Mahindra","Marcos",
            "Martin motors","Maruti","Maserati","Maybach","Mazda","Mazzanti","Mazzieri","Mclaren","Mega","Melex","Mercedes","Meta",
            "Mg","Mia electric","Micro vett","Microcar","Middlebridge","Militem","Minauto","Mini","Mitsubishi","Moke","Moretti",
            "Morgan","Mpm motors","Mustang","Nanocar","Nissan","Nissan spagna","Noble","Oltcit","Omai","Opel","Oto melara","P.g.o.",
            "Pagani","Panther","Peugeot","Piaggio","Polestar","Pontiac","Porsche","Puma italia","Qvale","Rayton fissore","Regis","Renault",
            "Rolls royce","Romeo ferraris","Saab","Saleen","Santana","Savel-erad","Seat","Seca","Secma","Seres","Shuanghuan","Skoda","Smart",
            "Ssangyong","Start lab","Suzuki","Talbot","Tasso","Tata","Tazzari ev","Tesla","Today sunshine","Torpedo","Town life","Toyota","Tvr",
            "Uaz","Umm","Valentini","Venturi","Volga","Volkswagen","Volkswagen messico","Volvo","Xev","Xindayang","Yugo","Zaz","Zd"};


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
