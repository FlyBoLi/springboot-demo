package libofei.controller.order;

import libofei.demo.IOrderServices;
import libofei.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping("/get/order")
    public Object getOrder(Long userId){
        return orderService.getOrderByUserId(userId);
    }

    @RequestMapping("/insert/order")
    public String insertOrder(Long requestId,Long userId){
        orderService.insert(requestId,userId);
        return "success";
    }
}
