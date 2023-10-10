package com.family.petmemory.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void printMessage() {
        String result = messageSource.getMessage("label.member.name", null, null);
        assertThat(result).isEqualTo("이름");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        assertThat(messageSource.getMessage("no_code", null, "기본 메시지", null)).isEqualTo("기본 메시지");
    }

    @Test
    void defaultLang() {
        assertThat(messageSource.getMessage("label.member.loginId", null, null)).isEqualTo("아이디");
        assertThat(messageSource.getMessage("label.member.loginId", null, Locale.KOREA)).isEqualTo("아이디");
    }

    @Test
    void enLang() {
        assertThat(messageSource.getMessage("label.member.password", null, Locale.ENGLISH)).isEqualTo("PASSWORD");
    }

    @Test
    void enumLang() {
        assertThat(messageSource.getMessage("BEEF", null, Locale.KOREAN)).isEqualTo("소고기");
        assertThat(messageSource.getMessage("BEEF", null, Locale.ENGLISH)).isEqualTo("Beef");
    }
}
