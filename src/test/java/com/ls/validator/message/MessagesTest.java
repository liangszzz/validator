package com.ls.validator.message;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MessagesTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test() {
        Messages message = new Messages("message", "en", new String[]{"zh"});
        log.info("Message:{}", message);
        Assertions.assertNotNull(message);
    }

    @Test
    void test1() {
        Messages message = new Messages("message", "zh", new String[]{"en"});
        log.info("Message:{}", message);
        Assertions.assertNotNull(message);
    }
}