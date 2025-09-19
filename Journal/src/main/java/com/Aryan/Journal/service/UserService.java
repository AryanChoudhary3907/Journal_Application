package com.Aryan.Journal.service;

import com.Aryan.Journal.Repository.JournalEntryRepo;
import com.Aryan.Journal.Repository.UserRepo;
import com.Aryan.Journal.entity.JournalEntry;
import com.Aryan.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }

    public void delete(ObjectId id,String username){
        User user = userRepo.findByUsername(username);
        for (JournalEntry journalEntry : user.getJournalEntries()) {
            journalEntryRepo.deleteById(journalEntry.getId());
        }
        userRepo.deleteById(id);
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

}
