package com.backbase.assignment.matnic.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.assignment.matnic.dlg.GetAtmsDelegate;
import com.backbase.assignment.matnic.model.to.GetAtmsFilter;
import com.backbase.assignment.matnic.model.to.GetAtmsResponse;

@RestController
public class ATMRestController extends AbstractSecurityWebApplicationInitializer {

	public ATMRestController() {
		super(SecurityConfiguration.class);
	}

	@Autowired
	private GetAtmsDelegate getAtmsDelegate_;

	@RequestMapping(value = "/atms", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GetAtmsResponse getAtms(@RequestParam(required = false) String city) {

		return getAtmsDelegate_.getAtms(new GetAtmsFilter(city));
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@Configuration
	@EnableWebSecurity
	@Order(1)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			CookieCsrfTokenRepository tokenRepo = new CookieCsrfTokenRepository();
			tokenRepo.setCookieHttpOnly(false);
			
			http.httpBasic().and().authorizeRequests().antMatchers("/index.html", "/pages/**", "/js/**", "/css/**", "/").permitAll().anyRequest().authenticated().and().csrf().csrfTokenRepository(tokenRepo);
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("frank").password("123").roles("ADMIN");
		}

	}

}
