/**
 * 
 */
package pl.parkin9.Igrzyska_Scierki.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author parkin9
 *
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder; 

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.jdbcAuthentication().usersByUsernameQuery("SELECT username, password, enable FROM users_accounts WHERE username=?")
                                 .authoritiesByUsernameQuery("SELECT username, 'ROLE_USER' FROM users_accounts WHERE username=?")
                                 .dataSource(dataSource)
                                 .passwordEncoder(passwordEncoder);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/panel")
                .and()
            .logout()
                .permitAll();
    }
}