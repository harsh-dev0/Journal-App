package xyz.znet.journalapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.znet.journalapp.cache.AppCache;
import xyz.znet.journalapp.entity.User;
import xyz.znet.journalapp.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;

    @GetMapping("/health-check")
    public String healthCheck() {
        log.info("Health is ok !");
        return "Ok";
    }


    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewEntry(user);
    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
