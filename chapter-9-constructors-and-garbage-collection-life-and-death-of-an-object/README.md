# Life and Death of an Object

## constructors and garbage collection

## Table of Contents

- The stack and the heap, where objects and variables live
- Methods on the stack
- Where local variables live
- Where instance variables live
- The miracle of object creation
- Constructors (the code that runs when you say new)
- Initializing the state of a new Duck
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

## What about local variables that are objects?

Remember, a non-primitive variable holds a `reference` to an object, not the object itself. You already know where objects live--on the heap. It doesn't matter where they're declared or created. If the local variable is a reference to an object, only the variable (the reference/remote control) goes on the stack. The object itself still goes in the heap.

````java

public class StackRef {
    public void foof() {
        barf();
    }
    public void barf() {
        Duck d = new Duck(24);
    }
}

````

`barf()` declares and creates a new `Duck` reference variable 'd' (since it's declared inside the method, it's a local variable and foes on the stack). No matter WHERE the object reference variable is declared (inside a method vs. as an instance variable of a class) the object always goes on the heap.

## If local variables live on the stack, where do instance variables live?

Instance variables live on the Heap, inside the object they belong to. Remember that the `values` of an object's instance variables live inside the object. If the instance variables are all primitives, Java makes space for the instance variables based on the primitive type. Java doesn't care about the value inside primitive variables; the bit size of an int variable is the same (32 bits) whether the value of the int is in the thousands or not. But what if the instance variables are `objects`? When the object has instance variables that are object references rather than primitives, the real question is: does the object need space for all the objects it holds references to? The answer is, `not exactly`. No matter what, Java has to make space for the instance variable `values`. But remember that a reference variable value is not the whole `object`, but merely a `remote control` to the object. When the object of the variable is created,the `object` gets space on the Heap which depends on the instance variable declaration. If the instance variable is declared but no object is assigned to it, then only the space for the reference variable (the remote control) is created.

````java

private Antenna ant;

````

No actual Antenna object is made on the heap unless or until the reference variable is assigned a new Antenna object.

````java

private Antenna ant = new Antenna();

````

## The miracle of object creation

Now that you know where variables and objects live, we can divide into the mysterious world of object creation. Remember the three steps of object declaration and assignmentL declare a reference variable, create an object, and assign the object to the reference. But the miracle occurs and the new object is "born", in two of the steps.

### Review the three steps of object declaration, creation and assignment:

````java

Duck myDuck = new Duck();

````

`Duck my Duck` - Make a new reference variables of a class or interface type. (Duck reference = myDuck)

`new Duck()` - A miracle occurs here. (Duck object)

`=` - Assign the new object to the reference.

Are we calling a method named `Duck()`? Because it sure `looks` like it.

````java

Duck myDuck = new Duck();
// It looks like we're calling a method named Duck() because of the parentheses.

````

No. We're calling the Duck `constructor`. A constructor `does` look and feel a lot like a method, but it's not a method. It's got the code that runs when you say `new`. in other words, `the code that runs when you instantiate an object`. The only way to invoke a constructor is when the keyword `new` followed by the class name. The JVM finds that class and invokes the constructor in that class. (Technically this isn't the `only` way to invoke a constructor. But it's the only way to do it from `outside` a constructor. You `can` call a constructor from within another constructor, with restrictions.) But where is the constructor? If we didn't write it, who did? You can write a constructor for your class, but if you don't the compiler writes one for you! Here is what the compiler's default constructor looks like:

````java

public Duck() {
// constructor code goes here
}

````

Notice something missing? How is this different from a method? Where is the return type? If this were a method, you'd need a return type between 'public' and 'Duck()'. Its name is the same as the class name. That is mandatory.

## Construct a Duck

The key feature of a constructor is that it runs `before` the object can be assigned to a reference. That means you get a chance to step in and do things to get the object ready for use. In other words, before anyone can use the remote control for an object, the object has a chance to help construct itself. In our Duck constructor, we're not doing anything useful, but it still demonstrates the sequence of events.

````java

public class Duck {
    public Duck() {
        System.out.println("Quack");
        // Constructor code.
    }
}

public class UseADuck {
    public static void main(String[] args) {
        Duck d = new Duck();
        // This calls the Duck constructor.
    }
}

````

## Initializing the state of a new Duck

Most people use constructors to initialize the state of an object. In other words, to make and assign values to the object's instance variables.

````java

public Duck() {
    size = 34;
}

````

That;s all fine when the Duck class `developer` knows how big the Duck object should be. But what if we want the programmer who is `using` Duck to decide how big a particle Duck should be? Imagine the Duck has a size instance variable, and you want the programmer using your Duck class to set the size of the new Duck. How could you do it? Well, you could add a `setSize()` setter method to the class. But that leaves the Duck temporarily without a size*, and forces the Duck user to write `two` statements--one to create the Duck, and one to call the setSize() method.. The code below uses a setter method to set the initial size of the new Duck.

````java

public class Duck {
    int size;
    // Instance variable

    public Duck() {
        System.out.println("Quack");
        // Constructor
    }

