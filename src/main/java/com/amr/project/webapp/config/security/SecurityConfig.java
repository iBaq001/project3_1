package com.amr.project.webapp.config.security;

import com.amr.project.service.impl.OidcUserServiceImpl;
import com.amr.project.webapp.config.google2fa.CustomAuthenticationProvider;
import com.amr.project.webapp.config.google2fa.CustomWebAuthenticationDetailsSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final SuccessUserHandler successUserHandler;

    private final LogoutSuccess logoutSuccess;

    @Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;


    //    @Bean
//    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        //final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
//        authProvider.setPostAuthenticationChecks(differentLocationChecker);
        return authProvider;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/feedback_service/**").authenticated()
                .antMatchers("/signup", "/confirm", "/code", "/confirm-account").permitAll()
                .antMatchers("/api/order/**").hasAnyAuthority("ADMIN","MODERATOR")

                .anyRequest()
//                .authenticated()
                .permitAll()
                .and()

                //логин с кастомным Auth provider (для 2FA)
                .formLogin().
                loginPage("/login").permitAll().
                loginProcessingUrl("/login")
                .successHandler(successUserHandler)
//                .defaultSuccessUrl("/")
//                .authenticationDetailsSource(authenticationDetailsSource)

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login").logoutSuccessHandler(logoutSuccess)
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .oidcUserService(oidcUserService());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**", "/js/**");
    }

    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserServiceImpl();
    }


}