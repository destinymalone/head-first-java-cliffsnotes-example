class Output {
    public static void main(String[] args) {
        Output o = new Output();
        o.go();
    }

    void go() {
        int y = 7;

        for (int x = 1; x < 8; x++) {
            y++;

            if (x > 4) {
                System.out.print(++y + " ");
            }

            if (y > 14) {
                System.out.println(" x = " + x);
                break;
            }
        }
    }
}

// Output: 12 14

// Output: 12 14 x = 6

// Output: 13 15 x = 6

// Solution

class Output {
    public static void main(String[] args) {
        Output o = new Output();
        o.go();
    }

    void go() {
        int y = 7;

        for (int x = 1; x < 8; x++) {
            y++;

            if (x > 4) {
                System.out.print(++y + " ");
            }

            if (y > 14) {
                System.out.println(" x = " + x);
                break;
            }
        }
    }
}

// Did you remember to factor in the break statement? How did that affect the output?

// Output: 13 15 x = 6