package com.borzfele.machinemother.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "spring_session_attributes")
public class SessionAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "attribute_name")
    private String attributeName;
    @Column(name = "attribute_bytes")
    private byte[] attributeBytes;
}
