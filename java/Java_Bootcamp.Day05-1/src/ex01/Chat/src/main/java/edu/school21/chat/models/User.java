package edu.school21.chat.models;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
public class User {
    private Long id;
    private String login;
    private String password;
    private List<ChatRoom> createdRooms;
    private List<ChatRoom> socializeRooms;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", createdRoomsCount=" + (createdRooms != null ? createdRooms.size() : 0) +
                ", socializeRoomsCount=" + (socializeRooms != null ? socializeRooms.size() : 0) +
                '}';
    }
}