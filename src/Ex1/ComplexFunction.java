package Ex1;

public class ComplexFunction implements complex_function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private function left;
	private function right;
	private Operation op;

	/**
	 * This function is constructor that get Operator as string and two function , f1 and f2
	 * @param string
	 * @param left
	 * @param right
	 */
	public ComplexFunction(String string, function left, function right) {
		this.left = left.copy();
		this.right = right.copy();
		this.op = getOperationByString(string);
	}
	
	public ComplexFunction(ComplexFunction cf) {
		this.op = cf.op;
		this.left = cf.left.copy();
		this.right = cf.right.copy();
	}

	public ComplexFunction(function f) {
		if (!(f instanceof ComplexFunction)) {
			this.left = f.copy();
			this.right = null;
			this.op = Operation.None;
		}
		else {
			throw new ArithmeticException("For ComplexFunction object please use the constructor that get ComplexFunction as param");
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
		this.left = left;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}

	/**
	 * @param op the op to set
	 */
	public void setOp(Operation op) {
		this.op = op;
	}

	@Override
	public double f(double x) {
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

	@Override
	public function initFromString(String s) {

		return null;
	}

	@Override
	public function copy() {
		return new ComplexFunction(this);
	}

	@Override
	public void plus(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Times;
	}

	@Override
	public void div(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Divid;
	}

	@Override
	public void max(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Max;
	}

	@Override
	public void min(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Min;
	}

	@Override
	public void comp(function f1) {
		this.left = this.copy();
		this.right = f1;
		this.op = Operation.Comp;
	}

	private Operation getOperationByString(String string) {
		switch (string.toLowerCase()) {
		case "plus":
			return Operation.Plus;
		case "times":
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
			return Operation.Error;
		default:
			throw new ArithmeticException("Operation doesn't exist");
		}
	}

}
