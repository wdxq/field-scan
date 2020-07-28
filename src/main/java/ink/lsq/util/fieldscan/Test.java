package ink.lsq.util.fieldscan;

import lombok.Data;

import java.util.List;

@Data
public class Test extends BaseTest {

    private String code;

    private B b;

    private A a;

    private int i;

    private List<B> bList;

}
