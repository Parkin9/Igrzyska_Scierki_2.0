/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.parkin9.Igrzyska_Scierki.models.Player;

/**
 * @author parkin9
 *
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
