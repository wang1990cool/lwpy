package edu.ahut.core.base;

import java.io.Serializable;

/**
 * 所有自定义Service的顶级接口,封装常用的增删查改操作
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型
 * PK :代表对象的主键类型
 */
public interface BaseService<T,ID extends Serializable> {
    void setBaseMapper();
    int deleteByPrimaryKey(ID id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(ID id);
    T selectAll();
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKeyWithBLOBs(T record);
    int updateByPrimaryKey(T record);
}
