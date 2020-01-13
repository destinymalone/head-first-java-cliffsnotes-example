# Writing a program

## Extra-Strength Methods

## Table of Contents

- Building the Sink a Dot Com game
- Starting with the Simple Dot Com game (a simpler version)
- Writing prepcode (pseudocode for the game)
- Test code for Simple Dot Com
- Coding the Simple Dot Com game
- Final code for Simple Dot Com
- Generating random numbers with Math.random()
- Ready-bake code for getting user input from the command-line
- Looping with for loops
- Casting primitives from a large size to a smaller size
- Converting a String to an int with Integer.parseInt()
- Exercises and puzzles
- Overview

#### Each lesson within chapter 5 is completed and explained in order

## Let's build a Battleship-style game: "Sink a Dot Com"

Build a game with a 7 x 7 grid and three Dot Coms. Each Dot Com takes up three cells.
Sink all of the computer's ships with the fewest number guesses.

## What should the methods and classes be?

You need a general idea of what the flow should be first.

The flow--

User starts the game:
Game creates three Dot Coms. Game places the three Dot Coms onto a virtual grid.

Game play begins:
Prompt the user for a guess ("A2", "C0", "A3", "C5", etc.). Check the user guess against all Dot Coms to look for a hit, miss, or kill. Take appropriate action: If a hit, delete cell ("A2", "D4", etc.). If a kill, delete Dot Com.

Game finishes:
Give the user a rating based on the number of guesses.

The next step is to figure out what kind of objects we'll need to do the work. Remember, think like Brad rather than Larry; focus first on the things in the program rather than the procedures.

## The "Simple Dot Com Game" a gentler introduction

We are going to need at least two classes, a Game class and a DotCom class. We first start with a stripped-down, simplified version, Simple Dot Com Game. Instead of a 2-D grid, we hide the Dot Com in just a single row and we will have just one Dot Com. The game still needs to make a DotCom instance, assign it a location somewhere in the row, get user input, and when all of the DotCom's cells have been hit, the game is over.

In the simple version, the game class has no instance variables, and all the game code is in the main() method, so when the program is launched and main() begins to run, it will make the one and only DotCom instance, pick a location for it(three consecutive cells on the single virtual seven-cell row), and ask the user for a guess, check the guess, and repeat until all three cells have been hit.

### Developing a Class

We start with prepcode. Most prepcode include three parts: instance variable declarations, method declarations, and method logic. The most important part of prepcode is the method logic because it defines what has to happen, which will later translate into how, when we actually write the method code.

---

Declare an int array to hold the location cells, Call it `locationCells`.

Declare an int to hold the number of hits. Call `numOfHits` and `SET` it to 0.

---

Declare a `checkYourself()` method that takes a String for the user's guess ("1", "3", etc.), checks it and returns a result representing a `hit`, `miss`, or `kill`.

Declare a `setLocationCell()` setter method that takes an int array (which has the three cell locations as ints (2, 3, 4, etc.)).

---

    Method: String checkYourself(String userGuess)

        Get the user guess to an int

        Convert the user guess to an int

        Repeat with each other of the location cells in the int array

            <!-- Compare the user guess to the location cell -->

            If the user guess matcher

                Increment the number of hits

                <!-- Find out if it was the last location cell: -->

                If number of hits is 3, return "kill" as the result

                Else it was not a kill, so return "hit"

                End if

            Else the user guess did not match, so return "miss"

            End if

        End repeat

    End method

    Method: void setLocationCells(int[] cellLocations)

        Get the cell location as an int array parameter

        Assign the cell locations parameter to the cell locations instance variables

    End method

## What should I know about writing the method implementations?

We start with writing test code next. This is called `Extreme Programming`, which is a method that was used to allow code to be written and tested much quicker. So, we need to write test code that can make a `SimpleDotCom` object and run its methods. For the `SimpleDotCom` class, we only care about the `checkYourself()` method, although we will have to implement the other methods in order to get the `checkYourself()` method to run correctly.

If the `checkYourself()` method were implemented, what test code could I write that would prove to me the method is working correctly?

Based on this prepcode:

    Method: String checkYourself(String userGuess)

        Get the user guess as a String parameter

        Convert the user guess to an int

        Repeat with each of the location cells in the int array

            <!-- Compare the user guess to the location cell -->

            If the user guess matches

                Increment the number of hits

                <!-- Find out if it was the last location cell -->

                If number of hits is 3, return "Kill" as the result

                Else it was not a kill, so return "Hit"

                End if

            Else the user guess did not match, so return "Miss"

            End if

        End repeat

    End method

Here is what we should test:

Instantiate a `SimpleDotCom` object. Assign it a location (an array of 3 ints, like {2,3,4}). Create a `String` to represent a user guess("2", "0", etc.). Invoke the `checkYourself()` method passing it the fake user guess. Print out the result to see if it's correct("passed" or "failed").

