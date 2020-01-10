# A Trip to Objectville

## classes and objects

## Table of Contents

- Chair Wars (Brad the OO guy vs. Larry the procedural guy)
- Inheritance (an introduction)
- Overriding methods (an introduction)
- What's in a class? (methods,instance variables)
- Making your first object
- Using main()
- Guessing Game Code
- Exercises and puzzles
- Overview

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

### "Who got the Aeron?"

Larry does not use methods when adding code, which affects all of the code and each test that was made previously. Brad uses methods and classes that separate each working code and test, which is not affected when code is suddenly changed or added. So, neither of them received the Aeron because there was a third programmer given the task who was able to finish before Larry and Brad.

### What is OO?--Object Oriented Development in Java

- Works with superclasses, subclasses, instance variables, methods, and attributes.

- Things an object knows about itself are called instance variables.
  They represent an object's state (the data), and can have unique values for each object of that type.

- Things that an object can do are called methods.
  When designing a class, think about the data an object will need to know about itself, and also designing the methods that operate on that data. Objects commonly have methods that are read to write the value of the instance variables.

  Objects have instance variables and methods, but those instance variables and methods are designed as part of the class.

Object Oriented works with superclasses, subclasses, instance variables, methods, and attributes.
Objects know instance variables already. The instance variables represent an object's state, which have certain values for objects provided for that type.

What an object does, is known as a method. The methods write the value/output of the given instance variables.

Objects, their methods, and instance variable are all compiled within the class of that file being ran.

## What should I know about inheritance and overriding methods?

Inheritance and overriding methods are used when you have the "same" methods used throughout many "functions"/classes. If you have repeating features (instance variables or methods), abstract out the features and put them into a new class that other subclasses can link to. This is how you inherit from the superclasses, while not repeating things in each subclass. When it comes to overriding, subclasses re-define one of the inherited methods when it needs to change or extend the behavior of that method.

## What is the difference between a class and an object?

A class is not an object, but it's used to construct objects. A class is a blueprint for an object. It tells the program how to make an object of that certain type. Each object which is made from that class, can have its own values of that class.

Ex. Button class which makes dozens of different buttons. Each button has it's own color, size, type, etc.

## Making Your First Object-How is it all put together?

```java

class Dog {
    int size;
    String breed;
    String name;

    void bark() {
        System.out.println("Ruff! Ruff!");
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

Each variable has it's value defined with a primitive, like: int, string, double, boolean, etc. The TestDrive is to test the code you have in the superclass. The main method is now running while using the first class without repeating any values.

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

## How do we get out of main?

There are two uses of main:
to test your real class
to launch/start your Java application

A real Java application is nothing but objects "talking" to other objects. Talking means objects calling methods on one another. The real classes are tested when you decide to call a main() method. Once the compiled file can successfully run with no errors, your application is now launched.

## Guessing Game

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
            System.out.println("Number to guess is" + targetNumber);

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

GuessGame has three instance variables for the three Player objects. You can now create three Player objects and assign them to the three Player instance variables. The next step is to declare three variables to hold the three guesses the Players make. Now you would declare three variables to hold a true or false, based on the player's answer. Make a "target" number that the players have to guess to tell if a player is correct or not. Afterwards, you would call each player's guess() method, get each Player's guess (the result of their guess() method running) by accessing the number variable of each player, and check each player's guess to see if it matches the target number. If a player is right, then set that player's variable to be true since it was set to false by default. If player 1 or player 2 or player 3 is right, the game is over and while loop breaks, else, stay in the loop and ask the players for another guess.

### Running The Guessing Game

```java

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

```

Summary:

The guessing game involves a 'game' object and three 'player' objects. The game generates a random number between 0 and 9, and the three player objects try to guess it.

---

The Logic:

1. The GameLauncher class is where the application starts; it has the main() method.

2. In the main() method, a GuessGame object is created, and its startGame() method is called.

3. The GuessGame object's startGame() method is where the entire game plays out. It creates three players, then "thinks" of a random number (the target for the players to guess). It then asks each player to guess, check the results, and either prints out information about the winning player(s) or ask them to guess again.

### Who am I?

- I am compiled from a .java file. -- class

- My instance variable values can be different from my buddy's values. -- object

- I behave like a template. -- class

- I like to do stuff. -- object method

- I can have many methods. -- class & objects

- I represent "state". -- instance variable

- I have behaviors. -- objects & class

- I am located in objects. -- methods & instance variable

- I live on the heap. -- object

- I am used to create object instances. -- class

- My "state" can change. -- object & instance variable

- I declare methods. -- class

- I can change at runtime. -- object & instance variable

## Note:

Both classes and objects are said to have "state" and "behavior".

They are defined in the class, but the object is also said to 'have' them.

# Overview

Java has an overall source file that holds each class. Inside of that source file, there are classes that use methods to give instructions for how the finishing product should be produced. Methods work as a function or procedure that gets every detail defined for the main "function" to carry. Variables are used as object "state" and as local variables. -- Instance variables and variables declared within a method. Again, variables are declared as Integers, Strings, arrays, etc.
