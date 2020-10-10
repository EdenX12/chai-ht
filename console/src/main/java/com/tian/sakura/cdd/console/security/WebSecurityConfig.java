package com.tian.sakura.cdd.console.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 安全控制中心,用于管控登录访问权限 spring security的配置类，定制spring security的一些行为
 * 
 * @author dell
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MyAuthenticationFailHandler failHandler;
	@Autowired
	private MyAuthenticationSuccessHandler successHandler;
	@Autowired
	private MyAccessDeniedHandler accessDeniedHandler;
	@Autowired
	private CurrUserDetailLoad currUserDetailLoad;
	@Autowired
	private MyLogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	private MyAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	public DaoAuthenticationProvider daoAuthenticationProvider() {
	    DaoAuthenticationProvider authProvider
	            = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(currUserDetailLoad);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}

	/**
	 * 该方法动态注册认证Provider
	 * @return
	 */
	@Bean
	public AuthenticationProvider decionAuthenticationProvider(){
		return daoAuthenticationProvider();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.authenticationProvider(decionAuthenticationProvider());
	}

	/**
	 * 用于请求拦截:WebSecurityConfigurerAdapter的拦截要优先于ResourceServerConfigurerAdapter
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * spring security
		 */
		// 禁用csrf防御机制(跨域请求伪造)，这么做在测试和开发会比较方便。
		http.csrf().disable();
		http.authorizeRequests()// authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护
				.anyRequest()
				.authenticated()// 所有请求认证后都可以访问
				;

		http.formLogin()
				//.loginPage("/")// 登陆页面 // 自定义登陆处理
				.loginProcessingUrl("/login")
				// 前后端分离
				.successHandler(successHandler)
				.failureHandler(failHandler)
				// 登陆页面用户任意访问
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true)
				// 退出行为任意访问
				.permitAll();

		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.accessDeniedHandler(accessDeniedHandler);
		http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

//		//设置对spring security的UserDetails进行session保存,这个必须要有，不然不会保存至session对应的缓存redis中
//		http.securityContext().securityContextRepository(securityContextRepository());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);

		//设置不进行访问控制的url static下的不做访问控制
		web.ignoring()
				.antMatchers("/views/*")
				.antMatchers("/static/**/*.*");

		web.ignoring().antMatchers("/index.html");

		web.ignoring().antMatchers("/demo/*");
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new Md5PasswordEncoder();
	}

}
