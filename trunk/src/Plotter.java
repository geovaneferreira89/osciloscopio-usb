import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

public class Plotter implements Runnable{

	private XYPlot plot;
	private CombinedDomainXYPlot combinedPlot;
	private Controle controle;
	private XYSeriesCollection collection;
	
	public Plotter(Controle c){
		controle = c;
        XYItemRenderer renderer = new StandardXYItemRenderer();
        NumberAxis rangeAxis = new NumberAxis("Tensão");
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(-5, 5);
        rangeAxis.setAutoTickUnitSelection(false);
        rangeAxis.centerRange(0);
        
        //Verificar o dataset dessa inicialização.
        plot = new XYPlot(null, null, rangeAxis, renderer);
     
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        
        NumberAxis domainAxis = new NumberAxis("Tempo");
        domainAxis.setAutoRange(false);
        domainAxis.setRange(-5, 5);
        domainAxis.setAutoTickUnitSelection(false);
        domainAxis.centerRange(0);
        
        combinedPlot = new CombinedDomainXYPlot(domainAxis);
        combinedPlot.add(plot);
	}
	
	@Override
	public void run() {
		
		//collection.addSeries(seriesCH1);
	    //collection.addSeries(seriesCH2);
		//plotter.setDataset(collection);
		while(true){
			atualizaGrafico();

			
			controle.getFrameProjeto().getChartPanel().repaint();
	        try{ 
	            Thread.sleep(1);
	         } catch( InterruptedException e ) {
	             System.out.println("Interrupted Exception caught");
	         }

		}
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