// Note: all of your .java source files should be named whatever your superclass name is.

// Excerciselb.java

class Excerciselb {
    int x = 5;

    while(x>1)
    {
        x = x - 1;

        if (x < 3) {
            System.out.println("small x");
        }
    }
}

// Solution

class Excerciselb {
    public static void main(String[] args) {
        int x = 5;

        while (x > 1) {
            x = x - 1;

            if (x < 3) {
                System.out.println("small x");
            }
        }
    }
}

// The 'while' loop code must be inside a method, not hanging out inside the
// class.