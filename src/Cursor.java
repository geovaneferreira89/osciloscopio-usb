import org.jfree.chart.plot.ValueMarker;

public class Cursor{
	private ValueMarker marker;
	private boolean ativo;
	public Cursor(){
		
	}
	public void setPoicao(double posicao){
		
	}
	public double getPosicao(){
		return marker.getValue();
	}
	public double[] getDados(Canal ch1, Canal ch2){
		return null;
	}
	public void select (boolean ativo){
		this.ativo = ativo;
	}
	public boolean isEnable(){
		return ativo;
	}
	public ValueMarker getValueMarker(){
		return marker;
	}
	
}