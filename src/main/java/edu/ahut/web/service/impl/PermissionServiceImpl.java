package edu.ahut.web.service.impl;

import edu.ahut.core.base.AbstractService;
import edu.ahut.web.dao.PermissionMapper;
import edu.ahut.web.model.Permission;
import edu.ahut.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午12:05:03
 */
@Service
public class PermissionServiceImpl extends AbstractService<Permission, Long> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Autowired
    public void setBaseMapper(){
        super.setBaseMapper(permissionMapper);
    }


    @Override
    public List<Permission> selectPermissionsByRoleId(Long roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }
}
