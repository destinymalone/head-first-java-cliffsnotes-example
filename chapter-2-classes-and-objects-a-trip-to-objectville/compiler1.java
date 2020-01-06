// Note: all of your .java source files should be named whatever your superclass name is.

// TapeDeck.java

class TapeDeck {
    boolean canRecord = false;

    void recordTape() {
        System.out.println("tape recording");
    }
}

class TapeDeckTestDrive {
    public static void main(String[] args) {
        t.canRecord = true;
        t.playTape();

        if (t.canRecord == true) {
            t.recordTape();
        }
    }
}

// Solution

class TapeDeck {
    boolean canRecord = false;
    void playTape() {
        System.out.println("tape recording");
    }
}

class TapeDeckTestDrive {
    public static void main(String[] args) {
        TapeDeck t = new TapeDeck();

        t.canRecord = true;
        t.playTape();

        if (t.canRecord == true) {
            t.recordTape();
        }
    }
}

// We have the template made, now we have to make an object.