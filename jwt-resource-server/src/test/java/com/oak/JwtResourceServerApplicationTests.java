package com.oak;

import com.oak.utils.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtResourceServerApplicationTests {

	@Test
	public void contextLoads() {
		String url="http://localhost:9080/oauth/token?grant_type=client_credentials";
		String response =HttpUtils.executeBasicCredentialRequest(url,"service-account-1","service-account-1-secret");
		response =response+"";
	}



}
