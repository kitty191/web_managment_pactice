package org.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testlog() {

        log.debug("计算开始");

        int sum = 0;
        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9, 8, 7, 7};
        for (int num : nums) {
            sum += num;
        }

        log.info("计算结果" + sum);
        log.debug("计算结束");

    }
}
