# Breaking The Surface

## A Quick Dip

## Table of Contents

- The way Java works
- Code Structure in Java
- Anatomy of a class
- The main() method
- Looping
- Conditional branching(if tests)
- Coding the "99 bottles of beer" app
- Phrase-o-matic
- Fireside chat: compiler vs. JVM
- Exercises and puzzles

**Each lesson within chapter 1 is completed and explained in order.**

## Note:

Java -
Friendly syntax, object-oriented features, memory management, promise of portability.

Write-once/run anywhere

## How does Java work?

In order to run Java successfully you need to create a source file and then use an
established protocol for the file. The file will then be run through a source code
compiler, which will check for errors and won't finish compiling until it's satisfied
everything will run smoothly.

The compiler will then create a new file that is coded into Java bytecode. Any device
capable of running Java will be able to interpret/translate this file into something
it can run, meaning the compiled bytecode is platform-independent. You don't have a
physical Java Machine, but you have a virtual Java machine (implemented in software)
running inside each electronic gadget. The virtual machine reads and runs the bytecode.

### What is a Source File?

A source file is just a file with some code in it. In python, we write our code in `.py`
files. In Java, these "source" files will be in `.java` files.

## Can you explain the code structure in Java?

- Once you have a source file you will need to put a class in the source file, place methods within the class, and then place statements inside of that method.

## What is the anatomy of a class?

When Java Virtual Machine starts running, it looks for the class you give it at the command line. Then it starts looking for a specially-written method that looks exactly like:

```java

public static void main(String[] args) {
    // methods and statements here
}

```

Next, the Java Virtual Machine runs everything between the curly braces of your main method. Every Java application has to have at least one class, and at least one main method (not one main per class, just one main per application).

```java

public class MyFirstApp {
    public static void main(String[] args) {
        System.out.print("I Rule!");
    }
}

```

Let's explain what each piece's, from the code above, purpose is:

- `public`: Everyone can access it
- `class`: A class
- `MyFirstApp`: Name of this specific class
- `{`: First opening curly brace of the class
- `void`: The return type void means there`s no return value
- `main`: The name of this method
- `(String[] args)`: Arguments to the method. This method must be given an array of Strings, and the array- will be called `args`
- `{`: Opening brace of the method
- `System.out.print`: This says print to standard output (defaults to command-line)
- `("I Rule!")`: The string you want printed
- `;`: Every statement must end in a semicolon
- `}`: Closing brace of `main` method
- `}`: Closing brace of `MyFirstApp` class

Now that you know what each piece is used for, you can now compile and run this code inside of it's own file.

## How to write a class in main?

Everything goes in a class when using Java. You'll type a source code file, then compile it into a new class file. When you run the program, you'll really be running a class. Running a program means telling the Java Virtual Machine to "Load the 'MyFirstApp' class, them start executing its 'main()' method. Keep running until all the code in main is finished." Remember, no matter how many classes your program uses, there has to be a main() method to get the started.

### What can you say in the main method?

Main methods consist of statements, loops, and branching. The statements tell the file to do something. If you want that something to have some special instructions, you would implement a loop. There are for, while, and do-while loops. Each loop must pass a boolean within the parentheses, because it is used too check if your information is true or false. Branching does the same thing, tests your code to see whether or not it is true or false so it can continue to the end of the file.

## What should I know about looping?

Looping basically says, "As long as some condition is true, do everything inside the loop block". The loop block is bounded by a pair of curly braces, so whatever you want to repeat needs to be inside that block. The key to a loop is the conditional test. In Java, a conditional test is an expression that results in a boolean value--true or false, just as I stated in the section above. Below is a representation of how looping can be used.

```java

int x = 4;

while (x > 3) {
    x = x - 1;
}

int z = 27;

while (z == 17 ) {
    // This loop will not run because z is assigned to 27 and not 17, so no code is needed.
}

```

## What about conditional branching?

The code below will put your skills to the test. You can now write a class, structure it to be able to run, and use an if to test your work, producing an our come showing that it was either true or false.

```java

class IfTest {
    public static void main (String[] args) {
        int x = 3;

        if (x == 3) {
            System.out.println("x must be 3");
        }
        System.out.println("This runs no matter what");
    }
}

```

Only runs, "x must be 3", if x = 3 is true

Always will run, "This runs no matter what", even if x != 3

```java

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

```

Output:

x is NOT 3

This runs no matter what

### Note:

System.out.print vs. System.out.println:

System.out.println inserts a newline while System.out.print keeps printing on the same line.

## Coding A Serious Business Application

```java

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

```

You have compiled the file. It can run. You utilized looping, branching, printing strings, arrays, and using statements to make your class "do something". You also have concatenated strings and numbers together so the user can understand your work. Now you can run a Java file!

### Code Magnets

```java

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


```

Output:

a-b c-d

# Overview

Java has an overall source file that holds each class. Inside of that source file, there are classes that use methods to give instructions on how the finishing product should be produced. Methods work as a function or procedure that gets every detail defined for the main "function" to carry. The methods may consist of print statements, loops, and so forth. If/while/do-while statements are used to produce a boolean and you would help the user understand the work by printing a message which simply explains that the if/while/do-while statement, which was used to test work, was either true or false. Branching is used when testing code. if/while/do-while--Do this if it is true, else do something different without touching the lines associated with true statements.