```java

public String checkYourself(String stringGuess) {
    int guess = Integer.parseInt(stringGuess);

    String result = "miss";

    for (int cell: locationCells) {
        if (guess == cell) {
            result = "hit";
            numOfHits++;
            break;
        }
    }

    if (numOfHits == locationCells.length) {
        result = "kill";
    }

    System.out.println(result);
    return result;
}

```

There are things that we haven't seen before so, let's explain them:

```java


Integer.parseInt("3");

```

`Integer`: A class that ships with Java.

`parseInt`: A method in the Integer class that knows how to "parse" a String into the int it represents.

`("3")`: Takes a String.

```java

for (int cell:locationCells) { }

```

`for` - Read this for loop declaration as "repeat for each element in the 'locationCells array: take the element in the array and assign it to the in variable 'cell'."

`(int cell)` - Declare a variable that will hold one element from the array. Each time through the loop, this variable (in this case an int variable named "cell"), will hold a different element from the array, until there are no more elements (or the code does a "break").

`:` - The colon means "in", so the whole thing means "for each int value IN locationCells..."

`locationCells` - The array to iterate over in the loop. Each time through the loop, the next element in the array will be assigned to the variable "cell".

```java

numOfHits++

```

`numOfHits++` is the same (in this case) as saying `numOfHits = numOfHits + 1`, except slightly more efficient.

```java

break;

```

Gets you out of a loop. Immediately. No, iteration, no boolean test, just leave the loop completely.

## Final code for SimpleDotCom and SimpleDotComTester

```java

public class SimpleDotComTestDrive {
    public static void main(String[] args) {
        SimpleDotCom dot = new SimpleDotCom();

        int [] locations = {2, 3, 4};
        dot.setLocationCells(locations);

        String userGuess = "2";
        String result = dot.checkYourself(userGuess);
    }
}

public class SimpleDotCom {
    int [] locationCells;
    int numOfHits = 0;

    public void setLocationCells(int[] locs) {
        locationCells = locs;
    }

    public String checkYourself(String stringGuess) {
        int guess = Integer.parseInt(stringGuess);

        String result = "miss";

        for (int cell : locationCells) {
            if (guess == cell) {
                result = "hit";
                numOfHits++;
                break;
            }
        }

        if (numOfHits == locationCells.length) {
            result = "kill";
        }

        System.out.println(result)
            return result;
    }
}

```

### What should we see when we run this code?

The test code makes a `SimpleDotCom` object and gives it a location at `2,3,4`. Then it sends a fake user guess of "2" into the `checkYourself()` method. If the code is working correctly, we should see the result print out: java `SimpleDotComTestDrive` ~ hit

## The game's main() method

Just as we did with the `SimpleDotCom` class, think about parts of the following code you might need to improve.

```java

public static void main(String[] args) {
    int numOfGuesses = 0;

    GameHelper helper = new GameHelper();

    SimpleDotCom theDotCom = new SimpleDotCom();

    int randomNum = (int) (Math.random() * 5);

    int [] locations = {randomNum, randomNum+1, randomNum+2};

    theDotCom.setLocationCells(locations);

    boolean isAlive = true;

    while (isAlive == true) {
        String guess = helper.getUserInput("enter a number");
        String result = theDotCom.checkYourself(guess);

        numOfGuesses++;

        if (result.equals("kill")) {
            isAlive = false;

            System.out.println("You took " + numOfGuesses + " guesses");
        }
    }
}

```

Now, let's talk about `ranom()` and `getUserInput()`.

#### random()

`int randomNum` - We declare an int variable to hold the random number we got back

`(int)` - This is a 'cast', and it forces the thing immediately after it to become the type of the cat (the type in the parenthesis). Math.random returns a double, so we have to cast it to be an int (we want nice whole numbers between 0 and 4). In this case, the cast drops the fraction part of the double.

`(Math)` - A class that comes with Java.

`random()` - A method of the Math class

`(() * 5)` - The Math.random method returns a number from 0 to just less than 1. So this formula (with the cast), returns a number from 0 to 4 (0 - 4.9999999999, cast to an int and drop numbers after the decimal).

#### getUserInput()

`String guess` - We declare a `String` variable to hold the user input `String` we get back ("3", "5", etc.).

`helper` - An instance we made earlier, of a class that we built to help with the game. It's called `GameHelper` and you haven't seen it yet.

`getUserInput` - A method of the GameHelper class that asks the user for command-line input, reads it in after the user hits RETURN/ENTER, and gives back the result as a String.

`("enter a number")` - This method takes a String argument that it uses to prompt the user at the command-line. Whatever you pass in here gets displayed in the terminal just before the method starts looking for user input.

## One last class: GameHelper

We made the dot com class. We made the game class. All that's left is the `helper` class, the one with the `getUserInput()` method.

```java

public class GameHelper {
    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");

        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));

            inputLine = is.readLine();

            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }
}

```

