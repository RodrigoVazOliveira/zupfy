package br.com.zupfy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATHERS_GET = {
            "/bandas/**",
            "/musicas/**",
            "/albums/**"
    };

    private static final String[] PUBLIC_MATHERS_POST = {
            "/bandas/**",
            "/musicas/**",
            "/albums/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.cors();
            http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATHERS_GET).permitAll()
                    .antMatchers(HttpMethod.POST, PUBLIC_MATHERS_POST).permitAll()
            .anyRequest().authenticated();
    }

    @Bean
    protected UrlBasedCorsConfigurationSource configuracaoDeCors() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
