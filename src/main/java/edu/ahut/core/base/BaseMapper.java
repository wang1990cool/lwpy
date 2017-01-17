package edu.ahut.core.base;

/**
 * Created by wangcaner on 2017/1/13.
 */
import java.io.Serializable;
public interface BaseMapper<T,ID extends Serializable> {

    int deleteByPrimaryKey(ID id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(ID id);
    T selectAll();
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKeyWithBLOBs(T record);
    int updateByPrimaryKey(T record);
}
