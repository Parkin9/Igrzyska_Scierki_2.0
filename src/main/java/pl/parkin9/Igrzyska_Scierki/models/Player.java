/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author parkin9
 *
 */
@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @NotBlank(message = "*Gracz musi posiadać nazwę")
    @Column(name = "player_name")
    private String playerName;

    @Column(name = "score")
    private Integer score = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_account_id")
    private UsersAccount usersAccount;

/////////////////////////////////////////////////////////////
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public UsersAccount getUsersAccount() {
        return usersAccount;
    }

    public void setUsersAccount(UsersAccount usersAccount) {
        this.usersAccount = usersAccount;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + playerName + ", score=" + score + ", usersAccount=" + usersAccount + "]";
    }
    
    // TODO Override hashCode() and equals
}
