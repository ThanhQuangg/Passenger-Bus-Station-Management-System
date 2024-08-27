package com.ntq.services.impl;

import com.ntq.repositories.StatsRepository;
import com.ntq.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsRepository statsRepo;
    
    @Override
    public List<Object[]> statsRevenueByPeriod(int year, String period) {
        return this.statsRepo.statsRevenueByPeriod(year, period);
    }
}
