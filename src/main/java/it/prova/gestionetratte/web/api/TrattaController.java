package it.prova.gestionetratte.web.api;

import it.prova.gestionetratte.dto.TrattaDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.airbus.AirbusService;
import it.prova.gestionetratte.service.tratta.TrattaService;
import it.prova.gestionetratte.web.api.exception.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tratta")
public class TrattaController {

    @Autowired
    private TrattaService trattaService;
    private AirbusService airbusService;

    @GetMapping
    public Set<TrattaDTO> listAll() {
        return TrattaDTO.createTrattaDTOSetFromModelSet(trattaService.listAllElements(true)
                .stream().collect(Collectors.toSet()), true);
    }

    @GetMapping("/eager/{id}")
    public TrattaDTO getByIdEager( @PathVariable(required = true) Long id) {
        Tratta tratta = trattaService.findById(id, true);
        if (tratta == null) throw new TrattaNotFoundException("Tratta non trovata");
        return TrattaDTO.buildTrattaDTOFromModel(tratta, true);
    }

    @GetMapping("/{id}")
    public TrattaDTO getById( @PathVariable(required = true) Long id) {
        Tratta tratta = trattaService.findById(id, false);
        if (tratta == null) throw new TrattaNotFoundException("Tratta non trovata");
        return TrattaDTO.buildTrattaDTOFromModel(tratta, false);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrattaDTO insert(@Valid @RequestBody TrattaDTO trattaDTO) {
        if (trattaDTO.getId() != null)
            throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

        Tratta trattaDaInserire = trattaService.insert(trattaDTO.buildModelFromDTO());
        return TrattaDTO.buildTrattaDTOFromModel(trattaDaInserire, true);
    }

    @PutMapping("/{id}")
    public TrattaDTO update(@Valid @RequestBody TrattaDTO trattaDTO,  @PathVariable(required = true) Long id) {
        Tratta trattaDaAggiornare = trattaService.findById(id, false);
        if (trattaDaAggiornare == null)
            throw new AirbusNotFoundException("Airbus not found con id: " + id);
        trattaDTO.setId(id);
        Tratta trattaAggiornata = trattaService.update(trattaDTO.buildModelFromDTO());
        return TrattaDTO.buildTrattaDTOFromModel(trattaAggiornata, false);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) Long id) {
        Tratta trattaDaAggiornare = trattaService.findById(id, true);

        if(trattaDaAggiornare.getStato().equals(Stato.ATTIVA))
            throw new NotRemovableIfIsNotAnnullataException("Non può essere rimossa la tratta se non è stata annullata o conclusa.");

        trattaService.remove(id);
    }





}
