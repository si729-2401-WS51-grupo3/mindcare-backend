package pe.edu.upc.mind.care.platform.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.upc.mind.care.platform.iam.application.internal.outboundservices.hashing.HashingService;
import pe.edu.upc.mind.care.platform.iam.infrastructure.hashing.bcrypt.services.HashingServiceImpl;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
