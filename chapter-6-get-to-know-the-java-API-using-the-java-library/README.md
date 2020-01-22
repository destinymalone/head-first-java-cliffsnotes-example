# Using the Java Library

## get to know the JAVA API

## Table of Contents

- Analyzing the bug in the Simple Dot Com Game
- ArrayList (taking advantage of the Java API)
- Fixing the DotCom class code
- Building the real game (Sink a Dot Com)
- Prepcode for the real game
- Code for the real game
- Boolean expressions
- Using the library (Java API)
- Using packages (import statements, fully-qualified names)
- Using the HTML API docs and reference books
- Exercises and puzzles
- Overview

#### Each lesson within chapter 6 is completed and explained in order

### Analyzing the bug in the Simple Dot Com Game

In the last chapter, you had a bug with the cliff-hanger. It was supposed to look like when you enter the numbers: '1', '2', '3', '4', '5', '6' --

enter a number - 1:
miss
enter a number - 2:
miss
enter a number - 3:
miss
enter a number - 4:
hit
enter a number - 5:
hit
enter a number - 6:
kill
You took 6 guesses.

When you enter: '2', '2', '2'; it is supposed to look like this:

enter a number - 2:
hit
enter a number - 2:
hit
enter a number - 2:
kill
You took 3 guesses.

In the current version, once you get a hit, you can simply repeat that hit two more times to get a kill! So what happened?

```java

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

    System.out.println(result);

    return result;
}

```

Where it went wrong:

```java

if (guess == cell) {
    result = "hit";
    numOfHits++;
}

```

We counted a hit every time the user guessed a cell location, even if that location had already been hit! We need a way to know that when a user makes a hit, the user hasn't previously hit that cell. If the user has, then we don't want to count it as a hit.

#### How do we fix it?

What we know: We have a virtual row of 7 cells, and a DotCom will occupy three consecutive cells somewhere in that row. This virtual ro shows a DotCom placed at cell locations 4, 5, and 6. The DotCom has an instance variable-an int array-that holds that DotCom object's cell locations.

#### Option 1:

We could make a second array, and each time the user makes a hit, we store that hit in the second array. and then check that array each time we get a hit, to see if that cell has been hit before. The value of the `hitCells` array would be a boolean.

##### Option 1 is too clunky

Option one seems like more work than you'd expect. It means that each time the user makes a hit, you have to change the state of the second array (the `hitCells` array), but first you have to CHECK the `hitCells` array to see if that cell has already been hit anyway. It would work, but there's got to be something better.....

#### Option 2:

We could just keep the one original array, but change the value of any hit cells to -1. That way, we only have ONE array to check and manipulate. A -1 at a particular cell location would mean that the cell has already been hit, so we're only looking for non-negative numbers in the array.

##### Option 2 us a little better, but still pretty clunky

Option two is a little less clunky than option one, but not very efficient. You'd still have to loop through all three slots (index positions) in the array, even if one or more are already invalid because they've been `hit` (and have a -1 value). Let's try again.

#### Option 3:

We delete each cell location as it gets hit, and then modify the array to be smaller. Except array's can't change their size, so we have to make a new array and copy the remaining cells from the old array into the new smaller array.

#### Note:

Option three would be much better if the array could shrink, so that we wouldn't have to make a new smaller array, copy the remaining values in, and reassign the reference.

### Library

You may not be able to shrink an array, but you can use an ArrayList. A class in the core Java library (the API).

The Java Standard Edition ships with hundreds of pre-built classes and these built-in classes are already compiled. That means no typing.

### ArrayList

