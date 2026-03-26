package it.prova.gestionetratte.web.api;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.service.airbus.AirbusService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirbusDTO insert(@Valid @RequestBody AirbusDTO airbusDTO) {
        if (airbusDTO.getId() != null)
            throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

        Airbus airbusDaInserire = airbusService.insert(airbusDTO.buildModelFromDTO());
        System.out.println(airbusDTO.buildModelFromDTO());
        return AirbusDTO.buildAirbusDTOFromModel(airbusDaInserire, false);
    }



}
