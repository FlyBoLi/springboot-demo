package libofei.activeMq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;


@Component
public class Consumer{

    public static int count = 0;

    @JmsListener(destination = "user.queue")
    public void receiveUser(TextMessage text, Session session) throws JMSException {
        try {
            System.out.println("user>>>>>>生产者第" + (++count) + "次向消费者发送消息..");
//             int id = 1 / 0;
            String value = text.getText();
            System.out.println("消费者收到消息:" + value);
            //手动签收
            text.acknowledge();
        } catch (Exception e) {
            // 如果代码发生异常，需要发布版本才可以解决的问题，不要使用重试机制，采用日志记录方式，定时Job进行补偿。
            // 如果不需要发布版本解决的问题，可以采用重试机制进行补偿。
             session.recover();// 继续重试
//            e.printStackTrace();
        }
    }

    @JmsListener(destination = "shop.queue")
    public void receiveShop(TextMessage text, Session session) throws JMSException {
        try {
            System.out.println("shop>>>>>>生产者第" + (++count) + "次向消费者发送消息..");
//             int id = 1 / 0;
            String value = text.getText();
            System.out.println("消费者收到消息:" + value);
            //手动签收
            text.acknowledge();
        } catch (Exception e) {
            // 如果代码发生异常，需要发布版本才可以解决的问题，不要使用重试机制，采用日志记录方式，定时Job进行补偿。
            // 如果不需要发布版本解决的问题，可以采用重试机制进行补偿。
            session.recover();// 继续重试
//            e.printStackTrace();
        }
    }


}