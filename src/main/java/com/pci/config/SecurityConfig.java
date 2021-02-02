package com.pci.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/css/**","/js/**","/images/**").permitAll()
				.antMatchers("/Mgr/**").hasRole("MANAGER")
				.antMatchers("/Staff/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginProcessingUrl("/login")	// /loginリクエストを受けて認証処理を行う
				.loginPage("/")					// ログイン画面のURL
				.defaultSuccessUrl("/index", true)	// ログイン認証成功時のURL
				.failureUrl("/login-error")			// ログイン認証失敗時のURL
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}
	
	/**
	 * AuthenticationProviderの設定を行う
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	void configureAuthenthicationManager(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	/**
	 * パスワードのハッシュ化
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}