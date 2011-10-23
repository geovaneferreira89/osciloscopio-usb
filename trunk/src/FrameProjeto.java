/**
 * Esse c�digo usa as bibliotecas JCommon, JFreeChart e JGUIFramework, todas
 * sob licen�a GNU LGPL v2.1. Vide os respectivos arquivos (na pasta "lib")
 * para mais informa��es.
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

	

	public FrameProjeto() throws ParseException  {
		
		// Frame Principal
		setTitle("Oscilosc\u00F3pio ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100,702);
		setResizable(true);
		controle = new Controle(this);
        
		//Paineis
		JPanel contentPane = new JPanel();
		JPanel pnl_Opcoes = new JPanel();
		JPanel pnl_Plotter = new JPanel();
		JPanel pnl_CH1 = new JPanel();
		JPanel pnl_CH2 = new JPanel();
		JPanel panel_BT = new JPanel();
		JPanel panel_Cursores = new JPanel();
		JPanel pnl_trigger = new JPanel();
		JPanel panel_singleShot = new JPanel();	
		
		//Botoes
		JButton btn_ConectarUSB = new JButton("Iniciar Conex\u00E3o");
		JButton btn_Teste = new JButton("Teste");
		JButton btn_CH1_Mais = new JButton("+");
		JButton btn_CH1_Menos = new JButton("-");
		JButton btn_CH2_Menos = new JButton("-");
		JButton btn_BT_Mais = new JButton("+");
		JButton btn_BT_Menos = new JButton("-");
		JButton btn_CH2_Mais = new JButton("+");
		
		//Labels
		JLabel lbl_UsbStatus = new JLabel("USB: N\u00E3o Conectada");
		JLabel lblEscala_1 = new JLabel("Escala");
		final JLabel lbl_EscalaCH1 = new JLabel("2 mV / div");
		JLabel lbl_rms1 = new JLabel("Tens\u00E3o RMS:");
		JLabel lbl_pp1 = new JLabel("Tens\u00E3o Pico \u00E0 Pico:");
		JLabel lbl_frq1 = new JLabel("Frequ\u00EAncia:");
		JLabel lbl_RMSCH1 = new JLabel("0000 mV");
		JLabel lbl_PPCH1 = new JLabel("0000 mV");
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
		JLabel lblEscala = new JLabel("Escala");
		JLabel lblCursor = new JLabel("Cursor1");
		lblCursor.setForeground(Color.BLUE);
		JLabel lblCursor_1 = new JLabel("Cursor2");
		lblCursor_1.setForeground(Color.RED);
		JLabel lblCursorCursor = new JLabel("Cursor2 - Cursor1");
		JLabel lblCanal = new JLabel("Tensao Canal 1");
		JLabel lblTensaoCanal = new JLabel("Tensao Canal 2");
		JLabel lblV = new JLabel("0.00 V");
		JLabel label_4 = new JLabel("0.00 V");
		JLabel label_5 = new JLabel("Tensao Canal 1");
		JLabel label_6 = new JLabel("0.00 V");
		JLabel label_7 = new JLabel("Tensao Canal 2");
		JLabel label_8 = new JLabel("0.00 V");
		JLabel label_11 = new JLabel("Tensao Canal 1");
		JLabel label_12 = new JLabel("0.00 V");
		JLabel label_13 = new JLabel("Tensao Canal 2");
		JLabel label_14 = new JLabel("0.00 V");
		JLabel label_15 = new JLabel("Tempo");
		JLabel label_16 = new JLabel("0.00 s");
		JLabel lblTensao = new JLabel("Posi\u00E7\u00E3o (div.):");
		JSeparator separator = new JSeparator();
		
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
		rdbtn_Trigger.setForeground(Color.BLACK);
		
		rdbtn_T2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.selectCanalTrigger(1);
			}
		});
		
		rdbtn_T1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.selectCanalTrigger(1);
			}
		});
		
		
		rdbtn_SingleShot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.setSingleShot(rdbtn_SingleShot.isSelected());
			}
		});
		
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		pnl_Opcoes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Op\u00E7\u00F5es ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_Opcoes.setBounds(760, 8, 314, 71);
		contentPane.add(pnl_Opcoes);
		pnl_Opcoes.setLayout(null);
		
		btn_ConectarUSB.setBounds(17, 25, 107, 29);
		pnl_Opcoes.add(btn_ConectarUSB);
		
		pnl_Plotter.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnl_Plotter.setBounds(6, 8, 744, 585);
		contentPane.add(pnl_Plotter);
		pnl_Plotter.setLayout(null);
		
        JFreeChart chart = new JFreeChart("", null, controle.getCombinedPlot(), false);
		chartPanel = new ChartPanel(chart);

		chartPanel.setBounds(16, 40, 680, 534);
		pnl_Plotter.add(chartPanel);
		chartPanel.setBackground(Color.GRAY);
		chartPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		chartPanel.setDomainZoomable(false);
		chartPanel.setRangeZoomable(false);
		chartPanel.setHorizontalAxisTrace(false);
	
		
		lbl_UsbStatus.setForeground(Color.RED);
		lbl_UsbStatus.setBounds(16, 20, 131, 16);
		pnl_Plotter.add(lbl_UsbStatus);
		
	
		btn_Teste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(kk);
			}
		});
		
		btn_Teste.setBounds(578, 14, 74, 28);
		pnl_Plotter.add(btn_Teste);
		btn_Teste.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		 

			}
		});
		
		pnl_CH1.setBounds(760, 195, 314, 94);
		contentPane.add(pnl_CH1);
		pnl_CH1.setBorder(new TitledBorder(null, "Canal 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_CH1.setLayout(null);

		rdbtn_CH1.setSelected(true);
		rdbtn_CH1.setBounds(6, 18, 41, 23);
		pnl_CH1.add(rdbtn_CH1);
		
		btn_CH1_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_EscalaCH1.setText(controle.atualizaEscalaTensao(1,1));
				//controle.warnEmb(); Vai depender de algumas coisas;
			}
		});
		btn_CH1_Mais.setBounds(110, 43, 40, 14);
		pnl_CH1.add(btn_CH1_Mais);
		
		btn_CH1_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH1.setText(controle.atualizaEscalaTensao(1,-1));
				//controle.warnEmb(); Vai depender de algumas coisas;
			}
		});
		btn_CH1_Menos.setBounds(110, 56, 40, 14);
		pnl_CH1.add(btn_CH1_Menos);
		
		lblEscala_1.setBounds(16, 48, 41, 14);
		pnl_CH1.add(lblEscala_1);
		
		lbl_EscalaCH1.setBounds(54, 48, 55, 14);
		pnl_CH1.add(lbl_EscalaCH1);
		
		lbl_rms1.setBounds(170, 27, 63, 14);
		pnl_CH1.add(lbl_rms1);
		
		lbl_pp1.setBounds(170, 48, 92, 14);
		pnl_CH1.add(lbl_pp1);
		
		lbl_frq1.setBounds(170, 69, 57, 14);
		pnl_CH1.add(lbl_frq1);
		
		lbl_RMSCH1.setBounds(236, 27, 46, 14);
		pnl_CH1.add(lbl_RMSCH1);
		
		lbl_PPCH1.setBounds(264, 48, 46, 14);
		pnl_CH1.add(lbl_PPCH1);
		
		lbl_FRQCH1.setBounds(227, 69, 46, 14);
		pnl_CH1.add(lbl_FRQCH1);
		
		pnl_CH2.setBounds(760, 292, 314, 94);
		contentPane.add(pnl_CH2);
		pnl_CH2.setBorder(new TitledBorder(null, "Canal 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_CH2.setLayout(null);

		rdbtn_CH2.setSelected(true);
		rdbtn_CH2.setBounds(6, 18, 39, 23);
		pnl_CH2.add(rdbtn_CH2);
		
		lblEscala.setBounds(16, 46, 41, 16);
		pnl_CH2.add(lblEscala);
		
		btn_CH2_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH2.setText(controle.atualizaEscalaTensao(2,-1));
				//controle.warnEmb(); Vai depender de algumas coisas;
			}
		});
		btn_CH2_Menos.setBounds(110, 56, 40, 14);
		pnl_CH2.add(btn_CH2_Menos);
	
		btn_CH2_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH2.setText(controle.atualizaEscalaTensao(2,1));
				//controle.warnEmb(); Vai depender de algumas coisas;
			}
		});
		btn_CH2_Mais.setBounds(110, 43, 40, 14);
		pnl_CH2.add(btn_CH2_Mais);
		
		lbl_EscalaCH2.setBounds(56, 48, 55, 14);
		pnl_CH2.add(lbl_EscalaCH2);
		
		label.setBounds(168, 22, 63, 14);
		pnl_CH2.add(label);
		
		label_1.setBounds(168, 43, 92, 14);
		pnl_CH2.add(label_1);
		
		label_2.setBounds(168, 64, 57, 14);
		pnl_CH2.add(label_2);
		
		lbl_FRQCH2.setBounds(225, 64, 46, 14);
		pnl_CH2.add(lbl_FRQCH2);
		
		lbl_PPCH2.setBounds(262, 43, 46, 14);
		pnl_CH2.add(lbl_PPCH2);
		
		lbl_RMSCH2.setBounds(234, 22, 46, 14);
		pnl_CH2.add(lbl_RMSCH2);
		
		panel_BT.setBorder(new TitledBorder(null, "Base de Tempo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_BT.setBounds(760, 397, 314, 78);
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
		panel_Cursores.setBounds(760, 476, 314, 163);
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
		btn_ConectarUSB.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0)
			{
				controle.conectarUSB();
				JOptionPane.showMessageDialog(FrameProjeto.this, "Conectando... ");
			}
			
		});
		
	
		lblCursor.setBounds(6, 39, 46, 14);
		panel_Cursores.add(lblCursor);
		
		lblCursor_1.setBounds(112, 39, 46, 14);
		panel_Cursores.add(lblCursor_1);
		
		lblCursorCursor.setBounds(214, 39, 94, 14);
		panel_Cursores.add(lblCursorCursor);
		
		lblCanal.setBounds(6, 51, 74, 14);
		panel_Cursores.add(lblCanal);
		
		lblTensaoCanal.setBounds(6, 76, 74, 14);
		panel_Cursores.add(lblTensaoCanal);
		
		lblV.setBounds(6, 89, 46, 14);
		panel_Cursores.add(lblV);
		
		label_4.setBounds(6, 64, 46, 14);
		panel_Cursores.add(label_4);
		
		separator.setBounds(112, 66, 1, 2);
		panel_Cursores.add(separator);
		
		label_5.setBounds(111, 51, 74, 14);
		panel_Cursores.add(label_5);
		
		label_6.setBounds(111, 64, 46, 14);
		panel_Cursores.add(label_6);
		
		label_7.setBounds(111, 76, 74, 14);
		panel_Cursores.add(label_7);
		
		label_8.setBounds(111, 89, 46, 14);
		panel_Cursores.add(label_8);
		
		label_11.setBounds(214, 64, 74, 14);
		panel_Cursores.add(label_11);
		
		label_12.setBounds(214, 77, 46, 14);
		panel_Cursores.add(label_12);
		
		label_13.setBounds(214, 89, 74, 14);
		panel_Cursores.add(label_13);
		
		label_14.setBounds(214, 102, 46, 14);
		panel_Cursores.add(label_14);
		
		label_15.setBounds(214, 123, 74, 14);
		panel_Cursores.add(label_15);
		
		label_16.setBounds(214, 136, 46, 14);
		panel_Cursores.add(label_16);
		
		pnl_trigger.setBorder(new TitledBorder(null, "Trigger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_trigger.setLayout(null);
		pnl_trigger.setBounds(760, 90, 229, 94);
		contentPane.add(pnl_trigger);
	
		rdbtn_T1.setSelected(true);
		rdbtn_T1.setBounds(58, 15, 69, 23);
		pnl_trigger.add(rdbtn_T1);
		
		rdbtn_T2.setSelected(true);
		rdbtn_T2.setBounds(129, 15, 69, 23);
		pnl_trigger.add(rdbtn_T2);
		
		
		lblTensao.setBounds(10, 60, 76, 14);
		pnl_trigger.add(lblTensao);
		
		panel_singleShot.setBorder(new TitledBorder(null, "SingleShot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_singleShot.setLayout(null);
		panel_singleShot.setBounds(999, 90, 75, 41);
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
		number.setMinimumFractionDigits(1); 
		final JFormattedTextField ftf_Trigger = new JFormattedTextField(number);
		ftf_Trigger.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar() == '.')
				{
					arg0.setKeyChar(',');
				}

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
		
		
		ftf_Trigger.setBounds(82, 57, 67, 20);
		pnl_trigger.add(ftf_Trigger);
		
		
		// Configura��o de in�cio
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtn_Cursor1);
		grupo.add(rdbtn_Cursor2);
		rdbtn_Cursor1.setEnabled(false);
		rdbtn_Cursor2.setEnabled(false);
		ButtonGroup grupo2 = new ButtonGroup();
		grupo2.add(rdbtn_T1);
		grupo2.add(rdbtn_T2);
		rdbtn_T1.setEnabled(false);
		rdbtn_T2.setEnabled(false);
		rdbtn_Trigger.setSelected(false);
		
		JButton button = new JButton("+");
		final DecimalFormat aproximador = new DecimalFormat( " 0.00 " );
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.atualizaPosTrigger(1);
				
				ftf_Trigger.setText("" + aproximador.format(controle.getTrigger().getPosicao()));
			}
		});
		button.setAlignmentY(Component.TOP_ALIGNMENT);
		button.setBounds(150, 54, 40, 14);
		pnl_trigger.add(button);
		
		JButton button_1 = new JButton("-");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.atualizaPosTrigger(-1);
				ftf_Trigger.setText("" + aproximador.format(controle.getTrigger().getPosicao()));
			}
		});
		button_1.setBounds(150, 67, 40, 14);
		pnl_trigger.add(button_1);
		

		lbl_EscalaBT.setText(Canal.escalaTempoStr[0]);
		lbl_EscalaCH1.setText(Canal.escalaTensaoStr[0]);
		final JRadioButton rdbtn_AntAliasingCH1 = new JRadioButton("Ant-Aliasing");
		rdbtn_AntAliasingCH1.setBounds(49, 18, 100, 23);
		pnl_CH1.add(rdbtn_AntAliasingCH1);
		
		
		rdbtn_AntAliasingCH1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controle.setAntAliasing(rdbtn_AntAliasingCH1.isSelected());
				controle.warnEmb();
			}
		});
		

		rdbtn_AntAliasingCH1.setSelected(true);
		lbl_EscalaCH2.setText(Canal.escalaTensaoStr[0]);
		
		JRadioButton rdbtn_AntAliasingCH2 = new JRadioButton("Ant-Aliasing");
		rdbtn_AntAliasingCH2.setSelected(true);
		rdbtn_AntAliasingCH2.setBounds(49, 18, 100, 23);
		pnl_CH2.add(rdbtn_AntAliasingCH2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Stop", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		panel.setBounds(999, 142, 75, 41);
		contentPane.add(panel);
		
		JRadioButton radioButton = new JRadioButton("On");
		radioButton.setSelected(true);
		radioButton.setBounds(6, 18, 50, 16);
		panel.add(radioButton);
		
		controle.startAll();
	}
	public ChartPanel getChartPanel(){
		return chartPanel;
	}
}
