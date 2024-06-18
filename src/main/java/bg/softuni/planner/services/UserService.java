package bg.softuni.planner.services;

import bg.softuni.planner.models.dtos.LoginDTO;
import bg.softuni.planner.models.dtos.RegisterDTO;
import bg.softuni.planner.models.entities.User;
import bg.softuni.planner.repositories.UserRepository;
import bg.softuni.planner.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }
//
//    public boolean userExits(String username, String password) {
//        Optional<User> byUsername = this.userRepository.findByUsernameAndPassword(username, password);
//
//        return byUsername.isPresent();
//    }

    public void register(RegisterDTO registerDTO) {
        User optUser = this.userRepository.findByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail()).orElse(null);

        if (optUser != null) {
            throw new RuntimeException("User with email " + registerDTO.getEmail() + " already exists!");
        }

        User newUser = this.modelMapper.map(registerDTO, User.class);
        newUser.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));

        this.userRepository.save(newUser);
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> byUsernameAndPassword = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (byUsernameAndPassword.isEmpty()) {
            return false;
        }
        boolean passMatches = passwordEncoder.matches(loginDTO.getPassword(), byUsernameAndPassword.get().getPassword());
        if (!passMatches) {
            return false;
        }
        currentUser.login(byUsernameAndPassword.get().getId(), byUsernameAndPassword.get().getPassword());
        return true;
    }

    public boolean isLoggedIn() {
        return this.currentUser.isLoggedIn();
    }

}