    public void setSize(int newSize) {
        size = newSize;
        // Setter method
    }
}

````

````java

public class UseADuck {
    public static void main(String[] args) {
        Duck d = new Duck();

        // There's a bad thing here, The Duck is alive at this point in the code, with without the size!* And then you're replying on the Duck-user to KNOW that Duck creation is a two-part process: one to call the constructor and one to call the setter.

        d.setSize(42);
    }
}

````

Instance variables do have a default value. 0 or 0.0 for numeric primitives, false booleans, and null for reference.

### Using the constructor to initialize important Duck state*

If an object shouldn't be used until on eor more parts of its state (instance variables) have been initialized, don't let anyone get ahold of a Duck object until you're finished initializing! It's usually way too risky to let someone make--and get a reference to--a new Duck object that isn't quite ready for use until that someone turns around and calls the `setSize()` method. The best place to put initialization code is in the constructor. And all you need to do is make a constructor with arguments.

````java

public class Duck {
    int size;

    public Duck(int duckSize) {
        System.out.println("Quack");

        // Add an int parameter to the Duck constructor.

        size = duckSize;

        // Use the argument value to set the size instance variable.

        System.out.println("size is " + size);
    }
}

````

````java

public class UseADuck {
    public static void main(String[] args) {
        Duck d = new Duck(42);
        // This time there's only one statement. We make the new Duck and set its size in one statement.
        // Pass a value to the constructor.
    }
}

````

### Make it easy to make a Duck

Be sure you have a no-arg constructor. What happens if the Duck constructor takes an argument? Imagine that you want Duck users to have TWO options for making a Duck--one where they supply the Duck size (as the constructor argument) and one where they don't specify a argument(size) and thus get your `default` Duck size. You can't do this cleanly with just a single constructor. Remember, if a method (or constructor--same rules) has a parameter, you `must` pass an appropriate argument when you invoke that method or constructor. You can't just say, "If something doesn't pass anything to the constructor, then use the default size", because they won't even be able to compile without sending an int argument to the constructor call. You `could` do something clunkly like this:

````java

public class Duck {
    int size;

    public Duck(int newSize) {
        if (newSize == 0) {
            size = 27;
        } else {
            size = newSize;
        }
    }
}

// If the parameter value is zero, give the new Duck a default size, otherwise use the parameter value for the size. NOT a very good solution.

````

But that means the programmer making a new Duck object has to `know` that passing a "0" is the protocol for getting the default Duck size. What is the other programmer doesn't know that? Or what if he really `does` want a zero-size Duck? (Assuming a zero-sized Duck is allowed. If you don't want zero-sized Duck objects, put validation code in the constructor to prevent it.) The point is, it might not always be possible to distinguish between a genuine "I want zero for the size" constructor argument and a "I'm sending zero so you'll give me the default size, whatever it is" constructor argument.

## Doesn't the compiler always make a no-arg constructor for you?

No! You might think that if you write `only` a constructor with arguments, the compiler will see that you don't have a no-arg constructor, and stick one in for you. But that's not how it works. The compiler gets involved with constructor-making only if you don't say anything at all about constructors. If you write a constructor that takes arguments, and you still want a non-arg constructor, you'll have to build the no-arg constructor yourself! As soon as you provide a constructor, ANY kind of constructor, the compiler backs off and leaves you in charge of constructors now. If you have more than one constructor in a class, the constructors MUST have different argument lists. The argument list includes the order and types of arguments. As long as they're different, you can have more than one constructor: You can do this with methods as well. Overloaded constructors means you have more than one constructor in your class. To compile, each constructor must have a different argument list! The class below is legal because all five constructors have different argument lists If you had two constructors that took only an int, for example, the class wouldn't compile. What you name the parameter variable doesn't count. It's the variable `type` (int, Dog, etc.) and `order` that matters. You `can` have two constructors that have identical types, as long as the order is different. A constructor that takes a String followed by an int, is `not` the same as one that takes an int followed by a String.

````java

public class Mushroom {
    public Mushroom(int size) { }

    // When you know the size, but you don't know if it's magic.

    public Mushroom() { }

    //  When you don't know anything.

    public Mushroom(boolean isMagic) { }

    // When you know if it's magic or not, but don't know the size.

    public Mushroom(boolean isMagic, int size) { }
    public Mushroom(int size, boolean isMagic) { }

    // These two have the same args, but in different order, so it's OK.
    // When you know whether or not it's magic, AND you know the size as well.
}

````

## Wait a minute.... we never DID talk about superclasses and inheritance and how that all fits in with constructors.

Remember from the last chapter, the part where we looked at the Snowboard object wrapping around an inner core representing the Object portion of the Snowboard class? The Big Point there was that every object holds not just its `own` declared instance variables, but also `everything from its superclasses` (which, at a minimum, means class Object, since `every` class extends Object). So when an object is created (because someone said `new`; there is `no other way` to create an object other than someone, somewhere saying `new` on the class type), the object gets space for `all` the instance variables, from all the way up the inheritance tree. Think about it for a moment... a superclass might have setter methods encapsulating a private variable. But that variable has to live `somewhere`. When an object is created, it's almost as though `multiple` objects materialize--the object being new'd and one object per each superclass. Conceptually, though, it's much better to think of it as, the object being created, has layers of itself representing each superclass. 

