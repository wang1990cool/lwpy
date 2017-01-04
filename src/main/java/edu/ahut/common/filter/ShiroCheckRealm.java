package edu.ahut.common.filter;


import im.dhc.entity.Com_interface_defEntity;
import im.dhc.entity.Com_role_auth_relEntity;
import im.dhc.entity.Com_user_infoEntity;
import im.dhc.entity.meta.Com_interface_defMeta;
import im.dhc.entity.meta.Com_role_auth_relMeta;
import im.dhc.entity.meta.Com_user_infoMeta;
import im.dhc.orm.dao.Dao;
import im.dhc.orm.dao.condition.Cnd;
import im.dhc.orm.dao.condition.Col;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service  
@Transactional  
public class ShiroCheckRealm extends AuthorizingRealm{  
  
	@Autowired
	private Dao dao;
    /** 
     * 权限认证 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {  
        //获取登录时输入的用户名  
        String loginNo=(String) principalCollection.fromRealm(getName()).iterator().next();  
        //到数据库查是否有此对象  
        Com_user_infoEntity user;
		try {
			user = dao.fetch(Com_user_infoEntity.class, Cnd.where(Com_user_infoMeta.key.eq(loginNo)));
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			String loginRole=(String) session.getAttribute("loginRole");
			List<Map<String, Object>> authList = dao.listMany(
					Com_role_auth_relEntity.class, Com_interface_defEntity.class,
					Cnd.where(Com_role_auth_relMeta.interface_no.eq(Com_interface_defMeta.no)) 
					.and(Com_role_auth_relMeta.role_no.eq(loginRole)),
												new Col[]{
													Com_interface_defMeta.url
												});

			
			if(user!=null){  
	            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
	            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();  
	            //将岗位赋值给权限过滤器
	            info.addRole(loginRole);
	            //将岗位对应的权限给权限过滤器
	            for(int i=0;i<authList.size();i++ ){
	            	String url=(String) authList.get(i).get("url");
	            	info.addStringPermission(url);
	            }
	            return info;  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;  
    }  
  
    /** 
     * 登录认证; 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(  
            AuthenticationToken authenticationToken) throws AuthenticationException {  
        //UsernamePasswordToken对象用来存放提交的登录信息  
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;  
        //查出是否有此用户  
        Com_user_infoEntity user=new Com_user_infoEntity();
		try {
			user = dao.fetch(Com_user_infoEntity.class, Cnd.where(Com_user_infoMeta.key.eq(token.getUsername())));
		} catch (Exception e) {
			e.printStackTrace();
		}  
        if(user!=null){  
            //若存在，将此用户存放到登录认证info中  
            return new SimpleAuthenticationInfo(user.getKey(), user.getPasswd(), getName());  
        }  
        return null;  
    }  
    
}  