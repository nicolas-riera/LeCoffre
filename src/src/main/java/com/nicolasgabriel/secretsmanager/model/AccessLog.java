package com.nicolasgabriel.secretsmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "access_logs")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "secret_id", nullable = false)
    private Secret secret;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accessed_by_user_id", nullable = false)
    private User accessedBy;

    @Column(name = "accessed_at", nullable = false, updatable = false)
    private LocalDateTime accessedAt;

    @PrePersist
    protected void onAccess() {
        this.accessedAt = LocalDateTime.now();
    }

    public AccessLog() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Secret getSecret() { return secret; }
    public void setSecret(Secret secret) { this.secret = secret; }

    public User getAccessedBy() { return accessedBy; }
    public void setAccessedBy(User accessedBy) { this.accessedBy = accessedBy; }

    public LocalDateTime getAccessedAt() { return accessedAt; }
}