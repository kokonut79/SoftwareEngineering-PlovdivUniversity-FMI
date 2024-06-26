// Import the Processing library
import processing.core.PApplet;

// Define a class that extends PApplet
public class SquareExample extends PApplet {

    // Setup method runs once at the start
    public void settings() {
        // Set the size of the window
        size(500, 500);
    }

    // Setup method runs once at the start
    public void setup() {
        // Set the background color to white
        background(255);
    }

    // Draw method runs continuously
    public void draw() {
        // Set the fill color to black
        fill(0);

        // Draw a square at the center of the window
        // with a side length of 100 pixels
        int sideLength = 100;
        int x = (width - sideLength) / 2;
        int y = (height - sideLength) / 2;
        rect(x, y, sideLength, sideLength);
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Pass the class name as an argument to PApplet's main method
        PApplet.main("SquareExample");
    }
}