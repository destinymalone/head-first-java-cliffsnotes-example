class Excerciselb {
    public static void main(String[] args) {
        int x = 1;
        while (x < 10) {
            if (x > 3) {
                System.out.println("big x");
            }
        }
    }
}

// Solution

class Excerciselb {
    public static void main(String[] args) {
        int x = 1;
        while (x < 10) {
            x = x + 1;
            if (x > 3) {
                System.out.println("big x");
            }
        }
    }
}

// We need something to end the continuous while loop.