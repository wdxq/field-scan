package ink.lsq.util.fieldscan;

import lombok.Data;

import java.util.Map;

@Data
public class BaseTest {

    private String code;

    private Map<String, B> bMap;

    private A a;

}
