# Primitives and References

## Know Your Variables

## Table of Contents

Declaring a variable (Java cares about type)
Primitive types ("I'd like a double with extra foam, please")
Java keywords
Reference variables (remote control to an object)
Object declaration and assignment
Objects on the garbage-collectible heap
Arrays (a first look)
Exercises and puzzles


#### Each lesson within chapter 3 is completed and explained in order

## How do you declare a variable?

Java cares about type. A floating point number can not go into an integer variable, unless you acknowledge to the compiler that you know you might lose precision (like everything after the decimal point). Variables come in two flavors: primitive and object reference. Primitives hold fundamental values including integers, booleans, and floating point numbers, but object references hold references to other objects. There are two declaration rules to follow: variables must have a type and variables must have a name.

````java

int count;

````

int is a type

count is the name

### Note

When you see a statement like: "an object of type X", think of type and class as synonyms.

### "I'd like a double mocha, no, make it an int"

When you think of Java variables, think of cups. Coddee cups, tea cups, giant cups that hold lots and lots of liquids.
A variable is just a cup. A container. It holds something.

It has a size and a type. Primitives are like the cups they have at the coffeehouse. They come in different sizes, and each has a name like "small", "short", "tall", "grande"

And in Java, primitives come in different sizes, and those sizes have names. When you declare a variable in Java, you must declare it with a specific type. 

"long", "int", "short", "byte"

The four containers are four integer primitives in Java.

Each cup holds a value, so far Java primitives, you would say, "I'd like an int please, with the value of 2486, and name the variable height."

Each primitive variable has a fixed number of bits(cup size). The sizes for the six numeric primitives in Java are shown below:

byte: 8

short: 16

int: 32

long: 64

float: 32

double: 64

## What primitive types are there?

Below is a list of the different primitive types, their bit depth, and value range.

Type      Bit Depth     Value Range

boolean    true/false     true/false
char       16 bits        0 to 65535
byte       8 bits         -128 to 127
short      16 bits        -32768 to 32767
int        32 bits        -2147483648 to 2147483647
long       64 bits        -huge to huge
float      32 bits        varies
double     64 bits        varies

## What does primitive declarations with assignments look like?

Below is an example of what each primitive type lookes like in Java.

````java

int x;

x = 234;

byte b = 89;

boolean isFun = true;

double d = 3456.98;

char c = 'f';

int z = x;

boolean isPunkRock;

isPunkRock = false;

boolean powerOn;

powerOn = isFun;

long big = 3456789;

float f = 32.5f;


````

### Note

Have to have the "f" with a float, because Java thinks anything with a floating point is a double, unless you use "f".

### You really don't want to spill that...

Be sure the value can fit into the variable

You can't put a large value into a small cup, you will lose some. This is called spillage.

The compiler tries to help prevent spillage if it can tell from your code that something not going to fit in the container (variable / cup) you're using.

Ecample: you can't pour an int-full of stuff into a byte-sized container.

````java

int x = 24;

byte b = x;

````

The value of x is 24 and 24 is definitely small enough to fit into a byte. The compiler cares about  you trying to put a big thing into a small thing, and there's the possibility of spilling. The compiler does not know what the value of x is, even if you happen to be able to see it in your code.

You can assign a value to a variable in one of several ways including:

type a literal vale after the equal sign (x = 12, isGood = true, etc.)

assign the value of one variable to another (x = y)

use an expression combining the two (x = y + 43)

### In the examples below, the literal values are after the eqaul sign

int size = 32;  declare an int name size, assign it the value 32

char initial = 'j'; declare a char named initial, assign it the value 'j'

double d = 456.709; declare a double named d, assign it vale 456.709

boolean isCrazy; declare a boolean names isCrazy (no assignment)

isCrazy = true; assign the cale true to the previously-declared isCrazy

int y = x + 456; declare an int named y, assign it the value that is the sum of whatever x is now plus 456


### Back away from that keyword! What can you use as names?

You know you need a name and a type for your variables

You already know the primitive types

To answer the question, the rules are simple. You can name a class, method, or variable according to the following rules are keywords that the compiler recognizes. And if you really want to play confuse-a-compiler, then you just try using reserved words as a name.

(The real rules are slightly more flexible, but these will keep you safe):

    It must start with a letter, underscore(_), or dollar sign ($). You start a name with a number.

    After the first character, you can use numbers as well. Just don't start it with a number.

    It can be anything you like, subject to those two rules, just so long s it isn't one of Java's reserved words.

You've already seen some reserved words when we looked at writing our first main class:

````java

public

static

void

````

Don't use any of these for your own names

### Reserved words in Java, you can not use to name variables

````java

boolean

byte

char

double

float

int

long

short

public

private

protected

abstract

final

native

static

strictfp

transient

volatile

if

else

do

while

switch

case

default

for

break

continue

assert

class

extends

implements

import

instanceof

interface

new

package

super

this

catch

finally

try

throw

throws

return

void

const

goto

enum

````

### How can you control your Dog object?

You know how to declare a primitive variable and assign it to a value.

But now what about non-primitive variables? In other words, what about objects?

### Note

There is actually no such thing as an object variable

There's only an object reference variable

An object reference variable holds bits that represent a way to access an object

It doesn't hold the object itself, but it holds something like a pointer. Or an address. Except, in Java we don't really know what is inside a reference variable. We do know that whatever it is, it represents one and only one object. And the JVM (Java Virtual Machine) knows how to use the reference to get to the object.

### You can't stuff an object into a variable

We often think of it that way... we say thinks like, 

"I passed the String to System.out.println() method."

"The method returns a Dog"

"I put a new Foo object into the variable named myFoo."

