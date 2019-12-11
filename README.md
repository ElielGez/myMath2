# Ariel OOP - Ex1 (ComplexFunction / Polynom / Monom) , Drawing functions

![graph1](https://github.com/ElielGez/myMath2/blob/master/images_examples/Capture.JPG)

## Introduction:
This project is part of assignment in Ariel University.
Main purpose of the project is to create instances of Monom or Polynom or ComplexFunction
and drawing those instances on graph by f(x) values.

## Before using please read this:

* This project is using gson library , the library is part of the project , 
but after clone this project , you need to add the external jar of gson from the project folder.

* String format of Monom : 
	- "ax^b" , while a can be double , and b is integer.
	- Also support : "x","-x","1","1x"

* String format of Polynom: (based on Monom class)
	- Inifinte of monoms format that seperate by '+' or '-'
	- Spaces is allowed between monoms.

* String format of ComplexFunction: 
	- Can start with one of the following operations: ['plus','mul','div','min','max','comp'] , after that need to come open bracket '(' and then two values(Monom,Polynom or ComplexFunction as well) seperate by ',' and after that closing bracket ')'.
	- Can be String of Polynom or Monom.

* **The equals function of ComplexFunction isn't fully working, because there is infinity options to build complex function with polynom and monom.
	My partition solution is to run between -1000 and 1000 in loop(steps of i+0.7) and check if for any x on this range the f functions are equal.**
	
* For drawing functions on graph I used StdDraw library.
	
## Examples:

#### Monom Class:
```
Monom m = new Monom("5x^3");
double fx = m.f(1); //f(1) = 5 
```

#### Polynom Class:
```
Polynom p = new Polynom("-x+6-x^2");
double fx = p.f(3); //f(3) = -6
```

#### ComplexFunction Class:
```
String s = "mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
function f = new ComplexFunction().initFromString(s);
```

#### Drawing functions on graph:
```
Functions_GUI fg = new Functions_GUI();
String s = "mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
function f = new ComplexFunction().initFromString(s);
fg.add(f);
int w=1000, h=600, res=200;
Range rx = new Range(-10,10);
Range ry = new Range(-5,15);
fg.drawFunctions(w,h,rx,ry,res);
```

### **NOTE: More details about classes and interfaces of the project can be found on Wiki**
