package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;
import Ex1.Monom;
import org.junit.jupiter.api.Test;

/**
 * This class represents a simple (naive) tester for the Monom class, Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be
 * changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and
 * functionality of the Monom class. <br>
 * (iii) Expected output: <br>
 */
class MonomTest {

	@Test
	void testInvalidMonoms() {
		String[] monoms = { "2a", "-5^5", "-3.2x^2x", "0sda", "2x^0%4" };
		for (int i = 0; i < monoms.length; i++) {
			try {
				new Monom(monoms[i]);
				fail("Monom: " + monoms[i] + " not in the supported format");
			} catch (ArithmeticException e) {

			}
		}
	}

	@Test
	void testF() {
		Monom m1 = new Monom("2");
		double fx1 = m1.f(6);
		assertEquals(2, fx1, "fx1 = 2 , f(6) = 2");
		Monom m2 = new Monom("5x^3");
		double fx2 = m2.f(1);
		assertEquals(5, fx2, "fx2 = 5x^3 , f(1) = 5");
	}

	@Test
	void testAdd() {
		String[] monoms = { "5x", "x^2" };
		String[] monoms2 = { "15x", "x^1" };
		Monom m1 = new Monom(monoms[0]);
		Monom m2 = new Monom(monoms2[0]);
		m1.add(m2);
		assertEquals(m1, new Monom("20x"));
		m1 = new Monom(monoms[1]);
		m2 = new Monom(monoms2[1]);
		m1.add(m2);
		assertEquals(m1, new Monom("x^2"));
	}

	@Test
	void testSubstract() {
		String[] monoms = { "3x", "x^20" };
		String[] monoms2 = { "3x", "5x^19" };
		Monom m1 = new Monom(monoms[0]);
		Monom m2 = new Monom(monoms2[0]);
		m1.substract(m2);
		assertEquals(m1, Monom.ZERO);
		m1 = new Monom(monoms[1]);
		m2 = new Monom(monoms2[1]);
		m1.substract(m2);
		assertEquals(m1, new Monom("x^20"));

	}

	@Test
	void testEqual() {
		String[] monoms = { "3x^3", "3x" };
		String[] monoms2 = { "3.0000001x^3", "3" };
		Monom m1 = new Monom(monoms[0]);
		Monom m2 = new Monom(monoms2[0]);
		assertEquals(m1, m2);
		m1 = new Monom(monoms[1]);
		m2 = new Monom(monoms2[1]);
		assertFalse(m1.equals(m2));

	}

	@Test
	void testMultiply() {
		String[] monoms = { "5x", "3x^4" };
		String[] monoms2 = { "3", "3x" };
		Monom m1 = new Monom(monoms[0]);
		Monom m2 = new Monom(monoms2[0]);
		m1.multiply(m2);
		assertEquals(m1, new Monom("15x"));
		m1 = new Monom(monoms[1]);
		m2 = new Monom(monoms2[1]);
		m1.multiply(m2);
		assertEquals(m1, new Monom("9x^5"));

	}
}
