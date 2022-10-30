package Repository;

import DAO.UserDAO;
import DTO.UserDTO;
import Util.PasswordUtil;
import java.util.List;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class UserRepository extends Repository<UserDTO> {
    @Override
    protected UserDTO get(Integer Id) {
        UserDAO objectDAO = new UserDAO();
        UserDTO objectDTO = objectDAO.read(Id);
        return objectDTO;
    }

    @Override
    protected Boolean add(UserDTO object) {
        UserDAO objectDAO = new UserDAO();
        return objectDAO.create(object);
    }

    @Override
    protected Boolean update(UserDTO object) {
        UserDAO objectDAO = new UserDAO();
        return objectDAO.update(object);
    }

    @Override
    protected Boolean remove(UserDTO object) {
        UserDAO objectDAO = new UserDAO();
        return objectDAO.delete(object.getId());
    }
    
    public Boolean deleteUser(UserDTO object) {
        return remove(object);
    }
    
    public Boolean deleteUser(Integer Id) {
        return remove(get(Id));
    }
    
    public UserDTO getUser(Integer Id) {
        return get(Id);
    }
    
    public Boolean updateUser(UserDTO object){
        return update(object);
    }
    
    public Boolean updateUserPassword(UserDTO object){
        if (PasswordUtil.isValidPassword(object.getPassword())) {
            PasswordUtil passwordAuthentication = new PasswordUtil();
            object.setPassword(passwordAuthentication.hash(object.getPassword().toCharArray()));
            return update(object);
        } else {
            return Boolean.FALSE;
        }
    }
    
    public Boolean createUser(UserDTO object){
        if (PasswordUtil.isValidPassword(object.getPassword())) {
            PasswordUtil passwordAuthentication = new PasswordUtil();
            object.setPassword(passwordAuthentication.hash(object.getPassword().toCharArray()));
            return add(object);
        } else {
            return Boolean.FALSE;
        }
    }
    
    public List<UserDTO> all() {
        UserDAO objectDAO = new UserDAO();
        List<UserDTO> list = objectDAO.all();
        return list;
    }
    
    public UserDTO login(String username, String password) {
        PasswordUtil passwordAuthentication = new PasswordUtil();
        List<UserDTO> users = all();
        try {
            for (UserDTO user : users) { 
                if (user.getUsername().equals(username) &&
                        passwordAuthentication.authenticate(password.toCharArray(), user.getPassword())) {
                    return user;
                }
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}