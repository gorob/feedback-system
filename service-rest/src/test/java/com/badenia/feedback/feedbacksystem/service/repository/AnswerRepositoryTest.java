package com.badenia.feedback.feedbacksystem.service.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.badenia.feedback.feedbacksystem.service.repository.AnswerRepository;
import com.badenia.feedback.feedbacksystem.service.repository.model.AnswerTableModel;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class AnswerRepositoryTest {

	@Autowired
	AnswerRepository underTest;
	
	@Test
	public void testFindAll() {
		List<AnswerTableModel> list = underTest.findAll();
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
}