That's not what happens. There aren't giant expandable cups that can grow to the size of any object.

Objects live in one place and one place only-the garbage collectible heap!




Although a primitive variable is full of buts representing the actual vale of the variable, an object reference variable is full of buts representing a way to get to the object.

You use the dot operator (.) on a reference variable to say, "use the thing before the dot to get me the thing after the dot."

Example:

````java

myDog.bark();

````
This means, "use the object referenced by the variable myDog to invoke the bark() method."

Whe you use the dot operator on an object reference variable, think of it like pressing a button on the remote control for that object.

## Is an object reference just another variable value?

We have a cup and know that something has to go in the cup, only this time, the value is a remote control.

Below is just a reminder of the primitive types and their bit depth.

byte: 8

short: 16

int: 32

long: 64

reference: remote control  (bit depth not relevant)


Primitive Variable--Java visual

````java

byte x = 7;

````

The bits representing 7 go into the variable. (00000111).




Reference Variable--Java visual

````java

Dog myDog = new Dog();

````

The bits representing a way to get to the Dog object go into the variable. The Dog object itself does not go into the variable

### Note

With primitive variables, the value of the variable is....the value (5, -26.7, 'a'). With reference variables, the value of the variable is...bits representing a way to get to a specific object. You don't know (or care) how any particular JVM implements object references. Sure, they might be a pointer to a pointer to...etc. but even if you know, you still can't use the bits for anything other than accessing an object.

## Can you explain the 3 steps of object: declaration, creation, and assignment?

````java

Dog myDog = new Dog();

````

"Dog myDog" tells the JVM to allocate space for a reference variable, and names that variable myDog. The reference variable is, forever, of type Dog. The remote control has buttons to control a Dog.


"new Dog();" tells the JVM to allocate space for a new Dog object on the heap.


"=" assigns the new Dog to the reference variable myDog. In other words, programs the remote control.

## What does it mean by objects on the heap?

### Life and death on the heap

````java

Book b = new Book();

Book c = new Book();

````

Declare two "Book" reference variables. Create two new "Book" objects. Assign the "Book" objects to the reference variables.

The two book objects are now living on the heap.

Active References: 2

Reachable Objects: 2

````java

b = c;

````

Assign the value of variable "c" to variable "b". The bits inside variable "c" are copied, and that new copy is stuffed into variable "b". Both variables hold identical values.

Both "b" and "c" refer to the same object. Object 1 is abandoned and eligible for Garbage Collection (GC).

Active References: 2

Reachable Objects: 1

Abandoned Objects: 1

The first object that "b" referenced, Object 1, has no more references, It's unreachable.

````java

c = null;

````

Assign the value "null" to variable "c". This makes "c" a "null" reference, meaning it doesn't refer to anything. But it's still a reference variable, and another "Book" object can still be assigned to it.

Object 2 still has an active reference ("b"), and as long as it does, the object is not eligible for GC.

Active References: 1

"null" References: 1

Reachable Objects: 1

Abandoned Objects: 1


## What should I know about arrays?

An array is like a tray of cups.

1.) Declare an int array variable. An array variable is a remote control to an array object.

````java

int [] nums;

````

2.) Create a new int array with a length of 7, and assign it to the previously-declared "int[]" variable "nums".

````java

nums = new int[7];

````

3.) Give each element in the array an int value. Remember, elements in an int array are just int variables.

````java

nums[0] = 6;
nums[1] = 19;
nums[2] = 44;
nums[3] = 42;
nums[4] = 10;
nums[5] = 20;
nums[6] = 1;

````

### Note

Notice that the array itself is an object, even though the 7 elements are primitives.


### Arrays are objects too

Arrays are great when you just want a quick, ordered, efficient list of things.

Arrays give you fast random access by letting you use an index position to get to any element in the array.

Every element in an array is just a variable. In other words, one of the eight primiritve variable types or a reference variable.

Anything you would put in a variable of that type can be assigned to an array element of that type. So in an array of type int (int[]), each element can hold an int.

Remember that a reference variable just hold a reference (a remote control), not the object itself. So in a "Dog" array (Dog[]), each element can hold a "remote control" to a Dog. 

Arrays are always objects, whether they're declared to hold primitives or object references.

You can have an array object that's declared to "hold" primitive values. In other words, the array object can have "elements" which are primitives, but the array itself is never a primitive.

Regardless of what the array holds, the array itself is always an object!


### Code Magnets

````java

class TestArrays {
    public static void main(String[] args) {
        int [] index = new int[4];

        index[0] = 1;
        index[1] = 3;
        index[2] = 0;
        index[3] = 2;

        String [] islands = new String[4];

        islands[0] = "Bermuda";
        islands[1] = "Fiji";
        islands[2] = "Azores";
        islands[3] = "Cozumel";

        int y = 0;

        int ref;

        while (y < 4) {
            ref = index[y];

            System.out.print("island = ");
            System.out.println(islands[ref]);

            y = y + 1;
        }
    }
}

````

Output:

island = Fiji

island = Cozumel

island = Bermuda

island = Azores

# Overview

Variables come in two flavors: primitive and reference. Variables must always be declared with a name and a type. A primitive variable value is the bits representing the value (5, 'a', true, 3.1416, etc.) but, a reference variable value is the bits representing a way to get to an object on the heap.
Think of a reference variable as a remote control. Using the dot operator (.) on a reference variable, is like pressing a button on the remote control to access a method or instanec variable. Remember, a reference variable has a value of "null" when it is not referencing any object. Arrays are always an object, even if the array is declared to hold primitives. There is no such thing as a primitive array, only an array that "hold"s primitives.