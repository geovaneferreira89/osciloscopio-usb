import java.awt.Color;
import java.awt.GradientPaint;

import org.jfree.chart.plot.ValueMarker;
import org.jfree.ui.RectangleInsets;

public class Trigger{
	
	private boolean ativo;
	private ValueMarker marker;
	private Canal ch;
	
	public Trigger(boolean ativo,Canal ch){
		
		marker = new ValueMarker(0);
		marker.setLabelOffset(new RectangleInsets(0,20,0,0));
		marker.setPaint(new GradientPaint(1.0f, 2.0f, Color.green, 3.0f, 4.0f, Color.green));
		marker.setLabel("Trigger");
		this.ativo = ativo;
		this.ch = ch;
	}
	
	public void setCanal(Canal ch){
		this.ch = ch;
	}
	public Canal getCanal(){
		return ch;
	}
	
	public void setPosicao(double posicao){
		marker.setValue(posicao);
	}
	
	public double getPosicao(){
		return marker.getValue();
	}
	
	public void select(boolean ativo){
		this.ativo = ativo;
	}
	
	public boolean isEnable(){
		return ativo;
	}
	
	public ValueMarker getValueMarker(){
		return marker;
	}
}