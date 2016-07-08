package Framework;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import Expression.Function2D;
import Expression.Exception.MathErrorException;
import Expression.Exception.SyntaxErrorException;

public class CoordinatePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	JLabel xPosLabel;
	JLabel yPosLabel;
	
	List<Function2D> functionList;
	int maxFuncNum = 5;
	Color[] funcColors = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK, Color.YELLOW};
	
	double scale = 1.0;
	double[] scaleTimes = {
			1000, 800, 500, 300, 
			100, 80, 50, 30, 
			10, 8, 5, 3, 1.0, 
			0.8, 0.5, 0.3, 0.1, 
			0.08, 0.05, 0.03, 0.01, 
			0.008, 0.005, 0.003, 0.001};
	int scalePos = (scaleTimes.length - 1) / 2;
	int space = 40;
	int width;
	int height;
	int zeroXPos;
	int zeroYPos;
	
	int moveStartX;
	int moveStartY;
	boolean coordinateChanged = false;
	
	DecimalFormat decimalFormat = new DecimalFormat("0.000000");
	
	public CoordinatePanel(JLabel xPosLabel, JLabel yPosLabel) {
		super();
		this.xPosLabel = xPosLabel;
		this.yPosLabel = yPosLabel;
		functionList = new ArrayList<Function2D>();
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}
	
	public void addFunction(String func) throws SyntaxErrorException {
		if (functionList.size() >= maxFuncNum)
			return;
		functionList.add(new Function2D(func));
		repaint();
	}
	
	public void addFunction(String func, boolean Clear) throws SyntaxErrorException {
		functionList.clear();
		functionList.add(new Function2D(func));
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		width = getWidth();
		height = getHeight();
		if (!coordinateChanged) {
			zeroXPos = width / 2;
			zeroYPos = height / 2;
		}
		Graphics2D g_2D = (Graphics2D) g;
		
		Line2D xAxis;
		Line2D yAxis;
		Color gridColor = new Color(194, 194, 194);

		for (double x = zeroXPos; x > 0; x -= space) {
			g_2D.setColor(gridColor);
			yAxis = new Line2D.Double(x, 0, x, height);
			g_2D.draw(yAxis);
		}
		g_2D.setColor(Color.BLACK);
		yAxis = new Line2D.Double(zeroXPos, 0, zeroXPos, height);
		g_2D.draw(yAxis);
		for (double x = zeroXPos + space; x < width; x += space) {
			g_2D.setColor(gridColor);
			yAxis = new Line2D.Double(x, 0, x, height);
			g_2D.draw(yAxis);
		}

		for (double y = zeroYPos; y > 0; y -= space) {
			g_2D.setColor(gridColor);
			xAxis = new Line2D.Double(0, y, width, y);
			g_2D.draw(xAxis);
		}
		g_2D.setColor(Color.BLACK);
		xAxis = new Line2D.Double(0, zeroYPos, width, zeroYPos);
		g_2D.draw(xAxis);
		for (double y = zeroYPos + space; y < height; y += space) {
			g_2D.setColor(gridColor);
			xAxis = new Line2D.Double(0, y, width, y);
			g_2D.draw(xAxis);
		}
		
		if (functionList == null)
			return;
		int index = 0;
		for (Function2D function : functionList) {
			g_2D.setColor(funcColors[index++]);
			int currentXPointPos = 0;
			int currentYPointPos = 0;
			int prevXPointPos = 0;
			int prevYPointPos = 0;
			boolean breakFlag = true;
			for (; currentXPointPos < width; ++currentXPointPos) {
				double xPos = getXPos(currentXPointPos, zeroXPos);
				double yPos;
				try {
					yPos = function.calculateFunction(xPos);
					currentYPointPos = getYPointPos(yPos, zeroYPos);
					if (currentYPointPos < 0 || currentYPointPos > height) {
						breakFlag = true;
						continue;
					}
					if (breakFlag) {
						prevXPointPos = currentXPointPos;
						prevYPointPos = currentYPointPos;
						breakFlag = false;
						continue;
					}
					g_2D.drawLine(prevXPointPos, prevYPointPos, currentXPointPos, currentYPointPos);
					prevXPointPos = currentXPointPos;
					prevYPointPos = currentYPointPos;
					
				} catch (MathErrorException e) {
					breakFlag = true;
					continue;
				} catch (SyntaxErrorException e) {
					return;
				}
			}
		}
	}
	
	class FuncImageFilter extends FileFilter {

		public boolean accept(File f) {
			if (f.getName().endsWith(".png"))
				return true;
			else
				return false;
		}

		public String getDescription() {
			return "PNG Image";
		}
		
	}
	
	public void exportImage() {
		BufferedImage funcImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g_2D = funcImage.createGraphics();
		paint(g_2D);
		g_2D.dispose();
		
		JFileChooser saveFileChooser = new JFileChooser();
		saveFileChooser.setFileFilter(new FuncImageFilter());
		saveFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		int approve = saveFileChooser.showSaveDialog(getParent());
		if (approve == JFileChooser.APPROVE_OPTION) {
			File selectedFile = saveFileChooser.getSelectedFile();
			
			if (selectedFile.exists())
			{
				int ret = JOptionPane.showConfirmDialog(getParent(), "File Existed, do you want to replace it?", "Warning", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.NO_OPTION)
					return;
			}
			try {
				String name = selectedFile.getName();
				if (!name.endsWith(".png")) {
					File newFile = new File(selectedFile.getAbsolutePath() + ".png");
					ImageIO.write(funcImage, "png", newFile);
				}
				else
					ImageIO.write(funcImage, "png", selectedFile);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(getParent(), "Fail to save!");
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		zeroXPos += (x - moveStartX);
		zeroYPos += (y - moveStartY);
		changeLabelPos(e);
		coordinateChanged = true;
		moveStartX = x;
		moveStartY = y;
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		changeLabelPos(e);
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		int rotateNumber = 0 - e.getWheelRotation();
		int nextScalePos = rotateNumber + scalePos;
		if (nextScalePos >= scaleTimes.length) {
			nextScalePos = scaleTimes.length - 1;
		}
		else if(nextScalePos < 0) {
			nextScalePos = 0;
		}
		scale = scaleTimes[nextScalePos];
		scalePos = nextScalePos;
		if (nextScalePos != 0 && nextScalePos != scaleTimes.length - 1) {
			space += rotateNumber * 5;
			if (space > 60 || space < 20) {
				space = 40;
			}
		}
		changeLabelPos(e);
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		setCursor(new Cursor(Cursor.MOVE_CURSOR));
		moveStartX = e.getX();
		moveStartY = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
	private double getXPos(double xPointPos, double zeroXPos) {
		return (xPointPos - zeroXPos) * scale / space;
	}
	
	private double getYPos(double yPointPos, double zeroYPos) {
		return (zeroYPos - yPointPos) * scale / space;
	}
	
	private int getYPointPos(double yPos, double zeroYPos) {
		return (int) (zeroYPos - (yPos * space) / scale);
	}
	
	private void changeLabelPos(MouseEvent e) {
		double xPos = getXPos(e.getX(), zeroXPos);
		double yPos = getYPos(e.getY(), zeroYPos);
		
		xPosLabel.setText("x=" + decimalFormat.format(xPos));
		yPosLabel.setText("y=" + decimalFormat.format(yPos));
	}

}
