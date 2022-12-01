package com.ilCary.CarUnit.Crawler;

import com.ilCary.CarUnit.models.Search;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class CrawlerSubito implements CommandLineRunner {

    String[] fasciaKmDaSubito = {"Km 0", "0", "5.000", "10.000", "15.000", "20.000", "25.000", "30.000", "35.000", "40.000", "45.000", "50.000", "55.000", "60.000", "65.000", "70.000", "75.000", "80.000", "85.000", "90.000", "95.000", "100.000", "110.000", "120.000", "130.000", "140.000", "150.000", "160.000", "170.000", "180.000", "190.000", "200.000", "250.000", "300.000", "350.000", "400.000", "450.000", "500.000"};

    String[] fasciaKmASubito = {"km 0", "4.999", "9.999", "14.999", "19.999", "24.999", "29.999", "34.999", "39.999", "44.999", "49.999", "54.999", "59.999", "64.999", "69.999", "74.999", "79.999", "84.999", "89.999", "94.999", "99.999", "109.999", "119.999", "129.999", "139.999", "149.999", "159.999", "169.999", "179.999", "189.999", "199.999", "249.999", "299.999", "349.999", "399.999", "449.999", "499.999"};

    String[] fasciaPrezziSubito = {"500", "1.000", "2.000", "3.000", "4.000", "5.000", "6.000", "7.000", "8.000", "9.000", "10.000", "11.000", "12.000", "13.000", "14.000", "15.000", "16.000", "17.000", "18.000", "19.000", "20.000", "21.000", "22.000", "23.000", "24.000", "25.000", "26.000", "27.000", "28.000", "29.000", "30.000", "35.000", "40.000", "45.000", "50.000", "60.000", "70.000", "80.000", "90.000", "120.000", "150.000", "250.000", "500.000", "1.000.000", "2.500.000",};

    String[] tipologiaAuto ={"Utilitaria","Berlina","Station Wagon","Monovolume","SUV/Fuoristrada","Cabrio","Coupè","City Car", "Altro"};

    String[] tipologiaCarburante ={"Benzina", "Diesel", "Gpl", "Elettrica", "Ibrida", "Metano", "Altro"};

    String[] tipologiaColore = {"bianco", "grigio", "marrone", "rosso", "giallo", "verde", "blu"};

    String[] classiDiEmissone = {"euro 6", "euro 5", "euro 4", "euro 3", "euro 2", "euro 1", "pre-euro"};

    final static String url = "https://www.subito.it/annunci-italia/vendita/auto/";


    @Override
    public void run(String... args) throws Exception {

     // test();
    }
//TODO gestire i modelli auto
    public void test() throws InterruptedException {
        // System.setProperty("webdriver.chrome.driver", "src/main/resources/static/asset/browser_drivers/chromedriver.exe");
        //set the driver for chrome browser
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        Search subitoMercedes = Search.builder()
                .tipoDiVeicolo("Usato")
                .inserzionista("Privato")
                .Marca("Mercedes")
                .annoImmatricolazioneDa("2015")
                .annoImmatricolazioneA("2018")
                .prezzoDa("500")
                .prezzoA("20.000")
                .kmDa("20.000")//queste vanno per forza col puntino
                .kmA("99.999")
                .tipologiaAuto("Berlina")
                .carburante("Diesel")
                .cambio("Automatico")
                .porte("3/4")
                .colore("Nero")
                .classeDiEmissone("Euro 6")
                .build();

        //then get the page with the url we want
        driver.get(url);

        WebElement cookies_button = driver.findElement(By.id("didomi-notice-agree-button"));
        cookies_button.click();

        //   acceptCookies( driver);

        //----------------tipo di veicolo----------------

        WebElement Usato = driver.findElement(By.xpath("//*[@id=\"/vehicle_status__option--0\"]"));
        WebElement Km0 = driver.findElement(By.xpath("//*[@id=\"/vehicle_status__option--1\"]"));
        WebElement Nuovo = driver.findElement(By.xpath("//*[@id=\"/vehicle_status__option--2\"]"));

        switch (subitoMercedes.getTipoDiVeicolo()) {
            case "Usato":
                Usato.click();
                break;
            case "Km0":
                Km0.click();
                break;
            case "Nuovo":
                Nuovo.click();
                break;
            default:
                System.out.println("nessun tipo di veicolo selezionato");
        }

        //----------------Inserzionista----------------

        WebElement Privato = driver.findElement(By.xpath("//p[contains(text(),'Privato')]"));
        WebElement Azienda = driver.findElement(By.xpath("//p[contains(text(),'Azienda')]"));
        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[4]/div[2]/div/div/div")).click();//apro la ul

        Thread.sleep(1000);// aspetto che sia visibile

        switch (subitoMercedes.getInserzionista()) {
            case "Privato":
                Privato.click();
                break;
            case "Azienda":
                Azienda.click();
                break;
            default:
                System.out.println("nessun Inserzionista selezionato");
        }

        //----------------Marca----------------

        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[5]/div[2]/div/div/div")).click();
        Thread.sleep(500);
        WebElement Marca = driver.findElement(By.xpath("//*[contains(text(),'" + subitoMercedes.getMarca().toUpperCase() + "')]"));
        Marca.click();

        //----------------modello?----------------



        //----------------anno di immatricolazione da a----------------

        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[8]/div[1]/div/div/div")).click();
        Thread.sleep(500);
        // new WebDriverWait(driver, Duration.ofMillis(2000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'"+subitoMercedes.getAnnoImmatricolazioneDa()+"')]"))).click();
        WebElement immatricolazioneDa = driver.findElement(By.xpath("//*[@id=\"/year/min__option--" + annoImmatrcolazione(subitoMercedes.getAnnoImmatricolazioneDa()) + "\"]"));
        immatricolazioneDa.click();
        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[8]/div[2]/div/div/div")).click();
        Thread.sleep(500);
        WebElement immatricolazioneA = driver.findElement(By.xpath("//*[@id=\"/year/max__option--" + annoImmatrcolazione(subitoMercedes.getAnnoImmatricolazioneA()) + "\"]"));
        immatricolazioneA.click();

        Thread.sleep(500);
        //debug
        System.out.println(valueIndex(subitoMercedes.getPrezzoDa(), fasciaPrezziSubito) + " valueIndex(subitoMercedes.getPrezzoDa(), fasciaPrezziSubito)");
        System.out.println(valueIndex(subitoMercedes.getPrezzoA(), fasciaPrezziSubito) + " valueIndex(subitoMercedes.getPrezzoA(), fasciaPrezziSubito)");

        //----------------prezzo da a----------------
        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[9]/div[1]/div/div/div")).click();
        Thread.sleep(500);
        WebElement prezzoDa = driver.findElement(By.xpath("//*[@id=\"/price/min__option--" + valueIndex(subitoMercedes.getPrezzoDa(), fasciaPrezziSubito) + "\"]"));
        prezzoDa.click();

        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[9]/div[2]/div/div/div")).click();
        Thread.sleep(500);
        WebElement prezzoA = driver.findElement(By.xpath("//*[@id=\"/price/max__option--" + valueIndex(subitoMercedes.getPrezzoA(), fasciaPrezziSubito) + "\"]"));
        prezzoA.click();
        Thread.sleep(500);

        //debug
        System.out.println(valueIndex(subitoMercedes.getKmA(), fasciaKmDaSubito) + " valueIndex(subitoMercedes.getKmA(), fasciaKmSubito)");
        System.out.println(valueIndex(subitoMercedes.getKmDa(), fasciaKmASubito) + " valueIndex(subitoMercedes.getKmDa(), fasciaKmSubito)");

        //----------------km da a----------------
        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[10]/div[1]/div/div/div")).click();
        Thread.sleep(500);
        WebElement kmDa = driver.findElement(By.xpath("//*[@id=\"/mileage/min__option--" + valueIndex(subitoMercedes.getKmDa(), fasciaKmDaSubito) + "\"]"));
        kmDa.click();

        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[10]/div[2]/div/div/div")).click();
        Thread.sleep(500);
        WebElement kmA = driver.findElement(By.xpath("//*[@id=\"/mileage/max__option--" + valueIndex(subitoMercedes.getKmA(), fasciaKmASubito) + "\"]"));
        kmA.click();

        //----------------tipologia----------------

        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[11]/div[2]/div/div/div")).click();
        Thread.sleep(500);
        WebElement tipologia = driver.findElement(By.xpath("//*[@id=\"/car_type__option--"+valueIndex(subitoMercedes.getTipologiaAuto(), tipologiaAuto)+"\"]"));
        tipologia.click();//*[@id="/car_type__option--0"]

        //debug
        System.out.println(valueIndex(subitoMercedes.getCarburante(), tipologiaCarburante ) + " valueIndex(subitoMercedes.getCarburante(), tipologiaCarburante )");
        //----------------carburante----------------
//*[@id="filters-container"]/div/div[12]/div[2]/div/div
        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[12]/div[2]/div/div/div")).click();
        Thread.sleep(500);
        WebElement carburante = driver.findElement(By.xpath("//*[@id=\"/fuel__option--"+valueIndex(subitoMercedes.getCarburante(), tipologiaCarburante)+"\"]"));
        carburante.click();//*[@id="/fuel__option--0"]

        //----------------cambio----------------//*[@id="/gearbox__option--0"]

        WebElement Manuale = driver.findElement(By.xpath("//*[@id=\"/gearbox__option--0\"]"));
        WebElement Automatico = driver.findElement(By.xpath("//*[@id=\"/gearbox__option--1\"]"));
        WebElement Sequenziale = driver.findElement(By.xpath("//*[@id=\"/gearbox__option--2\"]"));
        WebElement Altro = driver.findElement(By.xpath("//*[@id=\"/gearbox__option--3\"]"));

        switch (subitoMercedes.getCambio()) {
            case "Manuale":
                Manuale.click();
                break;
            case "Automatico":
                Automatico.click();
                break;
            case "Sequenziale":
                Sequenziale.click();
                break;
            case "Altro":
                Altro.click();
                break;
            default:
                System.out.println("nessun tipo di cambio selezionato");
        }

        //----------------porte----------------

        WebElement due_tre = driver.findElement(By.xpath("//*[@id=\"/doors__option--0\"]"));
        WebElement quattro_cinque = driver.findElement(By.xpath("//*[@id=\"/doors__option--1\"]"));
        WebElement sei_sette = driver.findElement(By.xpath("//*[@id=\"/doors__option--2\"]"));

        switch (subitoMercedes.getPorte()) {
            case "2/3":
                due_tre.click();
                break;
            case "4/5":
                quattro_cinque.click();
                break;
            case "6/7":
                sei_sette.click();
                break;
            default:
                System.out.println("nessuna quantita di porte selezionato");
        }

        //----------------colore----------------
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[15]/div[2]/div/div/div")).click();
        Thread.sleep(500);
        WebElement colore = driver.findElement(By.xpath("//*[@id=\"/color__option--"+valueIndex(subitoMercedes.getColore(), tipologiaColore )+"\"]"));
        colore.click();//*[@id="/color__option--0"]

        //----------------classe di emissione----------------

        driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[16]/div[2]/div/div/div")).click();
        Thread.sleep(500);
        WebElement classeDiEmissione = driver.findElement(By.xpath("//*[@id=\"/pollution__option--"+valueIndex(subitoMercedes.getClasseDiEmissone(), classiDiEmissone )+"\"]"));
        classeDiEmissione.click();//*[@id="/pollution__option--2"]

    }

//    public void acceptCookies(WebDriver driver) {
//// usare il contains nel xpath per selezionare l'input
//        if (driver.findElement(By.xpath("//*[contains(text(),'Ok')]")).isDisplayed())
//            driver.findElement(By.xpath("//*[contains(text(),'Ok')]")).click();
//
//        if (driver.findElement(By.xpath("//*[contains(text(),'Accetto')]")).isDisplayed())
//            driver.findElement(By.xpath("//*[contains(text(),'Accetto')]")).click();
//
//        if (driver.findElement(By.xpath("//*[contains(text(),'Accetta')]")).isDisplayed())
//            driver.findElement(By.xpath("//*[contains(text(),'Accetta')]")).click();
//
//    }

    public String annoImmatrcolazione(String anno) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int numeroOption = Math.abs(year - Integer.parseInt(anno));
        return Integer.toString(numeroOption);
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
