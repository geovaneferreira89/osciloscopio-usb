import java.awt.Color;
import java.awt.GradientPaint;

import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import Testes.GeradorDeFuncoes;
import Testes.Monitor;
import Testes.microControlador;

public class Plotter implements Runnable{

	private XYPlot plot;
	private CombinedDomainXYPlot combinedPlot;
	private Controle controle;
	private XYSeriesCollection collection;
	
	private double posTempoCH1;
	private double posTempoCH2;
	
	public static final int rangePlotter = 5;
	
	private int [] dataCH1;
	private int [] dataCH2;
	
	public Plotter(Controle c){
		
		controle = c;
		collection = new XYSeriesCollection();
		
        XYItemRenderer renderer = new StandardXYItemRenderer();
        NumberAxis rangeAxis = new NumberAxis("Tens�o");
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(-rangePlotter, rangePlotter);
        rangeAxis.setAutoTickUnitSelection(false);
        rangeAxis.centerRange(0);

        plot = new XYPlot(null, null, rangeAxis, renderer);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        
        NumberAxis domainAxis = new NumberAxis("Tempo");
        domainAxis.setAutoRange(false);
        domainAxis.setRange(-rangePlotter, rangePlotter);
        domainAxis.setAutoTickUnitSelection(false);
        domainAxis.centerRange(0);
        
        combinedPlot = new CombinedDomainXYPlot(domainAxis);
        combinedPlot.add(plot);

        configDomainMarker();
        configRangeMarker();
        
        posTempoCH1 = -rangePlotter;
        posTempoCH2 = -rangePlotter;
        
        dataCH1 = new int[microControlador.bufferuC];
        dataCH2 = new int[microControlador.bufferuC];
        
	    collection.addSeries(controle.getCanal1().getSeries());
		plot.setDataset(collection);
	}
	public void atualizaDataCanais(int [] dataCH1, int [] dataCH2){
		this.dataCH1 = dataCH1;
		this.dataCH2 = dataCH2;
	}
	@Override
	public void run() {
		while(true){
			synchronized(Monitor.C_P){
				while(Monitor.C_P.livre){
					try {
						Monitor.C_P.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(controle.getStatusPlotar()){
					atualizaPlotter();
					controle.setStatusPlotar(false);
				}
				Monitor.C_P.livre = true;
				Monitor.C_P.notifyAll();
			}
		}
	}
	public void atualizaPlotter(){
		try{
			for(int i = 0 ; i < microControlador.bufferuC; i++)
			{
				posTempoCH1 = posTempoCH1 + (1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo];
				if(posTempoCH1 > rangePlotter){
					posTempoCH1 = -rangePlotter;
					System.out.println("OI1");
					controle.getCanal1().getSeries().clear();
					System.out.println("OI2");
				}
			}
		}
		catch(java.lang.IndexOutOfBoundsException e){
			System.out.println("PQP");
		}
		catch (java.lang.NullPointerException e ){
			System.out.println("PQP");
		}
		//controle.getFrameProjeto().getChartPanel().repaint();
	}
	public void configDomainMarker(){
		ValueMarker marker;
		marker = new ValueMarker(0);
		marker.setPaint(new GradientPaint(1.0f, 2.0f, Color.black, 4.0f, 4.0f, Color.black));
		plot.addDomainMarker(marker);
	}
	public void configRangeMarker(){
		ValueMarker marker;
		marker = new ValueMarker(0);
		marker.setPaint(new GradientPaint(1.0f, 2.0f, Color.black, 3.0f, 4.0f, Color.black));
		plot.addRangeMarker(marker);
	}
	public void clearDomainMarker(){
		plot.clearDomainMarkers();
	}
	public void clearRangeMarker(){
		plot.clearRangeMarkers();
	}
	public void addDomainMarker(ValueMarker marker){
		plot.addDomainMarker(marker);
	}
	public void addRangeMarker(ValueMarker marker){
		plot.addRangeMarker(marker);
	}
	public XYPlot getPlot(){
		return plot;
	}
	
	public CombinedDomainXYPlot getCombinedPlot(){
		return combinedPlot;
	}
	
}