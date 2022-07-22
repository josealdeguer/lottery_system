package com.bynder.lottery_system.domain.service;

import com.bynder.lottery_system.domain.Lottery;
import com.bynder.lottery_system.domain.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class WinnerSelection {
    @Autowired
    private LotteryService lotteryService;

    /**
     * This method runs every day at midnight and selects the
     * winners of the lotteries that finished on that day.
     */
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void selectWinner() {
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
