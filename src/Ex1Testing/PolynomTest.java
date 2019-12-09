package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Random;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

class PolynomTest {

	@Test
	void testInvalidPolynoms() {
		String[] polynoms = { "(2x*4)+3", "-x^32x+6-x^2xxx", "-3.2x^2+4-3x^2&^", "4x^20safa+8x+1+2", "-1.5x123^2" };
		for (int i = 0; i < polynoms.length; i++) {
			try {
				new Polynom(polynoms[i]);
				fail("Polynom: " + polynoms[i] + " not in the supported format");
			} catch (ArithmeticException e) {
			}
		}
	}

	@Test
	void testF() {
		Polynom p;
		double fx;
		String[] polynoms = { "2", "-x+6-x^2" };
		p = new Polynom(polynoms[0]);
		fx = p.f(5);
		assertEquals(2, fx);
		p = new Polynom(polynoms[1]);
		fx = p.f(3);
		assertEquals(-6, fx);
	}

	@Test
	void testAdd() {
		Polynom p;
		Polynom p2;
		String[] polynoms = { "4x^20+8x+1+2", "-1.5x^2" };
		String[] polynoms2 = { "4x^20+8x+1+2", "+1.5x^2" };
		p = new Polynom(polynoms[0]);
		p2 = new Polynom(polynoms2[0]);
		p.add(p2);
		assertEquals(new Polynom("8x^20+16x+6"), p);
		p = new Polynom(polynoms[1]);
		p2 = new Polynom(polynoms2[1]);
		p.add(p2);
		assertEquals(new Polynom("0"), p);
	}

	@Test
	void testSubstract() {
		Polynom p;
		Polynom p2;
		String[] polynoms = { "2", "-x+6-x^2" };
		String[] polynoms2 = { "-2", "+x-6+x^2" };
		p = new Polynom(polynoms[0]);
		p2 = new Polynom(polynoms2[0]);
		p.substract(p2);
		assertEquals(new Polynom("4"), p);
		p = new Polynom(polynoms[1]);
		p2 = new Polynom(polynoms2[1]);
		p.add(p2);
		assertEquals(new Polynom("0"), p);
	}

	@Test
	void testEqual() {
		Polynom p;
		Polynom p2;
		String[] polynoms = {"-3.2x^2+4-3x^2", "3x+3x^3"};
		String[] polynoms2 = { "-3.2x^2+4-3x^1", "3.0000001x^3+3.0000001x^1" };
		p = new Polynom(polynoms[0]);
		p2 = new Polynom(polynoms2[0]);
		assertFalse(p.equals(p2));
		p = new Polynom(polynoms[1]);
		p2 = new Polynom(polynoms2[1]);
		assertEquals(p,p2);
	}
}
