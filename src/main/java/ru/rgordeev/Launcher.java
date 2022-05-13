package ru.rgordeev;

import ru.rgordeev.controller.FrontController;
import ru.rgordeev.controller.SimpleConsoleController;
import ru.rgordeev.view.SimpleConsoleView;

public class Launcher {
  public static void main(String[] args) {
    FrontController fc = FrontController.getInstance();
    fc.init(SimpleConsoleView.getInstance(), SimpleConsoleController.getInstance());
    fc.process();
  }
}
