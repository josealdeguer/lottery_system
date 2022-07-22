package com.bynder.lottery_system.domain.service;

import com.bynder.lottery_system.domain.Participant;
import com.bynder.lottery_system.infrastructure.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepo;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepo) {
        this.participantRepo = participantRepo;
    }

    public List<Participant> getAllParticipants() {
        return participantRepo.findAll();
    }
}
