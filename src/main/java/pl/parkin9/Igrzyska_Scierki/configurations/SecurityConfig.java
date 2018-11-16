/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author parkin9
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder; 

//////////////////////////////////////////////////////////////////////
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
                .antMatchers("/welcome").permitAll()
                .antMatchers("/registration").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/welcome")
                .loginProcessingUrl("/welcome")
                .failureUrl("/welcome?error")
                .usernameParameter("accountName")
                .passwordParameter("password")
                .defaultSuccessUrl("/panel", true)
                .and()
            .logout()
                .logoutSuccessUrl("/welcome?logout")
                .logoutUrl("/bye")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.jdbcAuthentication().dataSource(dataSource)
                                 .usersByUsernameQuery("SELECT account_name, password, enable FROM users_accounts WHERE account_name=?")
                                 .authoritiesByUsernameQuery("SELECT account_name, 'ROLE_USER' FROM users_accounts WHERE account_name=?")
                                 .passwordEncoder(passwordEncoder);
    }
 
    @Override
    public void configure(WebSecurity web) throws Exception {
        
        web.ignoring().antMatchers("/css/**");
    }
}