package it.prova.gestionetratte.service.tratta;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;

import java.util.List;

public interface TrattaService {

    List<Tratta> listAllElements(boolean eager);

    Tratta insert(Tratta tratta);

    Tratta update(Tratta tratta);

    Tratta findById(Long id, boolean eager);

    void remove(Long id);

    public List<Tratta> updateStatoTratte();

}
