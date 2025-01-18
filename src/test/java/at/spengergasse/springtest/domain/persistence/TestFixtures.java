package at.spengergasse.springtest.domain.persistence;

import at.spengergasse.springtest.domain.*;

import java.time.LocalDateTime;

public class TestFixtures {
    public static User severinUser() {
        return User.builder()
                .name("Severin")
                .role(Role.ADMIN)
                .email(new Email("severin.gebesmair@gmail.com"))
                .address(new Address("Spengergasse", "wien", "1050"))
                .build();
    }

    public static User maximUser() {
        return User.builder()
                .name("Maxim")
                .role(Role.USER)
                .email(new Email("maxim.heller@gmail.com"))
                .address(new Address("Bahnhofstraße", "wien", "1010"))
                .build();
    }

    public static User leoUser() {
        return User.builder()
                .name("Leo")
                .role(Role.GUEST)
                .email(new Email("leo.stanislaus.steiner@gmail.com"))
                .address(new Address("Karlskirche", "wien", "1040"))
                .build();
    }
}
