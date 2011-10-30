import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.plot.ValueMarker;
import org.jfree.ui.RectangleInsets;

public class Cursor{
	private ValueMarker marker;
	private boolean selecionado;
	public static boolean ativo;
	
	public Cursor(int numCursor){
		
		marker = new ValueMarker(0);
		if(numCursor == 2){
			marker.setPaint(new GradientPaint(1.0f, 2.0f, Color.green, 3.0f, 4.0f, Color.red));
		}
		else{
			marker.setPaint(new GradientPaint(1.0f, 2.0f, Color.blue, 3.0f, 4.0f, Color.blue));
		}
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