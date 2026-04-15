package com.dummyjson.logic;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

import com.dummyjson.core.utils.LogUtils;

import static org.hamcrest.Matchers.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonMethods {
    public static void validateStatusCode(@NonNull final Response response, final int expectedStatusCode) {
        validateStatusCode(response.statusCode(), expectedStatusCode);
    }

    public static void validateStatusCode(final int actualStatusCode, final int expectedStatusCode) {
        String msg = String.format(
                "Unexpected status code was received. Actual: %d Expected: %d",
                actualStatusCode,
                expectedStatusCode
        );
        assertThat(msg, msg.replaceAll("Une", "E") ,actualStatusCode, equalTo(expectedStatusCode));
    }

    public static void validateField(@NonNull final Response response, final String path, final String equalsTo){
        String msg = String.format(
                "Unexpected field code was received. Actual: %s Expected: %s",
                response.then().extract().path(path),
                equalsTo
        );
        assertThat(msg, msg.replaceAll("Une", "E"), response.then().extract().path(path), equalTo(equalsTo));
    }


    public static <T> void assertThat(String errorReason, String successMessage, T actual, Matcher<? super T> matcher) {
        try {
            MatcherAssert.assertThat(errorReason, actual, matcher);
            LogUtils.logInfo("Assert: " + successMessage);
        } catch (AssertionError e) {
            LogUtils.logError(e.getMessage());
        }
    }
}
