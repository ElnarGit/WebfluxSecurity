package net.elnar.webfluxsecurity.security;

import lombok.RequiredArgsConstructor;
import net.elnar.webfluxsecurity.entity.UserEntity;
import net.elnar.webfluxsecurity.exception.UnauthorizedException;
import net.elnar.webfluxsecurity.service.UserService;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
	private final UserService userService;
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
		return userService.getUserById(principal.getId())
				.filter(UserEntity::isEnabled)
				.switchIfEmpty(Mono.error(new UnauthorizedException("User disabled")))
				.map(user -> authentication);
	}
}
