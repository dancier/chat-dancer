package net.dancier.chatdancer.application.port.out;

import lombok.Data;

import java.util.Set;

@Data
public class SendReadFlagUpdatedEventDto {

    String messageId;
    Boolean read;
    String readerId;

}
