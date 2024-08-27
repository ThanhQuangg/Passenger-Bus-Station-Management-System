package com.ntq.services.impl;

import com.ntq.pojo.Route;
import com.ntq.repositories.RouteRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ntq.services.RouteService;

@Service
@Transactional
public class RouteServicesImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Route> getRoutes(Map<String, String> params) {
        return this.routeRepository.getRoutes(params);
    }

    @Override
    public void addOrUpdate(Route r) {
        this.routeRepository.addOrUpdate(r);
    }

    @Override
    public Route getRoutesById(int routeID) {
        return this.routeRepository.getRouteById(routeID);
    }

    @Override
    public void deleteRoute(int routeID) {
        this.routeRepository.deleteRoute(routeID);
    }



    
}
