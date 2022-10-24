package net.dancer.chatdancer;

import java.sql.Timestamp;

public class Message {
    private final long id;
    private final long chat_id;
    private String text;
    private final long author_id;
    private final Timestamp created_at;


    public Message(long id, long chat_id, String text, long author_id, Timestamp created_at) {
        this.id = id;
        this.chat_id = chat_id;
        this.text = text;
        this.author_id = author_id;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public long getChat_id() {
        return chat_id;
    }

    public String getText() {
        return text;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }
}
