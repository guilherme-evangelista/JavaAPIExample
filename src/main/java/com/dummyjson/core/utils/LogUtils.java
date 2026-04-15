package com.dummyjson.core.utils;

import io.cucumber.java.Scenario;
import com.dummyjson.core.exeption.ValidationException;

public class LogUtils {

    private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();

    public static void setScenario(Scenario scenario) {
        currentScenario.set(scenario);
    }

    public static void logInfo(String value) {
        System.out.println(value);
        if (!value.contains("{}")) {
            Scenario scenario = currentScenario.get();
            if (scenario != null) {
                scenario.log("Log: " + value);
            }
        }
    }

    public static void logError(String value) {
        System.out.println(value);
        if (!value.contains("{}")) {
            Scenario scenario = currentScenario.get();
            if (scenario != null) {
                scenario.log("Log: " + value);
            }
            throw new ValidationException(value);
        }
    }
}
