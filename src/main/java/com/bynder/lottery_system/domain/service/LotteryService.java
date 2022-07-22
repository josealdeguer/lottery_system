package com.bynder.lottery_system.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bynder.lottery_system.infrastructure.LotteryRepository;
import com.bynder.lottery_system.domain.Lottery;

import java.util.List;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepo;

    @Autowired
    public LotteryService(LotteryRepository lotteryRepo) {
        this.lotteryRepo = lotteryRepo;
    }

    public String saveLottery(Lottery lottery) {
        lotteryRepo.save(lottery);

        return "Lottery saved into database: " + lottery.getName() + " - " + lottery.getId();
    }

    public Lottery getLottery(long id) {
        return lotteryRepo.getReferenceById(id);
    }

    public List<Lottery> getAllLotteries() {
        return lotteryRepo.findAll();
    }
}
