package com.lanou.springbootredis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

@SpringBootTest
class SpringbootRedisApplicationTests {
     @Autowired
   private StringRedisTemplate stringRedisTemplate;

     @Test
     public  void redisPutKv(){

     ValueOperations<String,String> ops=stringRedisTemplate.opsForValue();
      ops.set("name","zhangsan");
         String name= ops.get("name");
         System.out.println(name);
         Assertions.assertEquals("zhangsan",name,"应该返回zhangsan ");
        }

        @Test
        public void redisList(){

            ListOperations<String, String> ops = stringRedisTemplate.opsForList();
            ops.rightPush("student","李四");
            ops.leftPush("student","王五");
            ops.leftPush("student","蔡文姬");
            List<String> student = ops.range("student", 0, -1);
            Assertions.assertEquals(15,ops.size("student"),"有15个");
            student.forEach((v) ->{
                System.out.println(v);
            });



        }



}
