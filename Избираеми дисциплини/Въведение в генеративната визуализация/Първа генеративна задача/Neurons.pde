color[] colors = {
  color(255, 0, 0),    // Red
  color(0, 255, 0),    // Green
  color(0, 0, 255),    // Blue
  color(255, 255, 0),  // Yellow
  color(0, 255, 255),  // Cyan
  color(255, 0, 255),  // Magenta
  color(255, 165, 0),  // Orange
  color(128, 0, 128),  // Purple
  color(255, 255, 255) // White
};

void setup() {
  size(400, 400);
  frameRate(120);
}

float x;
float y;
float z;
float u;
float v;
float w;
float a;
float b;
float c;
float d;
float e;
float f;

void draw() {
  if (mousePressed) {
    x = mouseX;
    y = mouseY;
    z = x;
    u = y;
    v = x;
    w = y;
  } else {
    x = random(-100, 500);
    y = random(-100, 500);
    z = random(-100, 500);
    u = random(-100, 500);
    v = random(-100, 500);
    w = random(-100, 500);
  }
  
  a = (200 + x) / random(1, 10);
  b = (200 + y) / random(1, 10);
  c = (200 + z) / random(1, 10);
  d = (200 + u) / random(1, 10);
  e = (200 + v) / random(1, 10);
  f = (200 + w) / random(1, 10);
  
  noFill();
  background(0);
  
  stroke(pickRandomColor());
  bezier(200, 200, a, b, random(a, x), random(b, y), x, y);
  
  stroke(pickRandomColor());
  bezier(200, 200, c, d, random(c, z), random(d, u), z, u);
  
  stroke(pickRandomColor());
  bezier(200, 200, e, f, random(e, v), random(f, w), v, w);
  
  stroke(255, 255, 255);
  bezier(200, 200, e, f, random(e, v), random(f, w), v, w);
}

color pickRandomColor() {
  int index = int(random(colors.length));
  return colors[index];
}
