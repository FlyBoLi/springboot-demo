package libofei.shop.service;

import libofei.entity.order.Order;
import libofei.shop.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public Order getOrderByUserId(Long userId){
        return orderMapper.findByUserId(userId);
    }

    @Transactional
    public int insert(Long requestId,Long userId){
        return orderMapper.insertOrder(requestId,userId);
    }



}
