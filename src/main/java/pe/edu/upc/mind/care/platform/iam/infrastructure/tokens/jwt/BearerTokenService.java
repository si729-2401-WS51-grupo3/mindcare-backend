package pe.edu.upc.mind.care.platform.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import pe.edu.upc.mind.care.platform.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.upc.mind.care.platform.iam.infrastructure.tokens.jwt.services.TokenServiceImpl;

/**
 * This interface is a marker interface for the JWT token service.
 * It extends the {@link TokenService} interface.
 * This interface is used to inject the JWT token service in the {@link TokenServiceImpl} class.
 */
public interface BearerTokenService extends TokenService {

  String getBearerTokenFrom(HttpServletRequest token);
  String generateToken(Authentication authentication);
}
