package pe.edu.upc.mind.care.platform.iam.infrastructure.authorization.sfs.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 * This class is used to build the EmailPasswordAuthenticationToken object
 * that is used to authenticate the user.
 */
public class EmailPasswordAuthenticationTokenBuilder {

  /**
   * This method is responsible for building the EmailPasswordAuthenticationToken object.
   *
   * @param principal The user details.
   * @param request   The HTTP request.
   * @return The EmailPasswordAuthenticationToken object.
   * @see EmailPasswordAuthenticationToken
   * @see UserDetails
   */
  public static Authentication build(UserDetails principal,
                                     HttpServletRequest request) {

    var EmailPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal,
        null, principal.getAuthorities());
    EmailPasswordAuthenticationToken.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request));
    return EmailPasswordAuthenticationToken;
  }
}
