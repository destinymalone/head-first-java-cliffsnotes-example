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

#### Each lesson within chapter 6 is completed and explained in order

### Analyzing the bug in the Simple Dot Com Game

In the last chapter, you had a bug with the cliff-hanger. It was supposed to look like when you enter the numbers: 1, 2, 3, 4, 5, 6 -- 

enter a number - 1
    miss
enter a number - 2
    miss
enter a number - 3
    miss
enter a number - 4
    hit
enter a number - 5
    hit
enter a number - 6
    kill
You took 6 guesses.


When you enter: 2, 2, 2; it is supposed to look like this:

enter a number - 2
    hit
enter a number - 2
    hit
enter a number - 2
    kill
You took 3 guesses.

In the current version, once you get a hit, you can simply repeat that hit two more times to get a kill! So what happened?

````java

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

````

Where it went wrong:

````java 

if (guess == cell) {
    result = "hit";
    numOfHits++;
}

````

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

1) A plain old array has to know its size at the time it's created. But for ArrayList, you just make an objects of type ArrayList. Every time. It never needs to know how big it should be, because it grows and shrinks as objects are added or removed.

````java

new String[2];

new ArrayList<String>();

````

The first array needs a size. No size is required, for the ArrayList (you can give it a size if you want).

------------------------------------------------------------------------------------------------------------

2) To put an object in a regular array, you must assign it to a specific location.

````java

myList[1] = b;

// An index from 0 to one less than the length of the array.

````

The list needs and index. If that index is outside the boundaries of the array (like, the array was declared with a size of 2, and now you're trying to assign something to index 3), it blows up at runtime. With ArrayList, you can specify an index using the `add(anInt, anObject)` and the ArrayList will keep growing to make room for the new thing.

````java

myList.add(b);

// No index.

````

------------------------------------------------------------------------------------------------------------

3) Arrays use array syntax that's not used anywhere else in Java. But ArrayLists are plain old Java objects, so they have no specific syntax.

````java

myList[1];

// The array brackets `[]` are special syntax used for arrays.

````

------------------------------------------------------------------------------------------------------------

4) ArrayLists in Java 5.0 are parameterized. Unlike arrays, ArrayLists have no special syntax. But they do use something special that was added to Java 5.0 Tiger-parameterized types.

````java

ArrayList<String>;

// The <String> in angle brackets is a "type parameter". ArrayList<String> means simply "a list of Strings", as opposed to ArrayList<Dog> which means, "a list of Dogs".

````

Prior to Java 5.0, there was no way to declare the `type` of things that would go in the ArrayList, so to the compiler, all ArrayLists were simply heterogenous collections of objects. But now, using the <typeGoesHere> syntax, we can declare and create an ArrayList that knows (and restricts) the types of objects it can hold. All you need to know now is, the syntax is a way to force the compiler to allow ony a specific type of object (the type in angle brackets) in the ArrayList.


### Let's fix the DotCom code.

Remember, this is how the buggy version looks:

````java

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

````

#### Note:

The class was renames DotCom now (instead of SimpleDotCom), for the new advanced version, but this is the same code you saw in the last chapter.

#### New and improved DotCom class

````java

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

````


### Let's build the REAL game: "Sink a Dot Com"

Let's get out of simple and turn up the heat. Instead of a single row, we'll use a grid. And instead of one DotCom, we'll use three.

Goal: Sink all of the computer's Dot Coms in the fewest number of guesses. You're given a rating level based on how well you perform.

Setup: When the ame program is launched, the computer places three Dot Coms, randomly, on the virtual 7 x 7 grid. When that's complete, the game asks for your first guess.

How to play: On the command line, the computer will prompt you to enter a guess (a cell), which you'll type (as "A3", "C5", etc.). In response to your guess, you'll see a result at the command-line, either "hit", "miss", or "You sunk Pets.com" (or whatever the lucky Dot Com of the day is). When you've sent all three Dot Coms to that big 404 in the sky, the game ends by printing out your rating.

#### What need to change?

We have three classes that need to change: the DotCom class (which is now called DotCom instead of SimpleDotCom), the game class (DotComBust) as the game helper class.

A) DotCom class: Add a name variable to hold the name of the DotCom ("Pets.com" "Go2.com", etc.) so each DotCom can print its name when it's killed.

B) DotComBust class (the game): Create three DotComs instead of one. Give each of the three DotComs a `name`. Call a setter method on each DotCom instance, so that the DotCom can assign the name to its `name` instance variable. Put the DotComs on a grid rather than just a single row, and do it for all three DotComs. This step is now way more complex than before, if we're going to place the DotComs randomly. We put the algorithm for giving the DotComs a location into the GameHelper class. Check each user guess with all three DotComs, instead of just one. Keep playing the game until there are no more live DotComs. Get out of main.