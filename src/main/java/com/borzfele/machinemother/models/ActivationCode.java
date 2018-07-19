package com.borzfele.machinemother.models;

import javax.persistence.*;
import java.util.Random;

@Entity
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(unique = true)
    String activationCode;
    @OneToOne(fetch = FetchType.EAGER)
    User user;

    public ActivationCode() {
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvxyz1234567890";
        int codeLength = 66;

        for (int i = 0; i < codeLength; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }

        return stringBuilder.toString();

    }
}
