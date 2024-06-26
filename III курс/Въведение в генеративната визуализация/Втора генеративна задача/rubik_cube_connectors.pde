void setup() {
  size(900, 900);
  background(255);
  smooth();
  
  float x1 = 100;
  float y1 = height/2;
  float x2 = 800;
  float y2 = height/2;
  float radius = 50;
  
  // Draw first circle
  ellipse(x1, y1, radius*3, radius*3);
  
  // Draw second circle
  ellipse(x2, y2, radius*3, radius*3);
  
  // Calculate tube parameters
  float midX = (x1 + x2) / 2;
  float midY = (y1 + y2) / 2;
  float tubeWidth = 560; // Adjusted width
  
  // Draw cubic tube between the circles
  drawCubicalTube(midX, midY, tubeWidth, radius);
}

void drawCubicalTube(float midX, float midY, float tubeWidth, float radius) {
  float halfWidth = tubeWidth / 2;
  float halfRadius = radius / 2;
  
  pushMatrix();
  translate(midX, midY);
  beginShape();
  vertex(-halfWidth, -halfRadius); // Bottom-left corner
  vertex(halfWidth, -halfRadius);  // Bottom-right corner
  vertex(halfWidth, halfRadius);    // Top-right corner
  vertex(-halfWidth, halfRadius);   // Top-left corner
  endShape(CLOSE);
  popMatrix();
}
