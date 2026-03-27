package it.prova.gestionetratte.repository.airbus;

import it.prova.gestionetratte.model.Airbus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirbusRepository  extends CrudRepository<Airbus, Long>, CustomAirbusRepository {

    @Query("SELECT a from Airbus a LEFT JOIN FETCH a.tratte")
    List<Airbus> findAllEager();

    @Query("SELECT a from Airbus a LEFT JOIN FETCH a.tratte WHERE a.id = :id")
    Airbus findByIdEager(@Param("id") Long id);

}
