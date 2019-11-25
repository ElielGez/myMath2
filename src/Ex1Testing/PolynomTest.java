package Ex1Testing;

import java.util.Random;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

public class PolynomTest {
	public static void main(String[] args) {
		testInvalidPolynoms();
		testFDouble();
		testFInt();
		testAdd();
		testSubstract();
		testMultiply();
		testEqual();
		testCopy();
		testDerivative();
		testRoot();
		testArea();
	}

	public static void testInvalidPolynoms() {
		System.out.println("*****  testInvalidPolynoms:  *****");
		String[] polynoms = { "(2x*4)+3", "-x^32x+6-x^2xxx", "-3.2x^2+4-3x^2&^", "4x^20safa+8x+1+2", "-1.5x123^2" };
		for (int i = 0; i < polynoms.length; i++) {
			try {
				new Polynom(polynoms[i]);
			} catch (ArithmeticException e) {
				System.out.println("Test polynom: " + polynoms[i] + " " + e);
			}
		}
		System.out.println("");
	}

	public static void testFDouble() {
		System.out.println("*****  testFDouble:  *****");
		Polynom p;
		double random;
		String[] polynoms = { "2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			random = new Random().nextDouble();
			String pStr = polynoms[i];
			p = new Polynom(pStr);
			double fx = p.f(random);
			System.out.println("Polynom: " + p + " f(" + random + ") = " + fx);
		}
		System.out.println("");
	}

	public static void testFInt() {
		System.out.println("*****  testFInt:  *****");
		Polynom p;
		int random;
		String[] polynoms = { "2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			random = new Random().nextInt(101);
			String pStr = polynoms[i];
			p = new Polynom(pStr);
			double fx = p.f(random);
			System.out.println("Polynom: " + p + " f(" + random + ") = " + fx);
		}
		System.out.println("");
	}

	public static void testAdd() {
		System.out.println("*****  testAdd:  *****");
		Polynom p;
		Polynom p2;
		String[] polynoms = { "2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		String[] polynoms2 = { "-2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			p = new Polynom(polynoms[i]);
			System.out.print("Polynom before adding: " + p + " , ");
			p2 = new Polynom(polynoms2[i]);
			p.add(p2);
			System.out.println("Polynom after adding: " + p);
		}
		System.out.println("");
	}

	public static void testSubstract() {
		System.out.println("*****  testSubstract:  *****");
		Polynom p;
		Polynom p2;
		String[] polynoms = { "2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		String[] polynoms2 = { "-2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			p = new Polynom(polynoms[i]);
			System.out.print("Polynom before substract: " + p + " , ");
			p2 = new Polynom(polynoms2[i]);
			p.substract(p2);
			System.out.println("Polynom after substract: " + p);
		}
		System.out.println("");
	}

	public static void testEqual() {
		System.out.println("*****  testEqual:  *****");
		Polynom p;
		Polynom p2;
		String[] polynoms = { "2", "-x+6-x^2", "-3.2x^2+4-3x^2", "3x+3x^3", "-1.5x^2" };
		String[] polynoms2 = { "-2", "-x+6-x^2", "-3.2x^2+4-3x^2", "3.0000001x^3+3.0000001x^1", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			p = new Polynom(polynoms[i]);
			p2 = new Polynom(polynoms2[i]);
			System.out.println("Equals polynom #" + i + " isEquals : " + p.equals(p2));
		}
		System.out.println("");
	}

	public static void testMultiply() {
		System.out.println("*****  testMultiply:  *****");
		Polynom p;
		Polynom p2;
		String[] polynoms = { "2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		String[] polynoms2 = { "-2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			p = new Polynom(polynoms[i]);
			System.out.print("Polynom before multiply: " + p + " , ");
			p2 = new Polynom(polynoms2[i]);
			p.multiply(p2);
			System.out.println("Polynom after multiply: " + p);
		}
		System.out.println("");
	}

	public static void testCopy() {
		System.out.println("*****  testMultiply:  *****");
		Polynom p;
		Polynom_able p2;
		String[] polynoms = { "-x+6-x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			p = new Polynom(polynoms[i]);
			p2 = p.copy();
			System.out.println("Address of polynom1 is equal to address of polynom 2? " + (p == p2));
			System.out.println("But values of the polynoms is equal p1: " + p + ",p2: " + p2);
			// Can check here the addreses of the monoms , but need to run of both iterators
			// of the polynoms..
		}
		System.out.println("");
	}

	public static void testDerivative() {
		System.out.println("*****  testDerivative:  *****");
		Polynom p;
		Polynom_able p2;
		String[] polynoms = { "2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			p = new Polynom(polynoms[i]);
			p2 = p.derivative();
			System.out.println("Derivative of polynom: " + p + " is: " + p2);
		}
		System.out.println("");
	}

	public static void testRoot() {
		System.out.println("*****  testRoot:  *****");
		Polynom p;
		String[] polynoms = { "-x+6-x^2", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			try {
				double a = -2;
				double b = 5;
				p = new Polynom(polynoms[i]);
				double root = p.root(a, b, Monom.EPSILON);
				System.out.println("Root[" + a + "," + b + "] of polynom: " + p + " is: " + root);
			} catch (ArithmeticException e) {
				System.out.println(polynoms[i] + " " + e);
			}
		}
		System.out.println("");
	}

	public static void testArea() {
		System.out.println("*****  testArea:  ***** Might take a while because the eps is Monom.EPSILON");
		Polynom p;
		String[] polynoms = { "2", "-x+6-x^2", "-3.2x^2+4-3x^2", "4x^20+8x+1+2", "-1.5x^2" };
		for (int i = 0; i < polynoms.length; i++) {
			double a = -2;
			double b = 3;
			p = new Polynom(polynoms[i]);
			double area = p.area(a, b, Monom.EPSILON);
			System.out.println("Area[" + a + "," + b + "] of polynom: " + p + " is: " + area);
		}
		System.out.println("");
	}
}
