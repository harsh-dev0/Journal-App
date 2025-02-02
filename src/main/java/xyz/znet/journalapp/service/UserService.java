package xyz.znet.journalapp.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.znet.journalapp.entity.User;
import xyz.znet.journalapp.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewEntry(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("Exception:", e);
// ways to log with he;p of slf4j
//            log.error("Error Occurred: {}", user.getUserName(), e);
//            log.warn("hahahhahhahahahah");
//            log.info("hahahhahhahahahah");
//            log.debug("hahahhahhahahahah");
//            log.trace("hahahhahhahahahah");
            return false;
        }

    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
    public void saveAdmin(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER","ADMIN"));
            userRepository.save(user);

        } catch (Exception e) {
            log.error("Exception:", e);
        }
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }
    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }
}
