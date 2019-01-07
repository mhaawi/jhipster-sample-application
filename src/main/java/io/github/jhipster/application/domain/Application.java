package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Application.
 */
@Entity
@Table(name = "application")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "application_key", nullable = false)
    private String applicationKey;

    @NotNull
    @Column(name = "application_name", nullable = false)
    private String applicationName;

    @Column(name = "sms_active")
    private Boolean smsActive;

    @Column(name = "system_active")
    private Boolean systemActive;

    @Column(name = "email_active")
    private Boolean emailActive;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "application_notification_defination",
               joinColumns = @JoinColumn(name = "applications_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "notification_definations_id", referencedColumnName = "id"))
    private Set<NotificationDefination> notificationDefinations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationKey() {
        return applicationKey;
    }

    public Application applicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
        return this;
    }

    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public Application applicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Boolean isSmsActive() {
        return smsActive;
    }

    public Application smsActive(Boolean smsActive) {
        this.smsActive = smsActive;
        return this;
    }

    public void setSmsActive(Boolean smsActive) {
        this.smsActive = smsActive;
    }

    public Boolean isSystemActive() {
        return systemActive;
    }

    public Application systemActive(Boolean systemActive) {
        this.systemActive = systemActive;
        return this;
    }

    public void setSystemActive(Boolean systemActive) {
        this.systemActive = systemActive;
    }

    public Boolean isEmailActive() {
        return emailActive;
    }

    public Application emailActive(Boolean emailActive) {
        this.emailActive = emailActive;
        return this;
    }

    public void setEmailActive(Boolean emailActive) {
        this.emailActive = emailActive;
    }

    public Set<NotificationDefination> getNotificationDefinations() {
        return notificationDefinations;
    }

    public Application notificationDefinations(Set<NotificationDefination> notificationDefinations) {
        this.notificationDefinations = notificationDefinations;
        return this;
    }

    public Application addNotificationDefination(NotificationDefination notificationDefination) {
        this.notificationDefinations.add(notificationDefination);
        notificationDefination.getApplications().add(this);
        return this;
    }

    public Application removeNotificationDefination(NotificationDefination notificationDefination) {
        this.notificationDefinations.remove(notificationDefination);
        notificationDefination.getApplications().remove(this);
        return this;
    }

    public void setNotificationDefinations(Set<NotificationDefination> notificationDefinations) {
        this.notificationDefinations = notificationDefinations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Application application = (Application) o;
        if (application.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), application.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Application{" +
            "id=" + getId() +
            ", applicationKey='" + getApplicationKey() + "'" +
            ", applicationName='" + getApplicationName() + "'" +
            ", smsActive='" + isSmsActive() + "'" +
            ", systemActive='" + isSystemActive() + "'" +
            ", emailActive='" + isEmailActive() + "'" +
            "}";
    }
}
