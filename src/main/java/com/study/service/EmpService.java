package com.study.service;

import com.study.mapper.EmpMapper;
import com.study.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    @Autowired
    public EmpMapper empMapper;

    // 增加员工信息
    public int add(Emp e){
       return empMapper.insert(e);
    }

    // 查询所有的员工信息
    public List<Emp> getlist(){
        return empMapper.selectList(null);
    }


    // 根据id查询信息
    public Emp getEmp(Integer id){
        // 返回以为的员工信息
        return empMapper.selectById(id);
    }

    // 根据id修改员工的所有信息
    public int updateEmpById(Emp e){
       return  empMapper.updateById(e);
    }

    // 根据id查询员工信息
    public Emp selectOne(int id){
        return empMapper.selectById(id);
    }

    // 根据id删除员工信息
    public  int deleteById(int id){
        return empMapper.deleteById(id);
    }

}
