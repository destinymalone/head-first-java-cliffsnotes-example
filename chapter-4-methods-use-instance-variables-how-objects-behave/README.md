# Methods use Instance Variables

## How Objects Behave

## Table of Contents

- Methods use object state (bark different)
- Method arguments and return types
- Pass-by-value (the variable is always copied)
- Getters and Setters
- Encapsulation (do it or risk humiliation)
- Using references in an array
- Exercises and puzzles
- Overview


#### Each lesson within chapter 4 is completed and explained in order

## Remember: a class describes what an object knows and what an object does

As I told you before, a class is the blueprint for an object. When writing a class, you're describing how the JVM should make an object of that type. You already know that every object of that type can have different instance variable values.

### What about methods?

Every instance of a particular class has some methods, but the methods can behave differently based on the value of the instance variable.

### The size affects the bark

A small Dog's bark is different from a big Dog's bark. The Dog class has an instance variable size, that the bark() method uses to decide what kind of bark sound to make.

````java

class Dog {
    int size;
    String name;

    void bark() {
        if (size > 60) {
            System.out.println("Woof! Woof!");
        } else if (size > 14) {
            System.out.println("Ruff! Ruff!");
        } else {
            System.out.println("Yip! Yip!");
        }
    }
}

class DogTestDrive {
    public static void main(String[] args) {
        Dog one = new Dog();
        one.size = 70;

        Dog two = new Dog();
        two.size = 8;

        Dog three = new Dog();
        three.size = 35;

        one.bark();
        two.bark();
        three.bark();
    }
}

````

Output:

Woof! Woof!

Yip! Yip!

Ruff! Ruff!

## Can you send things to a method?

Yes you can. Just as you expect from any programming language, you can pass values into your methods. You might, for example, want to tell a Dog object how many times to bark by calling:

````java

d.bark(3);

````

A method uses parameters and a caller passes arguments. Arguments are the things you pass into the methods. An argument lands face-down into a parameter, while a parameter is nothing more than a local variable. A variable with a type and a name, can be used inside the body of the method, but here's the important part: if a method takes a parameter, you must pass it something. That something must be a value of the appropriate type.

````java

Dog d = new Dog();

d.bark(3);

void bark(int numOfBarks) {
    while (numOfBarks > 0) {
        Sysem.out.println("ruff");

        numOfBarks = numOfBarks - 1;
    }
}

````

Call the bark method on the Dog reference, and pass in the value 3 (as the argument to the method). The bits representing the int value 3 are delivered into the bark method. The bits, now land in the numOfBarks parameter (an int-sized variable). The numOfBarks parameter is used as a variable in the method code.

## Can You get things back from a method?

Yes you can! Methods can return values. Every method is declared with a return type, but now we've made all of our methods with a void return type, which means they don't give anything back.

````java

void go() {

}

````

But we can declare a method to give a specific type of value back to the caller such as:

````java

int giveSecret() {
    return 42;
}

````

If you declare a method to return a value, you must return a vale of the declared type! (Or a value that is compatible with the declared type.). The compiler won't let you return the wrong type of thing.

### Can you send more than one thing to a method?

Yes you can! Methods can have multiple parameters, which you separate with commas when declaring them. You also need to separate the arguments with commas when you pass them but most importantly, if a method has parameters, you must pass arguments of the right type and order.


### Calling a two-parameter method, and sending it two arguments.

````java

void go() {
    TestStuff t = new TestStuff();

    t.takeTwo(12, 34);
}

void takeTwo(int x, int y) {
    int z = x + y;
    System.out.println("Total is " + z);
}

````

The arguments you pass land in the same order you passed them. The first argument lands in the first parameter, the second argument in the second parameter, and so on. You can pass variables into a method, as long as the variable type matches the parameter type.

````java

void go() {
    int foo = 7;
    int bar = 3;

    t.takeTwo(foo, bar);
}

void takeTwo(int x, int y) {
    int z = x + y;
    System.out.println("Total is " + z);
}

````

The values of "foo" and "bar" land in the 'x' and 'y' parameters. So now the bits in x are identical to the bits in foo (the bit pattern for the integer '7') and the bits in y are identical to the bits in bar. Now you may ask, "What's the value of z?". It's the same result you'd get if you added foo + bar at the time you passed them into the "takeTwo" method.


------------------------------------------------------------------------------------------------------------

Java is pass-by-value and that means pass-by-copy.

````java

int x = 7;

````

You declare an int variable and assign it the value '7'. The bit pattern for 7 goes into the variable named 'x'.

````java

void go(int z) {   }

````

You can declare a method with an int parameter named z.

````java

foo.go(x);

````

Call the go() method, passing the variable 'x' as the argument. The bits in x are copied, and the copy lands in z.

````java

void go(int z) {
    z = 0;
}

````

Now, we change the value of z inside the method. The value of x doesn't change! The argument passed to the z parameter was only a copy of x. The method can't change the bits that were in the calling variable x (because it was passed the copy and not original).

