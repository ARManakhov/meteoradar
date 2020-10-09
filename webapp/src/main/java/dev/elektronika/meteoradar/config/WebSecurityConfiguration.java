package dev.elektronika.meteoradar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
    @Autowired
    private FormLoginConfigurerEnhancer enhancer;
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable();


        //enhancer.addRecaptchaSupport(http.formLogin())
        http.formLogin()
                .loginPage("/signIn")
                .loginProcessingUrl("/signIn")
                .usernameParameter("email")
                .defaultSuccessUrl("/blog");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/signIn");

        http.authorizeRequests()
                .antMatchers("/css/**","/img/**","/fonts/**","/js/**","/scss/**","/token/**").permitAll() //todo add redirect
                .antMatchers().hasRole("ADMIN")
                .antMatchers("/signIn", "/signUp").anonymous()
                .antMatchers("/**").authenticated();

    }
}
