package Ex1Testing;


import Ex1.ComplexFunction;
import Ex1.function;

public class ComplexFunctionTest {

	public static void main(String[] args) {
		String s = "mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
		function f = new ComplexFunction().initFromString(s);
		String s1 = "mul(-1.0x^4+2.4x^2+3.1,plus(+0.1x^5-1.2999999999999998x+5.0,-1.0x^4+2.4x^2+3.1))";
		function f1 = new ComplexFunction().initFromString(s1);
		System.out.println(f.equals(f1));
	}

}
