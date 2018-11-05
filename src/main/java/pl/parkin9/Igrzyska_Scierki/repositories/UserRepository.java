/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UsersAccount, Long> {

    UsersAccount findByUsername(String username);
}
