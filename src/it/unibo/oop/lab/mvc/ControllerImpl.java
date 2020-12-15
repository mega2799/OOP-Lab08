package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    private List<String> history = new ArrayList<>();
    private String line;

    @Override
    public void setNextString(final String next) {
        try {
            line = next;
            history.add(line);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getNextString() {
        return line;
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void printCurrent() {
        try {
            System.out.println(line);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
