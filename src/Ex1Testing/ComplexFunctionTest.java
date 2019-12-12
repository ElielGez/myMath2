package Ex1Testing;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.function;


class ComplexFunctionTest {

	ComplexFunction cf,cf2;
	String s = "div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
	String s2 = "plus(+54.0x^2+5.0x,+5.0x)";
	@BeforeEach
	void beforeEach() {
		cf = (ComplexFunction)new ComplexFunction().initFromString(s);
		cf2 = (ComplexFunction)new ComplexFunction().initFromString(s2);
	}
	
	@Test
	void testPlus() {
		cf.plus(cf2);
		String actual = cf.toString();
		String expected = "plus(" + s + "," + s2 + ")";
		assertEquals(actual,expected);
	}	
	@Test
	void testMul() {
		cf.mul(cf2);
		String actual = cf.toString();
		String expected = "mul(" + s + "," + s2 + ")";
		assertEquals(actual,expected);
	}	
	@Test
	void testDiv() {
		cf.div(cf2);
		String actual = cf.toString();
		String expected = "div(" + s + "," + s2 + ")";
		assertEquals(actual,expected);
	}	
	@Test
	void testMax() {
		cf.max(cf2);
		String actual = cf.toString();
		String expected = "max(" + s + "," + s2 + ")";
		assertEquals(actual,expected);
	}	
	@Test
	void testMin() {
		cf.min(cf2);
		String actual = cf.toString();
		String expected = "min(" + s + "," + s2 + ")";
		assertEquals(actual,expected);
	}	
	@Test
	void testComp() {
		cf.comp(cf2);
		String actual = cf.toString();
		String expected = "comp(" + s + "," + s2 + ")";
		assertEquals(actual,expected);
	}
	
	@Test
	void testisBracketsEqual() 
	{
		String[] complex= {"plus((1.0x+1,1)","max(max(max(1,1,1)","comp())","plus((39x-1,1)"};
		for (int i = 0; i < complex.length; i++)
		{
			try
			{
				new ComplexFunction().initFromString(complex[i]);
				fail("complex: " + complex[i]+ " not in the supported format");
			}
			catch(ArithmeticException e)
			{

			}
		}
	}
	@Test
	void Ftest()
	{
		function f1=new ComplexFunction().initFromString("plus(2x+3,2)");
		double solution =f1.f(1);
		assertEquals(solution, 7);
		
		function f2=new ComplexFunction().initFromString("mul(30x,3)");
		double solution2=f2.f(1.5);
		assertEquals(135, solution2);
		 
		function f3 =new ComplexFunction().initFromString("div(2x,2)");
		double solution3=f3.f(1);
		assertEquals(solution3, 1);
		
	
	}
	@Test
	void equalsTest()
	{
		String s = "mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
		function f = new ComplexFunction().initFromString(s);
		String s1 = "mul(-1.0x^4+2.4x^2+3.1,plus(+0.1x^5-1.2999999999999998x+5.0,-1.0x^4+2.4x^2+3.1))";
		function f1 = new ComplexFunction().initFromString(s1);
		assertEquals(f,f1);
		
		s = "div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
		f = new ComplexFunction().initFromString(s);
		s1 = "mul(-1.0x^4+2.4x^2+3.1,plus(+0.1x^5-1.2999999999999998x+5.0,-1.0x^4+2.4x^2+3.1))";
		f1 = new ComplexFunction().initFromString(s1);
		assertFalse(f.equals(f1));
		
	}
	
	@Test
	void testCopy()
	{
		String s = "mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
		function f = new ComplexFunction().initFromString(s);
		function f1 = f.copy();
		assertFalse(f == f1);
		
	}

}


