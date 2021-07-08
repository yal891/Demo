//package com.website.content.config;
//
//import com.website.content.Service.imp.MyUserDetailsService;
//import com.website.content.filter.JwtRequestFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfigBackup extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Autowired
//    MyUserDetailsService myUserDetailsService;
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
///*    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//
//        // Configure database-based user password query.
//        // Password uses BCryptEncoder (combined with random salt and encryption algorithm) that comes with security.
//        auth.userDetailsService(myUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }*/
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//                 httpSecurity.authorizeRequests()
//                .antMatchers("/authentication/*","/")
//                .permitAll()
//                .antMatchers("/user/**").hasAnyRole("USER")
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/authentication/login")
//                .loginProcessingUrl("/authentication/form")
//                .successForwardUrl("/user/hello")
//                         .and().exceptionHandling().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////       httpSecurity.csrf().disable()
////                .authorizeRequests().antMatchers("/users/login", "/hi").permitAll()
////                .antMatchers("/users/register").permitAll().
////                anyRequest().authenticated().and()
////                .exceptionHandling().and().sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
////        httpSecurity.csrf().disable()
////                //Cross-origin-resource-sharing
////                .cors().and()
////                .authorizeRequests()
////                .antMatchers("/authenticate", "/","/error","/hi" ,"/login" ).permitAll()// Allow authenticate method accessed without logging in
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .anyRequest().authenticated()// others need to be accessed after authentication
////                .and()
////                .logout().permitAll()
////                // redirect to the login page
////                .logoutRequestMatcher(new AntPathRequestMatcher("Users/logout","POST"))
////                .and().formLogin().loginPage("/users/login").and()
////                .exceptionHandling().and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Override
//    protected void configure( AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("abc").password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN","USER");
//        auth.inMemoryAuthentication().withUser("def").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
//
//    }
//
////    @Override
////    public UserDetailsService userDetailsService() {
////        UserDetails user =
////                User.withDefaultPasswordEncoder()
////                        .username("user")
////                        .password("password")
////                        .roles("USER")
////                        .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//
//
//}
