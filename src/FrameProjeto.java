/**
 * Esse código usa as bibliotecas JCommon, JFreeChart e JGUIFramework, todas
 * sob licença GNU LGPL v2.1. Vide os respectivos arquivos (na pasta "lib")
 * para mais informações.
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import java.awt.Component;

import Testes.*;
import java.text.Format;


@SuppressWarnings("serial")
public class FrameProjeto extends JFrame {
	
	public static final void main(String[] args) throws ParseException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		FrameProjeto fp = new FrameProjeto();
		fp.setVisible(true);
	}
	Controle controle;
	private ChartPanel chartPanel;
	private JLabel lbl_c1_ch1 = new JLabel("0.00 V");
	private JLabel lbl_c1_ch2 = new JLabel("0.00 V");
	private JLabel lbl_c2_ch1 = new JLabel("0.00 V");
	private JLabel lbl_c2_ch2 = new JLabel("0.00 V");
	private JLabel lbl_c12_ch1 = new JLabel("0.00 V");
	private JLabel lbl_c21_ch1;
	private JLabel lbl_c12_ch2 = new JLabel("0.00 V");
	private JLabel lbl_c21_ch2;
	private JLabel lbl_c12_T = new JLabel("0.00 s");
	private JLabel lbl_c21_T;
	
	public FrameProjeto() throws ParseException  {
		
		// Frame Principal
		setTitle("Oscilosc\u00F3pio ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1346,750);
		setResizable(true);
		controle = new Controle(this);
        
		//Paineis
		JPanel contentPane = new JPanel();
		JPanel pnl_Plotter = new JPanel();
		JPanel pnl_CH1 = new JPanel();
		JPanel pnl_CH2 = new JPanel();
		JPanel panel_BT = new JPanel();
		JPanel panel_Cursores = new JPanel();
		JPanel pnl_trigger = new JPanel();
		JPanel panel_singleShot = new JPanel();
		JButton btn_CH1_Mais = new JButton("+");
		JButton btn_CH1_Menos = new JButton("-");
		JButton btn_CH2_Menos = new JButton("-");
		JButton btn_BT_Mais = new JButton("+");
		JButton btn_BT_Menos = new JButton("-");
		JButton btn_CH2_Mais = new JButton("+");
		
		//Labels
		JLabel lbl_UsbStatus = new JLabel("USB: N\u00E3o Conectada");
		JLabel lblEscala_1 = new JLabel("Escala:");
		final JLabel lbl_EscalaCH1 = new JLabel("2 mV / div");
		JLabel lbl_rms1 = new JLabel("Tens\u00E3o RMS:");
		JLabel lbl_pp1 = new JLabel("Tens\u00E3o Pico \u00E0 Pico:");
		JLabel lbl_frq1 = new JLabel("Frequ\u00EAncia:");
		JLabel lbl_RMSCH1 = new JLabel("000 mV");
		JLabel lbl_PPCH1 = new JLabel("000 mV");
		JLabel lbl_FRQCH1 = new JLabel("0000 Hz");
		final JLabel lbl_EscalaCH2 = new JLabel("2 mV / div");
		JLabel label = new JLabel("Tens\u00E3o RMS:");
		JLabel label_1 = new JLabel("Tens\u00E3o Pico \u00E0 Pico:");
		JLabel label_2 = new JLabel("Frequ\u00EAncia:");
		JLabel lbl_FRQCH2 = new JLabel("0000 Hz");
		JLabel lbl_PPCH2 = new JLabel("0000 mV");
		JLabel lbl_RMSCH2 = new JLabel("0000 mV");
		JLabel lbl_Tempo = new JLabel("Tempo");
		final JLabel lbl_EscalaBT = new JLabel();
		JLabel lblEscala = new JLabel("Escala:");
		JLabel lblCursor = new JLabel("Cursor1");
		lblCursor.setForeground(Color.BLUE);
		JLabel lblCursor_1 = new JLabel("Cursor2");
		lblCursor_1.setForeground(Color.RED);
		JLabel lblCursorCursor = new JLabel("Cursor2 - Cursor1");
		JLabel lblCanal = new JLabel("Tensao Canal 1");
		JLabel lblTensaoCanal = new JLabel("Tensao Canal 2");
		JLabel label_5 = new JLabel("Tensao Canal 1");
		JLabel label_7 = new JLabel("Tensao Canal 2");
		JLabel label_11 = new JLabel("Tensao Canal 1");
		JLabel label_13 = new JLabel("Tensao Canal 2");
		JLabel label_15 = new JLabel("Tempo");
		JLabel lblTensao = new JLabel("Posi\u00E7\u00E3o (div):");
		JSeparator separator = new JSeparator();
		lbl_c1_ch2 = new JLabel("0.00 V");
		lbl_c1_ch1 = new JLabel("0.00 V");
		lbl_c2_ch1 = new JLabel("0.00 V");
		lbl_c2_ch2 = new JLabel("0.00 V");
		lbl_c21_ch1 = new JLabel("0.00 V");
		lbl_c21_ch2 = new JLabel("0.00 V");
		lbl_c21_T = new JLabel("0.00 s");
		
		// JRadioButton
		final JRadioButton rdbtn_CH1 = new JRadioButton("On");
		final JRadioButton rdbtn_CH2 = new JRadioButton("On");
		final JRadioButton rdbtn_Cursor1 = new JRadioButton("Cursor 1");
		final JRadioButton rdbtn_Cursor2 = new JRadioButton("Cursor 2");
		final JRadioButton rdbtn_Cursores = new JRadioButton("On");
		final JRadioButton rdbtn_T1 = new JRadioButton("Canal 1");
		final JRadioButton rdbtn_T2 = new JRadioButton("Canal 2");
		final JRadioButton rdbtn_SingleShot = new JRadioButton("On");
		final JRadioButton rdbtn_Trigger = new JRadioButton("On");
		final JRadioButton rdbtn_Stop = new JRadioButton("On");
		
		rdbtn_Trigger.setForeground(Color.BLACK);
		
		rdbtn_T2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.selectCanalTrigger(controle.getCanal2());
			}
		});
		
		rdbtn_T1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.selectCanalTrigger(controle.getCanal1());
			}
		});
		
		
		rdbtn_SingleShot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.setSingleShot(rdbtn_SingleShot.isSelected());
				rdbtn_Stop.setSelected(false);
				controle.setStop(false);
			}
		});
		
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnl_Plotter.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_Plotter.setBounds(10, 11, 990, 692);
		contentPane.add(pnl_Plotter);
		pnl_Plotter.setLayout(null);
		
        JFreeChart chart = new JFreeChart("", null, controle.getPlot(), false);
        
		chartPanel = new ChartPanel(chart);

		chartPanel.setBounds(10, 51, 970, 630);
		pnl_Plotter.add(chartPanel);
		chartPanel.setBackground(Color.GRAY);
		chartPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		chartPanel.setDomainZoomable(false);
		chartPanel.setRangeZoomable(false);
		chartPanel.setHorizontalAxisTrace(false);
	
		
		lbl_UsbStatus.setForeground(Color.RED);
		lbl_UsbStatus.setBounds(730, 11, 100, 16);
		pnl_Plotter.add(lbl_UsbStatus);
		
		//Botoes
		JButton btn_ConectarUSB = new JButton("Iniciar Conex\u00E3o");
		btn_ConectarUSB.setBounds(16, 11, 107, 29);
		pnl_Plotter.add(btn_ConectarUSB);
		btn_ConectarUSB.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0)
			{
				controle.conectarUSB();
				JOptionPane.showMessageDialog(FrameProjeto.this, "Conectando... ");
			}
			
		});
		
		pnl_CH1.setBounds(1010, 168, 310, 145);
		contentPane.add(pnl_CH1);
		pnl_CH1.setBorder(new TitledBorder(null, "Canal 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_CH1.setLayout(null);

		rdbtn_CH1.setSelected(true);
		rdbtn_CH1.setBounds(6, 18, 41, 23);
		pnl_CH1.add(rdbtn_CH1);
		
		btn_CH1_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_EscalaCH1.setText(controle.atualizaEscalaTensao(controle.getCanal1(),1));
				//controle.warnEmb(); Vai depender de algumas condições;
			}
		});
		btn_CH1_Mais.setBounds(100, 43, 40, 14);
		pnl_CH1.add(btn_CH1_Mais);
		
		btn_CH1_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH1.setText(controle.atualizaEscalaTensao(controle.getCanal1(),-1));
				//controle.warnEmb(); Vai depender de algumas coisas;
			}
		});
		btn_CH1_Menos.setBounds(100, 56, 40, 14);
		pnl_CH1.add(btn_CH1_Menos);
		
		lblEscala_1.setBounds(6, 48, 41, 14);
		pnl_CH1.add(lblEscala_1);
		
		lbl_EscalaCH1.setBounds(45, 48, 55, 14);
		pnl_CH1.add(lbl_EscalaCH1);
		
		lbl_rms1.setBounds(208, 18, 63, 14);
		pnl_CH1.add(lbl_rms1);
		
		lbl_pp1.setBounds(208, 61, 92, 14);
		pnl_CH1.add(lbl_pp1);
		
		lbl_frq1.setBounds(208, 102, 57, 14);
		pnl_CH1.add(lbl_frq1);
		
		lbl_RMSCH1.setBounds(208, 32, 46, 14);
		pnl_CH1.add(lbl_RMSCH1);
		
		lbl_PPCH1.setBounds(208, 72, 46, 14);
		pnl_CH1.add(lbl_PPCH1);
		
		lbl_FRQCH1.setBounds(208, 116, 46, 14);
		pnl_CH1.add(lbl_FRQCH1);
		
		pnl_CH2.setBounds(1010, 326, 310, 145);
		contentPane.add(pnl_CH2);
		pnl_CH2.setBorder(new TitledBorder(null, "Canal 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_CH2.setLayout(null);

		rdbtn_CH2.setSelected(true);
		rdbtn_CH2.setBounds(6, 18, 39, 23);
		pnl_CH2.add(rdbtn_CH2);
		
		lblEscala.setBounds(6, 48, 41, 14);
		pnl_CH2.add(lblEscala);
		
		btn_CH2_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH2.setText(controle.atualizaEscalaTensao(controle.getCanal2(),-1));
				//controle.warnEmb(); Vai depender de algumas coisas;
			}
		});
		btn_CH2_Menos.setBounds(100, 56, 40, 14);
		pnl_CH2.add(btn_CH2_Menos);
	
		btn_CH2_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH2.setText(controle.atualizaEscalaTensao(controle.getCanal2(),1));
				//controle.warnEmb(); Vai depender de algumas coisas;
			}
		});
		btn_CH2_Mais.setBounds(100, 43, 40, 14);
		pnl_CH2.add(btn_CH2_Mais);
		
		lbl_EscalaCH2.setBounds(45, 48, 55, 14);
		pnl_CH2.add(lbl_EscalaCH2);
		
		label.setBounds(208, 18, 63, 14);
		pnl_CH2.add(label);
		
		label_1.setBounds(208, 61, 92, 14);
		pnl_CH2.add(label_1);
		
		label_2.setBounds(208, 102, 57, 14);
		pnl_CH2.add(label_2);
		
		lbl_FRQCH2.setBounds(208, 116, 46, 14);
		pnl_CH2.add(lbl_FRQCH2);
		
		lbl_PPCH2.setBounds(208, 72, 46, 14);
		pnl_CH2.add(lbl_PPCH2);
		
		lbl_RMSCH2.setBounds(208, 32, 46, 14);
		pnl_CH2.add(lbl_RMSCH2);
		
		panel_BT.setBorder(new TitledBorder(null, "Base de Tempo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_BT.setBounds(1010, 482, 310, 78);
		contentPane.add(panel_BT);
		panel_BT.setLayout(null);
		
		lbl_Tempo.setBounds(10, 35, 46, 14);
		panel_BT.add(lbl_Tempo);
		
		btn_BT_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_EscalaBT.setText(controle.atualizaEscalaTempo(1));
			}
		});
		btn_BT_Mais.setBounds(110, 30, 40, 14);
		panel_BT.add(btn_BT_Mais);
		
		btn_BT_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaBT.setText(controle.atualizaEscalaTempo(-1));
			}
		});
		btn_BT_Menos.setBounds(110, 43, 40, 14);
		panel_BT.add(btn_BT_Menos);
		
		lbl_EscalaBT.setBounds(56, 35, 54, 14);
		panel_BT.add(lbl_EscalaBT);

		panel_Cursores.setBorder(new TitledBorder(null, "Cursores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Cursores.setBounds(1010, 571, 310, 132);
		contentPane.add(panel_Cursores);
		panel_Cursores.setLayout(null);
		

		rdbtn_Cursor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controle.ativaCursor(1);
			}
		});
		rdbtn_Cursor1.setBounds(58, 17, 73, 23);
		panel_Cursores.add(rdbtn_Cursor1);
		

		rdbtn_Cursor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.ativaCursor(2);
			}
		});
		rdbtn_Cursor2.setBounds(133, 17, 86, 23);
		panel_Cursores.add(rdbtn_Cursor2);
		

		rdbtn_Cursores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controle.selectCursores((rdbtn_Cursores.isSelected()));
				if(rdbtn_Cursores.isSelected()){
					rdbtn_Cursor1.setEnabled(true);
					rdbtn_Cursor2.setEnabled(true);
					rdbtn_Cursor1.setSelected(true);
				}
				else{
					rdbtn_Cursor1.setSelected(false);
					rdbtn_Cursor2.setSelected(false);
					rdbtn_Cursor1.setEnabled(false);
					rdbtn_Cursor2.setEnabled(false);
				}
				
			}
		});
		chartPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtn_Cursores.isSelected())
				{
					controle.atualizaPosCursores(arg0.getX());
				}
			}
		});
		rdbtn_Cursores.setBounds(6, 17, 39, 23);
		panel_Cursores.add(rdbtn_Cursores);
		
		rdbtn_CH2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.setCanal(2, rdbtn_CH1.isSelected());
			}
		});
		rdbtn_CH1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controle.setCanal(1, rdbtn_CH1.isSelected());
			}
		});
		
	
		lblCursor.setBounds(6, 39, 46, 14);
		panel_Cursores.add(lblCursor);
		
		lblCursor_1.setBounds(112, 39, 46, 14);
		panel_Cursores.add(lblCursor_1);
		
		lblCursorCursor.setBounds(210, 39, 94, 14);
		panel_Cursores.add(lblCursorCursor);
		
		lblCanal.setBounds(6, 51, 74, 14);
		panel_Cursores.add(lblCanal);
		
		lblTensaoCanal.setBounds(6, 76, 74, 14);
		panel_Cursores.add(lblTensaoCanal);
		
		lbl_c1_ch2.setBounds(6, 89, 74, 14);
		panel_Cursores.add(lbl_c1_ch2);
		
		lbl_c1_ch1.setBounds(6, 64, 74, 14);
		panel_Cursores.add(lbl_c1_ch1);
		
		separator.setBounds(112, 66, 1, 2);
		panel_Cursores.add(separator);
		
		label_5.setBounds(111, 51, 74, 14);
		panel_Cursores.add(label_5);
		
		lbl_c2_ch1.setBounds(111, 64, 74, 14);
		panel_Cursores.add(lbl_c2_ch1);
		
		label_7.setBounds(111, 76, 74, 14);
		panel_Cursores.add(label_7);
		
		lbl_c2_ch2.setBounds(111, 89, 74, 14);
		panel_Cursores.add(lbl_c2_ch2);
		
		label_11.setBounds(210, 51, 74, 14);
		panel_Cursores.add(label_11);
		
		lbl_c21_ch1.setBounds(210, 64, 74, 14);
		panel_Cursores.add(lbl_c21_ch1);
		
		label_13.setBounds(210, 76, 74, 14);
		panel_Cursores.add(label_13);
		
		lbl_c21_ch2.setBounds(210, 89, 74, 14);
		panel_Cursores.add(lbl_c21_ch2);
		
		label_15.setBounds(210, 103, 46, 14);
		panel_Cursores.add(label_15);
		
		lbl_c21_T.setBounds(210, 114, 74, 14);
		panel_Cursores.add(lbl_c21_T);
		
		pnl_trigger.setBorder(new TitledBorder(null, "Trigger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_trigger.setLayout(null);
		pnl_trigger.setBounds(1010, 11, 310, 94);
		contentPane.add(pnl_trigger);
	
		rdbtn_T1.setSelected(true);
		rdbtn_T1.setBounds(58, 15, 69, 23);
		pnl_trigger.add(rdbtn_T1);
		
		rdbtn_T2.setSelected(true);
		rdbtn_T2.setBounds(129, 15, 69, 23);
		pnl_trigger.add(rdbtn_T2);
		
		
		lblTensao.setBounds(10, 60, 69, 14);
		pnl_trigger.add(lblTensao);
		
		panel_singleShot.setBorder(new TitledBorder(null, "SingleShot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_singleShot.setLayout(null);
		panel_singleShot.setBounds(1113, 116, 93, 41);
		contentPane.add(panel_singleShot);
		

		rdbtn_SingleShot.setSelected(true);
		rdbtn_SingleShot.setBounds(6, 18, 50, 16);
		panel_singleShot.add(rdbtn_SingleShot);
		

		rdbtn_Trigger.setBounds(6, 15, 50, 23);
		rdbtn_Trigger.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				controle.selectTrigger(rdbtn_Trigger.isSelected());
				if(rdbtn_Trigger.isSelected()){
					rdbtn_T1.setEnabled(true);
					rdbtn_T2.setEnabled(true);
				}
				else{
					rdbtn_T1.setEnabled(false);
					rdbtn_T2.setEnabled(false);
				}
			}
			
		});
		pnl_trigger.add(rdbtn_Trigger);
		

		NumberFormat number = NumberFormat.getNumberInstance();
		number.setMinimumFractionDigits(0); 
		final JFormattedTextField ftf_Trigger = new JFormattedTextField(number);
		ftf_Trigger.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyTyped(KeyEvent arg0) {
				verificaDouble(arg0,ftf_Trigger);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(rdbtn_Trigger.isSelected()){
						
						double posicao = Double.parseDouble(ftf_Trigger.getText().replace(',', '.'));
						if(Math.abs(posicao)<Plotter.rangePlotter){
							controle.atualizaPosTrigger(posicao);
						}	
					}
				}
			}
		});
		
		
		ftf_Trigger.setBounds(78, 57, 55, 20);
		pnl_trigger.add(ftf_Trigger);
		
		JButton btn_Trigger_mais = new JButton("+");
		final DecimalFormat aproximador = new DecimalFormat( " 0.00 " );
		btn_Trigger_mais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtn_Trigger.isSelected()){
					controle.atualizaPosTrigger(1);
					ftf_Trigger.setText("" + aproximador.format(controle.getTrigger().getPosicao()));
				}
			}
		});
		btn_Trigger_mais.setAlignmentY(Component.TOP_ALIGNMENT);
		btn_Trigger_mais.setBounds(134, 53, 40, 14);
		pnl_trigger.add(btn_Trigger_mais);
		
		JButton btn_Trigger_menos = new JButton("-");
		btn_Trigger_menos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtn_Trigger.isSelected()){
					controle.atualizaPosTrigger(-1);
					ftf_Trigger.setText("" + aproximador.format(controle.getTrigger().getPosicao()));
				}
			}
		});
		btn_Trigger_menos.setBounds(134, 66, 40, 14);
		pnl_trigger.add(btn_Trigger_menos);
		

		lbl_EscalaBT.setText(Canal.escalaTempoStr[0]);
		lbl_EscalaCH1.setText(Canal.escalaTensaoStr[Canal.seriesEscalaTensao.length-1]);
		
		
		final JFormattedTextField ftf_offsetch1 = new JFormattedTextField((Format) null);
		ftf_offsetch1.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent arg0) {
				verificaDouble(arg0,ftf_offsetch1);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(rdbtn_CH1.isSelected()){
						
						double posicao = Double.parseDouble(ftf_offsetch1.getText().replace(',', '.'));
						if(Math.abs(posicao)<Plotter.rangePlotter){
							controle.atualizaPosicaoOffset(controle.getCanal1(),posicao);
						}	
					}
				}
			}
		});
		ftf_offsetch1.setBounds(78, 110, 55, 20);
		pnl_CH1.add(ftf_offsetch1);
		
		JButton btn_offsetCH1_mais = new JButton("+");
		btn_offsetCH1_mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtn_CH1.isSelected()){
					controle.atualizaPosicaoOffset(controle.getCanal1(), 1);
					ftf_offsetch1.setText("" + aproximador.format(controle.getCanal1().getOffset()));
				}
			}
		});
		btn_offsetCH1_mais.setAlignmentY(0.0f);
		btn_offsetCH1_mais.setBounds(134, 107, 40, 14);
		pnl_CH1.add(btn_offsetCH1_mais);
		
		JButton btn_offsetCH1_menos = new JButton("-");
		btn_offsetCH1_menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtn_CH1.isSelected()){
					controle.atualizaPosicaoOffset(controle.getCanal1(), -1);
					ftf_offsetch1.setText("" + aproximador.format(controle.getCanal1().getOffset()));
				}
			}
		});
		btn_offsetCH1_menos.setBounds(134, 120, 40, 14);
		pnl_CH1.add(btn_offsetCH1_menos);
		
		JLabel lblPosicao = new JLabel("Posi\u00E7\u00E3o (div):");
		lblPosicao.setBounds(6, 113, 69, 14);
		pnl_CH1.add(lblPosicao);
		
		JLabel lblOffset = new JLabel("OffSet");
		lblOffset.setBounds(6, 88, 46, 14);
		pnl_CH1.add(lblOffset);
		lbl_EscalaCH2.setText(Canal.escalaTensaoStr[Canal.seriesEscalaTensao.length-1]);
		
		JLabel lblOffset2 = new JLabel("OffSet");
		lblOffset2.setBounds(6, 88, 46, 14);
		pnl_CH2.add(lblOffset2);
		
		JLabel lblPosicao2 = new JLabel("Posi\u00E7\u00E3o (div):");
		lblPosicao2.setBounds(6, 113, 69, 14);
		pnl_CH2.add(lblPosicao2);
		
		final JFormattedTextField ftf_offsetch2 = new JFormattedTextField((Format) null);
		ftf_offsetch2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				verificaDouble(arg0,ftf_offsetch2);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(rdbtn_CH2.isSelected()){
						
						double posicao = Double.parseDouble(ftf_offsetch2.getText().replace(',', '.'));
						if(Math.abs(posicao)<Plotter.rangePlotter){
							controle.atualizaPosicaoOffset(controle.getCanal2(),posicao);
						}	
					}
				}
			}
		});
		ftf_offsetch2.setBounds(78, 110, 55, 20);
		pnl_CH2.add(ftf_offsetch2);
		
		JButton btn_offsetCH2_mais = new JButton("+");
		btn_offsetCH2_mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtn_CH2.isSelected()){
					controle.atualizaPosicaoOffset(controle.getCanal2(), 1);
					ftf_offsetch2.setText("" + aproximador.format(controle.getCanal2().getOffset()));
				}
			}
		});
		btn_offsetCH2_mais.setAlignmentY(0.0f);
		btn_offsetCH2_mais.setBounds(134, 107, 40, 14);
		pnl_CH2.add(btn_offsetCH2_mais);
		
		JButton btn_offsetCH2_menos = new JButton("-");
		btn_offsetCH2_menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtn_CH2.isSelected()){
					controle.atualizaPosicaoOffset(controle.getCanal2(), -1);
					ftf_offsetch2.setText("" + aproximador.format(controle.getCanal2().getOffset()));
				}
			}
		});
		btn_offsetCH2_menos.setBounds(134, 120, 40, 14);
		pnl_CH2.add(btn_offsetCH2_menos);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Stop", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		panel.setBounds(1010, 116, 93, 41);
		contentPane.add(panel);
		
		rdbtn_Stop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.setStop(rdbtn_Stop.isSelected());
				rdbtn_SingleShot.setSelected(false);
				controle.setSingleShot(false);
			}
		});
		rdbtn_Stop.setSelected(true);
		rdbtn_Stop.setBounds(6, 18, 50, 16);
		panel.add(rdbtn_Stop);
		
	    //Configuração de início
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtn_Cursor1);
		grupo.add(rdbtn_Cursor2);
		rdbtn_Cursor1.setEnabled(false);
		rdbtn_Cursor2.setEnabled(false);
		ButtonGroup grupo2 = new ButtonGroup();
		grupo2.add(rdbtn_T1);
		grupo2.add(rdbtn_T2);
		rdbtn_SingleShot.setSelected(false);
		rdbtn_Stop.setSelected(false);
		rdbtn_T1.setEnabled(false);
		rdbtn_T2.setEnabled(false);
		rdbtn_Trigger.setSelected(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "AntAliasing", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(1216, 116, 104, 41);
		contentPane.add(panel_1);
		final JRadioButton rdbtn_AntAliasing = new JRadioButton("On");
		rdbtn_AntAliasing.setBounds(6, 18, 50, 16);
		panel_1.add(rdbtn_AntAliasing);
		
		
		rdbtn_AntAliasing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controle.setAntAliasing(rdbtn_AntAliasing.isSelected());
				controle.warnEmb();
			}
		});
		

		rdbtn_AntAliasing.setSelected(true);
		rdbtn_AntAliasing.setSelected(false);
		controle.startAll();
		
		
	}
	public ChartPanel getChartPanel(){
		return chartPanel;
	}
	public void verificaDouble(KeyEvent arg0, JFormattedTextField ftf){
		if(arg0.getKeyChar() == '.')
		{
			arg0.setKeyChar(',');
		}
		else if(arg0.getKeyChar()=='-'){
			if(!ftf.getText().contains("-")){
				ftf.setText("-"+ftf.getText());
			}
			arg0.consume();
		}
		else if(arg0.getKeyChar()>57||arg0.getKeyChar()<48){
				arg0.consume();
		}
	}
	public void atualizaCursores(String [] data){
		lbl_c1_ch1.setText(data[0]);
		lbl_c1_ch2.setText(data[1]);
		lbl_c2_ch1.setText(data[2]);
		lbl_c2_ch2.setText(data[3]);
		lbl_c21_ch1.setText(data[4]);
		lbl_c21_ch2.setText(data[5]);
		lbl_c21_T.setText(data[6]);
	}
}
