/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    public UsersAccountServiceImpl(UsersAccountRepository usersAccountRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usersAccountRepository = usersAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void saveUsersAccount(UsersAccount usersAccount) {
        usersAccountRepository.save(usersAccount);
    }
    
    @Override
    public UsersAccount findUsersAccountByAccountName(String accountName) {
        return usersAccountRepository.findByAccountName(accountName);
    }
    
    @Override
    public void encodePassword(UsersAccount usersAccount) {
        
        String rawPassword = usersAccount.getPassword();
        usersAccount.setPassword(passwordEncoder.encode(rawPassword));
    }
    
    @Override
    public Boolean comparePasswordWithPasswordConfirm(UsersAccount usersAccount) {
        
        String password = usersAccount.getPassword();
        String passwordConfirm = usersAccount.getPasswordConfirm();
        
        if(password.equals(passwordConfirm)) {
            return true;
            
        } else {
            return false;
        }
    }
}
