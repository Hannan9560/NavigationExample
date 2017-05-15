package com.example.hannantalukder.navigationexample.Modules;

import java.util.List;

/**
 * Created by Hannan Talukder on 5/2/2017.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
