package com.ls.validator;

import com.ls.validator.annotations.Min;
import com.ls.validator.message.Messages;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;

class TestAnnotation {

    @Getter
    @Setter
    static class Demo {

        private String name;

        @Min(value = 19)
        private Integer age;

        @Min(value = 19, message = "age2 not less than 17 xxx")
        private Long age2;

        @Min(value = 19, message = "demo.errAge", key = true)
        private String errAge;

        @Min(18)
        private String errAge2;
    }


    @Test
    void test() throws IllegalAccessException {

        Demo demo = new Demo();
        demo.setName("aa");
        demo.setAge(18);
        demo.setAge2(18L);
        demo.setErrAge("18");
        demo.setErrAge2("18a");

        Messages messages = new Messages("message", null, new String[]{"en", "zh"});
        Validator validator = new Validator(false, messages);
        Set<String> validate = validator.validate(demo);
        Assertions.assertNotNull(validate);
    }


}
