package Test;

import DTO.RoleDTO;
import DTO.UserDTO;
import Repository.RoleRepository;
import Repository.UserRepository;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class Database {
    public static void main(String[] args) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("Administrator");
        RoleRepository roleRepository = new RoleRepository();
        if (roleRepository.createRole(roleDTO)) System.out.println("Role '" + roleDTO.getName() + "' has been created");
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("gabriel.inacio");
        userDTO.setPassword("Pa55w0rd#");
        userDTO.setForceUpdate(Boolean.FALSE);
        userDTO.setRole(roleDTO);
        UserRepository userRepository = new UserRepository();
        if (userRepository.createUser(userDTO)) System.out.println("User '" + userDTO.getUsername() + "' has been created");
        roleDTO = new RoleDTO();
        roleDTO.setName("Operator");
        if (roleRepository.createRole(roleDTO)) System.out.println("Role '" + roleDTO.getName() + "' has been created");
    }
}