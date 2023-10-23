package com.ecart;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.Curses;
import org.jline.utils.InfoCmp;
import org.jline.utils.InfoCmp.Capability;

import java.io.IOException;

import static com.ecart.uiMain.TUI.mainMenu;
import static com.ecart.uiMain.Utils.clear;
import static com.ecart.uiMain.Utils.sleep;

/** App's entry point */
public class App {
    // public static void moveCursorUp(Terminal terminal, int lines) {
    //     if (terminal != null && lines > 0) {
    //         String escapeSequence = Curses.tputs(terminal.puts(terminal.tputs("cuu", lines)));
    //         terminal.writer().write(escapeSequence);
    //         terminal.flush();
    //     }
    // }
	 // public static void clearCurrentLine() throws IOException {
  //       try {
  //           Terminal terminal = TerminalBuilder.builder().build();
  //           String escapeSequence = Curses.tputs(terminal.puts(terminal.tputs("el")));
  //           terminal.writer().write(escapeSequence);
  //           terminal.flush();
  //           terminal.close();
  //       } catch (IOException e) {
  //           throw new IOException("Failed to clear the current line.", e);
  //       }
  //   }

    public static int[] getTerminalDimensions() {
        int[] dimensions = new int[2];
        try {
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            dimensions[0] = terminal.getHeight();
            dimensions[1] = terminal.getWidth();
            terminal.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dimensions;
    }

	// public static void moveCursorToPosition(int x, int y) {
	// 	 try {
	// 		  Terminal terminal = TerminalBuilder.builder().system(true).build();
	// 		  terminal.puts(Capability.cursor_address, y, x);
	// 		  terminal.flush();
	// 	 } catch (Exception e) {
	// 		  e.printStackTrace();
	// 	 }
	// }

    // public static void moveCursorUp(int linesToMove) {
    //     try {
    //         Terminal terminal = TerminalBuilder.builder().build();
    //         for (int i = 0; i < linesToMove; i++) {
    //             terminal.puts(Terminal.Virr.UP);
    //         }
    //         terminal.flush();
    //         terminal.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    public static void moveCursorUp(int linesToMove) {
        try {
            Terminal terminal = TerminalBuilder.builder().build();
            for (int i = 0; i < linesToMove; i++) {
                terminal.puts(InfoCmp.Capability.cursor_up);
            }
            terminal.flush();
            terminal.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
		System.out.println("hello world 1");
		System.out.println("hello world 2");
		System.out.println("hello world 3");
		System.out.println("hello world 4");
		sleep(1);
		moveCursorUp(2);
        // moveCursorUp(3); // Move the cursor up 3 lines

        // int[] dimensions = getTerminalDimensions();
        // System.out.println("Terminal Height: " + dimensions[0]);
        // System.out.println("Terminal Width: " + dimensions[1]);
    //     try {
				// System.out.print("Hello world!");
				// sleep(1);
    //         clearCurrentLine();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    }

	// if (System.console() != null) {
	// executed before the main program exists (forcefully)
	// Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	// shutdown(false);
	// }));

	// mainMenu();
	// } else
	// System.err.println("The system is not running interactively");

	public static void shutdown(boolean force) {
		// <save db here>
		clear();
		if (force)
			System.exit(0);
	}
}
