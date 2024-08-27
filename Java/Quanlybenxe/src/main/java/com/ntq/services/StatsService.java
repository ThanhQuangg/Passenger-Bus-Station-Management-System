package com.ntq.services;

import java.util.List;

public interface StatsService {
     List<Object[]> statsRevenueByPeriod(int year, String period);
}
