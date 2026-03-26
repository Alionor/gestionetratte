package it.prova.gestionetratte.repository.airbus;

import it.prova.gestionetratte.model.Airbus;
import org.springframework.data.repository.CrudRepository;

public interface AirbusRepository  extends CrudRepository<Airbus, Long>, CustomAirbusRepository {
}
