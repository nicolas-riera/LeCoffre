package com.nicolasgabriel.secretsmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "shares",
    uniqueConstraints = @UniqueConstraint(columnNames = {"secret_id", "shared_with_user_id"})
)
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "secret_id", nullable = false)
    private Secret secret;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shared_with_user_id", nullable = false)
    private User sharedWith;

    @Column(name = "shared_at", nullable = false, updatable = false)
    private LocalDateTime sharedAt;

    @PrePersist
    protected void onShare() {
        this.sharedAt = LocalDateTime.now();
    }

    public Share() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Secret getSecret() { return secret; }
    public void setSecret(Secret secret) { this.secret = secret; }

    public User getSharedWith() { return sharedWith; }
    public void setSharedWith(User sharedWith) { this.sharedWith = sharedWith; }

    public LocalDateTime getSharedAt() { return sharedAt; }
}