package xyz.znet.journalapp.repository;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.znet.journalapp.entity.JournalEntry;
import xyz.znet.journalapp.entity.User;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
