package ru.rgordeev.command;

import ru.rgordeev.controller.Params;

public interface CommandFactory {
  Command buildCommand(Params params);
}
