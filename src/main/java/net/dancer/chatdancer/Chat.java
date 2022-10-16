package net.dancer.chatdancer;

import java.sql.Timestamp;

public class Chat {

    private final long id;
    private String name;
    private final Timestamp created_at;

    public Chat(long id, String name, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }
}
