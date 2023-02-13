package com.whatap.api.demo.test_demo.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String userName;

    private String password;

    public void updateMembers(String userName, String password) {
        if (Objects.nonNull(userName)) {
            this.userName = userName;
        }

        if (Objects.nonNull(password)) {
            this.password = password;
        }
    }

}
