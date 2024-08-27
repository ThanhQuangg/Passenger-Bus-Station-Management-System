package com.ntq.formatters;

import com.ntq.pojo.Route;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class RouteFormatter implements Formatter<Route> {

    @Override
    public String print(Route route, Locale locale) {
        return String.valueOf(route.getRouteID());
    }

    @Override
    public Route parse(String routeID, Locale locale) throws ParseException {
        Route route = new Route();
        route.setRouteID(Integer.parseInt(routeID));
        
        return route;
    }
}
