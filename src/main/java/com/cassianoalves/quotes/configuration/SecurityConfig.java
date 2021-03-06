package com.cassianoalves.quotes.configuration;

import com.cassianoalves.quotes.component.security.RESTAuthenticationEntryPoint;
import com.cassianoalves.quotes.component.security.RESTAuthenticationFailureHandler;
import com.cassianoalves.quotes.component.security.RESTAuthenticationSuccessHandler;
import com.cassianoalves.quotes.component.security.RESTLogoutSuccessHandler;
import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RESTAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private RESTLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthenticationProvider quotesAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/user/confirm/*").permitAll()
                .antMatchers(HttpMethod.GET, "/invite/*").permitAll()
                .antMatchers(HttpMethod.POST, "/invite/init").permitAll()
                .antMatchers(HttpMethod.GET, "/book/*/quote/random").permitAll()
                .antMatchers(HttpMethod.POST, "/contact/mailme").permitAll()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .formLogin()
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .permitAll()
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(quotesAuthenticationProvider)
//                .inMemoryAuthentication()
//                .withUser("user@quotes.com").password("password").roles("USER")
        ;
    }
}
