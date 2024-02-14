package us.dit.service.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

import java.util.function.Consumer;

public class JpaDaoFeatures {
    private static final Logger log = LogManager.getLogger();

    private static EntityManagerFactory emf=null;

    private static EntityManager myEntityManager=null;

    private static JpaDaoFeatures myInstance=null;

    private JpaDaoFeatures() {

    }
    public static JpaDaoFeatures getInstance() {
        if (myInstance==null){
            myInstance = new JpaDaoFeatures();
            log.info("Creando un nuevo EntityManager");
            try {
                if(emf==null) {
                    log.info("Comienzo creando la factoría de EntityManager\n");
                    emf =
                            Persistence.createEntityManagerFactory("postgreSQL-hibernate");
                    log.info("A continuación creo un entityManager, usando la factoría\n");
                }
                myEntityManager = emf.createEntityManager();
            }
            catch(Throwable ex) {
                log.error("Ha fallado la creación de SessionFactory "+ex+"\n");
                throw new ExceptionInInitializerError(ex);
            }
        }
        return myInstance;
    }
    public void executeInsideTransaction(Consumer<EntityManager> action) {

        EntityTransaction tx = myEntityManager.getTransaction();
        try {
            tx.begin();
            action.accept(myEntityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public EntityManager getEntityManager() {
        return myEntityManager;

    }

}
