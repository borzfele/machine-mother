package com.borzfele.machinemother.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "spring_session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "creation_time")
    private BigInteger creationTime;
    @Column(name = "last_access_time")
    private BigInteger lastAccessTime;
    @Column(name = "max_inactive_interval")
    private Integer maxInactiveInterval;
}
