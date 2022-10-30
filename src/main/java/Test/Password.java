package Test;

import Util.PasswordUtil;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
public class Password {
    public static void main(String[] args) {
        PasswordUtil passwordAuthentication = new PasswordUtil();
        String hash = passwordAuthentication.hash("Password".toCharArray());
        System.out.println("Hash: " + hash);

        // Valid Password
        System.out.println("Valid Password: " + String.valueOf(passwordAuthentication.authenticate("Password".toCharArray(), hash)));
        
        // Invalid Password
        System.out.println("Valid Password: " + String.valueOf(passwordAuthentication.authenticate("password".toCharArray(), hash)));
    }
}
