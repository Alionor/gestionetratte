package it.prova.gestionetratte.service.airbus;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.airbus.AirbusRepository;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AirbusServiceImpl implements AirbusService {

    @Autowired
    private AirbusRepository airbusRepository;

    public List<Airbus> listAllElements(boolean eager) {
        if (eager)
            return (List<Airbus>) airbusRepository.findAllEager();

        return (List<Airbus>) airbusRepository.findAll();
    }

    public Airbus findById(Long id, boolean eager) {
        if (eager)
            return airbusRepository.findByIdEager(id);
        return airbusRepository.findById(id).orElse(null);
    }


    @Transactional
    public Airbus insert(Airbus airbus) {
        return airbusRepository.save(airbus);
    }

    @Transactional
    public Airbus update(Airbus airbus) {
        return airbusRepository.save(airbus);
    }

    @Transactional
    public void remove(Long id) {
        airbusRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Airbus> listaAirbusConSovrapposizioni() {
        List<Airbus> listaAirbus = airbusRepository.findAllEager();

       return listaAirbus.stream().filter(airbus -> {
                    Set<Tratta> tratte = airbus.getTratte();
                    return tratte.stream().anyMatch(trattaSource ->
                            tratte.stream()
                                    .filter(trattaTarget -> trattaTarget != trattaSource)
                                    .anyMatch(trattaTarget ->
                                            !trattaSource.getOraDecollo().isAfter(trattaTarget.getOraAtterraggio()) &&
                                                    !trattaSource.getOraAtterraggio().isBefore(trattaTarget.getOraDecollo())
                                    )
                    );
                })
                .toList();

    }

}
