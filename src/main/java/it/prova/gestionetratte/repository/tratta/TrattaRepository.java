package it.prova.gestionetratte.repository.tratta;

import it.prova.gestionetratte.model.Tratta;
import org.springframework.data.repository.CrudRepository;

public interface TrattaRepository  extends CrudRepository<Tratta, Long>, CustomTrattaRepository  {
}
