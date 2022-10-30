package DTO;

import CSV.I18nCSV;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Gabriel Inácio <gabrielinacio@id.uff.br>
 */
@Entity
@Table(name = "user_conf")
public class UserDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    
    @Column(name = "username", unique = true, nullable = false)
    private String Username;
    
    @Column(name = "password", nullable = false)
    private String Password;

    @Column(name = "force_update", nullable = false)
    private Boolean ForceUpdate;

    @Column(name = "lang", nullable = false)
    private Integer Language = I18nCSV.PT_BR;

    @ManyToOne
    private RoleDTO Role;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Boolean getForceUpdate() {
        return ForceUpdate;
    }

    public void setForceUpdate(Boolean ForceUpdate) {
        this.ForceUpdate = ForceUpdate;
    }

    public RoleDTO getRole() {
        return Role;
    }

    public void setRole(RoleDTO Role) {
        this.Role = Role;
    }

    public Integer getLanguage() {
        return Language;
    }

    public void setLanguage(Integer Language) {
        this.Language = Language;
    }
}