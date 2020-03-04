package libofei.user.dao;

import libofei.entity.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserMapper{

	public User findByName(@Param("name") String name);

	public int insert(@Param("name") String name, @Param("age") Integer age);

	public int insertUser(@Param("user") User user);

	public List<User> getUserList();

	public List<User> getUserListByPage();

	public User getUserById(int id);

	public int deleteUserById(int id);
}
