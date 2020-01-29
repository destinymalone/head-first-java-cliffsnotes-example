# Serious Polymorphism

## interfaces and abstract classes

## Table of Contents

- Some classes just should `not` be instantiated
- Abstract classes (`can't` be instantiated)
- Abstract methods (must be implemented)
- Polymorphism in action
- Class Object (the ultimate superclass of `everything`)
- Taking objects out of an ArrayList (they come out as type Object)
- Compiler checks the reference type (before letting you call a method)
- Get in touch with your inner object
- Polymorphic references
- Casting an object reference (moving lower in the inheritance tree)
- Deadly Diamond of Death (multiple inheritance problem)
- Using interfaces (the best solution!)
- Exercises and puzzles
- Overview

#### Each lesson within chapter 8 is completed and explained in order

## Did we forget about something when we designed this?

We've designed the class structure, from the previous chapter, so that duplicate code is kept to a minimum. We've overridden the methods that we think should have subclass-specific implementations. We've made it nice and flexible from a `polymorphic` perspective because we can design `Animal`-using programs with `Animal` arguments (and array declarations), is that any `Animal` subtype--including those we never imagined at the time we wrote our code--can be passed in and used at runtime. We've put the common protocol from all `Animals` (the four methods that we want the world to know all `Animals` have) in the `Animal` superclass, and we're ready to start making new `Lions` and `Tigers` and `Hippos`.

### We know we can say:

```java

Wolf aWolf = new Wolf();

// A Wolf reference to a Wolf object of the same type.

```

### And we know we can say:

```java

Animal aHippo = new Hippo();

// Animal reference to a Hippo object. These two are NOT the same type.

```

### But here's where it gets weird:

```java

Animal anim = new Animal();

// Animal reference to an Animal object. These two are the same type, but ... what the heck does an Animal object look like?

```

### What does a new Animal() object look like?

### What are the instance variable values?

#### Some classes just should not be instantiated!

It makes sense to create a `Wolf` object or a `Hippo` object or a `Tiger` object, but what exactly is an `Animal` object? What shape is it? What color, size, number of legs.... We `need` an `Animal` class, for inheritance and polymorphism. But we want programmers to instantiate only the less abstract `subclasses` of class `Animal` itself. We want Tiger objects and Lion objects, not Animal objects. Fortunately, there's a simple way to prevent a class from ever being instantiated. In other words, to stop anyone from saying "new" on that type. By marking the class as `abstract` the compiler will stop any code, anywhere, from ever creating an instance of that type. You can still use that `abstract` type as a reference type. That's a big part of why you have that `abstract` class in the first place (to use ut as a polymorphic argument or return type or to make a polymorphic array). When you're designing your class inheritance structure, you have to decide which classes are `abstract` and which are `concrete`. Concrete classes are those that are specific enough to be instantiated. A `concrete` class just means that it;s OK to make objects of that type. Making a class abstract is easy--put the keyword `abstract` before the class declaration:

```java

abstract class Canine extends Animal {
    public void roam() {
        // code
    }
}

```

### The compiler won't let you instantiate an abstract class

An abstract class means that nobody can ever make a new instance of that class. You can still use that abstract class as a declared reference type, for polymorphism, but you don't have to worry about somebody making objects of that type. The compiler `guarantees` it.

```java

abstract public class Canine extends Animal
{
    public void roam() { }
}
------------------------------------------------------------------------------------------------------------

public class MakeCanine {
    public void go() {

        Canine c;
        c = new Dog();

        // This is OK because you can always assign a subclass object to a superclass reference, even if the subclass is abstract.

        c = new Canine();

        // Class Canine is marked abstract, so the compiler will NOT let you do this.

        c.roam();
    }
}

```

An `abstract class` has virtually\* no use, no value, no purpose in life unless it is `extended`. With an abstract class, the guys doing the work at runtime, are `instances of a subclass` of your abstract class.

#### Note:

There is an exception to this--an abstract class can have static members.

## Abstract vs. Concrete

