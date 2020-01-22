# Better Living in Objectville

## inheritance and polymorphism

## Table of Contents

- Understanding inheritance (superclass and subclass relationships)
- Designing an inheritance tree (the Animal simulation)
- Avoiding duplicate code (using inheritance)
- Overriding methods
- IS-A and HAS-A (bathtub girl)
- What do you inherit from your superclasses?
- What does inheritance really buy you?
- Polymorphism (using a supertype reference to a subclass object)
- Rules for overriding (don't touch those arguments and return types!)
- Method overloading (nothing more than method name re-use)
- Exercises and puzzles
- Overview

#### Each lesson within chapter 7 is completed and explained in order

### Chair Wars Revisited....

Remember way back in chapter 2, when Larry (procedural guy) and Brad (OO guy) were trying for the Aeron chair? Let's look at a few pieces of that story to review the basics of inheritance.

Larry: You've got duplicated code! The rotate procedure is in all four Shape things. It's a stupid design. You have to maintain four different rotate "methods". How can that ever be good?

Brad: Oh, I guess you didn't see the final design. Let me show you have `OO inheritance` works, Larry.

Look at what all four classes have in common. They're `Shapes`, and they all `rotate` and `playSound`. So I abstracted out the common features and put them into a new class called `Shape`. Then I linked the other four shape classes to the new `Shape` class, in a relationship called `inheritance`.

#### What about the Amoeba rotate()?

Larry: Wasn't that the whole problem here -- that the amoeba shape had a completely different `rotate` and `playSound` procedure?

Brad: That's the last step. The `Amoeba` class `overrides` the methods of the `Shape` class. Then at runtime, the JVM knows exactly which `rotate()` method to run when someone tels `Amoeba` to rotate.

I made the `Amoeba` class override the `rotate()` and `playSound()` methods of the `superclass` `Shape`. Overriding just means that a `subclass` redefines one of itd inherited methods when it needs to change or extend the `behavior` of that method.

### Understanding Inheritance

When you design with inheritance, you put common code in a class and then tell other more specific classes that the common (more abstract) class is their superclass. When one class inherits from another, the subclass inherits from the superclass. In Java, we say that the subclass extends the superclass. An inheritance relationship means that the subclass inherits the `members` of the superclass. When we say "members of a class" we mean the instance variables and methods.

#### An Inheritance example:

```java

public class Doctor {
    boolean worksAtHospital;

    void treatPatient() {
        // perform checkup
    }
}

public class FamilyDoctor extends Doctor {
    boolean makesHouseCalls;
    void giveAdvice() {
        // give homespun advice
    }
}

public class Surgeon extends Doctor {
    void treatPatient() {
        // perform surgery
    }

    void makeIncision() {
        // makes incision (yikes!)
    }
}

```

Superclass: Doctor

- `worksAtHospital`: (one instance variable)
- `treatPatient()`: (one method)

Subclass: Surgeon

- `treatPatient()`: (Overrides the inherited treatPatient() method)
- `makeIncision()`: (Adds one new method)

Subclass: FamilyDoctor

- `makesHouseCalls`: (Adds one new instance variable)
- `giveAdvice()`: (Adds one new method)

### Let's design the inheritance tree for an Animal simulation program

Imagine you're asked to design a simulation program that lets the user throw a bunch of different animals into an environment to see what happens. We've been given a list of some of the animals that will be in the program, but not all. We know each animal will be represented by an object, and that the objects will move around in the environment, doing whatever it is that each particular animal is programmed to do. And we want other programmers to be able to add new kinds of animals to the program at any time.

1. Look for objects that have common attributed and behaviors. What do these six types have in common? This helps you to abstract out behaviors. How are these types related? This helps you define the inheritance tree relationships.

2. Design a class that represents the common state and behavior. These objects are all animals, so we'll make a common superclass called `Animal`. We'll put in methods and instance variables that all animals might need.

3. Decide if a subclass needs behaviors (method implementations) that are specific to that particular subclass type. Looking at the `Animal` class, we decide the `eat()` and `makeNoise()` should be overridden by individual subclasses, since each animal eats differently and does not sound the same.

4. Look for more opportunities to use abstraction, by finding two or more subclasses that might need common behavior. We look at our classes and see that `Wolf` and `Dog` might have some behavior in common, and the same goes for `Lion`, `Tiger`, and `Cat`.

5. Finish the class hierarchy. Since animals already have an organizational hierarchy (the whole kingdom, genus, phylum thing), we can use the level that makes the most sense for class design. We'll use the biological "families" to organize the animals by making t=a `Feline` class and a `Canine` class. We decide that `Canines` could use a common `roam()`method, because they tend to move in packs. We also see that `Felines` could use a common `roam()` method, because they tend to avoid others of their own kind. We'll let `Hippo` continue to use its inherited `roam()` method-- the generic one it gets from `Animal`. So we're done with the design for now.

Superclass: Animal

- picture
- food
- hunger
- boundaries
- location
- makeNoise()
- eat()
- sleep()
- roam()

  Subclass: Feline

  - roam()

    Subclass: Lion

    - makeNoise()
    - eat()

    Subclass: Tiger

    - makeNoise()
    - eat()

    Subclass: Cat

    - makeNoise()
    - eat()

  Subclass: Hippo

  - makeNoise()
  - eat()

  Subclass: Canine

  - roam()

    Subclass: Wolf

    - makeNoise()
    - eat()

    Subclass: Dog

    - makeNoise()
    - eat()

#### Which method is called?

The `Wolf` class has four methods. One inherited from `Animal`, one inherited from `Canine` (which is actually an overridden version of a method in class `Animal`), ans two overridden in the `Wolf` class. When you create a `Wolf` object and assign it to a variable, you can use the dot operator on that reference variable to invoke all four methods. But which version of those methods gets called?

```java

Wolf w = new Wolf();
// Make a new Wolf object

w.makeNoise();
// calls the version in Wolf
w.roam();
// calls the version in Canine
w.eat();
// calls the version in Wolf
w.sleep();
// calls the version in Animal

```

When you call a method on an object reference, you're calling the most specific version of the method for that object type. In other words, the lowest one wins! "Lowest" meaning lowest on the inheritance tree. Canine is lower than Animal, and Wolf is lower that Canine, so invoking a method on a reference to a Wolf object means the JVM starts looking first in the Wolf class. If the JVM doesn't fins a version of the method in the Wolf class, it stars walking back up the inheritance hierarchy until it finds a match

#### Note:

A wolf is a canine and a canine is an animal----up the tree--search lowest to highest.

### Using IS-A and HAS-A

Remember that when one class inherits from another, we say that the subclass `extends` the superclass. When you want to know if one thing should extend another, apply `IS-A` TEST. Triangle IS-A Shape, yeah that works. Cat IS-A Feline, that works too. Surgeon IS-A Doctor, still good. Tub extends Bathroom sounds reasonable. Until you apply the IS-A test. To know if you've designed your types correctly, ask, "Does it make sense to say X IS-A Y?" If it doesn't, you know there's something wrong with the design, so if we apply IS-A test, Tub IS-A Bathroom is definitely false. What if we reverse it to Bathroom extends Tub? That still doesn't work, Bathroom IS-A Tub doesn't work. Tub and Bathroom are related, but not through inheritance. Tub and Bathroom are joined by a `HAS-A` relationship. Bathroom has a Tub instance variable. In other words, Bathroom has a reference to a Tub, but Bathroom does not extend Tub and vice-versa.

#### But wait! There's more!

The `IS-A` test works anywhere in the inheritance tree, if it is well designed. If class `B` extends class `A`, class `B` IS-A class `A`. This is true anywhere in the inheritance tree. If class `C` extends class `B`, class `C` passes the IS-A test for both `B` and `A`. With an inheritance tree, you're always allows to say "Wolf extends Animal" or "Wolf IS-A Animal". it makes no different if Animal is the superclass of the superclass Wolf. In fact, as long as Animal is somewhere in the inheritance hierarchy above Wolf, Wolf IS-A Animal will always be true.

#### How do you know if you've got your inheritance right?

There's obviously more to it than what we've covered so far. For now, though, a good guideline is to use the IS-A test/ If "X IS-A Y" makes sense, both classes (X and Y) should probably live in the same inheritance hierarchy. Chances are, they have the same or overlapping behaviors. Keep in mind that the inheritance IS-A relationship works in only one direction! Triangle IS-A Shape makes sense, so you can have Triangle extend Shape. But the reverse--Shape IS-A Triangle--does not make sense, so Shape should not extend Triangle. Remember that the IS-A relationship implies that if X IS-A Y, then X can do anything a Y can do (and possibly more).

### So what does all this inheritance really buy you?

You get a lot of OO mileage by designing with inheritance. You avoid duplicate code by abstracting out the behavior common to a group of classes, and sticking that in a superclass. You will only need to modify in one place instead of all places the behavior is presented. Make the change and compile the class again. You don't have to touch the subclasses! Just deliver the newly-changed superclass, and all classes that extend it will automatically use the new version. As long as the superclass doesn't `break` anything for the subclass, everything's fine. Inheritance lets you guarantee that all classes grouped under a certain supertype have all the methods that the supertype has. In other words, you define a common protocol for a set of classes related through inheritance. When defining methods in a superclass, that can be inherited by other subclasses, you're announcing a kind of protocol to other code that says, "All my subtypes (subclasses) can do these things, with these methods that look like this...". In other words, you established a `contract`. Class Animal establishes a common protocol for all Animal subtypes:

Superclass: Animal

- makeNoise()
- eat()
- sleep()
- roam()

You're telling the world that any Animal can do these four things. That includes the method arguments and return types. And remember, when we say any Animal, we mean Animal and any class that extends from Animal. Which again means, any class that has Animal somewhere above it in the inheritance hierarchy. When you define a supertype for a group of classes, any subclass of that supertype can be substituted where the supertype is expected. And you care because you get to take advantage of `polymorphism`. Which matters because you get to refer to a subclass object using a reference declared as the supertype. That means that you get to write really flexibly code. Code that's cleaner. Code that's not just easier to develop, but also much, much easier to extend.

#### Note:

"When we say 'all methods' we mean 'all inheritable methods', which for now actually means, 'all the public methods', although later we'll refine that definition a bit more.

### Polymorphism

The three steps of object declaration and assignment:

```java

Dog myDog = new Dog();

```

Declare the reference variable--Tells the JVM to allocate space for a reference variable. The reference variable is, forever, of type `Dog`. In other words, a remote control has buttons to control a `Dog`, but not a Cat or a Button etc. Create an object--Tells JVM to allocate space for a new `Dog` object on the garbage collectible heap. Link the object and reference--Assigns the new `Dog` to the reference variable `myDog`. In other words, program the remote control. The important point is that the reference type `AND` the object type are the same. But with polymorphism, the reference and the object can be different.

```java

Animal myDog = new Dog();

```

The two (Animal and Dog) are NOT the same type. The reference variable type is declared as Animal, but the object is created as a new Dog(). With polymorphism, the reference type can be a superclass of the actual object type. When you declare a reference variable, any object that passes the `IS-A` test for the declared type of the reference variable can be assigned to that reference. In other words, anything that `extends` the declared reference variable type can be `assigned` to the reference variable. This lets you do things like make polymorphic arrays.

```java

Animal[] animals = new Animal[5];
// Declare an array of type Animal. In other words, an array that will hold objects of type Animal.
animals[0] = new Dog();
animals[1] = new Cat();
animals[2] = new Wolf();
animals[3] = new Hippo();
animals[4] = new Lion();
// But look what you get to do... you can put ANY subclass of Animal in the Animal array!

for (int i = 0; i < animals.length; i++) {
    // And here's the best polymorphic part, you get to loop through the array and call one of the Animal-class methods, and every object does the right thing!

    animals[i].eat();
    // When 'i' is 0, a Dog is at index 0 in the array, so you get the Dog's eat() method. When 'i' is 1, you get the Cat's eat() method.

    animals[i].roam();
    // Same thing with roam().

}

```

#### But wait! There's more!

You can have polymorphic `arguments` and `return` types. If you can declare a reference variable of a supertype, say, Animal, and assign a subclass object to it, Dog, think of how that might work when the reference is an argument to a method....

```java

class Vet {
    public void giveShot(Animal a) {
        // do things to Animal at the other end of the 'a' parameter
        a.makeNoise();
    }
}

```

The Animal parameter can take ANY Animal type as the argument. And when the `Vet` is done giving the shot, it tells the Animal the `makeNoise()`, and whatever Animal is really out there on the heap, that's whose `makeNoise()` method will run.

```java

class PetOwner {
    public void start() {
        Vet v = new Vet();

        Dog d = new Dog();
        Hippo h = new Hippo();
        // The Vet's giveShot() method can take any Animal you give it. As long as the object you pass in as the argument is a subclass of Animal, it will work.

        v.giveShot(d);
        // Dog's makeNoise() runs
        v.giveShot(h);
        // Hippo's makeNoise() runs
    }
}

```

#### Let's Review

If I write my code using polymorphic arguments, where I declare the method parameter as a superclass type, I can pass in any subclass object at runtime. That also means I can write my code and someone else can add new subclass types to the program and my methods will still work. Remember that `Vet` class? If you write that `Vet` class using arguments declared as type `Animal`, your code can handle any Animal `subclass`. That means if others want to take advantage of your Vet class, all they have to do is make sure `their` new Animal types extend class Animal. The Vet methods will still work, even though the Vet class was written without any knowledge of the Animal subtypes the Vet will be working on.

### Keeping the Contract: rules of overriding

When overriding a method from a superclass, you're agreeing to fulfill the contract. The methods are the contract. If polymorphism is going to work, the Toaster's version of the overridden method from Appliance has to work at runtime. Remember, the compiler looks at the reference type to decide whether you can call a particular method on that reference. With an Appliance reference to a Toaster, the compiler cares only if class `Appliance` has the method you're invoking on an Appliance reference. But at runtime, the JVM looks not at the `reference` type (Appliance) but at the actual `Toaster` object on the heap. If the compiler has already `approved` the method call, the only way it can work is if the overriding method has the same arguments and return types. Otherwise, someone with an Appliance reference will call `turnOn()` as a no-arg method, even though there's a version in Toaster that takes an int. Which one is called at runtime? The one in Appliance. In other words, the `turnOn(int level)` method in Toaster is not an override!

1. Arguments must be the same, and return types must be compatible--whatever the superclass takes as an argument, the subclass overriding the method must use the same argument. Whatever the superclass declares as a return type, the overriding method must declare either the same type, or a subclass type.

2. The method can't be less accessible--Access level must be the same, or friendlier. Can't override a public method and make it private.

#### Overloading a method

It is nothing more than having two methods with the same name but different argument lists. There's no polymorphism involved with overloaded methods! An overloaded method is just a different method that happens to have the same method name. It has nothing to do with inheritance and polymorphism. An overloaded method is NOT the same as an overridden method.

1. The return types can be different--You can change the return types in overloaded methods, as long as the argument lists are different.

2. You can't change ONLY the return type--If the return type is the only difference, the compiler will assume you are overriding the method. Won't work unless the return type is a subtype of the return type declared in the superclass. To overload a method, you MUST change the argument list, although you can change the return type to anything.

3. You can vary the access levels in any direction--Can overload a method with a method that's more restrictive since the new method isn't obligated to fulfill the contract of the overload method.

```java

public class Overloads {
    String uniqueID;
    public int addNums(int a, int b) {
        return a + b;
    }
    public double addNums(double a, double b) {
        return a + b;
    }
    public void setUniqueID(String theID) {
        // lots of validation code, and then:
        uniqueID = theID;
    }
    public void setUniqueID(int ssNumber) {
        String numString = " " + ssNumber;
        setUniqueID(numString);
    }
}

```

# Overview

A subclass `extends` a superclass. A subclass inherits all `public` instance variables and methods of the superclass, but does not inherit the `private` instance variables and methods of the superclass. Inherited methods `can` be overridden; instance variable `cannot` be overridden (although they can be redefines in the subclass, but that's not the same thing, and there's almost never a need to do it.). Use the `IS-A` test to verify that your inheritance hierarchy is valid. If `X` extends Y, then `X` `IS-A` `Y` must make sense. The `IS-A` relationship works in only one direction. A `Hippo` is an `Animal`, but not all Animals are Hippos. When a method is overridden in a subclass, and that method is invoked on an instance of the subclass, the overridden version of the method is called. (The lowest one wins). If class `B` extends `A`, and `C` extends `B`, class `B` `IS-A` class `A`, and class `C` `IS-A` class `B`, and class `C` also `IS-A` class `A`.
