package us.dit.service.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import us.dit.model.Calendario;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@PersistenceContext(unitName = "postgreSQL-hibernate")
public class JpaCalendarioDao implements Dao<Calendario> {
    private static final Logger logger = LogManager.getLogger();

    private JpaDaoFeatures jpa;

    public JpaCalendarioDao () {
        logger.info("Constructor donde obtengo el objeto JpaDaoFeatures");
        jpa = JpaDaoFeatures.getInstance();
    }

    @Override
    public Optional<Calendario> findById(String primaryKey) {
        logger.info("Busco el calendario por su id "+ primaryKey);
        Optional<Calendario> calendario = Optional.ofNullable(jpa.getEntityManager().find(Calendario.class, primaryKey));
        return calendario;
    }

    @Override
    public List<Calendario> findAll(){
        logger.info("----\nBusco todos los calendarios");
        TypedQuery<Calendario> q = jpa.getEntityManager().createQuery("select b from Calendario b", Calendario.class);
        List<Calendario> resultList =q.getResultList();
        logger.info(q.getResultList().toString());

        return resultList;
    }

    @Override
    public void save(Calendario calendario) {
        logger.info("Voy a persistir el calendario\n"+calendario);
        jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().persist(calendario));
    }

    @Override
    public void update(Calendario calendario) {
        jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().merge(calendario));
    }

    @Override
    public void delete(Calendario calendario) {
        jpa.executeInsideTransaction(myEntityManager->jpa.getEntityManager().remove(calendario));
    }

    @Override
    public void delete(String id) {
        findById(id).ifPresent((value)->this.delete(value));
    }

    public Optional<Calendario> findCalendarByMonth(int monthValue) {
        logger.info("Consulto el calendario con valor de mes="+monthValue);
        Query query = jpa.getEntityManager().createQuery("Select a from Calendario where a.monthValue = :monthValue");
        query.setParameter("monthValue",monthValue);
        Calendario calendario = (Calendario) query.getSingleResult();

        return Optional.ofNullable((Calendario) calendario);
    }


}
