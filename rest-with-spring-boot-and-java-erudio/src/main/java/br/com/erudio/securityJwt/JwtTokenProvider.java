package br.com.erudio.securityJwt;

import br.com.erudio.data.vo.v1.security.TokenVO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("security.jwt.token.secret-key:secret")
    private  String secretKey = "secret";

    @Value("security.jwt.token.expire-lenght:3600000")
    private long validityInMinisecods = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAcessToke(String username, List<String> roles){
        Date now = new Date();
        Date validityDate = new Date(now.getTime()+validityInMinisecods);
        var acessToken = getAccessToken(username, roles, now, validityDate);
        var refreshToken = getRefreshToken(username, roles, now);
        return new TokenVO(username, true, now, validityDate,acessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validityDate) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validityDate).withSubject(username).withIssuer(issuerUrl).sign(algorithm).strip();
    }
    private String getRefreshToken(String username, List<String> roles, Date now) {

        Date validityDateRefreshToken = new Date(now.getTime()+ (validityInMinisecods * 3));
        return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validityDateRefreshToken).withSubject(username).sign(algorithm).strip();
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
    }
}