### Note:

Remember: Java cares about type! You can't return a Giraffe when the return type is declared as a Rabbit. Same thing with parameters. You can't pass a Giraffe into a method that takes a Rabbit.

## What cool things can you do with parameters and return types?

Getters and Setters can be referred to as, Accessors and Mutators. They are preferred to be called, Getters and Setters, because it fits the Java naming convention. Getters and Setters let you get and set instance variables, usually. A Getter's sole purpose in life is to send back, as a return value, the value of whatever it is that particular Getter is supposed to be Getting. Setters live and breath for the chance to take and argument value and use it to set the value of an instance variable.

````java

class ElectricGuitar {
    String brand;
    int numOfPickups;
    boolean rockStarUseIt;

    String getBrand() {
        return brand;
    }

    void setBrand(String aBrand) {
        brand = aBrand;
    }

    int getNumOfPickups() {
        return numOfPickups;
    }

    boolean getRockStarUsesIt() {
        return rockStarUsesIt;
    }

    void setRockStarUsesIt(boolean yesOrNo) {
        rockStarUsesIt = yesOrNo;
    }
}

````

### Note:

Using the naming conventions means you'll be following an important Java standard!

## What does encapsulation have to do with Java?

Our data is exposed. Exposed means reachable with the dot operator, as in:

````java

theCat.height = 27;

````

Think about the idea of using our remote control to make a direct change to the Cat object's size instance variable. In the hands of the wrong person, a reference variable (remote control) is quite a dangerous weapon. We can prevent changes being made like:

````java

theCat.height = 0;

````

This would be a Bad Thing. We need to build setter methods for all the instance variables, and find a way to force other code to call the setters rather than access the data directly. By forcing to cal a setter method, we can protect the cat from unacceptable size changes.

### Hide the data

Yes it is that simple to go from an implementation that's just begging for bad data to one that protects your data and protects your right to modify your implementation later. How exactly do you hide the data? With the public and private access modifiers. You're familiar with public-we use it with every main method. Here's an encapsulation starter rule of thumb (all standard disclaimers about rules of thumb are in effect): mark your instance variables private and provide public getters and setters for access control.

### Encapsulating in GoodDog class

````java

class GoodDog {
    private int size;

    public in getSize() {
        return size;
    }

    public void setSize(int s) {
        size = s;
    }

    void bark() {
        if (size > 60) {
            System.out.println("Woof! Woof!");
        } else if (size > 14) {
            System.out.println("Ruff! Ruff!");
        } else {
            System.out.println("Yip! Yip!");
        }
    }
}

class GoodDogTestDrive {
    public static void main(String[] args) {
        GoodDog one = new GoodDog();
        one.setSize(70);

        GoodDog two = new GoodDog();
        two.setSize(8);

        System.out.println("Dog one: " + one.getSize());
        System.out.println("Dog two: " + two.getSize());

        one.bark();
        two.bark();
    }
}

````

## How do objects in an array behave?

Just like any other object. The only difference is how you get to the objects. In other words, how you get to the remote control.

````java

Dog[] pets;

pets = new Dog[7];

````

Declare and create a Dog array, to hold 7 Dog references.

````java

pets[0] = new Dog();
pets[1] = new Dog();

````

Create two new Dog objects, and assign them to the first two array elements.

````java

pets[0].setSize(30);

int x = pets[0].getSize();

pets[1].setSize(8);

````

Call methods on the two Dog objects.

### Declaring and initializing instance variables

You already know that a variable declaration needs at least a name and a type. And you know that you can initialize (assign a value) to the variable at the same time. But when you don't initialize an instance variable, what happens when you call a getter method? In other words, what is the value of an instance variable before you initialize it?

You don't have to initialize instance variables, because they always have a default value. Number primitives (including char) get 0, booleans get false, and objects reference variables get null. 

### Note:

Remember, null just means a remote control that isn't controlling/programming to anything. A reference, but no actual object.

### Note:

Instance variables are declared inside a class but not within a method.

````java

class Horse {
    private double height = 15.2;
    private String breed;
}

````

Local variables are declared within a method and must be initialized before use.

````java

class Foo {
    public void go() {
        int x;
        int z = x + 3;
    }
}

````

This will not compile! You can declare 'x' without a value, but as soon as you try to use it, the compiler will freak out because it is not equal to anything.


## Overview

Classes define what an object knows and what an object does. Things an objects knows are its instance variables(state). Things an object does are its methods(behaviors). Methods can use instance variables so that objects of the same method type can behave differently. A method can have parameters, which means you can pass one or more values in to the method. The number and type of values you pass must match in the order and type of the parameters declared by the method. Values passed in and out of methods can be implicitly promoted to a larger type or explicitly can to a smaller type. The value you pass as an argument to a method can be a literal value (2, 'c', etc.) or a variable of the declared parameter type (for example, x where x is an int variable). A method must declare a return type. A void return type means the method doesn't return anything. If a method declares a non-void return type, it must return a value compatible with the declared return type.
