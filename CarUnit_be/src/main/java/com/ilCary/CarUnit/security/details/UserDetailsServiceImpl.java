package softLock.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softLock.entities.users.User;
import softLock.repositories.users.UserRepository;

import java.util.Optional;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = ur.findByUsernameAllIgnoreCase(username);

		if (user.isPresent()) {
			return UserDetailsImpl.build(user.get());
		} else {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
	}

}