A class that's not abstract is called a `concrete` class. In the Animal inheritance tree, if we make Animal, Canine, and Feline abstract, that leaves Hippo, Wolf, Dog, Tiger, Lion, and Cat as the concrete subclasses. What does a GUI Component look like? The Component class is the superclass of GUI-related classes for things like buttons, text areas, a scrollbar, dialog boxes, etc. You don't make an instance of a generic `Component` and put it on the screen, you make a JButton. In other words, you instantiate only a `concrete subclass` of Component, but never Component itself.

### Abstract methods

Besides classes, you can mark `methods` abstract, too. An abstract class means the class must be `extended`; an abstract method means the method must be `overridden`. You might decide that some (or all) behaviors in an abstract class don't make any sense unless they're implemented by a more specific subclass. In other words, you can't think of any generic method implementation that could be useful for subclasses. What would a generic `eat()` method look like?

#### An abstract method has no body!

Because you've already decided there isn't any code that would make sense in the abstract method, you won't put in a method body. So no curly braces--just end the declaration with a semicolon.

```java

public abstract void eat();

```

If you declare an abstract method, you MUST mark the `class` abstract as well. You can't have an abstract method in a non-abstract class. If you put even a single abstract method in a class, you have to make the class abstract. But you `can` mix both abstract and non-abstract methods in the abstract class.

## Implement all abstract methods

You MUST implement all abstract methods. Implementing an abstract method is just like overriding a method. Abstract methods don't have a body; they exist solely for polymorphism. That means the first concrete class in the inheritance tree must implement `all` abstract methods. If both Animal and Canine are abstract, for example, and both have abstract methods, class Canine does not have to implement the abstract methods from Animal. But as soon as we get to the first concrete subclass, like Dog, that subclass must implement `all` of the abstract methods from both Animal and Canine. But remember that an abstract class can have both abstract and non-abstract methods, so Canine, for example, could implement an abstract method from Animal, so that Dog didn't have to. But if Canine says nothing about the abstract methods from Animal, Dog has to implement all of Animal's abstract methods. When we say "you must implement the abstract method", that means you `must provide a body`. That means you must create a non-abstract method in your class with the same method signature (name and arguments) and a return type of the abstract method. What you put `in` that method is up to you. All Java cares about is that the method is `there`, in your concrete subclass.

## Polymorphism in action

Let's say we want to write our `own` kind of list class, one that will hold Dog objects, but we don't know about the ArrayList class. For the first pass, we'll give it just an `add()` method. We'll use a simple `Dog` array `(Dog[])` to keep the added Dog objects, and give it a length of `5`. When we reach the limit of 5 Dog objects, you can still call the `add()` method but it won't do anything. If we're `not` at the limit, the `add()` method puts the Dog in the array at the next available index position, then increments that next available index `(nextIndex)`.

```java

public class MyDogList {
    private Dog [] dogs = new Dog[5];
    // Use a plain old Dog array behind the scenes.
    private int nextIndex = 0;
    // We'll increment this each time a new Dog is added.

    public void add(Dog d) {
        if (nextIndex < dogs.length) {
            dogs[nextIndex] = d;
            System.out.println("Dog added at " + nextIndex);
            // If we're not already at the limit of the dogs array, add the Dog and print a message.

            nextIndex++;
            // Increment, to give us this next index to use.
        }
    }
}

```

### Uh-oh, now we need to keep Cats, too.

Options:

1. Make a separate class, `MyCatList`, to hold `Cat` objects. Pretty clunky.

2. Make a single class, `DogAndCatList`, that keeps two different arrays as instance variables and has two different `add()` methods: `addCat(Cat c)` and `addDog(Dog d)`. Another clunky situation.

