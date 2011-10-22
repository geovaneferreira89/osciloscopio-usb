import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.plot.ValueMarker;
import org.jfree.ui.RectangleInsets;

public class Cursor{
	private ValueMarker marker;
	private boolean selecionado;
	public static boolean ativo;
	RectangleInsets ri;
	
	public Cursor(int numCursor){
		
		marker = new ValueMarker(0);
		if(numCursor == 2){
			//ri = new RectangleInsets(80,30,0,0);
			marker.setPaint(new GradientPaint(1.0f, 2.0f, Color.green, 3.0f, 4.0f, Color.red));
		}
		else{
			//ri = new RectangleInsets(50,-30,0,0);
			marker.setPaint(new GradientPaint(1.0f, 2.0f, Color.blue, 3.0f, 4.0f, Color.blue));
		}
		//marker.setLabelOffset(ri);
		//marker.setLabel("Cursor" + numCursor);
		ativo = false;
	}
	
	public void setPosicao(double posicao){
		marker.setValue(posicao);
	}
	
	public double getPosicao(){
		return marker.getValue();
	}
	public double[] getDados(Canal ch1, Canal ch2){
		return null;
	}
	
	public void select (boolean ativo){
		this.selecionado = ativo;
	}
	
	public boolean isEnable(){
		return selecionado;
	}
	
	public ValueMarker getValueMarker(){
		return marker;
	}
	
}