package Repository;

import DAO.RoleDAO;
import DTO.RoleDTO;
import java.util.List;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class RoleRepository extends Repository<RoleDTO> {
    @Override
    protected RoleDTO get(Integer Id) {
        RoleDAO objectDAO = new RoleDAO();
        RoleDTO objectDTO = objectDAO.read(Id);
        return objectDTO;
    }

    @Override
    protected Boolean add(RoleDTO object) {
        RoleDAO objectDAO = new RoleDAO();
        return objectDAO.create(object);
    }

    @Override
    protected Boolean update(RoleDTO object) {
        RoleDAO objectDAO = new RoleDAO();
        return objectDAO.update(object);
    }

    @Override
    protected Boolean remove(RoleDTO object) {
        RoleDAO objectDAO = new RoleDAO();
        return objectDAO.delete(object.getId());
    }
    
    public RoleDTO getRole(String roleName) {
        try {
            RoleDAO objectDAO = new RoleDAO();
            List<RoleDTO> list = objectDAO.all();
            if(!list.isEmpty())
                for(RoleDTO o : list)
                    if (roleName.contentEquals(o.getName()))
                        return o;
            return new RoleDTO();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return new RoleDTO();
        }
    }
    
    public Boolean createRole(RoleDTO object) {
        return add(object);
    }
    
    public Boolean remove(String roleName) {
        try {
            RoleDAO objectDAO = new RoleDAO();
            List<RoleDTO> list = objectDAO.all();
            if(!list.isEmpty())
                for(RoleDTO o : list)
                    if (roleName.contentEquals(o.getName()))
                        return remove(o);
            return Boolean.FALSE;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return Boolean.FALSE;
        }
    }
}