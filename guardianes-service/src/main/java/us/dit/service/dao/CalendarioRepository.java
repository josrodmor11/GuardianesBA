package us.dit.service.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.dit.service.model.Calendario;

import java.util.Optional;
@Repository
public interface CalendarioRepository extends CrudRepository<Calendario, Integer> {
    Optional<Calendario> findById(Integer id);

}
