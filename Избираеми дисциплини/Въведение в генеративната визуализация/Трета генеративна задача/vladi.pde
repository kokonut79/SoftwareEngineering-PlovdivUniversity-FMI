import ddf.minim.*;
import ddf.minim.analysis.*;

Minim minim;
AudioPlayer player;
FFT fft;
int startTime;
boolean drawLines = false;
boolean drawShapes = false;
boolean drawText = false;
float angleX = 0;
float angleY = 0;
float angleZ = 0;

void setup() {
  size(800, 600, P3D);  
  minim = new Minim(this);

  player = minim.loadFile("hit.mp3");
  player.loop();

  fft = new FFT(player.bufferSize(), player.sampleRate());

  player.play();

  startTime = millis();
}

void draw() {
  background(0);

  if (millis() - startTime > 30000 && !drawShapes) {
    drawLines = true;
  }

  if (millis() - startTime > 60000 && !drawText) {
    drawLines = false;
    drawShapes = true;
  }

  if (millis() - startTime > 90000) {
    drawShapes = false;
    drawText = true;
  }

  if (drawText) {
    // Draw rotating 3D text
    translate(width / 2, height / 2, 0);
    rotateX(angleX);
    rotateY(angleY);
    rotateZ(angleZ);
    textAlign(CENTER, CENTER);
    textSize(32);
    fill(255);
    text("pozdravi za vsi4ki vladita", 0, 0);
    
    // Update angles for rotation
    angleX += 0.01;
    angleY += 0.01;
    angleZ += 0.01;
  } else if (drawShapes) {
    // Draw random squares, circles, and triangles
    for (int j = 0; j < 10; j++) {
      float x = random(width);
      float y = random(height);

      // Random size
      float size = random(20, 60);

      // Random color
      float r = random(255);
      float g = random(255);
      float b = random(255);

      fill(r, g, b, 150);
      noStroke();

      int shapeType = int(random(3));
      switch (shapeType) {
        case 0: // Square
          rect(x, y, size, size);
          break;
        case 1: // Circle
          ellipse(x, y, size, size);
          break;
        case 2: // Triangle
          float halfSize = size / 2;
          triangle(x, y - halfSize, x - halfSize, y + halfSize, x + halfSize, y + halfSize);
          break;
      }
    }
  } else if (drawLines) {
    for (int j = 0; j < 10; j++) {
      float x1 = random(width);
      float y1 = random(height);
      float x2 = random(width);
      float y2 = random(height);

      // Random color for lines
      float r = random(255);
      float g = random(255);
      float b = random(255);

      stroke(r, g, b, 150);
      strokeWeight(random(1, 5));
      line(x1, y1, x2, y2);
    }
  } else {
    fft.forward(player.mix);

    for (int i = 0; i < fft.specSize(); i++) {
      float freq = fft.getFreq(i);
      float amplitude = fft.getBand(i) * 10;

      // Random position
      float x = random(width);
      float y = random(height);

      // Random color
      float r = map(freq, 0, fft.specSize(), 0, 255);
      float g = map(amplitude, 0, 255, 0, 255);
      float b = map(i, 0, fft.specSize(), 255, 0);

      noStroke();
      fill(r, g, b, 150);
      ellipse(x, y, amplitude, amplitude);
    }
  }
}

void stop() {
  player.close();
  minim.stop();
  super.stop();
}
