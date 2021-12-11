package pl.uhu87.toolsborrower;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/login").permitAll()      // -- dla wszykcih bez uwierzytelnienia
                .antMatchers("/tool/all").permitAll()
                .antMatchers("/user/**", "/reservation/**",
                        "/borrowing/**", "/notification/**", "/tool/toolUsers/**").hasAnyRole("USER")
                .antMatchers("/user/edit").authenticated()
                .and().formLogin()  // -- przekierowany do strony logowania
                .loginPage("/login")
                .and().logout().logoutSuccessUrl("/hello")       // domyslny widok wylogowania
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }
}
