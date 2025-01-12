package xyz.znet.journalapp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.znet.journalapp.entity.JournalEntry;
import xyz.znet.journalapp.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myentry){
        myentry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry( myentry );

        return myentry;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getjournalEntrybyId(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }
    @DeleteMapping("id/{myId}")
    public boolean deletejournalEntrybyId(@PathVariable ObjectId myId){
         journalEntryService.deleteById(myId);
         return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){

       JournalEntry old =  journalEntryService.findById(id).orElse(null);
       if(old !=null){
           old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
           old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
       }
        journalEntryService.saveEntry( old );

        return old;

    }
}
