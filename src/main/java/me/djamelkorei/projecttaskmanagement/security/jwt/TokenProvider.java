package me.djamelkorei.projecttaskmanagement.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import me.djamelkorei.projecttaskmanagement.config.ApplicationProperties;
import me.djamelkorei.projecttaskmanagement.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

  private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

  private Key key;

  private long tokenValidityInSeconds;

  private long tokenValidityInSecondsForRememberMe;

  private final ApplicationProperties applicationProperties;

  public TokenProvider(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  @Override
  public void afterPropertiesSet() {
    byte[] keyBytes;
    String secret = applicationProperties.getSecurity().getJwt().getSecret();
    keyBytes = Decoders.BASE64.decode(secret);
    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.tokenValidityInSeconds = applicationProperties.getSecurity().getJwt().getTokenValidityInSeconds();
    this.tokenValidityInSecondsForRememberMe = applicationProperties.getSecurity().getJwt().getTokenValidityInSecondsForRememberMe();
  }

  public String createToken(Authentication authentication, boolean rememberMe) {
    User user = (User) authentication.getPrincipal();

    Instant validity = (rememberMe)
      ? Instant.now().plusSeconds(tokenValidityInSecondsForRememberMe)
      : Instant.now().plusSeconds(tokenValidityInSeconds);

    return Jwts.builder()
      .setSubject(authentication.getName())
      .claim("userId", user.getUserId())
      .claim("email", user.getEmail())
      .claim("role", user.getRole().getRoleName())
      .claim("authorities", user.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList()))
      .signWith(key, SignatureAlgorithm.HS512)
      .setExpiration(Date.from(validity))
      .compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .getBody();

    Collection<? extends GrantedAuthority> authorities = ((List<String>) claims.get("authorities"))
      .stream()
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());

    User principal = new User();
    principal.setUserId(((Number) claims.get("userId")).longValue());
    principal.setUsername((String) claims.get("username"));
    principal.setEmail((String) claims.get("email"));

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      log.info("Invalid JWT token.");
      log.trace("Invalid JWT token trace.", e);
    }
    return false;
  }

}
