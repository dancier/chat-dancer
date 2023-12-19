package net.dancier.chatdancer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class JacksonTest extends AbstractPostgreSQLEnabledTest{

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testSerialisation() throws JsonProcessingException {
        String serializedOffsetTimeStamp = objectMapper.writeValueAsString(OffsetDateTime.now(ZoneId.of("UTC")));
        then(serializedOffsetTimeStamp).isNotNull();
        OffsetDateTime deserialize = objectMapper.readValue(serializedOffsetTimeStamp, OffsetDateTime.class);
        then(deserialize).isNotNull();
    }
}
