package Framework;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Records.File.AnswerIO;
import Records.File.HistoryIO;
import Records.File.MemoryIO;
import Records.File.SystemConst;

public class CalculatorForm extends JFrame {
	
	int WIDTH = 523;
	int HEIGHT = 400;
	
	ExpressionPanel expressionPanel;
	
	public CalculatorForm() {
		MemoryIO.readMemory();
		HistoryIO.readHistory();
		AnswerIO.readAnswer();
		init();
	}
	
	private void init() {
		
		expressionPanel = new ExpressionPanel(this);
		
		add(expressionPanel);
		expressionPanel.setBounds(0, 0, WIDTH, HEIGHT);
		expressionPanel.setPanelSize(WIDTH, HEIGHT);
		expressionPanel.display();

		setBounds(350, 150, WIDTH, HEIGHT);
		setTitle("Calculator");
		setResizable(false);
		setVisible(true);
		
		ImageIcon imageIcon = new ImageIcon(SystemConst.rootDirectory + SystemConst.fileSeparator + "Calculator.png");
		setIconImage(imageIcon.getImage());
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				HistoryIO.writeHistory();
				MemoryIO.writeMemory();
				AnswerIO.writeAnswer();
				
				setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});
	}
}
