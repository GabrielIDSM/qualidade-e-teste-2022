package Factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class ConnectionFactory {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public EntityManager getConnection(){
        if (ENTITY_MANAGER_FACTORY == null)
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("PU");

        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
}