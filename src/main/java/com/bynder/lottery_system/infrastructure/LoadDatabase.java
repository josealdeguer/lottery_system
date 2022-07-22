package com.bynder.lottery_system.infrastructure;

import com.bynder.lottery_system.domain.Lottery;
import com.bynder.lottery_system.domain.Participant;
import com.bynder.lottery_system.domain.service.LotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(LotteryService lotteryService) {

        return args -> {
            log.info("Preloading -- " + lotteryService.saveLottery(
                    new Lottery(
                            "iPhone 13",
                            LocalDate.of(2022, 07, 18),
                            Lottery.Status.FINISHED,
                            Arrays.asList(new Participant("jose@gmail.com"), new Participant("luis@gmail.com"), new Participant("carla@gmail.com")),
                            new Participant("jose@gmail.com")
                    )));
            log.info("Preloading -- " + lotteryService.saveLottery(
                    new Lottery(
                            "PlayStation 5",
                            LocalDate.of(2022, 07, 20),
                            Lottery.Status.FINISHED,
                            Arrays.asList(new Participant("jose@gmail.com"), new Participant("luis@gmail.com")),
                            new Participant("luis@gmail.com")
                    )));
            log.info("Preloading -- " + lotteryService.saveLottery(
                    new Lottery(
                            "Bali Trip",
                            LocalDate.of(2022, 07, 20),
                            Lottery.Status.FINISHED,
                            Arrays.asList(new Participant("luis@gmail.com"), new Participant("carla@gmail.com")),
                            new Participant("carla@gmail.com")
                    )));
            log.info("Preloading -- " + lotteryService.saveLottery(
                    new Lottery(
                            "MacBook Air M2",
                            LocalDate.of(2022, 07, 21),
                            Lottery.Status.ACTIVE,
                            Arrays.asList(new Participant("jose@gmail.com"), new Participant("luis@gmail.com"))
                    )));
            log.info("Preloading -- " + lotteryService.saveLottery(
                    new Lottery(
                            "Gym membership",
                            LocalDate.of(2022, 07, 25),
                            Lottery.Status.ACTIVE,
                            Arrays.asList(new Participant("jose@gmail.com"), new Participant("luis@gmail.com"))
                    )));
            log.info("Preloading -- " + lotteryService.saveLottery(
                    new Lottery(
                            "Samsung Galaxy S22",
                            LocalDate.of(2022, 07, 26),
                            Lottery.Status.ACTIVE
                    )));
            log.info("" + LocalDateTime.now());
        };
    }
}