- add(Object element): Adds the object parameter to the list.
- remove(int index): Removes the object at the index parameter.
- remove(Object element): Removes this object (if it's in the ArrayList).
- contains(Object element): Returns `true` if there's a match for the object parameter.
- isEmpty(): Returns `true` if the list has no elements.
- indexOf(Object Element): Returns either the index of the object parameter, or -1.
- size(): Returns the number of elements currently in the list.
- get(int index): Returns the object currently at the index parameter.

#### Note:

The `add(Object element)` method actually looks a little stranger than the one we've shown. For now, just think of it as an `add()` method that takes the object you want to add. The methods above, are just SOME of the methods in ArrayList.

### Comparing ArrayList to a regular array

1. A plain old array has to know its size at the time it's created. But for ArrayList, you just make an objects of type ArrayList. Every time. It never needs to know how big it should be, because it grows and shrinks as objects are added or removed.

```java

new String[2];

new ArrayList<String>();

```

The first array needs a size. No size is required, for the ArrayList (you can give it a size if you want).

---

2. To put an object in a regular array, you must assign it to a specific location.

```java

myList[1] = b;

// An index from 0 to one less than the length of the array.

```

The list needs and index. If that index is outside the boundaries of the array (like, the array was declared with a size of '2', and now you're trying to assign something to index '3'), it blows up at runtime. With ArrayList, you can specify an index using the `add(anInt, anObject)` and the ArrayList will keep growing to make room for the new thing.

```java

myList.add(b);

// No index.

```

---

3. Arrays use array syntax that's not used anywhere else in Java. But ArrayLists are plain old Java objects, so they have no specific syntax.

```java

myList[1];

// The array brackets `[]` are special syntax used for arrays.

```

---

4. ArrayLists in Java 5.0 are parameterized. Unlike arrays, ArrayLists have no special syntax. But they do use something special that was added to Java 5.0 Tiger-parameterized types.

```java

ArrayList<String>;

// The <String> in angle brackets is a "type parameter". ArrayList<String> means simply "a list of Strings", as opposed to ArrayList<Dog> which means, "a list of Dogs".

```

Prior to Java 5.0, there was no way to declare the `type` of things that would go in the ArrayList, so to the compiler, all ArrayLists were simply heterogenous collections of objects. But now, using the <typeGoesHere> syntax, we can declare and create an ArrayList that knows (and restricts) the types of objects it can hold. All you need to know now is, the syntax is a way to force the compiler to allow ony a specific type of object (the type in angle brackets) in the ArrayList.

### Let's fix the DotCom code.

Remember, this is how the buggy version looks:

```java

public class DotCom {
    int[] locationCells;
    int numberOfHits = 0;

    public void setLocationCells(int[] locs) {
        locationCells = locs;
    }

    public String = checkYourself(String stringGuess) {
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

        System.out.println(result);
            return result;
    }
}

```

#### Note:

The class was renames DotCom now (instead of SimpleDotCom), for the new advanced version, but this is the same code you saw in the last chapter.

#### New and improved DotCom class

```java

import java.util.ArrayList;

// Ignore that line for now

public class DotCom {
    private ArrayList<String> locationCells;
    // Change the String array to an ArrayList that holds Strings.
    // private int numOfHits; -- don't need that now

    public void setLocationCells(ArrayList<String> locs) {
        locationCells = loc;
    }

    public String checkYourself(String userInput) {
        // new and improved argument name -- userInput

        String result = "miss";
            int index = locationCells.indexOf(userInput);

            // Find out if the user guess is in the ArrayList, by asking for its index. If it's not in the list, then indexOf() returns a -1

            if (index >= 0) {
                // If index is greater than or equal to zero, the user guess is definitely in the list, so remove it.

                locationCells.remove(index);

                // Removing the index from list

                if (locationCells.isEmpty()) {

                    // If the list is empty, this was the killing blow!

                    result = "kill";
                } else {
                    result = "hit";
                }
            }
            return result;
    }
}

```

### Let's build the REAL game: "Sink a Dot Com"

Let's get out of simple and turn up the heat. Instead of a single row, we'll use a grid. And instead of one DotCom, we'll use three.

Goal: Sink all of the computer's Dot Coms in the fewest number of guesses. You're given a rating level based on how well you perform.

Setup: When the ame program is launched, the computer places three Dot Coms, randomly, on the virtual 7 x 7 grid. When that's complete, the game asks for your first guess.

How to play: On the command line, the computer will prompt you to enter a guess (a cell), which you'll type (as "A3", "C5", etc.). In response to your guess, you'll see a result at the command-line, either "hit", "miss", or "You sunk Pets.com" (or whatever the lucky Dot Com of the day is). When you've sent all three Dot Coms to that big 404 in the sky, the game ends by printing out your rating.

#### What needs to change?

We have three classes that need to change: the DotCom class (which is now called DotCom instead of SimpleDotCom), the game class (DotComBust) as the game helper class.

A) DotCom class: Add a name variable to hold the name of the DotCom ("Pets.com" "Go2.com", etc.) so each DotCom can print its name when it's killed.

B) DotComBust class (the game): Create three DotComs instead of one. Give each of the three DotComs a `name`. Call a setter method on each DotCom instance, so that the DotCom can assign the name to its `name` instance variable. Put the DotComs on a grid rather than just a single row, and do it for all three DotComs. This step is now way more complex than before, if we're going to place the DotComs randomly. We put the algorithm for giving the DotComs a location into the GameHelper class. Check each user guess with all three DotComs, instead of just one. Keep playing the game until there are no more live DotComs. Get out of main.

