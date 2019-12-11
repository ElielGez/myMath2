package Ex1;

import java.util.Comparator;
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

	/**
	 * Function to return Comparator object with compare function
	 * @return
	 */
	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	/**
	 * Constructor to initialize coefficient and power
	 * @param a
	 * @param b
	 */
	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	/**
	 * Copy constructor
	 * @param ot
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * Get coefficient
	 * @return
	 */
	public double get_coefficient() {
		return this._coefficient;
	}

	/**
	 * Get power
	 * @return
	 */
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

	/**
	 * f Function to calculate the value of the function in specific x
	 */
	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	/**
	 * Check if monom is Zero
	 * @return
	 */
	public boolean isZero() {
		return this.get_coefficient() >= 0 && this.get_coefficient() <= EPSILON;
	}

	// ***************** add your code below **********************
	
	/**
	 * Constructor that gets string and create Monom object
	 * @param s
	 */
	public Monom(String s) {
		if (s == null)
			throw new ArithmeticException("String cannot be null");
		s = s.replaceAll(" ","");
		// need to check if comes invalid string (by regex)

		if (!validateFormat(s) || s.isEmpty()) {
			throw new ArithmeticException(
					"Invalid format for Monom . Could created by instance of Monom Class or Polynom Class");
		}
		splitMonomByString(s);
	}

	/**
	 * Add function , to add one monom to another , only if their powers are equal
	 * @param m
	 */
	public void add(Monom m) {
		if (this._power == m._power) {
			this.set_coefficient(this._coefficient + m._coefficient);
		}
	}

	/**
	 * Substract function , to substract monoms , only if their powers are equal
	 * @param m
	 */
	public void substract(Monom m) {
		if (this._power == m._power) {
			this.set_coefficient(this._coefficient - m._coefficient);
		}
	}

	/**
	 * This function check if Monoms are equals by substracting m1 from m2
	 * @param m
	 * @return
	 */
	private boolean substractEqualsCheck(Monom m) {
		if (this._power == m._power) {
			double result = this._coefficient - m._coefficient;
			return Math.abs(result) <= EPSILON;
		}
		return false;
	}

	/**
	 * Function to multiply monoms
	 * @param d
	 */
	public void multiply(Monom d) {
		this.set_coefficient(this._coefficient * d._coefficient);
		this.set_power(this._power + d._power);
	}

	/**
	 * Override toString function and return string
	 */
	public String toString() {
		String s = "";
		if (this._power > 1)
			s = this._coefficient + "x^" + this._power;
		else if (this._power == 1)
			s = this._coefficient + "x";
		else if (this._power == 0)
			s = "" + this._coefficient;
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

	/**
	 * This function handle start of Monom shape , and sign of the number
	 * @param s string to manipulate
	 * @return string after manipulation
	 */
	private String handleSignOfNumber(String s) {
		if (s == null || s.equals("") || s.equals("+"))
			return "1";
		else if (s.equals("-"))
			return "-1";
		return s;
	}

	/**
	 * This function get string and split it by Monom pattern, and set Monom params.
	 * This function is called only after its pass the validateFormat function
	 * @param s string to split
	 */
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

	/**
	 * This function get string and check if it's on the correct pattern , pattern of Monom 
	 * Monom look like this: ax^b
	 * I used regex.
	 * @param s - string to validate
	 * @return boolean
	 */
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

	/**
	 * initial Monom from string , just call the Monom constructor and return instance of the object
	 */
	@Override
	public function initFromString(String s) {
		return new Monom(s);
	}

	/**
	 * copy function
	 * calling the copy constructor of Monom class and return object that implements function interface. 
	 */
	@Override
	public function copy() {
		return new Monom(this);
	}
	
	/**
	 * equals function 
	 * Override Object function in order to use JUNIT assert functions
	 */
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
