package xyz.znet.journalapp.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.znet.journalapp.entity.User;
import xyz.znet.journalapp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public void saveEntry(User user) {
        try {
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
