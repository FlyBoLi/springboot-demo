package libofei.controller.user;


import libofei.activeMq.producer.Producer;
import libofei.common.cache.RedisService;
import libofei.demo.IUserService;
import libofei.entity.user.User;
import libofei.user.service.impl.user.UserServiceImpl;
import libofei.user.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;
    @Autowired
    private Producer producer;


    @RequestMapping("/get/name")
    public Object getName(String name){
        return null;
    }

    @RequestMapping("/insert/user")
    public String insertTestUser(String name , int age){
        userService.add(name,age);
        return "success";
    }

    @RequestMapping("/insert/user/object")
    public String insertTestUser(User user){
        userService.addUser(user);
        return "success";
    }

    @RequestMapping("/set/redis/user")
    public String insertRedisUser(String name){
        redisService.setObject("userName",name);
//        List<String> list = new ArrayList<String>();
//        list.add(name);
//        redisService.setObject("userNameList",list);
        return "success";
    }

    @RequestMapping("/get/redis/user")
    public String queryRedisUser(){
        return redisService.getString("userName");
    }

    @RequestMapping("/del/redis/user")
    public String delRedisUser(){
         redisService.delKey("userName");
        return "success";
    }


    @RequestMapping("/send/message/user")
    public String sendMessageUser(String message){
        logger.error(message);
        producer.sendByMessageUser(message);
        return "success";
    }

    @RequestMapping("/send/message/shop")
    public String sendMessageShop(String message){
        producer.sendByMessageShop(message);
        return "success";
    }

    @RequestMapping("/get/user/list")
    public Object getUserList(){
        List<User> userList = userService.getUserList();
        return userList;
    }

    @RequestMapping("/get/user/list/page")
    public Object getUserListByPage(int pageNum,int pageSize){
        List<User> userList = userService.getUserListByPage(pageNum,pageSize);
        return userList;
    }

//    @RequestMapping("/get/user/id")
//    public Object getUserListByPage(int id){
//        User user = userService.getUserById(id);
//        return user;
//    }
//
//    @RequestMapping("/delete/user/id")
//    public Object deleteUserById(int id){
//        int i  = userService.deleteUserById(id);
//        return i;
//    }

}