Take this code, compile it into a class named `GameHelper`(already done). Drop all three classes (`SimpleDotCom`, `SimpleDotComGame`, and `GameHelper`) into the same directory, and make it your working directory.

## Loops, Again

### Regular (non-enhanced) for loop

```java

for (int i = 0; i < 100; i++) { }

```

`(int i = 0)` - initialization

`i < 100` - boolean test

`i++` - iteration expression

`{ }` - the code to repeat goes here (the body)

---

What this says is, "Repeat 100 times.". The compiler sees it as: create a variable `i` and set it to 0, repeat while `i` is less than 100, at the end of each loop iteration, add 1 to `i`.

---

Initialization: Use this part to declare and initialize a variable to use within the loop body. You'll most often use this variable as a counter. You can actually initialize more than one here, but for now it is one.

Boolean test: This is where the conditional test goes. Whatever is in there, it must resolve to a boolean value (true or false). You can have a test, like (x >= 4), or you can even invoke a method that returns a boolean.

Iteration Expression: In this part, put one or more things you want to happen with each trip through the loop. Keep in mind that this stuff happens at the end of each loop.

### Trips through a loop

```java

for (int i = 0; i < 8; i++) {
    System.out.println(i);
}

System.out.println("done");

```

Declare `int i`. Set i to 0. Is i < 8? If true, enter loop body. Print the value of `i`. Increment `i` (the iteration expression). Repeat until loop ends. If false, print "done" (jump below loop).

### Difference between for and while

A `while` loop has only the boolean test; it doesn't have a built-in initialization or iteration expression. A `while` loop is good when you don't know how many times to loop and just want to keep going while some condition is true. But if you know how many times to loop, a for loop is cleaner.

Code rewritten using `while` loop:

```java

int i = 0;

while (i < 8) {
    System.out.println(i);
    i++;
}
System.out.println("done");

```

### The enhanced for loop

There is a second kind of "for" loop called the enhanced for, which makes it easier to iterate over all the elements in an array or other kinds of collections.

```java

for (String name: nameArray) { }

```

`String` - The elements in the array must be compatible with the declared variable type.

`name` - Declare an iteration variable that will hold a single element in the array.

`:` - The colon means "IN".

`nameArray` - The collection of elements that you want to iterate over. Imagine that somewhere earlier, the code said: `String[] nameArray = {"Fred", "Mary", "Bob"};` With the first iteration, the name variable has the value of "Fred", and with the second iteration, a value of "Mary", and so forth.

`{ }` - The code to repeat goes here (the body).

What this is actually saying is, "For each element in nameArray, assign the element to the 'name' variable, and run the body of the loop."

### What the compiler sees

Create a String variable called `name` and set it to `null`. Assign the first value in `nameArray` to `name`. Run the body of the loop (the code block bounded by curly braces). Assign the next value in `nameArray` to `name`. And, repeat while there are still elements in the array.

### Note:

Depending on the programming language they've used in the past, some people refer to the `enhanced for` loop as the "for each" or the "for in" loop, because that's how it reads: "for EACH thing IN the collection....".

## Casting primitives

In chapter 3, we talked about the sizes of the various primitives, and how you can't shove a big thing directly into a small thing:

```java

long y = 42;

int x = y;

```

A `long` is bigger than an `int` and the compiler can't be sure where that `long` has been. It might have gathered very big values by the time it was assigned to an `int`. To force the compiler to jam the value of a bigger primitive variable into a smaller one, you can use the `cast` operator. It looks like this:

```java

long y = 42;

int x = (int) y;

```

Putting in the `cast` tells the compiler to take the value of `y`, chop it down to `int` size, and set `x` equal to whatever is left. If the value of `y` was bigger than the maximum value of `x`, then what's left will be a weird (but 'calculable') number.

```java

long y = 40002;

// 40002 exceed the 16-bit limit of a short

short x = (short) y;

// x now equals -25534!

// The point is that the compiler still lets you cast it.

```

#### What if I have a float?

```java

float f = 3.14f;

int x = (int) f;

// x will equal 3

// You would do this, if you have a float and just want the whole number part (because you chose to use int)
```

### Note:

You can not cast anything to a boolean or vice versa. It involves sign bits, binary, and other factors that you do not know yet.

# Overview

Your Java program should start with a high-level design. Typically you'll write three things when you create a new class: precode, testcode, real (Java) code. Precode should describe what to do, not how to do it. Implementation comes later. Use the precode to help design the test code. Write test code before you implement the methods. Choose `for` loops over `while` loops when you know how many times you want to repeat the loop code. Use the pre/post increment operator to add 1 to a variable (x++;). Use pre/post decrement to subtract 1 from a variable (x--;). Use Integer.parseInt() to get the int value of a `String`. `Integer.parseInt()` works only if the String represents a digit ("0", "1", "2", etc.). Last, use break to leave a loop early (even if the boolean test condition is still true).
