package com.dummyjson.steps;

import com.dummyjson.core.utils.LogUtils;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setupScenario(Scenario scenario) {
        LogUtils.setScenario(scenario);
    }
}
