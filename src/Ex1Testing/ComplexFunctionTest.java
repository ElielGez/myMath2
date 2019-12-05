package Ex1Testing;


import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.function;

public class ComplexFunctionTest {

	public static void main(String[] args) {
		String s = "5x^2+3x";
		function f = new ComplexFunction().initFromString(s);
		Polynom p = new Polynom("5x^2+3x");
		System.out.println(f.equals(p));
	}

}
