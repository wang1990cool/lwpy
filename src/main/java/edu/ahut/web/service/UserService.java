package edu.ahut.web.service;

import edu.ahut.core.base.BaseService;
import edu.ahut.web.model.User;

/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserService extends BaseService<User, Long> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
//    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    User selectByUsername(String username);

    User authentication(User user);
}
