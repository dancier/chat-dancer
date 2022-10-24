package net.dancer.chatdancer;

import java.sql.Timestamp;

public class Participants {
    private final long id;
    private final long user_id;
    private final long chat_id;
    private final long last_read_message_id;
    private final long notified_for_message_id;
    private final long last_received_message_id;
    private final Timestamp created_at;


    public Participants(long id, long user_id, long chat_id, long last_read_message_id, long notified_for_message_id, long last_received_message_id, Timestamp created_at) {
        this.id = id;
        this.user_id = user_id;
        this.chat_id = chat_id;
        this.last_read_message_id = last_read_message_id;
        this.notified_for_message_id = notified_for_message_id;
        this.last_received_message_id = last_received_message_id;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getChat_id() {
        return chat_id;
    }

    public long getLast_read_message_id() {
        return last_read_message_id;
    }

    public long getNotified_for_message_id() {
        return notified_for_message_id;
    }

    public long getLast_received_message_id() {
        return last_received_message_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }
}
