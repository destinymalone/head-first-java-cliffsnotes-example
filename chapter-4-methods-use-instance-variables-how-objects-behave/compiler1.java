class XCopy {
    public static void main(String[] args) {
        int orig = 42;

        XCopy x = new XCopy();

        int y = x.go(orig);

        System.out.println(orig + " " + y);
    }

    int go(int arg) {
        arg = arg * 2;
        return arg;
    }
}

// Class 'XCopy' compiles and runs as it stands! 
// The output is '42 84'. Remember Java is pass-by-value, (which means pass-by-copy), 
// the variable 'orig' is not changed by the go() method.