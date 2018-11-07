/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;

/**
 * @author parkin9
 *
 */
public interface UsersAccountService {
    
    void saveUsersAccount(UsersAccount usersAccount);

    UsersAccount findUsersAccountByName(String name);

    void encodePassword(UsersAccount usersAccount);

}
