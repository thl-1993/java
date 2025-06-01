package edu.school21.sockets.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private Long ownerId;
    private String sender;
    private String text;
    private LocalDateTime timestamp;

    public Message() {
    }

    public Message(String messageText, String sender) {
        this.text = messageText;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Message{" +
                "ownerId=" + ownerId +
                ", sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}