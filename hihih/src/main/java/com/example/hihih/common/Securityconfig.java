package com.example.hihih.common;

import com.example.hihih.Member.Repository.MemberRepository;
import com.example.hihih.common.Jwt.JwtRequestFilter;
import com.example.hihih.common.Jwt.JwtService;
import com.example.hihih.common.SecurityService.UserService;
import com.example.hihih.common.auth.UsernamePasswordAuthenticationFilter;
import com.example.hihih.common.handler.SignInSuccessHandler;
import com.example.hihih.common.handler.SigninFailureHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class Securityconfig {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    private final String[] permitUrl = new String[]{"/swagger", "/swagger-ui.html", "/swagger-ui/**",
            "/api-docs", "/api-docs/**", "/v3/api-docs/**", "/uploadImage/**"};

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/error", "/favicon.ico");
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable).disable())
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                                .requestMatchers("/api/**").permitAll()
                        .requestMatchers(permitUrl).permitAll()
                        );

        http
                .addFilterAfter(usernamePasswordAuthenticationFilter(), LogoutFilter.class)
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//        config.setAllowedMethods(Collections.singletonList("*"));
//        config.setAllowCredentials(true);
//        config.setAllowedHeaders(Collections.singletonList("*"));
//        config.setExposedHeaders(Arrays.asList("Authorization", "Set-Cookie"));
//        config.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
        return delegatingPasswordEncoder;
    }

    @Bean
    public SignInSuccessHandler signInSuccessHandler() {
        return new SignInSuccessHandler(memberRepository, jwtService);
    }

    @Bean
    public SigninFailureHandler signInFailureHandler() {
        return new SigninFailureHandler();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter(objectMapper);
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(signInSuccessHandler());
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(signInFailureHandler());
        return usernamePasswordAuthenticationFilter;
    }
    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(jwtService,memberRepository);
    }


}