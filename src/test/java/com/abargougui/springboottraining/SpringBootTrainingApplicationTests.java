package com.abargougui.springboottraining;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTrainingApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	DatePrinter datePrinter;

	@MockBean
	TimeFactory timeFactory;

	@Test
	public void datePrinterWorks() {
		given(timeFactory.now()).willReturn(LocalDateTime.of(2018, 01, 27, 14, 19, 4));
		assertEquals("Now it is 2018-01-27T14:19:04", datePrinter.printDate());
	}

}
