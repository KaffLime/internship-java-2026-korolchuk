package com.example.internshipjava2026korolchuk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "requests_history")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RequestHistory {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "travel_request_id", referencedColumnName = "id")
    TravelRequest travelRequest;

    @Column(name = "old_status")
    @Enumerated(EnumType.STRING)
    TravelRequestStatus oldStatus;

    @Column(name = "new_status")
    @Enumerated(EnumType.STRING)
    TravelRequestStatus newStatus;

    @Column(name = "changed_at")
    LocalDate changedAt;

    @Column(name = "comment")
    String comment;
}
