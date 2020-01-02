# A Trip to Objectville

## classes and objects

## Table of Contents

???

#### Each lesson within chapter 2 is completed and explained in order

## Chair Wars-(How Objects Can Change Your Life)

Procedures:

-rotate
-playSound

Things (key players):

-The Shapes
-User
-the Sound
-Clicking Events

## "Who got the Aeron?"

Larry does not use methods when adding code, which affects all of the code and each test that was made previously.

Brad uses methods and classes that separate each working code and test, which is not affected when code is suddenly changed or added.

So, neither of them recieved the Aeron because there was a third programmer given the task who was able to finish before Larry and Brad.

## OO--Object Oriented Development in Java

- Works with superclasses, subclasses, instance variables, methods, and attributes.

- Things an object knows about itself are called instance variables
    They represent an objects state (the data), and can have unique values for ech object of that type.

- Things an object can do are called methods
    When designing a class, think about the data an object will need to know about itself, and also designing the methods that operate on that data. Objects commoly have methods that read to write the value of the instance variables.

    Objects have instance variables and methods, but those instance variables and methods are designed as part of the class.

## Difference between a class and an object

A class is not an object, but it's used to construct objects.

A class is a blueprint for an object. It tells the program how to make an object of that certain type. 

Each object made from that class can have its own values of that class.

Ex. Button class which makes dozens of different buttons. Each button has it's own color, size, type, etc.

## Making Your First Object

```java

class Dog {
    int size;
    String breed;
    String name;

    void bark() {
        System,out,println("Ruff! Ruff!");
    }
}

class DogTestDrive {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.size = 40;
        d.bark();
    }
}

```

### Note:

Primitives and objects used to define each variable and give off values needed to test each class.

Void is used to show that there is no return value

```java

class Movie {
    String title;
    String genre;
    int rating;

    void playIt() {
        System.out.println("Playing the movie");
    }
}

public class MovieTestDrive {
    public static void main(String[] args) {
        Movie one = new Movie();
        one.title = "Gone with the Stock";
        one.genre = "Tragic";
        one.rating = -2;

        Movie two = new Movie();
        two.title = "Lost in Cubicle Space";
        two.genre = "Comedy";
        two.rating = 5;
        two.playIt();

        Movie three = new Movie();
        three.title = "Byte Club";
        three.genre = "Tragic but ultimately uplifting";
        three.rating = 127;

    }
}

```

## Getting out of main

A real Java application is nothing but objects talking to other objects.

Calls methods on one another.

# Guessing Game

Player = objects

Game is super class

```java

public class GuessGame {
    Player p1;
    Player p2;
    Player p3;

    public void startGame() {
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();

        int guessp1 = 0;
        int guessp2 = 0;
        int guessp3 = 0;

        boolean p1isRight = false;
        boolean p2isRight = false;
        boolean p3isRight = false;

        int targetNumber = (int) (Math.random() * 10);
        System.out.println("I'm thinking of a number between 0 and 9...");

        while(true) {
            System.out.println("Number to gues is" + targetNumber);

            p1.guess();
            p2.guess();
            p3.guess();

            guessp1 = p1.number;
            System.out.println("Player one guessed" + guessp1);

            guessp2 = p2.number;
            System.out.println("Player two guessed" + guessp2);

            guessp3 = p3.number;
            System.out.println("Player three guessed" + guessp3);

            if (guessp1 == targetNumber) {
                p1isRight = true;
            }
            if (guessp2 == targetNumber) {
                p2isRight = true;
            }
            if (guessp3 == targetNumber) {
                p3isRight = true;
            }

            if (p1isRight || p2isRight || p3isRight) {
                System.out.println("We have a winner!");
                System.out.println("Player one got it right? " + p1isRight);
                System.out.println("Player two got it right? " + p2isRight);
                System.out.println("Player three got it right? " + p3isRight);
                System.out.println("Game is over.");
                break;

            } else {
                System.out.println("Players will have to try again.");
            }
        }
    }
}

```

GuessGame has three instance variables for the three Player objects

Create three Player objects and assign them to the three Player instance variables

Declare three variables to hold the three guesses the Players make

Declare three variables to hold a true or false based on the player's answer

Make a "target" number that the players have to guess

Call each player's guess() method

Get each Player's guess (the result of their guess() methof running) by accessing the number variable of each player

Check each player's guess to see if it matches the target number. If a player is right, then set that player's variable to be true since it was set to false by default.

If player 1 or player 2 or player 3 is right, the game is over and while loop breaks.

Else, stay in the loop and ask the players for another guess.


# Running The Guessing Game

````java

public class Player {
    int number = 0;

    public void guess() {
        number = (int) (Math.random() * 10);
        System.out.println("I'm guessing " + number);
    }
}

public class GameLauncher {
    public static void main(String[] args) {
        GuessGame game = new GuessGame();
        game.startGame();
    }
}

````

# Who am I?

I am compiled from a .java file. -- class

My instance variable alues can be different from my buddy's values. -- object

I behave like a template. -- class

I like to do stuff. -- object method

I can have many methods. -- class & objects

I represent "state". -- instance variable

I have behaviors. -- objects & class

I am located in objects. -- methods & instance variable

I live on the heap. -- object

I am used to create object instances. -- class

My "state" can change. -- object & instance variable

I declare methods. -- class

I can change at runtime. -- object & instance variable

### Note:

Both classes and objects are said to have "state" and "behavior".

They are defined in the class, but the object is also said to 'have' them.


## Overview

Java has an overall source file that holds each class.

Inside of that source file, there are classes that use methods to give instructions for how the finishing product should be produced.

Methods work as a function or procedure that gets every detail defined for the main function to carry.

Variables are used as object "state" and as local variables. -- Instance variables and variables declared within a method

Variables are declared as Integers, Strings, and arrays

