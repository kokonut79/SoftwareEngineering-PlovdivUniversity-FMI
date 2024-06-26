ArrayList<Dot> dots;
int dotLifetime = 1000; // Dot lifetime in milliseconds

void setup() {
  size(800, 600);
  dots = new ArrayList<Dot>();
}

void draw() {
  background(0);
  
  // Add a new dot at a random position with a random size
  float randomSize = random(5, 80); // Random size between 5 and 20 pixels
  dots.add(new Dot(random(width), random(height), millis(), randomSize));
  
  // Draw all dots and remove old ones
  for (int i = dots.size() - 1; i >= 0; i--) {
    Dot dot = dots.get(i);
    if (millis() - dot.birthTime > dotLifetime) {
      dots.remove(i);
    } else {
      dot.display();
    }
  }
}

class Dot {
  float x, y;
  int birthTime;
  float size;
  
  Dot(float x, float y, int birthTime, float size) {
    this.x = x;
    this.y = y;
    this.birthTime = birthTime;
    this.size = size;
  }
  
  void display() {
    fill(255);
    noStroke();
    ellipse(x, y, size, size);
  }
}
