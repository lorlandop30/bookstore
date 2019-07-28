package com.bookstore.bookstore.Configuration;

import com.bookstore.bookstore.services.UserSecurityService;
import com.bookstore.bookstore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private UserSecurityService userSecurityService;

    private BCryptPasswordEncoder passwordEncoder(){
        return  SecurityUtility.passwordEncoder();
    }

    // important update when adding a new template
    private static final String[] Public_MATCHERS={
            "/css/**",
            "/js/**",
            "/img/**",
            "/",
            "/MyAccount",
            "/newUser",
            "/forgetPassword",
            "/MyProfile",
            "/login",
            "/fonts/**",
            "/header",
            "/book/add",
            "/showGenres",
            "/bookshelf",
            "/bookDetail",
            "/book/genreBrowser",
            "/book/categoryBrowser",
            "/formbookshelf",
            "/searchTitle",
            "/bookauthorList"
    };

    @Override
    protected void configure (HttpSecurity http) throws  Exception{
       http
                .authorizeRequests().
               antMatchers(Public_MATCHERS).
               permitAll().anyRequest().authenticated();

       http
               .csrf().disable().cors().disable()
               .formLogin().failureUrl("/login?error")
               .defaultSuccessUrl("/")
               .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
}

@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
}
}
