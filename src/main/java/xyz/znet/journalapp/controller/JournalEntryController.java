package xyz.znet.journalapp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.znet.journalapp.entity.JournalEntry;
import xyz.znet.journalapp.entity.User;
import xyz.znet.journalapp.service.JournalEntryService;
import xyz.znet.journalapp.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesofUser(@PathVariable String userName) {
        User user = userService.findByUsername(userName);
        List<JournalEntry> all =user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myentry, @PathVariable String userName) {

        try {

            journalEntryService.saveEntry( myentry, userName );

            return new ResponseEntity<>(myentry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("id/{myId}")

    public ResponseEntity<JournalEntry> getjournalEntrybyId(@PathVariable ObjectId myId){
        Optional <JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deletejournalEntrybyId(@PathVariable ObjectId myId, @PathVariable String username) {
         journalEntryService.deleteById(myId, username);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String username) {

       JournalEntry old =  journalEntryService.findById(id).orElse(null);
       if(old !=null){
           old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
           old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
           journalEntryService.saveEntry(old);

           return new ResponseEntity<>(HttpStatus.OK);
       }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}