3. Make heterogeneous `AnimalList` class, that takes `any` kind of Animal subclass (since we know that if the spec changed to add Cats, sooner or later we'll have some `other` kind of animal added as well). We like this option best, so let's change our class to make it more generic, to take Animals instead of just Dogs. We've highlighted the key changes (the logic is the same, of course, but the type has changed from Dog to Animal everywhere in the code).

```java

public class MyAnimalList {
    private Animal[] animals = new Animal[5];
    // Don't panic. We're not making a new Animal object, we're making a new array object, of the type Animal. (Remember, you cannot make a new instance of an abstract type, but you CAN make an array object declared to HOLD that type.)

    private int nextIndex = 0;

    public void add(Animal a) {
        if (nextIndex < animals.length) {
            animals[nextIndex] = a;
            System.out.println("Animal added at " + nextIndex);
            nextIndex++;
        }
    }
}

public class AnimalTestDrive {
    public static void main(String[] args) {
        MyAnimalList list = new MyAnimalList();
        Dog a = new Dog();
        Cat c = new Cat();
        list.add(a);
        list.add(c);
    }
}

```

## What about non-Animals? Why not make a class generic enough to take anything?

We want to change the type of the array, along with the `add()` method argument, to something `above` Animal. Something even `more` generic, `more` abstract than Animal. But how can we do it? Remember those methods of `ArrayList`? Look how the remove, contains, and `indexOf` method all use an object of type....`Object`!

### Every class in Java extends class Object.

Class Object is the mother of all classes; it's the superclass of `everything`. Even if you take advantage of `polymorphism`, you still have to create a class with methods that take and return `your` polymorphic type. Without a common superclass for everything in Java, there'd be no way for the developers of Java to create classes with methods that could take `your` custom types....types they never knew about when they wrote the ArrayList class. Every class you write extends Object, without you ever saying it. But you can think of it as though a class you write looks like this:

```java

public class Dog extends Object { }

```

Dog already extends something, `Canine`. That is OK. The compiler will make `Canine` extend `Object` instead. Except `Canine` extends `Animal`. No problem, then the compiler will just make `Animal` extend `Object`. Any class that doesn't explicitly extend another class, implicitly extends Object. So, since Dog extends Canine, it doesn't directly extend Object (although it does extend it indirectly), and the same is true for Canine, but Animal `does` extend Object.

#### Note:

Many of the ArrayList methods use ultimate polymorphic type, Object. Since every class in Java is a subclass of Object, these ArrayList methods can take anything! As of Java 5.0 the `get()` and `add()` methods look a little different than the ones shown here, but for now this is the way to think about it.

## So what's in this ultra-super-mega-class Object?

Every object should have a method that lets you find out if one object is equal to another object, a method that can tell you the actual class type of that object, a method that gives you a hashcode for the object, so you can use the object in hashtables. Also, A method that prints a String message for that object. Class Object does indeed have methods for those for things. That's not al, though, but these are the ones that we care about.

equals(Object o) - Tells you if two objects are considered 'equal'.

getClass() - Gives you back the class that object was instantiated from.

hashCode() - Prints out a hashcode for the object (for now, think of it as a unique ID).

toString() - Prints out a String message with the name of the class and some other number we hardly care of.

### Using polymorphic references of type Object has a price....

Keep in mind that we're not talking about making instances of type Object; we're talking about making instances of some other type, but using a reference type of type Object, When you put an object into an ArrayList<Dog>, it foes in as a Dog, and comes our as a Dog:

```java

ArrayList<Dog> myDogArrayList = new ArrayList<Dog>();
// Make an ArrayList declared to hold Dog objects.

Dog aDog = new Dog();
// Make a Dog.

myDogArrayList.add(aDog);
// Add the Dog to the list.

Dog d = myDogArrayList.get(0);
// Assign the Dog from the list to a new Dog reference variable. (Think of it as though the get() method declares a Dog return type because you used ArrayList<Dog>).

```

But what happens when you declare it as ArrayList<Object>? If you want to make an ArrayList that will take `any` kind of Object, you declare it like this:

```java

ArrayList<Object> myDogArrayList = new ArrayList<Object>();
// Make an ArrayList declared to hold any type of Object.



Dog aDog = new Dog();
// Make a Dog.

myDogArrayList.add(aDog);
// Add the Dog to the list.

// Those two steps are the same.

```

But what happens when you try to get the Dog object and assign it to a Dog reference?

```java

Dog d = myDogArrayList.get(0);

```

No!!! This won't compile! When you use ArrayList<Object>, the get() method returns type Object. The Compiler knows only that the object inherits from Object (somewhere in its inheritance tree) but it doesn't know it's a Dog! Everything comes out of an ArrayList<Object> as a reference of type Object, regardless of what the actual object is, or what the reference type was when you added the object to the list.

## Get in touch with your inner Object.

An object contains `everything` it inherits from each of its superclasses. That means every object--regardless of its actual class type--is also an instance of class Object. That means any object in Java can be treated not just as a Dog, Button, or Snowboard, but also as an Object. When you say `new Snowboard()`, you get a single object on the heap--a Snowboard object--but that Snowboard wraps itself around an inner core representing the Object (capital "O") portion of itself. 'Polymorphism' means 'many forms'. You can treat a Snowboard as a Snowboard or as an Object. If a reference is like a remote control, the remote control takes on more and more buttons as you move down the inheritance tree. A remote control (reference) of type Object has only a few buttons--the buttons for the exposed methods of class Object. But a remote control of type Snowboard includes all the buttons from class Object, plus any new buttons (for new methods) of class Snowboard. The more specific the class, the more buttons it may have. Of course, that is not always true; a subclass might not ad any new methods, but simply override the methods of its superclass. The key point is that even if the object is of type Snowboard, an Object reference to the Snowboard object can't see the Snowboard-specific methods.

```java

Snowboard s = new Snowboard();
Object o = s;

```

#### Note:

When you put an object in an ArrayList<Object>, you can treat it only as an Object, regardless of the type it was when you put it in. When you get a reference from an ArrayList<Object>, the reference is always of type Object. That means you get an Object remote control. Casting an object reference back to its real type: It's really still a Dog object, but if you want to call Dog-specific methods, you need a reference declared as type Dog. If you are sure\* the object is really a Dog, you can make a new Dog reference to it by copying the Object reference, and forcing that copy to go into a Dog reference variable, using cast (Dog). You can use the new Dog reference to call Dog methods.

```java

Object o = al.get(index);

Dog d = (Dog) o;
// Cast the Object back to a Dog we know is there.

d.roam();

```

\*If you are not sure it's a Dog, you can use the `instanceof` operator to check. Because if you're wrong when you do the cast, you'll get a ClassCastException at runtime and come to a grinding halt.

```java

if (o instanceof Dog) {
    Dog d = (Dog) o;
}

```

So now you've seen how much Java cares about the methods in the class of the `reference` variable. You can call a method on an object only if the class of the reference variable has that method. Think of the public methods in your class as your contract, your promise to the outside world about the things you can do. When you write a class, you almost always `expose` some of the methods to code outside the class. To `expose` a method means you make a method `accessible`, usually by marking it public. Just remember that the compiler checks the class of the reference variable, not the class of the actual object at the other end of the reference.

### What if you need to change the contract?

Your Dog contract isn't the `only` contract that defines who you are. Remember, you inherit accessible (which usually means `public`) methods from all of your superclasses. Everything in class `Canine`, `Animal`, and `Object` is part of your contract. According to the `IS-A` test, you `are` each of those things. But what if the person who designed your class had in mind the Animal simulation program, and now they want to use you (class Dog) for a Science Fair Tutorial Animal objects. That's OK but what if they wanted somethings modified to do `Pet` things? This `PetShop` program has more than just `Dog`s. Options:

1. We take the easy path and put pet methods in class Animal.

2. We start with Option One, putting the pet methods in class Animal, but we make the methods abstract, forcing the Animal subclasses to override them.

3. Put the pet methods ONLY in the classes where they belong.

#### So what we REALLY need is:

A way to have pet behavior in `just` the pet classes. A way to guarantee that all pet classes have all of the same methods defined (same name, same arguments, same return types, no missing methods, etc.), without having to cross fingers and hope all the programmers get it right. A way to take advantage of polymorphism so that all pets can have their pet methods called, without having to use arguments, return types, and arrays for every pet class. So, it looks like we need two superclasses at the top. There is one problem though, it's called "multiple inheritance" and it can be a really bad thing.

## Interface to the rescue!

Java gives you a solution. An `interface`. Not a `GUI` interface, not the generic use of the `word` interface as in, "That's the public interface for the Button class API," but the Java `keyword` interface. A Java interface solves your multiple inheritance problem by giving you much of the polymorphic `benefits` of multiple inheritance without the pain and suffering from the Deadly Diamond Death (DDD). How interfaces side-step the DDD is surprisingly simple: make all the methods abstract! That way, the subclasses `must` implement the methods (remember, abstract methods `must` be implemented by the first concrete subclass), so at runtime, the JVM isn't confused about `which` of the two inherited versions it's supposed to call. A Java interface is like a 100% pure abstract class. All methods in an interface are abstract, so any class that IS-A Pet MUST implement (override) the methods of PET. To DEFINE an interface:

```java

public interface Pet {...}

// Use the keyword "interface" instead of "class"

```

To IMPLEMENT an interface:

```java

public class Dog extends Canine implements Pet {...}

// Use the keyword "implements" followed by the interface name. Note that when you implement an interface you still get to extend a class.

```

Making and implementing the Pet interface.

```java

public interface Pet {
    public abstract void beFriendly();
    public abstract void play();

    // All interface methods are abstract, so they MUST end in semicolons. Remember, they have no body!
}

```

```java

public class Dog extends Canine implements Pet {

    // You say 'implements' followed by the name of the interface.

    public void beFriendly() {...}
    public void play() {...}

    // You SAID you are a Pet, so you MUST implement the Pet methods. It's your contract. Notice the curly braces instead of the semicolons.

    public void roam() {...}
    public void eat() {...}

    // These are normal overriding methods.
}

```

Classes from different inheritance trees can implement the same interface. When you use a `class` a polymorphic type (like an array of type Animal or a method that takes a Canine argument), the objects you can stick in that type must be from the same inheritance tree. But not just anywhere in the inheritance tree; the objects must be from a class that is a subclass of the polymorphic type. An argument of type Canine can accept a Wolf and a Dog, but not a Cat or a Hippo. But when you use an `interface` as a polymorphic type (like an array of Pets), the objects can be from `anywhere` in the inheritance tree. The only requirement is that the objects are from a class that `implements` the interface. Allowing classes in different inheritance trees to implement a common interface is crucial in the Java API. Do you want an object to be able to save its state to a file? Implement the `Serializable` interface. Do you need objects to run their methods in a separate thread of execution? Implement Runnable. Remember that classes from `any` place in the inheritance tree might need to implement those interfaces. Nearly `any` class might want to be saveable or runnable. Better still, a class can implement `multiple` interfaces!

```java

public class Dog extends Animal implements Pet, Saveable, Paintable {...}

```

# Overview

When you don't want a class to be instantiated (in other words, you don't want anyone to make a new object of that class type) mark the class with the `abstract` keyword. An abstract class can have both abstract and non-abstract methods. If a class has even one abstract method, the class must be marked abstract. An abstract method has no body, and the declaration ends with a semicolon (no curly braces). All abstract methods must be implemented in the first concrete subclass in the inheritance tree. Every class in Java is either a direct or indirect subclass of class `Object` (java.lang.Object). Methods can be declared with Object arguments and/or return types. You can call methods on an object `only` if the methods are in a class (or inheritance) reference variable of the type, regardless of the actual `object` type. So, a reference variable type Object can be used only to call methods defined in class `Object`, regardless of the type of object to which the reference refers. A reference variable of type Object can't be assigned to any other reference type without a `cast`. A cast can be used to assign a reference variable of one type to a reference variable of a subtype, but at runtime the cast will fail if the object on the heap is NOT of a type compatible with the cast. Example:

```java

Dog d = (Dog) x.getObject(aDog);

```

All objects come out of an `ArrayList<Object>` as type Object (meaning, they can be referenced only by an Object reference variable, unless you use a `cast`). Multiple inheritance is not allowed in Java, because of the problems associated with the "Deadly Diamond of Death". That means you can extend only one class (you can have only one immediate superclass). An interface is like 100% pure abstract class. It defines `only` abstract methods. Implement an interface using the `interface` keyword instead of the word `class`. Implement an interface using the keyword, `implements`. Example:

```java

Dog implements Pet

```

Your class can implement multiple interfaces. A class that implements an interface `must` implement all the methods of the interface since all interface methods are implicitly public and abstract. To invoke the superclass version of a method from a subclass that's overridden the method, use the `super` keyword. Example:

```java

super.runReport();

```
