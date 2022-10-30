package DAO;

import java.util.List;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 * @param <ObjectDTO>
 */
public interface DAO<ObjectDTO> {
    Boolean create(ObjectDTO object);
    ObjectDTO read(Integer Id);
    Boolean update(ObjectDTO object);
    Boolean delete(Integer Id);
    List<ObjectDTO> all();
}