package testing.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author rick.
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true, doNotUseGetters = true)
public class SamplePojo {

    private String field1;
    private String field2;
    private String field3;
    private Integer field4;

    public SamplePojo(String s, String s1, String s2, int i) {
        field1 = s;
        field2 = s1;
        field3 = s2;
        field4 = i;
    }
}
