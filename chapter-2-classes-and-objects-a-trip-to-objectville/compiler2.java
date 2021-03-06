// Note: all of your .java source files should be named whatever your superclass name is.

// DVDPlayer.java

class DVDPlayer {
    boolean canRecord = false;

    void recordDVD() {
        System.out.println("DVD recording");
    }
}

class DVDPlayerTestDrive {
    public static void main(String[] args) {
        DVDPlayer d = new DVDPlayer;

        d.canRecord = true;
        d.playDVD();

        if (d.canRecord == true) {
            d.recordDVD();
        }
    }
}

// Solution

class DVDPlayer {
    boolean canRecord = false;

    void recordDVD() {
        System.out.println("DVD recording");
    }
    void playDVD() {
        System.out.println("DVD playing");
    }
}

class DVDPlayerTestDrive {
    public static void main(String[] args) {
        DVDPlayer d = new DVDPlayer();

        d.canRecord = true;
        d.playDVD();

        if (d.canRecord == true) {
            d.recordDVD();
        }
    }
}

// The line: d.playDVD(); wouldn't compile without a method.