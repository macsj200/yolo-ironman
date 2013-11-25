package gui;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class MonteCarloGui extends JFrame{
	private static MonteCarloGui instance = null;
	
	private CanvasPanel canvasPanel = null;
	
	public CanvasPanel getCanvasPanel() {
		return canvasPanel;
	}

	public void setCanvasPanel(CanvasPanel canvasPanel) {
		this.canvasPanel = canvasPanel;
	}

	public static MonteCarloGui getGuiInstance(){
		if(instance == null){
			instance = new MonteCarloGui();
		}
		return instance;
	}
	
	private MonteCarloGui(){
		setLayout(new FlowLayout());
		addAndInitComponents();
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addAndInitComponents() {
		canvasPanel = new CanvasPanel();
		addToContentPane(canvasPanel);
	}

	private void addToContentPane(Component c){
		getContentPane().add(c);
	}
}
