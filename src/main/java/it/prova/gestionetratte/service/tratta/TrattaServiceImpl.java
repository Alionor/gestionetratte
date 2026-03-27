package it.prova.gestionetratte.service.tratta;

import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.airbus.AirbusRepository;
import it.prova.gestionetratte.repository.tratta.TrattaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TrattaServiceImpl implements TrattaService {

    @Autowired
    private TrattaRepository trattaRepository;
    private AirbusRepository airbusRepository;

    public List<Tratta> listAllElements(boolean eager) {
        if (eager)
            return (List<Tratta>) trattaRepository.findAllEager();

        return (List<Tratta>) trattaRepository.findAll();
    }

    public Tratta findById(Long id, boolean eager) {
        if (eager)
            return trattaRepository.findByIdEager(id);
        return trattaRepository.findById(id).orElse(null);
    }


    @Transactional
    public Tratta insert(Tratta tratta) {
        return trattaRepository.save(tratta);
    }

    @Transactional
    public Tratta update(Tratta tratta) {
        return trattaRepository.save(tratta);
    }

    @Transactional
    public void remove(Long id) {
        trattaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Tratta> updateStatoTratte() {
        List<Tratta> tratteAttive = trattaRepository.findAllAttive();
        tratteAttive.stream().forEach(tratta -> {
            if (tratta.getData().equals(LocalDate.now()) && tratta.getOraAtterraggio().isBefore(LocalTime.now()) || tratta.getData().isBefore(LocalDate.now()))
                tratta.setStato(Stato.CONCLUSA);
        });
        return tratteAttive.stream().filter(tratta -> tratta.getStato().equals(Stato.CONCLUSA)).toList();
    }

}
