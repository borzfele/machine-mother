package com.borzfele.machinemother.services;

import com.borzfele.machinemother.models.ActivationCode;
import com.borzfele.machinemother.repositories.ActivationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationCodeService {

    private final ActivationCodeRepository activationCodeRepository;


    @Autowired
    public ActivationCodeService(ActivationCodeRepository activationCodeRepository) {
        this.activationCodeRepository = activationCodeRepository;
    }

    public ActivationCode findByActivationCode(String activationCode) {
        return activationCodeRepository.findByActivationCode(activationCode);
    }
}
