package com.example.mtb.entity;

import com.example.mtb.enums.UserRole;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name="user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id",nullable = false,updatable = false)
    private String userId;

    @Column(name = "user_name",nullable = false)
    private String username;

    @Column(name = "email",nullable = false,updatable = false)
    private String email;

    @Column(name = "password",nullable = false,updatable = false)
    private String password;

    @Column(name = "user_role",nullable = false,updatable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth",nullable = false)
    private LocalDate dateOfBirth;

    @CreatedDate
    @Column(name = "create_at",updatable = false)
    private Instant createAt;

    @LastModifiedDate
    @Column(name = "update_at",nullable = false,updatable = false)
    private Instant updateAt;

    @CreatedBy
    @Column(name = "created_by",nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "update_by",nullable = false)
    private String updatedBy;

    @Column(name = "is_delete",nullable = false)
    private boolean isDelete;


    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
