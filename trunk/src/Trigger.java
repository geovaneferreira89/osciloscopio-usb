import org.jfree.chart.plot.ValueMarker;
import org.jfree.ui.RectangleInsets;

public class Trigger{
	
	private boolean ativo;
	private ValueMarker marker;
	private Canal ch;
	
	public Trigger(boolean ativo){
		marker = new ValueMarker(0);
		marker.setLabelOffset(new RectangleInsets(0,20,0,0));
		marker.setLabel("Trigger");
		this.ativo = ativo;
	}
	
	public void config(Canal ch){
		this.ch = ch;
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