package com.ls.validator.annotations;

import com.ls.validator.Validator;
import com.ls.validator.message.Messages;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class MaxAnnotationTest {

    @Getter
    @Setter
    static class Demo {

        @Max(1)
        private byte max1;

        @Max(1)
        private byte max2;

        @Max(1)
        private int max3;

        @Max(1)
        private int max4;

        @Max(1)
        private long max5;

        @Max(1)
        private long max6;

        @Max(1)
        private double max7;

        @Max(1)
        private double max8;

        @Max(1)
        private String max9;

        @Max(1)
        private String max10;
    }

    @Test
    public void testMax() {

        Demo demo = new Demo();
        demo.setMax1((byte) 1);
        demo.setMax2((byte) 2);

        Messages messages = new Messages("message", null, new String[]{"en", "zh"});
        Validator validator = new Validator(false, messages);
        Set<String> validate = validator.validate(demo);
        Assertions.assertNotNull(validate);
    }
}
