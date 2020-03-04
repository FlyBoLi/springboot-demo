package libofei.shop.dao;

import libofei.entity.order.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface OrderMapper {


    public Order findByUserId(@Param("userId") Long userId);


    public int insertOrder(@Param("requestId") Long requestId,@Param("userId") Long userId);
}
