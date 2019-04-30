package app.hakon.ui.service;

import app.hakon.ui.model.User;
import app.hakon.ui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(s);
        if (!user.isPresent()) throw new UsernameNotFoundException("No user with username: " + s);

        return getUserDetails(user.get());
    }

    public UserDetails getUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.withUsername(user
                .getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().name())
                .build();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean match(String password1, String password2) {
        return passwordEncoder.matches(password1, password2);
    }


}
