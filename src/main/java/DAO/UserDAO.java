package DAO;

import DTO.UserDTO;
import Factory.ConnectionFactory;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class UserDAO implements DAO<UserDTO> {
    @Override
    public Boolean create(UserDTO object) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        Boolean operationResult;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
            operationResult = Boolean.TRUE;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
            operationResult = Boolean.FALSE;
        }finally{
            entityManager.close();
        }
        return operationResult;
    }

    @Override
    public UserDTO read(Integer Id) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        UserDTO object = null;
        try {
            object = entityManager.find(UserDTO.class, Id);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            entityManager.close();
        }
        return object;
    }

    @Override
    public Boolean update(UserDTO object) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        Boolean operationResult;
        try {
            entityManager.getTransaction().begin();
            if(object.getId() == null){
                entityManager.persist(object);
            }else{
                entityManager.merge(object);
            }   
            entityManager.getTransaction().commit();
            operationResult = Boolean.TRUE;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
            operationResult = Boolean.FALSE;
        }finally{
            entityManager.close();
        }
        return operationResult;
    }

    @Override
    public Boolean delete(Integer Id) {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        UserDTO object;
        Boolean operationResult;
        try {
            object = entityManager.find(UserDTO.class, Id);
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
            operationResult = Boolean.TRUE;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
            operationResult = Boolean.FALSE;
        }finally{
            entityManager.close();
        }
        return operationResult;
    }

    @Override
    public List<UserDTO> all() {
        EntityManager entityManager = new ConnectionFactory().getConnection();
        List<UserDTO> list = null;
        try {
            list = entityManager.createQuery("from UserDTO dto").getResultList();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            entityManager.close();
        }
        return list;
    }
}