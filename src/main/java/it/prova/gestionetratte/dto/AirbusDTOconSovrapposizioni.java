package it.prova.gestionetratte.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.gestionetratte.model.Airbus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirbusDTOconSovrapposizioni {


    private Long id;

    @NotBlank(message = "{codice.notblank}")
    private String codice;

    @NotBlank(message = "{descrizione.notblank}")
    private String descrizione;

    @NotNull(message = "{data.notnull}")
    private LocalDate dataInizioServizio;

    @NotNull(message = "{numeroPasseggeri.notnull}")
    private Integer numeroPasseggeri;

    private Boolean conSovrapposizioni = false;

    @JsonIgnoreProperties(value = { "airbus" })
    private Set<TrattaDTO> tratte = new HashSet<TrattaDTO>(0);

    public AirbusDTOconSovrapposizioni() {
    }

    public AirbusDTOconSovrapposizioni(Long id, String codice, String descrizione, LocalDate dataInizioServizio, Integer numeroPasseggeri, boolean conSovrapposizioni) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.dataInizioServizio = dataInizioServizio;
        this.numeroPasseggeri = numeroPasseggeri;
        this.conSovrapposizioni = conSovrapposizioni;
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

    public Boolean isConSovrapposizioni() {
        return conSovrapposizioni;
    }

    public void setConSovrapposizioni(Boolean conSovrapposizioni) {
        this.conSovrapposizioni = conSovrapposizioni;
    }

    public Set<TrattaDTO> getTratte() {
        return tratte;
    }

    public void setTratte(Set<TrattaDTO> tratte) {
        this.tratte = tratte;
    }

    public static AirbusDTOconSovrapposizioni buildAirbusDTOFromModel(Airbus airbusModel, boolean includeTratte) {
        AirbusDTOconSovrapposizioni result = new AirbusDTOconSovrapposizioni(airbusModel.getId(), airbusModel.getCodice(), airbusModel.getDescrizione(),
                airbusModel.getDataInizioServizio(), airbusModel.getNumeroPasseggeri(), true);
        if (includeTratte)
            result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(airbusModel.getTratte(), false));
        return result;
    }


    public static List<AirbusDTOconSovrapposizioni> createAirbusDTOListFromModelList(List<Airbus> modelListInput,
                                                                                     boolean includeTratte) {
        return modelListInput.stream().map(airbus -> {
            AirbusDTOconSovrapposizioni result = AirbusDTOconSovrapposizioni.buildAirbusDTOFromModel(airbus, includeTratte);
            if (includeTratte)
                result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(airbus.getTratte(), false));
            return result;
        }).collect(Collectors.toList());
    }


    
}
