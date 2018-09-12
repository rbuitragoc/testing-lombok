package testing.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author rick.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = false, doNotUseGetters = false)
public class SamplePojo {

    private String field1;
    private String field2;
    private String field3;
    private Integer field4;

}
