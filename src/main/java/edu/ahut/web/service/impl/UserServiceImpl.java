package edu.ahut.web.service.impl;

import edu.ahut.core.base.AbstractService;
import edu.ahut.web.dao.UserMapper;
import edu.ahut.web.model.User;
import edu.ahut.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserServiceImpl extends AbstractService<User,Long> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public void setBaseMapper(){
        super.setBaseMapper(userMapper);
    }
    @Override
    public int insert(User model) {
        return userMapper.insertSelective(model);
    }

}
