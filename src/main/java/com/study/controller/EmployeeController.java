package com.study.controller;

import com.study.pojo.Emp;
import com.study.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    public EmpService empService;

    /**
     * 查询员工表中的所有信息，返回列表
     * @return
     */
    @GetMapping("/emps")
    public List<Emp> getEmps(){
        return empService.getlist();
    }

    /**
     * 根据id查找员工信息
     */

    @GetMapping("/emp")
    public Emp geuEmp(String id){
        return empService.selectOne(Integer.parseInt(id));
    }

    /**
     * 新增或 修改员工信息
     * @param emp
     */
    @PostMapping("/save")
    public void save(@RequestBody Emp emp){
        if(StringUtils.isEmpty(emp.getId())){// 如果id为空，那么新增
            emp.setId(0);
            empService.add(emp);
        }else{ // 如果id非空，那么进行修改
            empService.updateEmpById(emp);
        }
    }

    @DeleteMapping("/delete")
    public void delete(String id){
        empService.deleteById(Integer.parseInt(id));
    }

}