### The DotComBust code

````java

import java.util.*;

public class DotComBust {
    // Declare and initialize the variables we'll need.
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    // Make an ArrayList of DotCom objects (in other words, a list that will hold ONLY DotCom objects, just as DotCom[] would mean an array of DotCom objects).
    private int numOfGuesses = 0;

    private void setUpGame() {
        // first make some dot coms and give them locations
        DotCom one = new DotCom();
        one.setName("Pets.com");

        DotCom two = new DotCom();
        two.setName("eToys.com");

        DotCom three = new DotCom();
        three.setName("Go2.com");

        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        // Make three DotCom objects, give them names, and stick them in the ArrayList.

        System.out.println("Your goal is to sink three dot coms.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them all in the fewest number of guesses");

        // Print brief instructions for user.

        for (DotCom dotComToSet : dotComsList) {
            // Repeat with each SotCom in the list
            ArrayList<String> newLocation = helper.placeDotCom(3);
            // Ask the helper for a DotCom location (an ArrayList of Strings).
            dotComToSet.setLocationCells(newLocation);
            // Call the setter method on this DotCom to give it the location you just got from the helper.
        }
    }

    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            // As long as the DotCom list is NOT empty(the '!' means NOT, it's the same as (dotComsList.isEmpty() == false).
            String userGuess = helper.getUserInput("Enter a guess");
            // Get user input.
            checkUserGuess(userGuess);
            // Call our own checkUserGuess method.
        }
        finishGame();
        // Call our own finishGame method.
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        // increment the number of guesses the user has made.

        String result = "miss";
        // Assume it's a "miss", unless told otherwise.

        for (DotCom dotComToTest : dotComsList) {
            // Repeat with all DotComs in the list.
            result = dotComToTest.checkYourself(userGuess);
            // Ask the DotCom to check the user guess, looking for a hit (or kill).

            if (result.equals("hit")) {
                break;
                // Get out of the loop early, no point in testing the others.
            }
            if (result.equals("kill")) {
                dotComsList.remove(dotComToTest);
                // This guy's dead, so take him out of the DotComs list then get out of the loop.
                break;
            }
        }

        System.out.println(result);
        // Print the result of the user.
    }

    private void finishGame() {
        System.out.println("All Dot Coms are dead! Your stock is now worthless.");

        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options");

            // Print a message telling the user how they did in the game.
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        // Create the game object
        game.setUpGame();
        // Tell the game object to set up the game
        game.startPlaying();
        // Tell the game object to start the main game play loop (keeps asking for user input and checking the guess).
    }
}

````

#### The final version of the DotCom class

````java

import java.util.*;

public class DotCom {
    private ArrayList<String> locationCells;
    private String name;
    // DotCom's instance variables: an ArrayList of cell location; the DotCom's name.

    public void setLocationCells(ArrayList<String> loc) {
        // A setter method that updates the DotCom's location. (Random location provided by the `GameHelper placeDotCom()` method.)
        locationCells = locs;
    }

    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        // The ArrayList `indexOf()` method in action! If the user guess is one of the entries in the ArrayList, indexOf() will return its ArrayList location. If not, indexOf() will return -1.

        if (index >= 0) {
            locationCells.remove(index);
            // Using ArrayList's `remove()` method to delete an entry.

            if (locationCells.isEmpty()) {
                // Using the `isEmpty()` method to see if all of the locations have been guessed.
                result = "kill";
                System.out.print("Ouch! You sunk " + name + " : ( ");
                // Tell user when a DotCom has been sunk.
            } else {
                result = "hit";
            }
        }
        return result;
        // Return: 'miss' or 'hit' or 'kill'.
    }
}

````

### Super Powerful Boolean Expressions

So far, we've used boolean expressions for our loops or `if` tests, they've been pretty simple. We will be using more powerful boolean expression in the next code you will see.

#### 'And' and 'Or' Operators ( &&, || )

