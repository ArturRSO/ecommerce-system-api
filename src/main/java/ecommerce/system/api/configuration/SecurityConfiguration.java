package ecommerce.system.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@ComponentScan("ecommerce.system.api")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    public void configure(HttpSecurity http) {

        try {
            http.cors()
                    .and()
                    .csrf().disable()
                    .authorizeRequests()
                    // ADDRESS
                    .antMatchers("/addresses/create").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/addresses/user/**").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/addresses/update").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/addresses/delete").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/addresses/**").permitAll()
                    // AUTHENTICATION
                    .antMatchers("/auth/login").permitAll()
                    .antMatchers("/auth/**").hasAnyRole("system_admin")
                    // ORDER
                    .antMatchers("/orders/create").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/orders/store/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/orders/user/**").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/orders/summary/**").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/orders/**").hasAnyRole("system_admin")
                    // PAYMENT METHOD
                    .antMatchers("/paymentmethods/**").permitAll()
                    // PRODUCT
                    .antMatchers("/products/create/**").hasAnyRole("store_admin")
                    .antMatchers("/products/store/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/products/update").hasAnyRole("store_admin")
                    .antMatchers("/products/delete/**").hasAnyRole("store_admin")
                    .antMatchers("/products/**").permitAll()
                    // REPORT
                    .antMatchers("/reports/orders/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/reports/orders").hasAnyRole("system_admin")
                    .antMatchers("/reports/products/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/reports/products").hasAnyRole("system_admin")
                    .antMatchers("/reports/stores/cashflow/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/reports/stores/cashflow/revenue/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/reports/stores/cashflow").hasAnyRole("system_admin")
                    .antMatchers("/reports/stores/cashflow/revenue").hasAnyRole("system_admin")
                    .antMatchers("/reports/stores/user/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/reports/stores/user").hasAnyRole("system_admin")
                    .antMatchers("/reports/**").hasAnyRole("system_admin")
                    // STORE
                    .antMatchers("/stores/create/**").hasAnyRole("store_admin")
                    .antMatchers("/stores/all").hasAnyRole("system_admin")
                    .antMatchers("/stores/user/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/stores/update/**").hasAnyRole("store_admin")
                    .antMatchers("/stores/delete/**").hasAnyRole("system_admin", "store_admin")
                    .antMatchers("/stores/**").permitAll()
                    // TELEPHONE
                    .antMatchers("/telephones/create").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/telephones/user/**").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/telephones/update").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/telephones/delete").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/telephone/**").permitAll()
                    // TEST
                    .antMatchers("/test/check").permitAll()
                    .antMatchers("/test/**").hasAnyRole("system_admin")
                    // USER
                    .antMatchers("/users/create").permitAll()
                    .antMatchers("/users/create/image/**").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/users/profile").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/users/update/**").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/users/recover/password/**").permitAll()
                    .antMatchers("/users/delete/profile/**").hasAnyRole("system_admin", "store_admin", "customer")
                    .antMatchers("/users/**").hasAnyRole("system_admin")
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        } catch (Exception e) {

            logger.error(e.getMessage());
        }
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.unmodifiableList(Collections.singletonList("*")));
        configuration.setAllowedMethods(Collections.unmodifiableList(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.unmodifiableList(Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
