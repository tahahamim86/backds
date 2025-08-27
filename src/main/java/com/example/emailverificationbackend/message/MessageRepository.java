package com.example.emailverificationbackend.message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.emailverificationbackend.appuser.AppUser;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiver(AppUser sender, AppUser receiver);
    
    @Query("SELECT m FROM Message m WHERE (m.sender = :AppUser1 AND m.receiver = :AppUser2) OR (m.sender = :AppUser2 AND m.receiver = :AppUser1) ORDER BY m.timestamp ASC")
    List<Message> findBySenderOrReceiver(@Param("AppUser1") AppUser AppUser1, @Param("AppUser2") AppUser AppUser2);
    
    List<Message> findBySenderOrderByTimestampDesc(AppUser sender);
    List<Message> findByReceiverOrderByTimestampDesc(AppUser receiver);
}
