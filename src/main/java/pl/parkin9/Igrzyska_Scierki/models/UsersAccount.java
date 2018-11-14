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
    @Column(name = "account_name", unique = true)
    private String accountName;
    
    @NotBlank(message = "*Pole obowiązkowe.")
    @Size(min = 6, message = "*Minimum 6 znaków.")
    @Column(name = "password")
    private String password;
    
    @NotNull
    @Column(name = "enable")
    private Boolean enable = true;
    
    @Transient
    private String passwordConfirm;
    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersAccount", cascade = CascadeType.ALL)
//    private Set<Player> players;
//    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersAccount", cascade = CascadeType.ALL)
//    private Set<Game> games;
//    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersAccount", cascade = CascadeType.ALL)
//    private Set<Task> tasks;

///////////////////////////////////////////////////////////////////////////
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
        return "UsersAccount [id=" + id + ", accountName=" + accountName + ", password=" + password + ", enable="
                + enable + ", passwordConfirm=" + passwordConfirm + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
        result = prime * result + ((enable == null) ? 0 : enable.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
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
        if (accountName == null) {
            if (other.accountName != null) {
                return false;
            }
        } else if (!accountName.equals(other.accountName)) {
            return false;
        }
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
        return true;
    }
}
