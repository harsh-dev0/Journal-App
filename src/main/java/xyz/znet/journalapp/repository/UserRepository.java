package xyz.znet.journalapp.repository;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.znet.journalapp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(@NonNull String userName);
    User deleteByUserName(@NonNull String userName);
}
