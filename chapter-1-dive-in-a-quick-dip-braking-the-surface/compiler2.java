public static void main(String[] args) {
    int x = 5;
    while (x > 1) {
        x = x - 1;

        if (x < 3) {
            System.out.println("small x");
        }
    }
}

// Solution

class Foo {
    public static void main(String[] args) {
        int x = 5;
        while ( x > 1) {
            x = x - 1;

            if (x < 3) {
                System.out.println("small x");
            }
        }
    }
}

// The filel can not compile without a class declaration and matching curly braces