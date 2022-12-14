package com.ilCary.CarUnit.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "starred_search")
@NoArgsConstructor
@AllArgsConstructor
public class StarredSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "StarredSearch{" +
                "id=" + id +
                ", user=" + user +
                ", nameSearch='" + nameSearch + '\'' +
                ", citta='" + citta + '\'' +
                ", tipoDiVeicolo='" + tipoDiVeicolo + '\'' +
                ", inserzionista='" + inserzionista + '\'' +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", annoImmatricolazioneDa='" + annoImmatricolazioneDa + '\'' +
                ", annoImmatricolazioneA='" + annoImmatricolazioneA + '\'' +
                ", prezzoDa='" + prezzoDa + '\'' +
                ", prezzoA='" + prezzoA + '\'' +
                ", kmDa='" + kmDa + '\'' +
                ", kmA='" + kmA + '\'' +
                ", tipologiaAuto='" + tipologiaAuto + '\'' +
                ", carburante='" + carburante + '\'' +
                ", cambio='" + cambio + '\'' +
                ", porte='" + porte + '\'' +
                ", colore='" + colore + '\'' +
                ", classeDiEmissone='" + classeDiEmissone + '\'' +
                '}';
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nameSearch;

    @ManyToOne
    @JoinColumn(name = "citta_id")
    private Comune citta;
    private String tipoDiVeicolo;//nuovo, usato o km0
    private String inserzionista;//azienda o privato
    private String marca;
    private String modello;
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
