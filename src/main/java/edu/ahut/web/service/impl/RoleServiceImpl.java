package edu.ahut.web.service.impl;

import edu.ahut.core.base.AbstractService;
import edu.ahut.web.dao.RoleMapper;
import edu.ahut.web.model.Role;
import edu.ahut.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class RoleServiceImpl extends AbstractService<Role,Long> implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    public void setBaseMapper(){
        super.setBaseMapper(roleMapper);
    }


    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }
}
