package com.impacta.oauth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    private static final String ROOT_PATTERN = "/**";
    public WebSecurityConfiguration(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
        return passwordEncoder;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        if (userDetailsService == null) {
            userDetailsService = new JdbcDaoImpl();
            ((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
        }
        return userDetailsService;
    }

    @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

    	// We don't need CSRF for this example
    	System.out.println("------------");
    	System.out.println("Passando pela configuracao de web permitindo web");
    	httpSecurity.csrf().disable().authorizeRequests()
          .antMatchers(HttpMethod.POST,"**/register/**").permitAll()
    	 .antMatchers(HttpMethod.POST,"**/token/**").permitAll()
    	 .antMatchers(HttpMethod.POST,"**/oauth/**").permitAll()
    	 .antMatchers(HttpMethod.POST,"/register").permitAll()
    	 .antMatchers(HttpMethod.POST,"/api/oauth/oauth/token").permitAll()
    	 .antMatchers(HttpMethod.POST,"**/oauth/**").permitAll()
    	 .antMatchers(HttpMethod.POST,"**/api/**").permitAll()
     	 .antMatchers(HttpMethod.POST,"/api/oauth/register").permitAll();
				 
  }

    
}
