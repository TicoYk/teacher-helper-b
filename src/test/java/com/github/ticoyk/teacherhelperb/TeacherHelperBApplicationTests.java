package com.github.ticoyk.teacherhelperb;

import com.github.ticoyk.teacherhelperb.controllers.rest.AuthController;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeacherHelperBApplicationTests extends Assertions {
	
	@Autowired
	private AuthController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
