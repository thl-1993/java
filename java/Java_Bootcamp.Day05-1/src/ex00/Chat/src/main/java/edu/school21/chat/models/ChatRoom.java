package edu.school21.chat.models;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
public class ChatRoom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messages;

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + (owner != null ? owner.getId() : "null") +
                ", messagesCount=" + (messages != null ? messages.size() : 0) +
                '}';
    }
}