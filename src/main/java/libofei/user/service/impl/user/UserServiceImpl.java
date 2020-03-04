package libofei.user.service.impl.user;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import libofei.common.cache.CacheConstants;
import libofei.common.cache.RedisService;
import libofei.entity.user.User;
import libofei.user.dao.UserMapper;
import libofei.user.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Transactional//spring集成事务，只需在方法上加上此注解
    public int add(String name,int age){
       return userMapper.insert(name,age);
    }

    @Transactional
    public int addUser(User user){
       int cnt =  userMapper.insertUser(user);
       if(cnt > 0){
           redisService.setObjectId(CacheConstants.USER,user.getId(), JSON.toJSONString(user));
       }
       return cnt;
    }

    public User getUserById(int id){
        User user = userMapper.getUserById(id);
        String object = redisService.getObjectId(CacheConstants.USER,id);
        if(!StringUtils.isEmpty(object)){
            user = JSON.parseObject(object,User.class);
        }
        return user;
    }

    public User getUserByName(String name){
        return userMapper.findByName(name);
    }

    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    public int deleteUserById(int id){
        int cnt = userMapper.deleteUserById(id);
        if(cnt > 0){
            redisService.deleteObjectId(CacheConstants.USER,id);
        }
        return cnt;
    }

    @Override
    public List<User> getUserListByPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.getUserListByPage();
        System.out.println("Total: " + ((Page) userList).getTotal());
        System.out.println(((Page) userList));
        for (User user : userList) {
            System.out.println("Name: " + user.getUserName());
        }
        return userList;
    }

}
