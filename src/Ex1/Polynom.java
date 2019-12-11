package Ex1;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {
	private LinkedList<Monom> monoms;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		monoms = new LinkedList<Monom>();
	}

	/**
	 * Constructor that get string and split by - or + , and then call Monom add function
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		this();
		if (s == null)
			throw new ArithmeticException("String cannot be null");
		s = s.replaceAll(" ","");
		String[] splittedArr = s.split("(?=[+-])");
		for (int i = 0; i < splittedArr.length; i++) {
			Monom m = new Monom(splittedArr[i]);
			this.add(m);
		}
	}

	/**
	 * Calling f function of Monom by iterator function of the LinkedList<Monom>
	 */
	@Override
	public double f(double x) {
		double ans = 0;
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			ans = ans + m.f(x);
		}
		return ans;
	}

	/**
	 * Calling add function of Polynom by iterator function of the LinkedList<Monom>
	 * @param p1 - Instance of object that implements Polynom_able
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			Monom m2 = it.next();
			this.add(m2);
		}
	}

	/**
	 * Calling add function of Monom by iterator function of the LinkedList<Monom>
	 */
	@Override
	public void add(Monom m1) {
		Iterator<Monom> it = this.iteretor();
		boolean flag = false;
		while (it.hasNext()) {
			Monom m = it.next();
			if (m.get_power() == m1.get_power()) {
				m.add(m1);
				flag = true;
				break;
			}
		}
		if (!flag) {
			monoms.add(m1);
		}
	}
	
	/**
	 * Calling substract function of Monom by iterator function of the LinkedList<Monom>
	 */
	@Override
	public void substract(Monom m1) {
		Iterator<Monom> it = this.iteretor();
		boolean flag = false;
		while (it.hasNext()) {
			Monom m = it.next();
			if (m.get_power() == m1.get_power()) {
				m.substract(m1);
				flag = true;
				break;
			}
		}
		if (!flag) {
			m1.multiply(Monom.MINUS1);
			monoms.add(m1);
		}
	}
	
	/**
	 * Calling substract function of Polynom by iterator function of the LinkedList<Monom>
	 * @param p1 - Instance of object that implements Polynom_able
	 */
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			Monom m2 = it.next();
			this.substract(m2);
		}
	}

	/**
	 * Calling substract function of Monom by iterator function of the LinkedList<Monom>
	 */
	@Override
	public void multiply(Monom m1) {
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			m.multiply(m1);
		}
	}

	/**
	 * Calling substract function of Polynom by iterator function of the LinkedList<Monom>
	 * @param p1 - Instance of object that implements Polynom_able
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom p = new Polynom();
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			Polynom_able copy = this.copy();
			Monom m2 = it.next();
			copy.multiply(m2);
			p.add(copy);
		}
		this.monoms = p.monoms;
	}

	/**
	 * equals function 
	 * Override Object function in order to use JUNIT assert functions
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Polynom_able) {
			Polynom_able p1 = (Polynom_able) obj;
			return substractEqualsCheck(p1);
		}
		return false;
	}

	@Override
	public boolean isZero() {
		boolean flag = true;
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			if (!m.isZero()) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if (f(x0) * f(x1) > 0) {
			throw new ArithmeticException("As asked , f(x0) * f(x1) need to be <= 0");
		}
		double root = x0;
		while ((x1 - x0) >= eps) {
			root = (x0 + x1) / 2;
			if (f(root) == 0.0)
				break;
			else if (f(root) * f(x0) < 0)
				x1 = root;
			else
				x0 = root;
		}
		return root;
	}

	@Override
	public Polynom_able copy() {
		Polynom p = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			p.monoms.add(new Monom(it.next()));
		}
		return p;
	}

	@Override
	public Polynom_able derivative() {
		Polynom p = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			p.add(m.derivative());
		}
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		if (x0 >= x1)
			return 0;
		double area = 0;
		int moves = (int) ((x1 - x0) / eps);
		for (int i = 0; i < moves; i++) {
			double temp = 0;
			double f1 = this.f(x0 + (eps * i));
			double f2 = this.f(x0 + (eps * (i - 1)));
			double averageHeight = (f1 + f2) / 2;
			temp = eps * averageHeight;
			if (temp >= 0) // take positive area only
				area += temp;
		}
		return area;
	}

	/**
	 * This function return the iterator of the LinkedList<Monom>. 
	 * Because LinkedList implements the interface List
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it = monoms.iterator();
		return it;
	}

	public String toString() {
		this.sort();
		String str = "";
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			str += m.get_coefficient() >= 0 ? "+" + m : m;
		}
		return str;
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	private boolean substractEqualsCheck(Polynom_able p) {
		Polynom_able copy = this.copy();
		copy.substract(p);
		boolean flag = true;
		Iterator<Monom> it = copy.iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			if (Math.abs(m.get_coefficient()) < 0 || Math.abs(m.get_coefficient()) > Monom.EPSILON) {
				flag = false;
				break;
			}
		}
		return flag;

	}

	public void sort() {
		this.monoms.sort(Monom.getComp());
	}

	/**
	 * initial Polynom from string , just call the Polynom constructor and return instance of the object
	 */
	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}

}
