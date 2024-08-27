/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Route;
import java.util.List;
import java.util.Map;

public interface RouteService {

//    List<Route> getRoutes();

    List<Route> getRoutes(Map<String, String> params);

    void addOrUpdate(Route r);

    Route getRoutesById(int routeID);

    void deleteRoute(int routeID);

}
