package libofei.activeMq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

@Component
@EnableScheduling
public class Producer{
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

//    @Autowired
//    @Qualifier("userQueue")
//    private Queue userQueue;
//    @Resource
//    @Qualifier("shopQueue")
//    private Queue shopQueue;

//    @Scheduled(fixedDelay = 5000) //定时发送消息
//    public void send() {
//        jmsMessagingTemplate.convertAndSend(userQueue, "message" + System.currentTimeMillis());
//    }
    @Value("${user.queue}")
    private String userQueue;

    @Value("${shop.queue}")
    private String shopQueue;


    //user
    @Async
    public void sendByMessageUser(String message) {
        jmsMessagingTemplate.convertAndSend(userQueue, message + System.currentTimeMillis());
    }

//    shop
    @Async
    public void sendByMessageShop(String message) {
        jmsMessagingTemplate.convertAndSend(shopQueue, message + System.currentTimeMillis());
    }
}

