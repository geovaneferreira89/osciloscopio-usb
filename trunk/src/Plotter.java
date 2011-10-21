import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;

public class Plotter implements Runnable{

	private XYPlot plot;
	private CombinedDomainXYPlot combinedPlot;
	private Controle control;
	
	public Plotter(Controle c){
		control = c;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	private void atualizaGrafico(){
		
	}
	public XYPlot getPlot(){
		return plot;
	}
	public CombinedDomainXYPlot getCombinedPlot(){
		return combinedPlot;
	}
	
}