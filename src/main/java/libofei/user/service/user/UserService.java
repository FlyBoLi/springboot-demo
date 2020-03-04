package libofei.user.service.user;

import libofei.entity.user.User;
import libofei.user.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {


    public int add(String name,int age);

    public int addUser(User user);

    public User getUserByName(String name);

    public List<User> getUserList();

    public List<User> getUserListByPage(int pageNum,int pageSize);
}
