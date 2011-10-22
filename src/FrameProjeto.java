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
		JPanel panel_AntAliasing = new JPanel();
		
		//Botoes
		JButton btn_ConectarUSB = new JButton("Iniciar Conex\u00E3o");
		JButton btn_Teste = new JButton("Teste");
		JButton btn_CH1_Mais = new JButton("+");
		JButton btn_CH1_Menos = new JButton("-");
		JButton btn_CH2_Menos = new JButton("-");
		JButton btn_BT_Mais = new JButton("+");
		JButton btn_BT_Menos = new JButton("-");
		JButton btn_CH2_Mais = new JButton("+");
		JButton btn_trigger = new JButton("OK");
		
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
		JLabel lblCursor_1 = new JLabel("Cursor2");
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
		final JRadioButton rdbtn_AntAliasing = new JRadioButton("On");
		final JRadioButton rdbtn_Trigger = new JRadioButton("On");
		
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
		
		
		rdbtn_AntAliasing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controle.setAntAliasing(rdbtn_AntAliasing.isSelected());
			}
		});
		
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		pnl_Opcoes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Op\u00E7\u00F5es ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_Opcoes.setBounds(760, 8, 314, 78);
		contentPane.add(pnl_Opcoes);
		pnl_Opcoes.setLayout(null);
		
		btn_ConectarUSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
		
		pnl_CH1.setBounds(760, 215, 314, 94);
		contentPane.add(pnl_CH1);
		pnl_CH1.setBorder(new TitledBorder(null, "Canal 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_CH1.setLayout(null);

		rdbtn_CH1.setSelected(true);
		rdbtn_CH1.setBounds(6, 18, 50, 23);
		pnl_CH1.add(rdbtn_CH1);
		
		btn_CH1_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_EscalaCH1.setText(controle.atualizaEscalaTensao(1,1));
			}
		});
		btn_CH1_Mais.setBounds(119, 34, 41, 23);
		pnl_CH1.add(btn_CH1_Mais);
		
		btn_CH1_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH1.setText(controle.atualizaEscalaTensao(1,-1));
			}
		});
		btn_CH1_Menos.setBounds(119, 60, 41, 23);
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
		
		pnl_CH2.setBounds(760, 312, 314, 94);
		contentPane.add(pnl_CH2);
		pnl_CH2.setBorder(new TitledBorder(null, "Canal 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_CH2.setLayout(null);

		rdbtn_CH2.setSelected(true);
		rdbtn_CH2.setBounds(6, 18, 50, 23);
		pnl_CH2.add(rdbtn_CH2);
		
		lblEscala.setBounds(16, 46, 41, 16);
		pnl_CH2.add(lblEscala);
		
		btn_CH2_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH2.setText(controle.atualizaEscalaTensao(2,-1));
			}
		});
		btn_CH2_Menos.setBounds(121, 55, 41, 23);
		pnl_CH2.add(btn_CH2_Menos);
	
		btn_CH2_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH2.setText(controle.atualizaEscalaTensao(2,1));
			}
		});
		btn_CH2_Mais.setBounds(121, 29, 41, 23);
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
		panel_BT.setBounds(760, 417, 314, 78);
		contentPane.add(panel_BT);
		panel_BT.setLayout(null);
		
		lbl_Tempo.setBounds(10, 35, 46, 14);
		panel_BT.add(lbl_Tempo);
		
		btn_BT_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_EscalaBT.setText(controle.atualizaEscalaTempo(1));
			}
		});
		btn_BT_Mais.setBounds(121, 18, 41, 23);
		panel_BT.add(btn_BT_Mais);
		
		btn_BT_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaBT.setText(controle.atualizaEscalaTempo(-1));
			}
		});
		btn_BT_Menos.setBounds(121, 44, 41, 23);
		panel_BT.add(btn_BT_Menos);
		
		lbl_EscalaBT.setBounds(56, 35, 55, 14);
		panel_BT.add(lbl_EscalaBT);

		panel_Cursores.setBorder(new TitledBorder(null, "Cursores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Cursores.setBounds(760, 496, 314, 163);
		contentPane.add(panel_Cursores);
		panel_Cursores.setLayout(null);
		

		rdbtn_Cursor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controle.ativaCursor(1);
			}
		});
		rdbtn_Cursor1.setBounds(47, 17, 63, 23);
		panel_Cursores.add(rdbtn_Cursor1);
		

		rdbtn_Cursor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.ativaCursor(2);
			}
		});
		rdbtn_Cursor2.setBounds(112, 17, 63, 23);
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
					
			}
		});
		rdbtn_CH1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
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
		
		lblCanal.setBounds(6, 66, 74, 14);
		panel_Cursores.add(lblCanal);
		
		lblTensaoCanal.setBounds(6, 91, 74, 14);
		panel_Cursores.add(lblTensaoCanal);
		
		lblV.setBounds(6, 104, 46, 14);
		panel_Cursores.add(lblV);
		
		label_4.setBounds(6, 79, 46, 14);
		panel_Cursores.add(label_4);
		
		separator.setBounds(112, 66, 1, 2);
		panel_Cursores.add(separator);
		
		label_5.setBounds(112, 64, 74, 14);
		panel_Cursores.add(label_5);
		
		label_6.setBounds(112, 77, 46, 14);
		panel_Cursores.add(label_6);
		
		label_7.setBounds(112, 89, 74, 14);
		panel_Cursores.add(label_7);
		
		label_8.setBounds(112, 102, 46, 14);
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
		pnl_trigger.setBounds(760, 97, 229, 107);
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
		panel_singleShot.setBounds(999, 153, 75, 51);
		contentPane.add(panel_singleShot);
		

		rdbtn_SingleShot.setSelected(true);
		rdbtn_SingleShot.setBounds(6, 15, 50, 23);
		panel_singleShot.add(rdbtn_SingleShot);
		
		panel_AntAliasing.setBorder(new TitledBorder(null, "Ant-Aliasing", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_AntAliasing.setLayout(null);
		panel_AntAliasing.setBounds(999, 97, 75, 51);
		contentPane.add(panel_AntAliasing);
		

		rdbtn_AntAliasing.setSelected(true);
		rdbtn_AntAliasing.setBounds(6, 15, 50, 23);
		panel_AntAliasing.add(rdbtn_AntAliasing);
		

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
		});
		
		
		ftf_Trigger.setBounds(82, 57, 67, 20);
		pnl_trigger.add(ftf_Trigger);
			
		btn_trigger.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtn_Trigger.isSelected()){
					double posicao = Double.parseDouble(ftf_Trigger.getText().replace(',', '.'));
					if(posicao>-5 && posicao<5){
						controle.atualizaPosTrigger(posicao);
					}	
				}
			}
		});
		
		
		// Configuração de início
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
		btn_trigger.setBounds(159, 53, 60, 29);
		pnl_trigger.add(btn_trigger);
		

		lbl_EscalaBT.setText(Canal.escalaTempoStr[0]);
		lbl_EscalaCH1.setText(Canal.escalaTensaoStr[0]);
		lbl_EscalaCH2.setText(Canal.escalaTensaoStr[0]);
		
		controle.startAll();
	}
	
	public ChartPanel getChartPanel(){
		return chartPanel;
	}
}
