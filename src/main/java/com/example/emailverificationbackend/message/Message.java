package com.example.emailverificationbackend.message;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.example.emailverificationbackend.appuser.AppUser;
@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private AppUser sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private AppUser receiver;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Default constructor
    public Message() {}

    // Constructor with parameters
    public Message(String content, AppUser sender, AppUser receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = LocalDateTime.now();
    }

  
}
