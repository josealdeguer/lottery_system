package com.bynder.lottery_system.infrastructure;

import com.bynder.lottery_system.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
