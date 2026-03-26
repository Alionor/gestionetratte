package it.prova.gestionetratte.service.airbus;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.airbus.AirbusRepository;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirbusServiceImpl implements AirbusService {

    @Autowired
    private AirbusRepository airbusRepository;

    public List<Airbus> listAllElements(boolean eager) {
        if (eager)
            return (List<Airbus>) airbusRepository.findAllEager();

        return (List<Airbus>) airbusRepository.findAll();
    }

    public Airbus insert(Airbus airbus) {
        return airbusRepository.save(airbus);
    }

}
