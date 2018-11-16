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
import javax.validation.constraints.NotNull;

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

    @NotBlank(message = "*Nazwa jest obowiązkowa.")
    @Column(name = "task_name")
    private String taskName;

    @NotNull(message = "*Wartość punktowa jest obowiązkowa.")
    @Min(value = 1, message = "*Zadanie musi być warte choć 1 pkt.")
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
        this.taskName = taskName.trim();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pointsValue == null) ? 0 : pointsValue.hashCode());
        result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
        result = prime * result + ((usersAccount == null) ? 0 : usersAccount.hashCode());
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
        Task other = (Task) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (pointsValue == null) {
            if (other.pointsValue != null) {
                return false;
            }
        } else if (!pointsValue.equals(other.pointsValue)) {
            return false;
        }
        if (taskName == null) {
            if (other.taskName != null) {
                return false;
            }
        } else if (!taskName.equals(other.taskName)) {
            return false;
        }
        if (usersAccount == null) {
            if (other.usersAccount != null) {
                return false;
            }
        } else if (!usersAccount.equals(other.usersAccount)) {
            return false;
        }
        return true;
    }
}
