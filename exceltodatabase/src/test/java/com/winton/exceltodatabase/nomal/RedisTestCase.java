package com.winton.exceltodatabase.nomal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.winton.exceltosql.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestCase {

	@Autowired
	StringRedisTemplate t;

	@Test
	public void contextLoads() {
		t.opsForValue().set("hi", "hi");
		t.delete("123");
		System.out.println(1);
	}

}