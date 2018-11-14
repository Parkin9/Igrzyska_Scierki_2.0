/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author parkin9
 *
 */
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "start", updatable = false)
    private Date start;

    @NotNull
    @Column(name = "end")
    private Date end;

    @NotNull
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_account_id")
    private UsersAccount usersAccount;

//////////////////////////////////////////////////////////////
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UsersAccount getUsersAccount() {
        return usersAccount;
    }

    public void setUsersAccount(UsersAccount usersAccount) {
        this.usersAccount = usersAccount;
    }

    @Override
    public String toString() {
        return "TheGame [id=" + id + ", start=" + start + ", end=" + end + ", active=" + active + ", usersAccount=" + usersAccount
                + "]";
    }
    
    // TODO Override hashCode() and equals
}
