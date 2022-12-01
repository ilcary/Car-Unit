package com.ilCary.CarUnit.models;

import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Search {

    private String tipoDiVeicolo;//nuovo, usato o km0
    private String inserzionista;//azienda o privato
    private String Marca;
    private String annoImmatricolazioneDa;
    private String annoImmatricolazioneA;
    private String prezzoDa;
    private String prezzoA;
    private String kmDa;
    private String kmA;
    private String tipologiaAuto;//berlina station ecc..
    private String carburante;
    private String cambio;
    private String porte;
    private String colore;
    private String classeDiEmissone;



}
