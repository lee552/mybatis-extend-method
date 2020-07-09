package com.example.mybatis.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatis.demo.mapper.PersonMapper;
import com.example.mybatis.demo.mapper.UserMapper;
import com.example.mybatis.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- custom update method test ------"));
        userMapper.insert(new User().setAge(20).setName("张三"));
        User user = userMapper.selectOne(new QueryWrapper<>(new User().setName("张三")));
        userMapper.updateCustom(user.setName("拉拉").setAge(30));
    }
}
