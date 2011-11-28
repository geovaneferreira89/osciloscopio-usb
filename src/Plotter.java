import java.awt.Color;
import java.awt.GradientPaint;

import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Testes.*;

public class Plotter implements Runnable{

	private XYPlot plot;
	private Controle controle;
	private XYSeriesCollection collection;
	public static final int rangePlotter = 3;
	
	private Canal ch1;
	private Canal ch2;
	
	public static int tamBufferSeries = 3;
	private XYSeries [] seriesCH1 = new XYSeries[tamBufferSeries];
	private XYSeries [] seriesCH2 = new XYSeries[tamBufferSeries];
	
	private int serieAtualCH1;
	private int serieAtualCH2;

	boolean trigPlotCH1;
	boolean trigPlotCH2;
	
	public Plotter(Controle c){
		controle = c;
		
		ch1 = c.getCanal1();
		ch2 = c.getCanal2();
		
		for(int i = 0;i<tamBufferSeries;i++){
			seriesCH1[i] = new XYSeries(" ");
			seriesCH2[i] = new XYSeries(" ");
		}
        XYItemRenderer renderer = new StandardXYItemRenderer();//SamplingXYLineRenderer();
        
        NumberAxis rangeAxis = new NumberAxis("Tens�o");
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(-rangePlotter, rangePlotter);
        rangeAxis.setAutoTickUnitSelection(false);
        rangeAxis.centerRange(0);
        
        NumberAxis domainAxis = new NumberAxis("Tempo");
        domainAxis.setAutoRange(false);
        domainAxis.setRange(0, 2*rangePlotter);
        domainAxis.setAutoTickUnitSelection(false);
        domainAxis.centerRange(rangePlotter);
        
        collection = new XYSeriesCollection();
        plot = new XYPlot(collection, domainAxis, rangeAxis, renderer);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        
        configDomainMarker();
        configRangeMarker();
        
        serieAtualCH1 = 0;
        serieAtualCH2 = 0;
        
        trigPlotCH1 = false;
        trigPlotCH2 = false;
        
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
					if(!controle.getStop()){
						if(ch1.isEnable()){
							atualizaPlotter(1, seriesCH1, serieAtualCH1, ch1.getPosTempo(), ch1, trigPlotCH1);
						}
						controle.getFrameProjeto().getChartPanel().repaint();
					}
					controle.setStatusPlotar(false);
				}
				
				Monitor.C_P.livre = true;
				Monitor.C_P.notifyAll();
			}
		}
	}
	public void atualizaPlotter(int numCanal, XYSeries series[], int serieAtual, double posTempo, Canal ch, boolean trigPlot){
		double dataOld;
		double posTrigger;
		double dataAtual;
		ch.setSeries(series[serieAtual]);
		//Com buffer.
		if(Canal.escalaTempo<=Canal.realTime){
			for(int i = 0 ; i < microControlador.bufferuC; i++)
			{
				dataOld = Double.MAX_VALUE;
				if(posTempo==0 && controle.getTrigger().isEnable() && controle.getTrigger().getCanal()==ch){
					if(i>1){
						dataOld = Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i-1]) + ch.getOffset();
					}
					posTrigger = controle.getTrigger().getPosicao(); 
					dataAtual = Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i]) + ch.getOffset();
					if(dataAtual>dataOld && posTrigger<=dataAtual && posTrigger>=dataOld){
						trigPlot = true;
						//series[(serieAtual+tamBufferSeries-1) % tamBufferSeries].add(posTempo,Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i]) + ch.getOffset());
						//posTempo = posTempo + (1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo];
					}					
					if(trigPlot && dataAtual > posTrigger+0.2){
						trigPlot = false;
						series[(serieAtual+tamBufferSeries-1) % tamBufferSeries].add(posTempo,Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i]) + ch.getOffset());
						posTempo = posTempo + (1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo];
					}
				}
				else{
					series[(serieAtual+tamBufferSeries-1) % tamBufferSeries].add(posTempo,Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i]) + ch.getOffset());
					posTempo = posTempo + (1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo];
					if(posTempo > 2*rangePlotter+(1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo]){
						
						posTempo = 0;
						collection.removeSeries(series[serieAtual]);
						ch.clear_RMS_FREQ();
						series[serieAtual].clear();
						series[serieAtual].clear();
						series[serieAtual].clear();
						serieAtual = (serieAtual+1)%tamBufferSeries;
						ch.setSeries(series[serieAtual]);
						collection.addSeries(series[serieAtual]);
					}
				}
			}
		}
		//Sem buffer.
		else{
			for(int i = 0 ; i < microControlador.bufferuC; i++){
				dataOld = Double.MAX_VALUE;
				if(posTempo==0 && controle.getTrigger().isEnable() && controle.getTrigger().getCanal()==ch){
					if(i>1){
						dataOld = Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i-1]) + ch.getOffset();
					}
					posTrigger = controle.getTrigger().getPosicao(); 
					dataAtual = Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i]) + ch.getOffset();
					if(dataAtual>dataOld && posTrigger<=dataAtual && posTrigger>=dataOld){
						trigPlot = true;
						//series[(serieAtual+tamBufferSeries-1) % tamBufferSeries].add(posTempo,Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i]) + ch.getOffset());
						//posTempo = posTempo + (1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo];
					}					
					if(trigPlot && dataAtual > posTrigger+0.2){
						trigPlot = false;
						series[(serieAtual) % tamBufferSeries].add(posTempo,Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i]) + ch.getOffset());
						posTempo = posTempo + (1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo];
					}				
				}
				
				else{
					series[serieAtual].add(posTempo,Converter.converteDigitalDouble(ch, ch.getDataComunicacao()[i])+ch.getOffset());
					posTempo = posTempo + (1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo];
					if(posTempo >2*rangePlotter+(1/GeradorDeFuncoes.frequenciaAmostragem)/Canal.seriesEscalaTempo[Canal.escalaTempo]){
						posTempo = 0;
						ch.clear_RMS_FREQ();
						series[serieAtual].clear();
						series[serieAtual].clear();
						series[serieAtual].clear();
						ch.setSeries(series[serieAtual]);
					}
				}
			}
		}
		ch.setPosTempo(posTempo);
		atualizaSerieAtual(numCanal,serieAtual,trigPlot);
		
	}
	public void clearSeriesBuffers(int numSerie){
		switch(numSerie){
		case 1:
			for(int i = 0 ; i<tamBufferSeries ; i++){
				seriesCH1[i].clear();
				ch1.setPosTempo(0);
				ch1.clear_RMS_FREQ();
			}
			break;
		case 2:
			for(int i = 0 ; i<tamBufferSeries ; i++){
				seriesCH2[i].clear();
				ch2.setPosTempo(0);
				ch2.clear_RMS_FREQ();
			}
			break;
		case 3:
			for(int i = 0 ; i<tamBufferSeries ; i++){
				seriesCH1[i].clear();
				seriesCH2[i].clear();
				ch1.setPosTempo(0);
				ch2.setPosTempo(0);
				ch1.clear_RMS_FREQ();
				ch2.clear_RMS_FREQ();
			}
			break;
		}
	}
	public void atualizaSerieAtual(int numCanal, int serieAtual, boolean trigPlot){
		if(numCanal ==1 )
		{
			serieAtualCH1 = serieAtual;
			trigPlotCH1 = trigPlot; 
		}
		else{
			serieAtualCH2 = serieAtual;
			trigPlotCH2 = trigPlot; 
		}
	}
	public void configDomainMarker(){
		ValueMarker marker;
		marker = new ValueMarker(3);
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
}