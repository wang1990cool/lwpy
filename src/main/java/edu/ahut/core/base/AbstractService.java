package edu.ahut.core.base;

import java.io.Serializable;

/**
 * Created by wangcaner on 2017/1/13.
 */
public abstract  class AbstractService<T, ID extends Serializable> implements BaseService<T, ID> {
    private BaseMapper<T, ID> baseMapper;
    public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
        this.baseMapper = baseMapper;
    }
    @Override
    public int deleteByPrimaryKey(ID id) {
        return baseMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }
    @Override
    public T selectByPrimaryKey(ID id) {
        return baseMapper.selectByPrimaryKey(id);
    }
    @Override
    public T selectAll() {
        return baseMapper.selectAll();
    }
    @Override
    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }
    @Override
    public int updateByPrimaryKeyWithBLOBs(T record) {
        return baseMapper.updateByPrimaryKeyWithBLOBs(record);
    }
    @Override
    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }
    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }
}