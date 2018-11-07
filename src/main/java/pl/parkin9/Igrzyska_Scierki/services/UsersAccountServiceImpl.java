/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.parkin9.Igrzyska_Scierki.models.UsersAccount;
import pl.parkin9.Igrzyska_Scierki.repositories.UsersAccountRepository;

/**
 * @author parkin9
 *
 */

@Service
public class UsersAccountServiceImpl implements UsersAccountService {

    private final UsersAccountRepository usersAccountRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UsersAccountServiceImpl(UsersAccountRepository usersAccountRepository, PasswordEncoder passwordEncoder) {
        this.usersAccountRepository = usersAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void saveUsersAccount(UsersAccount usersAccount) {
        usersAccountRepository.save(usersAccount);
    }
    
    @Override
    public UsersAccount findUsersAccountByName(String name) {
        return usersAccountRepository.findByName(name);
    }
    
    @Override
    public void encodePassword(UsersAccount usersAccount) {
        
        String rawPassword = usersAccount.getPassword();
        usersAccount.setPassword(passwordEncoder.encode(rawPassword));
    }
}
