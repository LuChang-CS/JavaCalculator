package Framework;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Framework.CalculatorActionListener.ButtonMouseListener;

public class CalculatorButton extends JButton {
	
	String command;

	public static final int MEMORY_READ = 0;
	public static final int MEMORY_CLEAR = 1;
	public static final int MEMORY_PLUS = 2;
	public static final int MEMORY_MINUS = 3;
	
	public static final int SIN = 0;
	public static final int COS = 1;
	public static final int TAN = 2;
	public static final int LN = 3;
	public static final int ABS = 4;
	
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int POINT = 10;
	public static final int E = 11;
	public static final int PI = 12;
	public static final int ANS = 13;
	
	public static final int RECIPROCAL = 0;
	public static final int SQUARE = 1;
	public static final int CUBE = 2;
	public static final int OPEN_BRACKET = 3;
	public static final int CLOSE_BRACKET = 4;
	public static final int SQUARE_ROOT = 5;
	public static final int POWER = 6;
	public static final int NEGATIVE = 7;
	public static final int PLUS = 8;
	public static final int MINUS = 9;
	public static final int MULTIPLY = 10;
	public static final int DIVIDE = 11;
	
	public CalculatorButton() {
		command = "";
		setText("");
		setStyle();
		addMouseListener(new ButtonMouseListener(this));
	}
	
	public CalculatorButton(String text) {
		command = "";
		setText(text);
		setStyle();
		addMouseListener(new ButtonMouseListener(this));
	}
	
	public CalculatorButton(String text, String command) {
		this.command = command;
		setText(text);
		setStyle();
		addMouseListener(new ButtonMouseListener(this));
	}
	
	public CalculatorButton(String text, String command, int type) {
		this.command = command;
		setText(text);
		setStyle();
		addMouseListener(new ButtonMouseListener(this));
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	private void setStyle() {
		setBackground(new Color(32, 32, 32));
		setForeground(new Color(240, 240, 240));
		setFont(new Font("Microsoft Yahei light", Font.PLAIN, 22));
		setBorderPainted(false);
		setFocusPainted(false);
	}
}
