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
public class AirbusDTO {


    private Long id;

    @NotBlank(message = "{codice.notblank}")
    private String codice;

    @NotBlank(message = "{descrizione.notblank}")
    private String descrizione;

    @NotNull(message = "{data.notnull}")
    private LocalDate dataInizioServizio;

    @NotNull(message = "{numeroPasseggeri.notnull}")
    private Integer numeroPasseggeri;

   // @JsonIgnoreProperties(value = { "airbus" })
    private Set<TrattaDTO> tratte = new HashSet<TrattaDTO>(0);

    public AirbusDTO() {
    }

    public AirbusDTO(Long id, String codice, String descrizione, LocalDate dataInizioServizio, Integer numeroPasseggeri) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.dataInizioServizio = dataInizioServizio;
        this.numeroPasseggeri = numeroPasseggeri;
    }
    public AirbusDTO(Long id, String codice, String descrizione, LocalDate dataInizioServizio, Integer numeroPasseggeri, Set<TrattaDTO> tratte) {
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

    public Set<TrattaDTO> getTratte() {
        return tratte;
    }

    public void setTratte(Set<TrattaDTO> tratte) {
        this.tratte = tratte;
    }

    public static AirbusDTO buildAirbusDTOFromModel(Airbus airbusModel, boolean includeTratte) {
        AirbusDTO result = new AirbusDTO(airbusModel.getId(), airbusModel.getCodice(), airbusModel.getDescrizione(),
                airbusModel.getDataInizioServizio(), airbusModel.getNumeroPasseggeri());
        if (includeTratte)
            result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(airbusModel.getTratte(), false));
        return result;
    }

    public Airbus buildModelFromDTO() {
        Airbus result = new Airbus(this.id, this.codice, this.descrizione, this.dataInizioServizio, this.numeroPasseggeri);
        return result;
    }

    public static List<AirbusDTO> createAirbusDTOListFromModelList(List<Airbus> modelListInput,
                                                                     boolean includeTratte) {
        return modelListInput.stream().map(airbus -> {
            AirbusDTO result = AirbusDTO.buildAirbusDTOFromModel(airbus, includeTratte);
            if (includeTratte)
                result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(airbus.getTratte(), false));
            return result;
        }).collect(Collectors.toList());
    }


    
}
