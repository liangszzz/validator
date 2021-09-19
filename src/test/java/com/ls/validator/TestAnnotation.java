package com.ls.validator;

import com.ls.validator.annotations.Min;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class TestAnnotation {

    @Getter
    @Setter
    static class Demo {

        private String name;

        @Min(18)
        private Integer age;

        @Min(18)
        private Long age2;

        @Min(18)
        private String errAge;

        @Min(18)
        private String errAge2;
    }


    @Test
    public void test() {

        Demo demo = new Demo();
        demo.setName("aa");
        demo.setAge(18);
        demo.setAge2(18L);
        demo.setErrAge("18");
        demo.setErrAge2("18a");

        List<String> validator = Validator.validator(demo);
        Assertions.assertNotNull(validator);
    }


}
