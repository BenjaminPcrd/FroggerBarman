Frog frog;
Car[] cars;

float grid = 50;

void resetGame() {
  frog = new Frog(width/2-grid/2, height-grid, grid);
}

void setup() {
  size(640, 480);
  resetGame();

  int index = 0;
  cars = new Car[11];
  // ROW 1
  for (int i = 0; i < 2; i++) {
    float x = i * 300;
    cars[index] = new Car(x, height-grid*2, grid*2, grid, 2);
    index++;
  }
  
  // ROW 2
  for (int i = 0; i < 2; i++) {
    float x = i * 200 + 150;
    cars[index] = new Car(x, height-grid*3, grid, grid, 3.5);
    index++;
  }
  
  // ROW 3
  for (int i = 0; i < 4; i++) {
    float x = i * 150 + 25;
    cars[index] = new Car(x, height-grid*4, grid, grid, 1.5);
    index++;
  }
  
  // ROW 4
  for (int i = 0; i < 3; i++) {
    float x = i * 250 + 30;
    cars[index] = new Car(x, height-grid*5, grid*3, grid, -2.5);
    index++;
  }
}

void draw() {
  background(0);
  frog.show();
  for (Car car : cars) {
    car.show();
    car.update();
    if (frog.intersects(car)) {
      resetGame();
    }
  }
}

void keyPressed() {
  if (keyCode == UP) {
    frog.move(0, -1);
  } else if (keyCode == DOWN) {
    frog.move(0, 1);
  } else if (keyCode == RIGHT) {
    frog.move(1, 0);
  } else if (keyCode == LEFT) {
    frog.move(-1, 0);
  }
}
