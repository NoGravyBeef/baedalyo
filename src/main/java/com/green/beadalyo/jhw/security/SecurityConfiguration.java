package com.green.beadalyo.jhw.security;

import com.green.beadalyo.jhw.security.jwt.JwtAuthenticationAccessDeniedHandler;
import com.green.beadalyo.jhw.security.jwt.JwtAuthenticationEntryPoint;
import com.green.beadalyo.jhw.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ) //시큐리티에서 세션 사용을 하지 않음을 세팅
                .httpBasic(http -> http.disable())
                // (SSR 서버사이드 렌더링 하지 않는다. 즉 html화면을 백엔드가 만들지 않는다.)
                // 백엔드에서 화면을 만들지 않더라도 위 세팅을 끄지 않아도 괜찮다. 사용하지 않는 걸 끔으로써 리소스 확보
                // 하기 위해서 사용하는 개념
                // 정리하면, 시큐리티에서 제공해주는 로그인 화면 사용하지 않겠다.
                .formLogin(form -> form.disable()) //form 로그인 방식을 사용하지 않음을 세팅
                .csrf(csrf -> csrf.disable()) //CSRF (CORS랑 많이 헷갈려 함)
                //requestMatchers
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(
                                        //회원가입, 로그인 인증이 안 되어 있더라도 사용 가능하게 세팅
                                        "/api/user/sign-up"
                                        ,"/api/user/sign-in"
                                        ,"/api/user/access-token"

                                        //swagger 사용할 수 있게 세팅
                                        , "/swagger"
                                        , "/swagger-ui/**"
                                        , "/v3/api-docs/**"

                                        //사진
                                        , "/pic/**"
                                        ,   "/fimg/**"

                                        //프론트 화면 보일수 있게 세팅
                                        ,"/"
                                        ,"/index.html"
                                        , "/css/**"
                                        , "/js/**"
                                        , "/static/**"

                                        //프론트에서 사용하는 라우터 주소
                                        , "/sign-in"
                                        , "/sign-up"
                                        , "/profile/*"
                                        , "/feed"

                                        //actuator
                                        ,"/actuator"
                                        ,"/actuator/*"

                                ).permitAll()
                                .anyRequest().permitAll()
//                                .anyRequest().authenticated() //로그인이 되어 있어야만 허용
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                        .accessDeniedHandler(new JwtAuthenticationAccessDeniedHandler())
                )
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}