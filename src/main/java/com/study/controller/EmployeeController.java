package com.study.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.mapper.EmployeeMapper;
import com.study.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin // 解决跨域的问题
public class EmployeeController {

    @Autowired
    public EmployeeMapper employeeMapper;

    // 根据指定id进行查询
    @GetMapping("/selectOne")
    public Employee selectOne(@RequestParam("id") String id){
        //System.out.println("访问查询id");
        return employeeMapper.selectById(Integer.parseInt(id));
    }

    // 查询全部数据
    @GetMapping("/emp")
    public List<Employee> selectAll(){
        //System.out.println("访问查询全部!");
        return employeeMapper.selectList(null);
    }

    // 新增一条用户信息
    @PostMapping("/emp")
    public String add(@RequestBody Employee employee){
        employee.setId(0);
        //System.out.println("进入增加的接口之中");
        //System.out.println(employee);
        int rows = employeeMapper.insert(employee);
        return "增加成功";
    }

    // 更改操作
    @PutMapping("/update")
    public String test3(@RequestBody Employee employee){
        System.out.println(employee);
        employeeMapper.updateById(employee);
        return "修改成功!";
    }

    // 根据id进行删除
    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id){
        //System.out.println("访问删除接口!");
        employeeMapper.deleteById(id);
        return "删除成功!";
    }

}
