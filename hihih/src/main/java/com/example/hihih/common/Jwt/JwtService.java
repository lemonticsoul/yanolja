package com.example.hihih.common.Jwt;

import com.example.hihih.common.SecurityService.UserService;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private UserService userService;

    @Value("05b9aacf503ab94672ddec94ff26814e4fffa8b83688ef308f0485029455c7e0fc6012d573a47b8b7275832f4caf7aee848c99915f8386b73095700efd2aeb94")
    private String secret;

    private static final long ACCESS_TOKEN_EXPIRED_TIME = 1000L * 60L * 60L * 24L; // 1일
    // private static final long ACCESS_TOKEN_EXPIRED_TIME = 1000L * 10L; // 10초
    private static final long REFRESH_TOKEN_EXPIRED_TIME = 1000L * 60L * 60L * 24L * 7L; // 7일
    // private static final long REFRESH_TOKEN_EXPIRED_TIME = 1000L * 60L * 10L; // 10분
    private static final String ACCESS_TOKEN = "Authorization";
    private static final String REFRESH_TOKEN = "RefreshToken";
    private static final String BEARER = "Bearer ";



    private Key getSecretKey(){
        byte[] keyBytes=secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String username){
        Date date=new Date();

        Claims claims = Jwts.claims()
                .setSubject(ACCESS_TOKEN)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + ACCESS_TOKEN_EXPIRED_TIME));

        claims.put("username",username);

        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(claims)
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String createRefreshToken() {
        Date date = new Date();

        Claims claims = Jwts.claims()
                .setSubject(REFRESH_TOKEN)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + REFRESH_TOKEN_EXPIRED_TIME));

        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(claims)
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS512");
        return header;
    }
    public void sendAccessAndRefreshToken(HttpServletResponse response,String accessToken,String refreshToken){
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response,accessToken);
    }
    public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(ACCESS_TOKEN, BEARER + accessToken);
    }



    private Claims getClaims(String token){
        Key key=getSecretKey();

        try{
            System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody());
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }catch(ExpiredJwtException e){
            throw new ExpiredJwtException(null,null,"토큰이 만료되었습니다");
        }
    }
//    private Claims getClaims(String token) {
//        Claims claims;
//        try {
//            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        } catch (SignatureException e) {
//            throw new BadCredentialsException("잘못된 비밀키", e);
//        } catch (ExpiredJwtException e) {
//            throw new BadCredentialsException("만료된 토큰", e);
//        } catch (MalformedJwtException e) {
//            throw new BadCredentialsException("유효하지 않은 구성의 토큰", e);
//        } catch (UnsupportedJwtException e) {
//            throw new BadCredentialsException("지원되지 않는 형식이나 구성의 토큰", e);
//        } catch (IllegalArgumentException e) {
//            throw new BadCredentialsException("잘못된 입력값", e);
//        }
//        return claims;
//    }

    public String getUsername(String token){
        return getClaims(token).getSubject();
    }
    public  Boolean isTokenExpired(String accessToken){
        Key key=getSecretKey();
        Claims claims=getClaims(accessToken);
        return claims.getExpiration().before(new Date());
    }


    public boolean validateToken(String accessToken) {


        try{
            if(!isTokenExpired(accessToken)){
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims=getClaims(accessToken);
        UserDetails userDetails=userService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
