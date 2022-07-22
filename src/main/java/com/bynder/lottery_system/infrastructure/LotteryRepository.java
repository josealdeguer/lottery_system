package com.bynder.lottery_system.infrastructure;

import com.bynder.lottery_system.domain.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery, Long> {
}
