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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author parkin9
 *
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long id;

    @NotBlank(message = "*Pole obowiązkowe.")
    @Column(name = "task_name")
    private String taskName;

    @NotBlank(message = "*Pole obowiązkowe.")
    @Min(value = 1, message = "Zadanie musi być coś warte.")
    @Column(name = "points_value")
    private Integer pointsValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_account_id")
    private UsersAccount usersAccount;

////////////////////////////////////////////////////////////
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getPointsValue() {
        return pointsValue;
    }

    public void setPointsValue(Integer pointsValue) {
        this.pointsValue = pointsValue;
    }

    public UsersAccount getAccount() {
        return usersAccount;
    }

    public void setUsersAccount(UsersAccount usersAccount) {
        this.usersAccount = usersAccount;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", taskName=" + taskName + ", pointsValue=" + pointsValue + ", usersAccount=" + usersAccount + "]";
    }

    // TODO Override hashCode() and equals
}