### The role of superclass constructors in an object's life.

All the constructors in an object's inheritance tree must run when you make a new object. That means every superclass has a constructor (because every class has a constructor), and each constructor up the hierarchy runs at the time an object of a subclass is created. Saying `new` is a Big Deal. It starts the whole constructor chain reaction. And yes, even abstract classes have constructors. Although you can never say new on an abstract class, an abstract class is still a superclass, so its constructor runs when someone makes an instance of a concrete subclass. The super constructors run to build out the superclass parts of the object. Remember; a subclass might inherit methods that depend on superclass state (in other words, the value of instance variables in the superclass). For an object to be fully-formed, all the superclass parts itself must be fully-formed, and that's why the super constructor `must` run. All instance variables from every class in the inheritance tree have to be declared and initialized. Even if Animal has instance variables that Hippo doesn't inherit (if the variables are private, for example), the Hippo still depends on the Animal methods that `use` those variables. When a constructor runs, it immediately calls its superclass constructor, all the way up the chain until you get to the class Object constructor.

### Note:

A single Hippo object is on the heap. A new Hippo object also IS-A Animal and IS-A Object. If you want to make a Hippo, you must also make the Animal and Object parts of the Hippo. This all happens in a process called `Constructor Chaining`.

````java

public class Animal {
    public Animal() {
        System.out.println("Making an Animal");
    }
}

````

````java

public class Hippo extends Animal {
    public Hippo() {
        System.out.println("Making a Hippo");
    }
}

````

````java

public class TestHippo {
    public static void main(String[] args) {
        System.out.println("Starting....");
        Hippo h = new Hippo();
    }
}

````

### How do you invoke a superclass constructor?

You might think that somewhere in, say, a Duck constructor, if Duck extends Animal you'd call Animal(). But that's not how it works:

````java

public class Duck extends Animal {
    int size;

    public Duck (int newSize) {
        Animal();
        // No! That's not legal!
        size = newSize;
    }
}

````

The only way to call a super constructor is by calling `super()`. `super()` calls the `super constructor`:

````java

public class Duck extends Animal {
    int size;

    public Duck(int newSize) {
        super();
        size = newSize;
    }
}

````

A call to `super()` in your constructor puts the superclass constructor on the top of the Stack. And what do you think that superclass constructor does? Calls its superclass constructor. And so it goes until the Object constructor is on the top of the Stack. Once `Object()` finishes, it's popped off the Stack and the next thing down the Stack (the subclass constructor that called `Object()`) is now on top. `That` constructor is on the top of the Stack, where `it` can now finish.

### Can the child exist before the parents?

The superclass parts of an object have to be fully-formed (completely built) before the subclass parts can be constructed. Remember; the subclass object might depend on things it inherits from the superclass, so it's important that those inherited things be finished. No way around it. The superclass constructor must finish before its subclass constructor. The call to super() must be the first statement in each constructor!

````java

public Boop() {
    super();
    // This is OK because the programmer explicitly coded the call to super(), as the first statement.
}

public Boop(int i) {
    super();
    // This is OK because the programmer explicitly coded the call to super(), as the first statement.

    size = i;
}

````

````java

public Boop() {

}

public Boop(int i) {
    size = i;
}

// These are OK because the compiler will put a call to super() in as the first statement.

public Boop(int i) {
    size = i;

    super();

    // BAD! This won't compile! You can't explicitly put the call to super() below anything else.
}

````

# Overview

Java has two areas of memory we care about: the Stack and the Heap. Instance variables are variables declared inside a class but outside any method. Local variables are variables declared inside a method or method parameter. All local variables live on the stack, in the frame corresponding to the method where the variables are declared. Object reference variables work just like primitive variables--if the reference is declared as a local variable, it goes on the stack. All objects live in the heap, regardless of whether the reference is a local or instance variable. Instance variables live within the object they belong to, on the Heap. If the instance variable is a reference to an object, both the reference and the object it refers to are on the Heap. A constructor is the code that runs when you say `new` on a class type. A constructor must have the same names as the class, and must `not` have a return type. You can use a constructor to initialize the state (the instance variables) of the object being constructed. If you don't put a constructor in your class, the compiler will put in a default constructor. The default constructor is always a no-arg constructor. If you pur a constructor--any constructor--in your class, the compiler will not build the default constructor. If you want a no-arg constructor, and you've already put in a constructor with arguments, you'll have to build the no-arg constructor yourself. Always provide a no-arg constructor if you can, to make it easy for programmers to make a working object. Supply default values. Overloaded constructors means you have more than one constructor in your class. Overloaded constructors must have different argument lists. You cannot have two constructors with the same argument lists. An argument list includes the order and/or type of argument. Instance variables are assigned a default value, even when you don't explicitly assign one. The default values are 0/0.0/false for primitives, and null for references.