Let's say you're writing a `chooseCamera()` method, with lots of rules about which camera to select. Maybe you can choose cameras ranging from $50 to $1000, but in some cases you want to limit the price range more precisely. You want to say something like: 

'If the price `range` is between $300 `and` $400 then choose X.'

````java

if (price >= 300 && price < 400) {
    camera = "X";
}

````

Let's say that of the ten camera brands available, you have some logic that applies to only a `few` of the list:

````java

if (brand.equals("A") || brand.equals("B")) {
    // do stuff for only brand A or brand B
}

````

Boolean expressions can get really big and complicated:

````java

if ((zoomType.equals("optical") && (zoomDegree >= 3 && zoomDegree <= 8)) || (zoomType.equals("digital") && (zoomDegree >= 5 && zoomDegree <= 12))) {
    // do appropriate zoom stuff
}

````

If you want to get really technical, you might wonder about the precedence of these operators. Instead of becoming an expert in the arcane world of precedence, we recommend that you use parentheses to make your code clear.

#### Not equals ( != and ! )

Let's say that you have a logic like, "Of the ten available camera models, a certain thing is `true` for all but one."

````java

if (model != 2000) {
    // do non-model 2000 stuff
}

````

Or for comparing objects like strings....

````java

if (!brand.equals("X")) {
    // do non-brand X stuff
}

````

#### Short Circuit Operators ( &&, || )

The operators we've looked at so far, `&&` and `||`, are known as `short circuit` operators. In the case of `&&`, the expression will be true only if `both` sides of the `&&` are true. So if the JVM sees that the left side of a `&&` expression is false, it stops right there! Doesn't eben bother to look at the right side.

Similarly, with `||`, the expression will be true if `either` side is true, so if the JVM sees that the left side is true, it declares the entire statement to be true and doesn't bother to check the right side.

This is great! Let's say that you have a reference variable and you're not sure whether it's been assigned to an object. If you try to call a method using this null reference variable (no object has been assigned), you'll get a NullPointerException. So ,try this:

````java

if (refVar != null && refVar.isValidType()) {
    // do 'got a valid type' stuff
}

````

#### Not Short Circuit Operators ( &, | )

When used in boolean expressions, the `&` and `|` operators act like their `&&` and `||` counterparts, except that they force the JVM to `always` check both sides of the expression. Typically, `&` and `|` are used in another context, for manipulating bits.

#### GameHelper

This is the helper class for the game. Besides the user input method (that prompts the user and reads input from the command-line), the helper's Big Service is to create the cell locations fro the DotComs. If we were you, we would just type this code in and compile it. Remember, you won't be able to compile the DotComBust game class until you have this class.

#### Note:

For extra credit, you might try 'un-commenting' the System.out.print(ln)'s in the placeDotCom() method, just to watch it work! These print statements will let you "cheat" by giving you the location of the DotComs, but it will help you test it.

````java

import java.io.*;
import java.util.*;

public class GameHelper {
    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int [] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");

        try {
            BufferReader is = new BufferReader {
                new InputStreamReader(System.in);
                inputLine = is.readLine();

                if (inputLine.length() == 0) return null;
            } catch (IOException e) {
                System.out.println("IOException: " + e);
            }
            return inputLine.toLowerCase();
        }

        public ArrayList<String> placeDotCom(int comSize) {
            ArrayList<String> alphaCells = new ArrayList<String>();
            String [] alphacoords = new String [comSize];
            String temp = null;
            int [] coords = new int[comSize];
            int attempts = 0;
            boolean success = false;
            int location = 0;

            comCount++;
            int incr = 1;
            if ((comCount % 2) == 1) {
                incr = gridLength;
            }

            while (!success & attempts++ < 200) {
                location = (int) (Math.random() * gridSize);
                // System.out.print( "try " + location);

                int x = 0;
                    success = true;

                    while (success && x < comSize) {
                        if (grid[location] == 0) {
                            coords[x++] = location;
                            location += incr;

                            if (location >= gridSize) {
                                success = false;
                            }

                            if (x > 0 && (location % gridLength == 0)) {
                                success = false;
                            }
                        } else {
                            // System.out.print(" used " + location);
                            success = false;
                        }
                    }
            }

            int x = 0;
            int row = 0;
            int column = 0;
            // System.out.println("/n");

            while (x < comSize) {
                grid[coords[x]] = 1;
                row = (int) (coords[x] / gridLength);
                column = coords[x] % gridLength;
                temp = String.valueOf(alphabet.charAt(column));

                alphaCells.add(temp.concat(Integer.toString(row)));
                x++;
                // System.out.print(" coord "+x+" =" +alphaCells.get(x-1));
            }

            // System.out.println("/n");
            return alphaCells;
        }
    }
}

