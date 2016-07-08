package Records;

public class Memory {
	
	double memory;
	
	public Memory() {
		memory = 0.0;
	}
	
	public void plus(double d) {
		memory += d;
	}
	
	public void minus(double d) {
		memory -= d;
	}
	
	public void clear() {
		memory = 0.0;
	}
	
	public double read() {
		return memory;
	}
	
	public void setMemory(double memory) {
		this.memory = memory;
	}
}
