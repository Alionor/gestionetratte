package it.prova.gestionetratte.web.api;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.service.airbus.AirbusService;
import it.prova.gestionetratte.web.api.exception.AirbusNotFoundException;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airbus")
public class AirbusController {

    @Autowired
    private AirbusService airbusService;

    @GetMapping
    public List<AirbusDTO> listAll() {
        return AirbusDTO.createAirbusDTOListFromModelList(airbusService.listAllElements(true), true);
    }

    @GetMapping("/eager/{id}")
    public AirbusDTO getByIdEager( @PathVariable(required = true) Long id) {
        return AirbusDTO.buildAirbusDTOFromModel(airbusService.findById(id, true), true);
    }

    @GetMapping("/{id}")
    public AirbusDTO getById( @PathVariable(required = true) Long id) {
        return AirbusDTO.buildAirbusDTOFromModel(airbusService.findById(id, false), false);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirbusDTO insert(@Valid @RequestBody AirbusDTO airbusDTO) {
        if (airbusDTO.getId() != null)
            throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

        Airbus airbusDaInserire = airbusService.insert(airbusDTO.buildModelFromDTO());
        return AirbusDTO.buildAirbusDTOFromModel(airbusDaInserire, false);
    }

    @PutMapping("/{id}")
    public AirbusDTO update(@Valid @RequestBody AirbusDTO airbusDTO,  @PathVariable(required = true) Long id) {
        Airbus airbusDaAggiornare = airbusService.findById(id, false);
        if (airbusDaAggiornare == null)
            throw new AirbusNotFoundException("Airbus not found con id: " + id);
        airbusDTO.setId(id);
        Airbus airbusAggiornato = airbusService.update(airbusDTO.buildModelFromDTO());
        return AirbusDTO.buildAirbusDTOFromModel(airbusAggiornato, false);

    }



}
