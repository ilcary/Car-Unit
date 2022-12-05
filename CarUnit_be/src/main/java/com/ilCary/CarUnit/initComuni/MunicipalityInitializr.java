package com.ilCary.CarUnit.initComuni;

import com.ilCary.CarUnit.models.Comune;
import com.ilCary.CarUnit.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MunicipalityInitializr implements CommandLineRunner {
    @Autowired
    ComuneService cs;

//---------------------------- populate the database with all the localities

    public void addProvincesAndMunicipalities() {

        List<List<String>> records = new ArrayList<>();



        Scanner scanner;


        try {

            scanner = new Scanner(new File("src/main/resources/static/asset/comuni-localita-cap-italia.csv"));


            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        records.remove(0);

        // Province

        for (List<String> stringa : records) {

            String[] s = stringa.get(0).split(",");
          //  ABANO TERME BAGNI,PD,35031,,Veneto
            String comune = s[0];
            String provincia = s[1];
            String cap = s[2];
            String regione = s[4];

            Comune c = Comune.builder().comune(comune).cap(cap).regione(regione).provincia(provincia)
                    .build();
            cs.save(c);

        }
        System.out.println("done?");
        // Comuni



    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter("\n");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }


    @Override
    public void run(String... args) throws Exception {
        //addProvincesAndMunicipalities();
    }
}
