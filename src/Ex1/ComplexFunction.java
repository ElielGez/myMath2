package Ex1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexFunction implements complex_function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private function left;
	private function right;
	private Operation op;

	public ComplexFunction() {

	}
	
	public ComplexFunction(Operation op, function left, function right) {
		if (left == null)
			throw new ArithmeticException("Left function is required");

		this.left = left.copy();
		if (right != null) // right can be null (In none operation for example)
			this.right = right.copy();
		else if (op != Operation.None)
			throw new ArithmeticException("Right function can be null only if operation is none");
		this.op = op;
	}

	/**
	 * This function is constructor that get Operator as string and two function ,
	 * f1 and f2
	 * 
	 * @param string - operation in string
	 * @param left   - f1
	 * @param right  - f2
	 */
	public ComplexFunction(String string, function left, function right) {
		if (left == null)
			throw new ArithmeticException("Left function is required");
		if (string == null)
			throw new ArithmeticException("Operation is required");

		this.left = left.copy();
		if (right != null) // right can be null (In none operation for example)
			this.right = right.copy();
		else if (string != "none")
			throw new ArithmeticException("Right function can be null only if operation is none");
		this.op = getOperationByString(string);
	}

	/**
	 * ComplexFunction copy constructor Even instance of class that implement
	 * function interface , can be copied here into new complex function with none
	 * operation.
	 * 
	 * @param f
	 */
	public ComplexFunction(function f) {
		if (!(f instanceof ComplexFunction)) {
			this.left = f.copy();
			this.right = null;
			this.op = Operation.None;
		} else {
			ComplexFunction cf = (ComplexFunction) f;
			this.op = cf.op;
			this.left = cf.left.copy();
			if (cf.right != null)
				this.right = cf.right.copy();
		}
	}

	@Override
	public function right() {
		return this.right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(function right) {
		this.right = right;
	}

	@Override
	public function left() {
		return this.left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(function left) {
		if (left == null)
			throw new ArithmeticException("Left function is required");
		this.left = left;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}

	/**
	 * This equals function isn't fully working for any x, so I created a temp
	 * solution as asked from Boaz.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof function) {
			function f = (function) obj;
			for (double i = -1000; i < 1000; i = i + 0.7) {
				if (Math.abs(f.f(i) - this.f(i)) > Monom.EPSILON)
					return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Function toString for ComplexFunction if operation is none im returning only
	 * the left side , because the right side is null.
	 */
	@Override
	public String toString() {
		if (this.op != Operation.None)
			return getStringByOperation(this.op) + "(" + this.left + "," + this.right + ")";
		else
			return this.left.toString();
	}

	/**
	 * f Function , returning by Operation the value of the function in x value ==
	 * f(x)
	 */
	@Override
	public double f(double x) {
		if(this.op != Operation.None && this.right == null)
			throw new ArithmeticException("Right function can be null only if operation is none");
		switch (this.op) {
		case Plus:
			return (this.left.f(x) + this.right.f(x));
		case Times:
			return (this.left.f(x) * this.right.f(x));
		case Divid:
			return (this.left.f(x) / this.right.f(x));
		case Max:
			return Math.max(this.left.f(x), this.right.f(x));
		case Min:
			return Math.min(this.left.f(x), this.right.f(x));
		case Comp:
			return this.left.f(this.right.f(x));
		case None:
			return this.left.f(x);
		case Error:
			throw new ArithmeticException("Operation is Error");
		}
		return 0;
	}

	/**
	 * initFromString function , using the initFromStrRecursive function to create
	 * ComplexFunction from string
	 */
	@Override
	public function initFromString(String s) {
		if (s == null)
			throw new ArithmeticException("String cannot be null");

		s = s.replaceAll(" ", "");

		// this if checks that if I got in the string the char ',' it must come with
		// operation. otherwise it's normal polynom
		if (!checkStringStartsOperation(s) && s.contains(","))
			throw new ArithmeticException("String must start with valid operation (invalid is error or none)");

		function cf = initFromStrRecursive(new ComplexFunction(), s);
		if (!(cf instanceof ComplexFunction)) {
			cf = new ComplexFunction(cf);
		}
		return cf;
	}

	/**
	 * function to check if string starts with operation.
	 * 
	 * @param s
	 * @return
	 */
	private boolean checkStringStartsOperation(String s) {
		return s.startsWith("plus") || s.startsWith("div") || s.startsWith("mul") || s.startsWith("min")
				|| s.startsWith("max") || s.startsWith("comp");
	}

	/**
	 * copy function , call the copy constructor
	 */
	@Override
	public function copy() {
		return new ComplexFunction(this);
	}

	/**
	 * All of the below functions untill comp function is the same , moving and copy
	 * current object to left function. And putting f1 in the right side , and set
	 * the Operation by function. I'm copying the current object because after that
	 * I'm override this.right and this.op
	 */
	@Override
	public void plus(function f1) {
		if (f1 == null)
			throw new ArithmeticException("Function cannot be null");

		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		if (f1 == null)
			throw new ArithmeticException("Function cannot be null");

		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Times;
	}

	@Override
	public void div(function f1) {
		if (f1 == null)
			throw new ArithmeticException("Function cannot be null");

		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Divid;
	}

	@Override
	public void max(function f1) {
		if (f1 == null)
			throw new ArithmeticException("Function cannot be null");

		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Max;
	}

	@Override
	public void min(function f1) {
		if (f1 == null)
			throw new ArithmeticException("Function cannot be null");

		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Min;
	}

	@Override
	public void comp(function f1) {
		if (f1 == null)
			throw new ArithmeticException("Function cannot be null");

		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Comp;
	}

	// ********** utils function for this class only ***********

	/**
	 * Function to return operation from enums by string.
	 * 
	 * @param string
	 * @return
	 */
	private Operation getOperationByString(String string) {
		switch (string.toLowerCase()) {
		case "plus":
			return Operation.Plus;
		case "mul":
			return Operation.Times;
		case "div":
			return Operation.Divid;
		case "max":
			return Operation.Max;
		case "min":
			return Operation.Min;
		case "comp":
			return Operation.Comp;
		case "none":
			return Operation.None;
		case "error":
			throw new ArithmeticException("Operation doesn't exist");
		default:
			throw new ArithmeticException("Operation doesn't exist");
		}
	}

	/**
	 * Function to return string by operation enum.
	 * 
	 * @param Operation
	 * @return
	 */
	private String getStringByOperation(Operation op) {
		switch (op) {
		case Plus:
			return "plus";
		case Times:
			return "mul";
		case Divid:
			return "div";
		case Max:
			return "max";
		case Min:
			return "min";
		case Comp:
			return "comp";
		case None:
			return "none";
		case Error:
			throw new ArithmeticException("Operation doesn't exist");
		default:
			throw new ArithmeticException("Operation doesn't exist");
		}
	}

	/**
	 * This function is the core of initFromString I used recursive way to handle
	 * the string , every time cut the operation , the left side of the function and
	 * the right side. Then create new ComplexFunction object , and calling again to
	 * the recursive function. My stop condition is when the currentString is not
	 * one the operations that is valid by the validateOperationString function If
	 * from some reason , the user didn't enter a valid operation (See what valid in
	 * the function validateOperationString) so the Polynom constructor will throw
	 * exception because it's not a valid polynom format.
	 * 
	 * @param cf
	 * @param s
	 * @return
	 * @throws ArithmeticException
	 */
	private function initFromStrRecursive(ComplexFunction cf, String s) throws ArithmeticException {
		if (!isBracketsEqual(s))
			throw new ArithmeticException("The brackets of the string isn't equal , so the string is not valid");

		String nextString = s;
		String currentString = "";
		if (s.contains("(")) {
			currentString = s.substring(0, s.indexOf("("));
			nextString = s.substring(s.indexOf("(") + 1, s.length() - 1);
		}
		String arr[];
		if (nextString.contains(")")) {
			arr = splitStringWithBrackets(nextString);
		} else {
			arr = nextString.split(",");
		}
		if (validateOperationString(currentString)) {
			// operation
			cf = new ComplexFunction();
			cf.op = getOperationByString(currentString);
			cf.left = initFromStrRecursive(cf, arr[0]);
			cf.right = initFromStrRecursive(cf, arr[1]);
		} else {
			// polynom or some operation that isn't valid...
			// if it's operation that isn't valid , will throw error in Monom class.
			// maybe can check here with regex if it's valid Polynom or not..
			return new Polynom(arr[0]);

		}
		return cf;
	}

	/**
	 * This function split the string by comma and brackets If I saw the char '('
	 * and the char ')' the same time before I saw comma , I'm splitting the string
	 * by brackets , otherwise I'm splitting by comma and then break the function.
	 * 
	 * @param nextString - next string to handle on initFrom string
	 * @return arr of 2 string , on index 0 got the left function , on index 1 got
	 *         the right function.
	 */
	private String[] splitStringWithBrackets(String nextString) {
		String[] arr = new String[2];
		int brackets[] = { 0, 0 };
		boolean sawComma = false;
		for (int i = 0; i < nextString.length(); i++) {
			char strAt = nextString.charAt(i);
			if (strAt == '(')
				brackets[0]++;
			else if (strAt == ')')
				brackets[1]++;
			else if (strAt == ',' && brackets[0] == 0 && brackets[1] == 0)
				sawComma = true;
			if ((brackets[0] != 0 && brackets[1] != 0 && brackets[0] == brackets[1])) {
				arr[0] = nextString.substring(0, i + 1);
				arr[1] = nextString.substring(i + 2, nextString.length());
				break;
			} else if (sawComma) {
				arr[0] = nextString.substring(0, i);
				arr[1] = nextString.substring(i + 1, nextString.length());
				break;
			}
		}
		return arr;
	}

	/**
	 * This function check before initFromStrRecursive is starting , that the input
	 * string is valid. That means that the amount of open brackets and closing
	 * brackets are equal. (Not in the order) - this why I'm surrounding the
	 * function with Execption
	 * 
	 * @param s
	 * @return
	 */
	private boolean isBracketsEqual(String s) {
		int brackets[] = { 0, 0 };
		// brackets[0] = count of '('
		// brackets[1] = count of ')'
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				brackets[0]++;
			else if (s.charAt(i) == ')')
				brackets[1]++;
		}
		return brackets[0] == brackets[1];
	}

	/**
	 * Function to check if string is exactly one of the operations strings
	 * (plus,div,mul,min,max,comp)
	 * 
	 * @param s
	 * @return
	 */
	private boolean validateOperationString(String s) {
		String patternStr = "^(plus|div|mul|min|max|comp)$";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

}
