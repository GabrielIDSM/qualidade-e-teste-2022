package Repository;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 * @param <ObjectDTO>
 */
public abstract class Repository<ObjectDTO> {
    protected ObjectDTO get(Integer Id) {
        throw new UnsupportedOperationException("Not supported");
    }
    protected Boolean add(ObjectDTO object) {
        throw new UnsupportedOperationException("Not supported");
    }
    protected Boolean update(ObjectDTO object) {
        throw new UnsupportedOperationException("Not supported");
    }
    protected Boolean remove(ObjectDTO object) {
        throw new UnsupportedOperationException("Not supported");
    }
}