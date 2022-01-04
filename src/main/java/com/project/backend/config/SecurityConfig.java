package com.project.backend.config;

import com.project.backend.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ShopAccessDeniedHandler shopAccessDeniedHandler;

    @Bean
    public AuthFailHandler authFailHandler() {
        return new AuthFailHandler();
    };

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public BeforeAuthenticationFilter authFilter() throws Exception {
        BeforeAuthenticationFilter filter = new BeforeAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationFailureHandler(authFailHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/customer/**").hasAnyRole("CUSTOMER", "LYCUSTOMER", "ADMIN")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(shopAccessDeniedHandler)
                .and()
                .addFilter(authFilter())
                .formLogin().loginPage("/login").failureHandler(new AuthenticationFailureHandler() {

            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
                String email = request.getParameter("email");
                String error = exception.getMessage();
                System.out.println("A failed login attempt with email: "
                        + email + ". Reason: " + error);

                String redirectUrl = request.getContextPath() + "/login?error";
                response.sendRedirect(redirectUrl);
            }
        })
                .permitAll().and()
                .logout()
                .logoutSuccessUrl("/home")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean for REST client
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Filter customization through @Bean (@Component wouldn't offer all options)
    @Bean
    public FilterRegistrationBean loggingFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loggingFilter());
        registration.addUrlPatterns("/login");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("loggingFilter");
        registration.setOrder(1);
        return registration;
    }

    public Filter loggingFilter() {
        return new LoggingFilter();
    }
}
