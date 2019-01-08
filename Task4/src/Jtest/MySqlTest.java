package Jtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataBase.MySql;

class MySqlTest {

	@Test
	void testMySql() {
		try {
			MySql mq = new MySql();
		}
		catch (Exception e) {
			fail("Should not get exception!!!");
		}
	}

	@Test
	void testGetScore() {
		MySql mq = new MySql();
		try {
			String score = mq.getScore(false, "315392852");
			String score2 = mq.getScore(true, "315392852");
			assertNotEquals(score, score2);
		}
		catch (Exception e) {
			fail("Should not get exception!!!");
		}
	}

	@Test
	void testQuery() {
		MySql mq = new MySql();
		try {
			mq.Query("315392852", true);
		}
		catch(Exception e) {
			fail("Should not get exception!!!");
		}
	}

	@Test
	void testCloseConnection() {
		MySql mq = new MySql();
		try {
			mq.closeConnection();
		}
		catch(Exception e) {
			fail("Should not get exception!!!");
		}
	}

}
