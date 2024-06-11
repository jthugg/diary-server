package com.github.jthugg.diary.external.data.access.mysql.utils;

import lombok.Getter;
import org.slf4j.Logger;

import java.util.concurrent.Callable;

public class TestUtils {

    public static <T> TestResult<T> execute(Callable<T> action) throws Exception {
        long startTime = System.currentTimeMillis();
        action.call();
        long duration = System.currentTimeMillis() - startTime;
        return TestResult.of(action.call(), duration);
    }

    public static class TestResult<T> {

        @Getter
        private final T result;
        private final long millis;

        private TestResult(T result, long millis) {
            this.result = result;
            this.millis = millis;
        }

        public static <T> TestResult<T> of(T result, long duration) {
            return new TestResult<>(result, duration);
        }

        public void log(Logger logger) {
            logger.info("\n\n----------Test Done----------\nduration: {}ms\nresult: {}\n", millis, result);
        }

    }

}
