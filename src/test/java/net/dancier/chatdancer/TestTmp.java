package net.dancier.chatdancer;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestTmp {

    @Test
    public void test() {
        Clock australiaClock = Clock.system(ZoneId.of("Australia/Sydney"));
        Clock londonClock = Clock.system(ZoneId.of("Europe/London"));

        LocalDateTime austDT = LocalDateTime.now(australiaClock);
        LocalDateTime londDT = LocalDateTime.now(londonClock);
        System.out.println(austDT);
        System.out.println(londDT);
    }

}
