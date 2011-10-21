import org.jfree.chart.plot.ValueMarker;
import org.jfree.ui.RectangleInsets;

public class Cursor{
	private ValueMarker marker;
	private boolean selecionado;
	public static boolean ativo;
	
	public Cursor(int numCursor){
		marker = new ValueMarker(0);
		marker.setLabelOffset(new RectangleInsets(50,-30,0,0));
		marker.setLabel("Cursor" + numCursor);
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