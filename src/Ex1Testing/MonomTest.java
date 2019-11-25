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
			try
			{
				new Monom(monoms[i]);
				fail("Monom: " + monoms[i] + " not in the supported format");
			}
			catch (Exception e) {
				
			}
		}
	}
	
	@Test
	void testF() {
		Monom m1 = new Monom("2");
		double fx1 = m1.f(6);
		assertEquals(2, fx1,"fx1 = 2 , f(6) = 2");
		Monom m2 = new Monom("5x^3");
		double fx2 = m2.f(1);
		assertEquals(5, fx2,"fx2 = 5x^3 , f(1) = 5");
	}

	@Test
	void testAdd() {
		String[] monoms = { "5x", "x^2", "3", "x^20" };
		String[] monoms2 = { "15x", "x^1", "3", "5x^20" };
		Monom m1 = new Monom(monoms[0]);
		Monom m2 = new Monom(monoms2[0]);
		m1.add(m2);
		assertEquals(m1,new Monom("20x"));
		m1 = new Monom(monoms[1]);
		m2 = new Monom(monoms2[1]);
		m1.add(m2);
		assertEquals(m1,new Monom("x^2"));
	}
//
//	private static void testSubstract() {
//		System.out.println("*****  testSubstract:  *****");
//		String[] monoms = { "5", "x^2", "3", "x^20" };
//		String[] monoms2 = { "15", "x^2", "3x", "5x^19" };
//		for (int i = 0; i < monoms.length; i++) {
//			Monom m = new Monom(monoms[i]);
//			Monom m1 = new Monom(monoms2[i]);
//			m1.substract(m);
//			System.out.println("Substract monoms #" + i + " new result is : " + m1);
//		}
//		System.out.println("");
//	}
//
//	private static void testEqual() {
//		System.out.println("*****  testEqual:  *****");
//		String[] monoms = { "3", "3x^3", "3", "x^20" };
//		String[] monoms2 = { "3", "3.0000001x^3", "3x", "5x^19" };
//		for (int i = 0; i < monoms.length; i++) {
//			Monom m = new Monom(monoms[i]);
//			Monom m1 = new Monom(monoms2[i]);
//
//			System.out.println("Equals monoms #" + i + " isEquals : " + m.equals(m1));
//		}
//		System.out.println("");
//	}
//
//	private static void testMultiply() {
//		System.out.println("*****  testMultiply:  *****");
//		String[] monoms = { "5x", "3.0000001x^3", "3x^4", "x^20" };
//		String[] monoms2 = { "3", "3x^3", "3x", "1x^19" };
//		for (int i = 0; i < monoms.length; i++) {
//			Monom m = new Monom(monoms[i]);
//			Monom m1 = new Monom(monoms2[i]);
//			m.multiply(m1);
//			System.out.println("Multiply monoms #" + i + " new result is : " + m);
//		}
//		System.out.println("");
//	}
}
