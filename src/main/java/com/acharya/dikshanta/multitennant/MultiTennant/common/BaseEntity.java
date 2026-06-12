package com.acharya.dikshanta.multitennant.MultiTennant.common;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@SuperBuilder
public abstract class BaseEntity {
    @Id
    @UuidGenerator
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

}
