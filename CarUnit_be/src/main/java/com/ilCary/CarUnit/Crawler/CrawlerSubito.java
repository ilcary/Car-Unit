package com.ilCary.CarUnit.Crawler;

import com.ilCary.CarUnit.models.CarAdv;
import com.ilCary.CarUnit.models.Search;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CrawlerSubito implements CommandLineRunner {

    String[] fasciaKmDaSubito = {"Km 0", "0", "5.000", "10.000", "15.000", "20.000", "25.000", "30.000", "35.000", "40.000", "45.000", "50.000", "55.000", "60.000", "65.000", "70.000", "75.000", "80.000", "85.000", "90.000", "95.000", "100.000", "110.000", "120.000", "130.000", "140.000", "150.000", "160.000", "170.000", "180.000", "190.000", "200.000", "250.000", "300.000", "350.000", "400.000", "450.000", "500.000"};

    String[] fasciaKmASubito = {"km 0", "4.999", "9.999", "14.999", "19.999", "24.999", "29.999", "34.999", "39.999", "44.999", "49.999", "54.999", "59.999", "64.999", "69.999", "74.999", "79.999", "84.999", "89.999", "94.999", "99.999", "109.999", "119.999", "129.999", "139.999", "149.999", "159.999", "169.999", "179.999", "189.999", "199.999", "249.999", "299.999", "349.999", "399.999", "449.999", "499.999"};

    String[] fasciaPrezziSubito = {"500", "1.000", "2.000", "3.000", "4.000", "5.000", "6.000", "7.000", "8.000", "9.000", "10.000", "11.000", "12.000", "13.000", "14.000", "15.000", "16.000", "17.000", "18.000", "19.000", "20.000", "21.000", "22.000", "23.000", "24.000", "25.000", "26.000", "27.000", "28.000", "29.000", "30.000", "35.000", "40.000", "45.000", "50.000", "60.000", "70.000", "80.000", "90.000", "120.000", "150.000", "250.000", "500.000", "1.000.000", "2.500.000",};

    String[] tipologiaAuto = {"Utilitaria", "Berlina", "Station Wagon", "Monovolume", "SUV/Fuoristrada", "Cabrio", "Coupè", "City Car", "Altro"};

    String[] tipologiaCarburante = {"Benzina", "Diesel", "Gpl", "Elettrica", "Ibrida", "Metano", "Altro"};

    String[] tipologiaColore = {"bianco", "grigio", "nero", "marrone", "rosso", "giallo", "verde", "blu"};

    String[] classiDiEmissone = {"euro 6", "euro 5", "euro 4", "euro 3", "euro 2", "euro 1", "pre-euro"};

    final static String url = "https://www.subito.it/annunci-italia/vendita/auto/";


    @Override
    public void run(String... args) throws Exception {

        //findCarBySearch();
    }

    //TODO 
    public List<CarAdv> findCarBySearch(Search searchForm) throws InterruptedException {
        // System.setProperty("webdriver.chrome.driver", "src/main/resources/static/asset/browser_drivers/chromedriver.exe");

//  CLASSE DI TEST
//        Search searchForm = Search.builder()
//                .tipoDiVeicolo("Usato")
//                .inserzionista("Privato")
//                .marca("Mercedes")
//                .modello("Classe A")
//                .annoImmatricolazioneDa("2015")
//                .annoImmatricolazioneA("2018")
//                .prezzoDa("500")
//                .prezzoA("20.000")
//                .kmDa("20.000")//queste vanno per forza col puntino
//                //  .kmA("99.999")
//                .tipologiaAuto("Berlina")
//                .carburante("Diesel")
//                .cambio("Automatico")
//                //.colore("Nero")
//                .classeDiEmissone("Euro 6")
//                .build();

        //set the driver for chrome browser
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        //then get the page with the url we want
        driver.get(url);

        WebElement cookies_button = driver.findElement(By.id("didomi-notice-agree-button"));
        cookies_button.click();

        //   acceptCookies( driver);

        //----------------citta----------------

        if(searchForm.getCitta() != null){

            WebElement citta = driver.findElement(By.xpath("//*[@id=\"/geo\"]"));
            citta.sendKeys(searchForm.getCitta());
           Thread.sleep(500);
            citta.sendKeys(Keys.ARROW_DOWN);
           // Thread.sleep(500);
            citta.sendKeys(Keys.ENTER);
            //Thread.sleep(500);
        }

        //----------------tipo di veicolo----------------

        if (searchForm.getTipoDiVeicolo() != null) {

            WebElement Usato = driver.findElement(By.xpath("//*[@id=\"/vehicle_status__option--0\"]"));
            WebElement Km0 = driver.findElement(By.xpath("//*[@id=\"/vehicle_status__option--1\"]"));
            WebElement Nuovo = driver.findElement(By.xpath("//*[@id=\"/vehicle_status__option--2\"]"));

            switch (searchForm.getTipoDiVeicolo()) {
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
        }

        //----------------Inserzionista----------------

        if (searchForm.getInserzionista() != null) {

            WebElement Privato = driver.findElement(By.xpath("//p[contains(text(),'Privato')]"));
            WebElement Azienda = driver.findElement(By.xpath("//p[contains(text(),'Azienda')]"));
            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[4]/div[2]/div/div/div")).click();//apro la ul

            Thread.sleep(1000);// aspetto che sia visibile

            switch (searchForm.getInserzionista()) {
                case "Privato":
                    Privato.click();
                    break;
                case "Azienda":
                    Azienda.click();
                    break;
                default:
                    System.out.println("nessun Inserzionista selezionato");
            }
        }

        //----------------Marca----------------

        if (searchForm.getMarca() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[5]/div[2]/div/div/div")).click();
            Thread.sleep(500);
            WebElement Marca = driver.findElement(By.xpath("//*[contains(text(),'" + searchForm.getMarca().toUpperCase() + "')]"));
            Marca.click();

        }

        //----------------modello?----------------

        if (searchForm.getModello() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[6]/div[2]/div/div/div")).click();
            Thread.sleep(500);
           // WebElement Marca = driver.findElement(By.xpath("//*[contains(text(),'" + searchForm.getModello() + "')]"));
            WebElement Marca = driver.findElement(By.xpath("//*[text()='"+ searchForm.getModello() +"']"));
            Marca.click();

        }

        //----------------anno di immatricolazione da a----------------

        if (searchForm.getAnnoImmatricolazioneDa() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[8]/div[1]/div/div/div")).click();
            Thread.sleep(500);
            // new WebDriverWait(driver, Duration.ofMillis(2000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'"+searchForm.getAnnoImmatricolazioneDa()+"')]"))).click();
            WebElement immatricolazioneDa = driver.findElement(By.xpath("//*[@id=\"/year/min__option--" + annoImmatrcolazione(searchForm.getAnnoImmatricolazioneDa()) + "\"]"));
            immatricolazioneDa.click();
            Thread.sleep(500);

        }
        if (searchForm.getAnnoImmatricolazioneA() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[8]/div[2]/div/div/div")).click();
            Thread.sleep(500);
            WebElement immatricolazioneA = driver.findElement(By.xpath("//*[@id=\"/year/max__option--" + annoImmatrcolazione(searchForm.getAnnoImmatricolazioneA()) + "\"]"));
            immatricolazioneA.click();
        }
        Thread.sleep(500);
        //debug
        System.out.println(valueIndex(searchForm.getPrezzoDa(), fasciaPrezziSubito) + " valueIndex(searchForm.getPrezzoDa(), fasciaPrezziSubito)");
        System.out.println(valueIndex(searchForm.getPrezzoA(), fasciaPrezziSubito) + " valueIndex(searchForm.getPrezzoA(), fasciaPrezziSubito)");

        //----------------prezzo da a----------------

        if (searchForm.getPrezzoDa() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[9]/div[1]/div/div/div")).click();
            Thread.sleep(500);
            WebElement prezzoDa = driver.findElement(By.xpath("//*[@id=\"/price/min__option--" + valueIndex(searchForm.getPrezzoDa(), fasciaPrezziSubito) + "\"]"));
            prezzoDa.click();
            Thread.sleep(500);

        }
        if (searchForm.getPrezzoA() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[9]/div[2]/div/div/div")).click();
            Thread.sleep(500);
            WebElement prezzoA = driver.findElement(By.xpath("//*[@id=\"/price/max__option--" + valueIndex(searchForm.getPrezzoA(), fasciaPrezziSubito) + "\"]"));
            prezzoA.click();
            Thread.sleep(500);

        }


        //debug
        System.out.println(valueIndex(searchForm.getKmA(), fasciaKmDaSubito) + " valueIndex(searchForm.getKmA(), fasciaKmSubito)");
        System.out.println(valueIndex(searchForm.getKmDa(), fasciaKmASubito) + " valueIndex(searchForm.getKmDa(), fasciaKmSubito)");

        //----------------km da a----------------

        if (searchForm.getKmDa() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[10]/div[1]/div/div/div")).click();
            Thread.sleep(500);
            WebElement kmDa = driver.findElement(By.xpath("//*[@id=\"/mileage/min__option--" + valueIndex(searchForm.getKmDa(), fasciaKmDaSubito) + "\"]"));
            kmDa.click();
            Thread.sleep(500);

        }

        if (searchForm.getKmA() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[10]/div[2]/div/div/div")).click();
            Thread.sleep(500);
            WebElement kmA = driver.findElement(By.xpath("//*[@id=\"/mileage/max__option--" + valueIndex(searchForm.getKmA(), fasciaKmASubito) + "\"]"));
            kmA.click();

        }

        //----------------tipologia----------------

        if (searchForm.getTipologiaAuto() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[11]/div[2]/div/div/div")).click();
            Thread.sleep(500);
            WebElement tipologia = driver.findElement(By.xpath("//*[@id=\"/car_type__option--" + valueIndex(searchForm.getTipologiaAuto(), tipologiaAuto) + "\"]"));
            tipologia.click();//*[@id="/car_type__option--0"]

        }

        //debug
        System.out.println(valueIndex(searchForm.getCarburante(), tipologiaCarburante) + " valueIndex(searchForm.getCarburante(), tipologiaCarburante )");

        //----------------carburante----------------
        //*[@id="filters-container"]/div/div[12]/div[2]/div/div

        if (searchForm.getCarburante() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[12]/div[2]/div/div/div")).click();
            Thread.sleep(500);
            WebElement carburante = driver.findElement(By.xpath("//*[@id=\"/fuel__option--" + valueIndex(searchForm.getCarburante(), tipologiaCarburante) + "\"]"));
            carburante.click();//*[@id="/fuel__option--0"]

        }

        //----------------cambio----------------
        // *[@id="/gearbox__option--0"]

        if (searchForm.getCambio() != null) {

            WebElement Manuale = driver.findElement(By.xpath("//*[@id=\"/gearbox__option--0\"]"));
            WebElement Automatico = driver.findElement(By.xpath("//*[@id=\"/gearbox__option--1\"]"));
            WebElement Sequenziale = driver.findElement(By.xpath("//*[@id=\"/gearbox__option--2\"]"));
            WebElement Altro = driver.findElement(By.xpath("//*[@id=\"/gearbox__option--3\"]"));

            switch (searchForm.getCambio()) {
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
        }

        //----------------porte----------------

        if (searchForm.getPorte() != null) {

            WebElement due_tre = driver.findElement(By.xpath("//*[@id=\"/doors__option--0\"]"));
            WebElement quattro_cinque = driver.findElement(By.xpath("//*[@id=\"/doors__option--1\"]"));
            WebElement sei_sette = driver.findElement(By.xpath("//*[@id=\"/doors__option--2\"]"));

            switch (searchForm.getPorte()) {
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

        }

        //----------------colore----------------

        if (searchForm.getColore() != null) {

            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[16]/div[2]/div/div/div")).click();
            Thread.sleep(500);
            WebElement colore = driver.findElement(By.xpath("//*[@id=\"/color__option--" + valueIndex(searchForm.getColore(), tipologiaColore) + "\"]"));
            colore.click();//*[@id="/color__option--0"]
            Thread.sleep(300);

        }

        //----------------classe di emissione----------------

        if (searchForm.getClasseDiEmissone() != null) {

            driver.findElement(By.xpath("//*[@id=\"filters-container\"]/div/div[17]/div[2]/div/div/div")).click();
            Thread.sleep(500);
            WebElement classeDiEmissione = driver.findElement(By.xpath("//*[@id=\"/pollution__option--" + valueIndex(searchForm.getClasseDiEmissone(), classiDiEmissone) + "\"]"));
            classeDiEmissione.click();//*[@id="/pollution__option--2"]

        }

        //xpath di prossima pagina
        //*[@id="layout"]/main/div[3]/div[2]/div[2]/nav/button[2]
        //per ciclare tutte le eventuali pagine fino a quando le ad attuali sono 30 vai avanti
        int adsSize = 0;
        int numPagine = 0;
        List<CarAdv> annunci = new ArrayList<>();
        do {
            Thread.sleep(500);
            numPagine++;
            System.out.println("----------//////-----------Questa è la pagina numero: "+ numPagine);
            String adsText = driver.findElement(By.xpath("//*[@id=\"layout\"]/main/div[3]/div[2]/div[2]/div[1]/div[3]/div")).getText();
            List<WebElement> ads = driver.findElements(By.className("BigCard-module_card__Exzqv"));
            System.out.println(adsText);
            System.out.println(ads);
            adsSize = ads.size();


            for (WebElement a : ads) {
                System.out.println("ad attuale " + a);
                String link = a.findElement(By.className("BigCard-module_link__kVqPE")).getAttribute("href");//prendo il link dellannuncio
                System.out.println("link attuale " + link);
                List<WebElement> linkImgArr = a.findElements(By.className("CardImage-module_photo__WMsiO"));//prendo il link della foto

                String linkImg="";
                if (linkImgArr.size() > 0) {
                    linkImg = linkImgArr.get(0).getAttribute("src");
                }
                String proprieta = a.getText();//prendo gli attributi anno prezzo ecc
                System.out.println("proprieta attuale " + proprieta);
                Scanner scanner2 = new Scanner(proprieta);
                List<String> propAdv = new ArrayList<>();//array delle prop dell adv che servirà a costruirne uno

                while (scanner2.hasNextLine()) {//ciclo per riemipre l'array con le proprietà dell'annuncio prese dal get text
                    propAdv.add(scanner2.nextLine());
                }
                scanner2.close();
                System.out.println("propAdv attuale " + propAdv);
                CarAdv annAttuale = new CarAdv();
                if ( isNumeric(propAdv.get(0)) ) {//controllo se il primo item dell'array' sono il numero delle foto se si lo scartiamo
                    propAdv.remove(0);
                }
                    annAttuale = CarAdv.builder()
                            .title(propAdv.get(0))
                            .placeAndDate(propAdv.get(1))
                            .price(propAdv.get(2))
                            .condition(propAdv.get(3))
                            .year(propAdv.get(4))
                            .km(propAdv.get(5))
                            .powerSupply(propAdv.get(6))
                            .gearbox(propAdv.get(7))
                            .emissionClass(propAdv.get(8))
                            .link(link)
                            .photoLink(linkImg)
                            .build();


                annunci.add(annAttuale);
                System.out.println(annAttuale.toString());

            }

            List<WebElement> next = driver.findElements(By.xpath("//*[@id=\"layout\"]/main/div[3]/div[2]/div[2]/nav/button[2]"));
            if (next.size() > 0) {
                System.out.println("ESISTE IL BOTTONE NEXT");
                next.get(0).click();
                //rifare la stessa cosa fino a quando la pagina non contiene meno di 30 ads
            }
            if(numPagine>5){
                break;  
            }
        } while (adsSize == 30);

return annunci;
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

    private String annoImmatrcolazione(String anno) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int numeroOption = Math.abs(year - Integer.parseInt(anno));
        return Integer.toString(numeroOption);
    }

    private String valueIndex(String value, String[] arr) {
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                k = i;
                break;
            }
        }
        return Integer.toString(k);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
