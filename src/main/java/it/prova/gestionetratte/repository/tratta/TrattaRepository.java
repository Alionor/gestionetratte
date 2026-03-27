package it.prova.gestionetratte.repository.tratta;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrattaRepository  extends CrudRepository<Tratta, Long>, CustomTrattaRepository  {


    @Query("SELECT t from Tratta t LEFT JOIN FETCH t.airbus")
    List<Tratta> findAllEager();

    @Query("SELECT t from Tratta t LEFT JOIN FETCH t.airbus WHERE t.id = :id")
    Tratta findByIdEager(@Param("id") Long id);

    @Query("SELECT t from Tratta t WHERE t.stato = 'ATTIVA'")
    List<Tratta> findAllAttive();

}
