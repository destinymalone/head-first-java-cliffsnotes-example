# Life and Death of an Object

## constructors and garbage collection

## Table of Contents

- The stack and the heap, where objects and variables live
- Methods on the stack
- Where local variables live
- Where instance variables live
- The miracle of object creation
- Constructors (the code that runs when you say new)
- Initalizing the state of a new Duck
- Overloaded constructors
- Superclass constructors (constructor chaining)
- Invoking overloading constructors using this()
- Life of an object
- Garbage Collection (and making objects eligible)
- Exercises and puzzles
- Overview

#### Each lesson within chapter 9 is completed and explained in order

## The Stack and the Heap: where things live

In Java, we (programmers) care about two areas of memory--the one where objects live (the heap), and the one where method invocations and local variables live (the stack). When a JVM starts up, it gets a chunk of memory from the underlying OS, and uses it to run you Java program. How `much` memory, and whether or not you can tweak it, is dependent on which version of the JVM (and on which platform) you're running. But usually you `won't` have anything to say about it. And with good programming, you probably won't care. We know that all `objects` live on the garbage-collectible heap, but we haven't yet looked at where `variables` live. And where a variable lives depends on what `kind` of variable it is. And by "kind", we don't mean `type` (primitive or object reference). The two `kinds` of variables whose lives we care about now are `instance` variables and `local` variables. Local variables are also known as `stack` variables, which is big clue for where they live.

- Instance Variables: Instance variables are declared inside a `class` but `not` inside a method. They represent the "fields" that each individual object has (which can be filled with different values for each instance of the class). Instance variables live inside the object they belong to.

```java

public class Duck {
    int size;
    // Every Duck has a "size" instance variable.
}

```

- Local Variables: Local variables are declared inside a method, including method parameters. They're temporary, and live only as long as the method is on the stack (in other words, as long as the method has not reached the closing curly brace).

```java

public void foo(int x) {
    int i = x + 3;

    boolean b = true;
    // The parameter x and the variables i and b are all local variables.
}

```

## Methods are stacked

When you call a method, the method lands on the top of a call stack. That new thing that's actually pushed onto the stack is the stack `frame`, and it holds the state of the method including which line of code is executing, and the values of al local variables. The method at the `top` of the stack is always the currently-running method for that stack. A method stays on the stack until the method hits its closing curly brace (which means the method's done). If method `foo()` calls method `bar()`, method `bar()` is stacking on top of method `foo()`.

### A stack scenario

```java

public void doStuff() {
    boolean b = true;
    go(4);
}

public void go(int x) {
    int z = z + 24;
    crazy();
    // imagine more code here
}

public void crazy() {
    char c = 'a';
}

```