````

### Using the Library (the Java API)

You made it through the DotComBust game, thanks to the help of `ArrayList`. And now, it is time to learn how to fool around in the Java library.

In the Java API, classes are grouped into packages. To use a class in the API, you have to know which `package` the class is in. Every class in the Java library belongs to a package. The package has a name, like `javax.swing` (a package that holds some of the Swing GUI classes). `ArrayList` is in the package called `java.util`, which holds a pile of `utility` classes. Usin ga class from the API, in your own code, is simple. You just treat the class as though you wrote it yourself... as though you compiled it, there it sits, waiting to be used. With one big difference: somewhere in your code you have to indicate the `full` name of the library class you want to use, and that means package name + class name. Even if you didn't know it, you've already been using classes from a package. System (System.out.println), String, and Math(Math.random()), al belong to the `java.lang` package.

### You have to know the full name* of the class you want to use in your code.

`ArrayList` is not the full name of `ArrayList`. The full name of `ArrayList` is actually:

````java

java.util.ArrayList

````

Package name: java.util
Class name: ArrayList

You have to tell Java which ArrayList you want to use. You have two options: Import or Type. Import: Put an import stament at the top of your source code file:

````java

import java.util.ArrayList;

public class MyClass{...}

````

Type: Type the full name everywhere in your code. Each time you use it. Anywhere you use it. When you declare and/or instantiate it:

````java

java.util.ArrayList<Dog> list = new java.util.ArrayList<Dog>();

````

When you use it as an argument type:

````java

public void go(java.util.ArrayList<Dog> list) { }

````

When you use it as a return type:

````java

public java.util.ArrayList<Dog> foo() {...}

````

*Unless the class is in the `java.lang` package.


### Use the HTML API docs

Java comes with a set of online docs called, the Java API. They're part of a larger set called the Java 5 Standard Edition Documentation, and you have to download the docs separately; they don't come shrink-wrapped with the Java 5 download. The API docs are the best reference for getting more details about a class and its methods. The book tells you a little, enough to know that this is indeed what you want to use, but you still need more about the methods. The reference book, for example, tells you what the methods take, as arguments, and what they return. Look at ArrayList, for example. In the reference book, you'll find the method `indexOf()`, that we used in the DotCom class. But iif all ypu knew is that there is a method called `indexOf()` that takes an object and returns the index (an int) of that object, you still need to know one crucial thing: what happens if the objects is not in the ArrayList? Looking at the method signature alone won't tell you how that works. But the API docs will. The API docs tell you that the `indexOf()` method returns a -1 if the object parameter is not in the ArrayList. That's how we knew we could use it both as a way to check if an object is even in the ArrayList, and to get its index at the same time, if the object was there. But without the API docs, we might have thought that the `indexOf()` method would blow up if the object wasn't in the ArrayList.

# Overview

`ArrayList` is a class in the Java API. To put something into an `ArrayList`, use `add()`. To remove something from an `ArrayList` use `remove()`. To find out where something is (and if it is) in an `ArrayList`, use `indexOf()`. To find out if an `ArrayList` is empty, use `isEmpty()`. To get the size (number of elements) in an `ArrayList`, use the `size()` method. to get the length (number of elements) in a regular old array, remember, you use the length variable. An `ArrayList` resizes dynamically to whatever size is needed. It grows when objects are added, and it shrinks when objects are removed. You declare the type of the array using a type parameter, which is a type name in angle brackets. Example ArrayList<Button> means the `ArrayList` will be able to hold only objects of type Button (or subclasses of Button). Although an `ArrayList` holds objects and not primitives, the compiler will automatically "wrap" (and "unwrap" when you take it out) a primitive into an `Object`, and place that object in the `ArrayList` instead of the primitive. Classes are grouped into packages. A class has a full name, which is a combination of the package name and the class name. `Class ArrayList` is really `java.util.ArrayList. To use a class in a package other than java.lang, you must tell Java the full name of the class. You use either an important statement at the top of your source code, or you can type the full name every place you use the class in your code.