package libofei.entity.order;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Order implements Serializable {

    private Long id;

    private Long requestId;

    private Long userId;

}
