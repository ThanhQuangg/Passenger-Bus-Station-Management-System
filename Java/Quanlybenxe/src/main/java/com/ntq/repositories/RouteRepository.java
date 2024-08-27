package com.ntq.repositories;

import com.ntq.pojo.Route;
import java.util.List;
import java.util.Map;


public interface RouteRepository {

//    List<Route> getRoutes();

    List<Route> getRoutes(Map<String, String> params);

    void addOrUpdate(Route r);

    Route getRouteById(int routeID);

    void deleteRoute(int routeID);
}
