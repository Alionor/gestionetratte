package it.prova.gestionetratte.service.airbus;

import it.prova.gestionetratte.model.Airbus;

import java.util.List;

public interface AirbusService {

    List<Airbus> listAllElements(boolean eager);

    Airbus insert(Airbus airbus);

    Airbus update(Airbus airbus);

    Airbus findById(Long id, boolean eager);

    void remove(Long id);

    public List<Airbus> listaAirbusConSovrapposizioni();
}
