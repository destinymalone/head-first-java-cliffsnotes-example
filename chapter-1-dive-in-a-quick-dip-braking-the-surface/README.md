# Breaking The Surface

## A Quick Dip

## Table of Contents

The way Java works
Code Structure in Java
Anatomy of a class
The main() method
Looping
Conditional branching(if tests)
Coding the "99 bottles of beer" app
Phrase-o-matic
Fireside chat: compiler vs. JVM
Excerxises and puzzles

#### Each lesson within chapter 1 is completed and explained in order

## Note:

Java - 
Friendly syntax, object-oriented features, memory management, promise of portability.

Write-once/run anywhere


## The Way Java Works

Create a source document. Use an established protocol.

Run your document through a source code compiler. The compiler checks for errors and won't let you compile until it's satisfied that everything will run correctly.

The compiler creates a new document, coded into Java bytecode. Any device capable of running Java will be able to interpret/translate this file into something it can run. The compiled bytecode is platform-independent.

You don't have a physical Java Machine, but you have a virtual Java machine (implented in software) running inside each electronic gadget. The virtual machine reads and runs the bytecode

## What you'll do in Java

Type a source file, compile it using javac compiler, then run the compiled bytecode on a Java virtual machine

## Code Structure in Java

- Put a class in a source file.

- Put methods in a class.

- Put statements in a method.

## Anatomy of a class

When Java Virtual Machine starts running, it lookss for the class you give it at the command line. Then it starts looking for a specially-written method that looks exactly like:

````java

public static void main(String[] args) {
    // methods and statements here
}

````

Next, the Java Virtual Machine runs everything between the curly braces off your main method. Every Java application has to have at least one class, and at least one main method (not one main per class, just one main per application).

````java

public class MyFirstApp {
    public static void main(String[] args) {
        System.out.print("I Rule!");
    }
}

````

Public: Everyone can access it

Class: A class

MyFirstApp: Name of this specific class

'{': First opening curly brace of the class

Void: The return type void means there's no return value

Main: The name of this method

(String[] args): Arguments to the method. This method must be given an array of Strings, and the array will be called 'args'

'{': Opening brace of the method

System.out.print: This says print to standard output (defaults to command-line)

("I Rule!"): The string you want printed

';': Every statement must end in a semicolon

'}': Closing brace of main method

'}': Closing brace of 'MyFirstApp' class


## Writing a class with a main

Everythiing goes in a class when using Java.

You'll type a source code file, then compile it into a new class file.

When you run the program, you'll really be running a class.

Running a program means telling the Java Virtual Machine to "Load the 'MyFirstApp' class, them start executing its 'main()' method. Keep running until all the code in main is finished."

No matter how many classes your program uses, there has to be a main() method to get the ball rolling.

## What can you say in the main method?

Statements--do something

Loops--for, while, and do-while: > (greater than), < (less than), == (equality)--one = is assignment---boolean inside parentheses

Branching--if/else tests

## Looping

As long as some condition is true, you do everything inside the loop block

The loop block is bounded by a pair of curly braces, so whatever you want to repeat needs to be inside that block.

The key to a loop is the conditional test.

In Java, a conditional test is an expression that results in a boolean value--true or false

````java

int x = 4;

while (x > 3) {
    x = x - 1;
}

int z = 27;

while (z == 17 ) {
    // This loop will not run because z is assigned to 27 and not 17, so no code is needed.
}

````

## Conditional Branching

````java

class IfTest {
    public static void main (String[] args) {
        int x = 3;

        if (x == 3) {
            System.out.println("x must be 3");
        }
        System.out.println("This runs no matter what");
    }
}

````

Only runs, "x must be 3", if x = 3 is true

Always wil run, "This runs no matter what", even if x != 3

````java

class IfTest2 {
    public static void main(String[] args) {
        int x = 2;

        if (x == 3) {
            System.out.println("x must be 3");
        } else {
            System.out.println("x is NOT 3");
        }
        System.out.println("This runs no matter what");
    }
}

````

Output: 

x is NOT 3

This runs no matter what



## Note:

System.out.print vs. System.out.println:

System.out.println inserts a newline while System.out.print keeps printing on the same liine.


## Coding A Serious Business Application

````java

public class BeerSong {
    public static void main(String[] args) {
        int beerNum = 99;
        String word = "bottles";

        while (beerNum > 0) {
            if (beerNum == 1) {
                word = "bottle";
            }

            System.out.println(beerNum + " " + word + " of beer on the wall");
            System.out.println(beerNum + " " + word + " of beer.");
            System.out.println("Take one down.");
            System.out.println("Pass it around.");
            beerNum = beerNum - 1;

            if (beerNum > 0) {
                System.out.println(beerNum + " " + word + " of beer on the wall");
            } else {
                System.out.println("No more bottles of beer on the wall");
            }
        }
    }
}

````

You have compiled the file. It can run. You utilized looping, branching, printing strings, arrays, and using statements to make your class "do something". You also have concatenated strings and numbers together so the user can understand your work.

Now you can program Java

## Code Magnets

````java

class Shuffle1 {
    public static void main(String[] args) {
        int x = 3;
        while (x > 0) {

            if (x > 2) {
                System.out.print("a");
            }

            x = x - 1;
            System.out.print("-");

            if (x == 2) {
                System.out.print("b c");
            }

            if (x == 1) {
                System.out.print("d");
                x = x - 1;
            }
        }
    }
}


````

Output:

a-b c-d


# Overview

Java has an overall source file that holds each class.

Inside of that source file, there are classes that use methods to give instructions for how the finishing product should be produced.

Methods work as a function or procedure that gets every detail defined for the main function to carry.

The methods may consist of print statements, loops, and so forth.

If/while/do-while statements are used to produce a boolean and you would help the user understand the work by printing a message which simply explains that that if/while/do-while statement, which was used to test work, was either true or false.

Variables are used as object "state" and as local variables. -- Instance variables and variables declared within a method

Variables are declared as Integers, Strings, and arrays

