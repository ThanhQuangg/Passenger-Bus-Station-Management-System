package com.ntq.repositories;

import java.util.List;


public interface StatsRepository {
//    List<Object[]> statsRevenueByRoute();
    List<Object[]> statsRevenueByPeriod(int year, String period);
}
