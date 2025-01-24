package xyz.znet.journalapp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.znet.journalapp.entity.User;
import xyz.znet.journalapp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testsaveNewEntry(User user) {
        assertEquals(4, 2+2);
        assertTrue(userService.saveNewEntry(user));
    }
    @Disabled
    @ParameterizedTest
    // can use value source and enum source
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int e, int a , int b) {
        assertEquals(e, a + b, "failed for:"+ e);
    }

}
