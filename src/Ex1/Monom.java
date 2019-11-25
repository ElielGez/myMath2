package Ex1;

import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * 
	 * @return
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	public boolean isZero() {
		return this.get_coefficient() >= 0 && this.get_coefficient() <= EPSILON;
	}

	// ***************** add your code below **********************
	public Monom(String s) {
		// need to check if comes invalid string (by regex)

		if (!validateFormat(s)) {
			throw new ArithmeticException(
					"Invalid format for Monom . Could created by instance of Monom Class or Polynom Class");
		}
		splitMonomByString(s);
	}

	public void add(Monom m) {
		if (this._power == m._power) {
			this.set_coefficient(this._coefficient + m._coefficient);
		}
	}

	public void substract(Monom m) {
		if (this._power == m._power) {
			this.set_coefficient(this._coefficient - m._coefficient);
		}
	}

	private boolean substractEqualsCheck(Monom m) {
		if (this._power == m._power) {
			double result = this._coefficient - m._coefficient;
			return Math.abs(result) <= EPSILON;
		}
		return false;
	}

	public void multiply(Monom d) {
		this.set_coefficient(this._coefficient * d._coefficient);
		this.set_power(this._power + d._power);
	}

	public String toString() {
		String s = "";
		if (this._power > 1)
			s = String.format("%.2f", this._coefficient) + "x^" + this._power;
		else if (this._power == 1)
			s = String.format("%.2f", this._coefficient) + "x";
		else if (this._power == 0)
			s = "" + String.format("%.2f", this._coefficient);
		return s;
	}

	// you may (always) add other methods.

	// ****************** Private Methods and Data *****************

	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new ArithmeticException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	private String handleSignOfNumber(String s) {
		if (s == null || s.equals("") || s.equals("+"))
			return "1";
		else if (s.equals("-"))
			return "-1";
		return s;
	}

	private void splitMonomByString(String s) {
		if (s.contains("x^")) {
			String[] splittedStr = s.split("x\\^");
			this.set_coefficient(Double.parseDouble(handleSignOfNumber(splittedStr[0])));
			this.set_power(Integer.parseInt(splittedStr[1]));
		} else if (s.contains("x")) {
			final String splittedStr[] = s.split("x");
			if (s.equals("x"))
				this.set_coefficient(1.0);
			else
				this.set_coefficient(Double.parseDouble(handleSignOfNumber(splittedStr[0])));
			this.set_power(1);
		} else {
			this.set_coefficient(Double.parseDouble(handleSignOfNumber(s)));
			this.set_power(0);
		}
	}

	private boolean validateFormat(String s) {
		String patternStr = "(^[-+]?([0-9]*\\.[0-9]+|[0-9]+|)([x](\\^[0-9]+)?)?)";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Monom) {
			Monom m = (Monom) obj;
			if (this.isZero() && m.isZero())
				return true;
			return substractEqualsCheck(m);
		}
		return false;
	}

}
