package com.borzfele.machinemother.models;

import javax.persistence.*;

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
