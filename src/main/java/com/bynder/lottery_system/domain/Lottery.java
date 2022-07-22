package com.bynder.lottery_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The Lottery class represents a lottery raffle
 */
@Entity
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDate finishDate;
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private Participant winner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lottery_id")
    private List<Participant> participants;

    public enum Status {
        ACTIVE, FINISHED
    }

    public Lottery(){}

    public Lottery(String name) {
        this.name = name;
    }

    public Lottery(String name, LocalDate finishDate) {
        this(name, finishDate, Status.ACTIVE);
    }

    public Lottery(String name, LocalDate finishDate, Status status) {
        this.name = name;
        this.finishDate = finishDate;
        this.status = status;
    }

    public Lottery(String name, LocalDate finishDate, Status status, List<Participant> participants) {
        this.name = name;
        this.finishDate = finishDate;
        this.status = status;
        this.participants = participants;
    }

    public Lottery(String name, LocalDate finishDate, Status status, List<Participant> participants, Participant winner) {
        this.name = name;
        this.finishDate = finishDate;
        this.status = status;
        this.participants = participants;
        this.winner = winner;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Participant getWinner() {
        return winner;
    }

    public void setWinner(Participant winner) {
        this.winner = winner;
    }
}
