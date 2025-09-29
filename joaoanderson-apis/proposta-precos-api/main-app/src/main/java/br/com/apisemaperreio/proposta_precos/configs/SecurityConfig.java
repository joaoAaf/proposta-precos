package br.com.apisemaperreio.proposta_precos.configs;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers("/h2/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/gerenciador-proposta/*/proposta/modelo").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/gerenciador-proposta/*/proposta/cadastrar").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/gerenciador-proposta/gerar-token").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/gerenciador-proposta/*/invalidar").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/gerenciador-proposta/limpar").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/gerenciador-proposta/**").hasAnyRole("ADMIN", "USER")
                                .anyRequest().authenticated())
                .httpBasic(withDefaults());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var encodedAdminPass = passwordEncoder().encode("adminPass");
        var encodedUserPass = passwordEncoder().encode("userPass");

        var admin = User.builder().username("admin").password(encodedAdminPass).roles("ADMIN").build();
        var user = User.builder().username("user").password(encodedUserPass).roles("USER").build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
