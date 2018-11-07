    /**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author parkin9
 *
 */

@Entity
@Table(name = "users_accounts")
public class UsersAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_account_id")
    private Long id;
    
    @NotBlank(message = "*Pole obowiązkowe.")
    @Column(name = "username", unique = true)
    private String username;
    
    @NotBlank(message = "*Pole obowiązkowe.")
    @Size(min = 6, message = "*Minimum 6 znaków.")
    @Column(name = "password")
    private String password;
    
    @NotNull
    @Column(name = "enable")
    private Boolean enable = false;
    
    @Transient
    private String passwordConfirm;

///////////////////////////////////////////////////////////////////////////////
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    @Override
    public String toString() {
        return "UsersAccount [id=" + id + ", username=" + username + ", password=" + password + ", enable=" + enable
                + ", passwordConfirm=" + passwordConfirm + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((enable == null) ? 0 : enable.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UsersAccount other = (UsersAccount) obj;
        if (enable == null) {
            if (other.enable != null) {
                return false;
            }
        } else if (!enable.equals(other.enable)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (passwordConfirm == null) {
            if (other.passwordConfirm != null) {
                return false;
            }
        } else if (!passwordConfirm.equals(other.passwordConfirm)) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }
}
