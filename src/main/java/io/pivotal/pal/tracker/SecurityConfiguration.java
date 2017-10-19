package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Value("${SECURITY_FORCE_HTTPS:false}")
    Boolean useHTTPS;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //check SECURITY_FORCE_HTTPS

        if(useHTTPS){
            http.authorizeRequests().antMatchers("/**").hasRole("USER").
                    and().requiresChannel().anyRequest().requiresSecure();

        }
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic().and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
