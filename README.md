# Ariel OOP - Ex1 (ComplexFunction / Polynom / Monom) , Drawing functions

## Introduction:
This project is assignment from Ariel University.
Main purpose of the project is to create instances of Monom or Polynom or ComplexFunction
and drawing those instances on graph by f(x) values.

## Before using please read this:

* This project is using gson library , the library is part of the project , 
but after clone this project , you need to add the External jar of gson from the project folder.

* String format of Monom : 
	- "ax^b" , while a can be double , and b is integer.
	- Also support : "x","-x","1","1x"

* String format of Polynom: (based on Monom class)
	- Inifinte of monoms format that seperate by '+' or '-'
	- **No spaces is allowed between monoms..**

* String format of ComplexFunction: 
	- Can start with one of the following operations: ['plus','mul','div','min','max','comp'] , after that need to come open bracket '(' and then two values(Monom,Polynom or ComplexFunction as well) seperate by ',' and after that closing bracket ')'.
	- Can be String of Polynom or Monom.

