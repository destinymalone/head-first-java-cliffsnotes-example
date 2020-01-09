class MultiFor {
    public static void main(String[] args) {
        for (int x = 0; x < 4; x++) {
            for (int y = 4; y > 2; y--) {
                System.out.println(x + " " + y);
            }

            if (x == 1) {
                x++;
            }
        }
    }
}

// What would happen if the "if" statement came before the 'y' for loop?

// Output: 
// 0 4
// 0 3
// 1 4
// 1 3
// 3 4
// 3 3