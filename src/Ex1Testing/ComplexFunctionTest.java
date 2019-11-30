package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Polynom;

public class ComplexFunctionTest {

	public static void main(String[] args) {
		String s2 = "5+2x-3.3x+0.1x^5";
		ComplexFunction cf3 = new ComplexFunction(new Polynom(s2));
		cf3.mul(new Polynom("5x"));
		System.out.println(cf3.f(5));
		

	}

}
