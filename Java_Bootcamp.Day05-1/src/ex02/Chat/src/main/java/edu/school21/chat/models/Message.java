package edu.school21.chat.models;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Message {
    private Long id;
    private User author;
    private ChatRoom room;
    private String text;
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Message : {\n")
                .append("\tid=").append(id).append(",\n");
        if (author != null) {
            result.append("\tauthor={")
                    .append("id=").append(author.getId()).append(", ")
                    .append("login=\"").append(author.getLogin()).append("\", ")
                    .append("password=\"").append(author.getPassword()).append("\", ")
                    .append("createdRooms=null, socializeRooms=null")
                    .append("},\n");
        } else {
            result.append("\tauthor=null,\n");
        }
        if (room != null) {
            result.append("\troom={")
                    .append("id=").append(room.getId()).append(", ")
                    .append("name=\"").append(room.getName()).append("\", ")
                    .append("creator=null, messages=null")
                    .append("},\n");
        } else {
            result.append("\troom=null,\n");
        }
        result.append("\ttext=")
                .append("\"")
                .append(text != null ? text : "null")
                .append("\"")
                .append(",\n");
        result.append("\tdateTime=")
                .append(dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) : "null")
                .append(",\n}");
        return result.toString();
    }
}