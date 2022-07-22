package com.bynder.lottery_system.application;

import com.bynder.lottery_system.domain.Lottery;
import com.bynder.lottery_system.domain.Participant;
import com.bynder.lottery_system.domain.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    // Handles Get call to retrieve all lotteries/lotteries finished on a given date
    @GetMapping("/lotteries")
    List<Lottery> all(@RequestParam(required = false) String finishDate) {
        LocalDate actualDate = LocalDate.now();

        if (finishDate != null) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate finishDateAux = LocalDate.parse(finishDate, df);

            if (finishDateAux != null && actualDate.isAfter(finishDateAux)) {
                List<Lottery> lotteriesAux = lotteryService.getAllLotteries();
                List<Lottery> lotteries = lotteriesAux.stream()
                        .filter(l -> l.getFinishDate().equals(finishDateAux)).collect(Collectors.toList());
                return lotteries;
            }
        }
        return lotteryService.getAllLotteries();
    }

    // Handles Post call to add new Participant to a Lottery
    @PostMapping("/lotteries/{lotteryId}/participants")
    public String addParticipant(@PathVariable(value = "lotteryId") Long lotteryId, @RequestBody Participant newParticipant) {
        Lottery lottery = lotteryService.getLottery(lotteryId);

        if(lottery.getStatus() == Lottery.Status.ACTIVE){
            lottery.addParticipant(newParticipant);

            return lotteryService.saveLottery(lottery);
        } else {
            return "The lottery has already finished";
        }
    }

    // Handles Post call to add new Lottery
    @PostMapping("/lotteries")
    public String addLoterry(@RequestBody Lottery lottery) {
        return lotteryService.saveLottery(lottery);
    }

    // This method is only for testing purposes during presentation
    //@Scheduled(cron = "0 30 13 * * ?")
    @GetMapping("/selectWinner")
    public void testSelectWinner() {
        List<Lottery> lotteries = lotteryService.getAllLotteries();
        for (Lottery lottery: lotteries) {
            if(lottery.getStatus() == Lottery.Status.ACTIVE && lottery.getFinishDate().isBefore(LocalDate.now())) {
                List<Participant> lotteryParticipants = lottery.getParticipants();

                Random rand = new Random();
                Participant randomWinner = lotteryParticipants.get(rand.nextInt(lotteryParticipants.size()));

                lottery.setWinner(randomWinner);
                lotteryService.saveLottery(lottery);

                lottery.setStatus(Lottery.Status.FINISHED);
            }
        }
    }
}
