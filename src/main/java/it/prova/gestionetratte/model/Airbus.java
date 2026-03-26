package it.prova.gestionetratte.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "airbus")
public class Airbus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "codice", unique = true, nullable = false)
    private String codice;

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Column(name = "datainizioservizio", nullable = false)
    private LocalDate dataInizioServizio;

    @Column(name = "numeropasseggeri", nullable = false)
    private Integer numeroPasseggeri;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airbus")
    private Set<Tratta> tratte = new HashSet<>();

    public Airbus() {
    }

    public Airbus(Long id, String codice, String descrizione, LocalDate dataInizioServizio, Integer numeroPasseggeri) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.dataInizioServizio = dataInizioServizio;
        this.numeroPasseggeri = numeroPasseggeri;
    }

    public Airbus(Long id, String codice, String descrizione, LocalDate dataInizioServizio, Integer numeroPasseggeri, Set<Tratta> tratte) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.dataInizioServizio = dataInizioServizio;
        this.numeroPasseggeri = numeroPasseggeri;
        this.tratte = tratte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataInizioServizio() {
        return dataInizioServizio;
    }

    public void setDataInizioServizio(LocalDate dataInizioServizio) {
        this.dataInizioServizio = dataInizioServizio;
    }

    public Integer getNumeroPasseggeri() {
        return numeroPasseggeri;
    }

    public void setNumeroPasseggeri(Integer numeroPasseggeri) {
        this.numeroPasseggeri = numeroPasseggeri;
    }

    public Set<Tratta> getTratte() {
        return tratte;
    }

    public void setTratte() {
        this.tratte = tratte;
    }
}




