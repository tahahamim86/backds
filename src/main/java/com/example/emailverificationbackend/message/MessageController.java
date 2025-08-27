package com.example.emailverificationbackend.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.emailverificationbackend.appuser.AppUser;
import com.example.emailverificationbackend.appuser.AppUserRepository;
import com.example.emailverificationbackend.userprofile.UserprofileRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(Authentication authentication) {
        String username = authentication.getName();
        AppUser user = userRepository.findByEmail(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<Message> messages = messageRepository.findBySenderOrReceiver(user, user);
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest messageRequest, Authentication authentication) {
        String senderUsername = authentication.getName();
        AppUser sender = userRepository.findByEmail(senderUsername)
            .orElseThrow(() -> new RuntimeException("Sender not found"));
        
        AppUser receiver = userRepository.findByEmail(messageRequest.getReceiverUsername())
            .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        // Send message via WebSocket
        messagingTemplate.convertAndSendToUser(
            messageRequest.getReceiverUsername(),
            "/queue/messages",
            savedMessage
        );

        return ResponseEntity.ok(savedMessage);
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message handleMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public static class MessageRequest {
        private String content;
        private String receiverUsername;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReceiverUsername() {
            return receiverUsername;
        }

        public void setReceiverUsername(String receiverUsername) {
            this.receiverUsername = receiverUsername;
        }
    }
}

