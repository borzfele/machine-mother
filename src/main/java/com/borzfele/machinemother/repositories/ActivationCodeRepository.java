package com.borzfele.machinemother.repositories;

import com.borzfele.machinemother.models.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {

    ActivationCode findByActivationCode(String activationCode);

}
