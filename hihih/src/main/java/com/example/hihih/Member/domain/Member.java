package com.example.hihih.Member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;

    private String refreshToken;

    private String role="MEMBER";


    private String password;

    private String nickname;

    public void passwordEncode(PasswordEncoder encoder) {
        this.password = encoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    public void destroyRefreshToken() {
        this.refreshToken = null;
    }


}
