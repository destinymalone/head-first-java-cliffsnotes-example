class MultiFor {
    public static void main(String[] args) {
        for (int x = 0; x < 4; x++) {
            if (x == 1) {
                x++;
            }

            for (int y = 4; y > 2; y--) {
                System.out.println(x + " " + y);
            }
        }
    }
}

// Output:
// 0 4
// 0 3
// 2 4
// 2 3
// 3 4
// 3 3