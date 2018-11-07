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

/**
 * @author parkin9
 *
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                                 .usersByUsernameQuery("SELECT name, password, enabled FROM users_accounts WHERE name=?");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin().loginPage("/").permitAll()
            .and()
                .logout().permitAll();
    }
}
