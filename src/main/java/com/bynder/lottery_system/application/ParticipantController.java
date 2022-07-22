package com.bynder.lottery_system.application;

import com.bynder.lottery_system.domain.Participant;
import com.bynder.lottery_system.domain.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    // Handles Get call to get all participants
    @GetMapping("/participants")
    List<Participant> all() {
        return participantService.getAllParticipants();
    }
}
