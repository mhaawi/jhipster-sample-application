package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.ParamType;

/**
 * A ApiParam.
 */
@Entity
@Table(name = "api_param")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApiParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "param_type", nullable = false)
    private ParamType paramType;

    @NotNull
    @Column(name = "param_name", nullable = false)
    private String paramName;

    @NotNull
    @Column(name = "param_default_value", nullable = false)
    private String paramDefaultValue;

    @ManyToOne
    @JsonIgnoreProperties("apiParams")
    private ApiDefination apiDefination;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParamType getParamType() {
        return paramType;
    }

    public ApiParam paramType(ParamType paramType) {
        this.paramType = paramType;
        return this;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public ApiParam paramName(String paramName) {
        this.paramName = paramName;
        return this;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDefaultValue() {
        return paramDefaultValue;
    }

    public ApiParam paramDefaultValue(String paramDefaultValue) {
        this.paramDefaultValue = paramDefaultValue;
        return this;
    }

    public void setParamDefaultValue(String paramDefaultValue) {
        this.paramDefaultValue = paramDefaultValue;
    }

    public ApiDefination getApiDefination() {
        return apiDefination;
    }

    public ApiParam apiDefination(ApiDefination apiDefination) {
        this.apiDefination = apiDefination;
        return this;
    }

    public void setApiDefination(ApiDefination apiDefination) {
        this.apiDefination = apiDefination;
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
        ApiParam apiParam = (ApiParam) o;
        if (apiParam.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apiParam.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApiParam{" +
            "id=" + getId() +
            ", paramType='" + getParamType() + "'" +
            ", paramName='" + getParamName() + "'" +
            ", paramDefaultValue='" + getParamDefaultValue() + "'" +
            "}";
    }
}
