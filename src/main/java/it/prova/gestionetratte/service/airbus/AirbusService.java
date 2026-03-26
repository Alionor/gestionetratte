package it.prova.gestionetratte.service.airbus;

import it.prova.gestionetratte.model.Airbus;

import java.util.List;

public interface AirbusService {

    List<Airbus> listAllElements(boolean eager);

    Airbus insert(Airbus airbus);
}
