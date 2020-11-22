package it.unibo.oop.lab.mvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UseController implements Controller {
	private final List<String> lines = new ArrayList<>();
	private String printable = null;
	
	public final void setNextString(final String next) {
		if(next.equals(null)) {
			throw new NullPointerException();
		}
		this.printable = next;
		lines.add(this.printable);
	}

	public final String getNextString(final String getNext) {
		return this.printable;
	}

	public final List<String> getHistory() {
		return lines;
	}

	public final void printCurrentOnFile(final File file) throws IllegalStateException {
		try (PrintStream ps = new PrintStream(file)) {
			ps.print(this.printable);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
	}
	
	public final void printCurrentOnStdOut() throws IllegalStateException {
		System.out.println("Printer: " + this.printable);
	}

}
