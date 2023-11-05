package net.dancier.chatdancer.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "chat")
@NoArgsConstructor
public class JpaChatEntity {

    @Id
    @GeneratedValue
    private UUID id;


}
