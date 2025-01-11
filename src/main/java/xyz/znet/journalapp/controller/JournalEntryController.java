package xyz.znet.journalapp.controller;

import org.springframework.web.bind.annotation.*;
import xyz.znet.journalapp.entity.JournalEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalentries = new HashMap();
    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalentries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myentry){

        journalentries.put(myentry.getId(), myentry);
        return true;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getjournalEntrybyId(@PathVariable Long myId){
        return journalentries.get(myId);
    }
    @DeleteMapping("id/{myId}")
    public JournalEntry deletejournalEntrybyId(@PathVariable Long myId){
        return journalentries.remove(myId);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable Long id, @RequestBody JournalEntry entry){
        return journalentries.put(id, entry);

    }
}
