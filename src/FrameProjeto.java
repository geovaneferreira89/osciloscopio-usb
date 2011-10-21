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

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


import javax.swing.JSeparator;



@SuppressWarnings("serial")
public class FrameProjeto extends JFrame {
	
	public static final void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		FrameProjeto fp = new FrameProjeto();
		fp.setVisible(true);
		
		
	}
	Controle controle;

	private ChartPanel chartPanel;
	
	private JLabel lbl_EscalaCH1;
	private JLabel lbl_EscalaCH2;
	private JLabel lbl_EscalaBT;
	
	private JRadioButton rdbtn_Cursor1;
	private JRadioButton rdbtn_Cursor2;
	
	private ButtonGroup grupo;
	private ButtonGroup grupo2;
	

	public FrameProjeto() {
		
		// Frame Principal
		setTitle("Oscilosc\u00F3pio ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100,702);
		
		controle = new Controle(this);
        
		JPanel contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JPanel pnl_Opcoes = new JPanel();
		pnl_Opcoes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Op\u00E7\u00F5es ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_Opcoes.setBounds(760, 8, 314, 78);
		contentPane.add(pnl_Opcoes);
		pnl_Opcoes.setLayout(null);
		
		JButton btn_ConectarUSB = new JButton("Iniciar Conex\u00E3o");
		btn_ConectarUSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_ConectarUSB.setBounds(17, 25, 107, 29);
		pnl_Opcoes.add(btn_ConectarUSB);
		
		JPanel pnl_Plotter = new JPanel();
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
	
		JLabel lbl_UsbStatus = new JLabel("USB: N\u00E3o Conectada");
		lbl_UsbStatus.setForeground(Color.RED);
		lbl_UsbStatus.setBounds(16, 20, 131, 16);
		pnl_Plotter.add(lbl_UsbStatus);
		
		JButton btn_Teste = new JButton("Teste");
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
		
		JPanel pnl_CH1 = new JPanel();
		pnl_CH1.setBounds(760, 215, 314, 94);
		contentPane.add(pnl_CH1);
		pnl_CH1.setBorder(new TitledBorder(null, "Canal 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_CH1.setLayout(null);
		final JRadioButton rdbtn_CH1 = new JRadioButton("On");
		rdbtn_CH1.setSelected(true);
		rdbtn_CH1.setBounds(6, 18, 50, 23);
		pnl_CH1.add(rdbtn_CH1);
		
		JButton btn_CH1_Mais = new JButton("+");
		btn_CH1_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_EscalaCH1.setText(controle.atualizaEscalaTensao(1,1));
			}
		});
		btn_CH1_Mais.setBounds(119, 34, 41, 23);
		pnl_CH1.add(btn_CH1_Mais);
		
		JButton btn_CH1_Menos = new JButton("-");
		btn_CH1_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH1.setText(controle.atualizaEscalaTensao(1,-1));
			}
		});
		btn_CH1_Menos.setBounds(119, 60, 41, 23);
		pnl_CH1.add(btn_CH1_Menos);
		
		JLabel lblEscala_1 = new JLabel("Escala");
		lblEscala_1.setBounds(16, 48, 41, 14);
		pnl_CH1.add(lblEscala_1);
		
		lbl_EscalaCH1 = new JLabel("2 mV / div");
		lbl_EscalaCH1.setBounds(54, 48, 55, 14);
		pnl_CH1.add(lbl_EscalaCH1);
		
		JLabel lbl_rms1 = new JLabel("Tens\u00E3o RMS:");
		lbl_rms1.setBounds(170, 27, 63, 14);
		pnl_CH1.add(lbl_rms1);
		
		JLabel lbl_pp1 = new JLabel("Tens\u00E3o Pico \u00E0 Pico:");
		lbl_pp1.setBounds(170, 48, 92, 14);
		pnl_CH1.add(lbl_pp1);
		
		JLabel lbl_frq1 = new JLabel("Frequ\u00EAncia:");
		lbl_frq1.setBounds(170, 69, 57, 14);
		pnl_CH1.add(lbl_frq1);
		
		JLabel lbl_RMSCH1 = new JLabel("0000 mV");
		lbl_RMSCH1.setBounds(236, 27, 46, 14);
		pnl_CH1.add(lbl_RMSCH1);
		
		JLabel lbl_PPCH1 = new JLabel("0000 mV");
		lbl_PPCH1.setBounds(264, 48, 46, 14);
		pnl_CH1.add(lbl_PPCH1);
		
		JLabel lbl_FRQCH1 = new JLabel("0000 Hz");
		lbl_FRQCH1.setBounds(227, 69, 46, 14);
		pnl_CH1.add(lbl_FRQCH1);
		
		JPanel pnl_CH2 = new JPanel();
		pnl_CH2.setBounds(760, 312, 314, 94);
		contentPane.add(pnl_CH2);
		pnl_CH2.setBorder(new TitledBorder(null, "Canal 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_CH2.setLayout(null);
		//Canal 1 selecionado
		final JRadioButton rdbtn_CH2 = new JRadioButton("On");
		rdbtn_CH2.setSelected(true);
		rdbtn_CH2.setBounds(6, 18, 50, 23);
		pnl_CH2.add(rdbtn_CH2);
		
		JLabel lblEscala = new JLabel("Escala");
		lblEscala.setBounds(16, 46, 41, 16);
		pnl_CH2.add(lblEscala);
		
		JButton btn_CH2_Menos = new JButton("-");
		btn_CH2_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH2.setText(controle.atualizaEscalaTensao(2,-1));
			}
		});
		btn_CH2_Menos.setBounds(121, 55, 41, 23);
		pnl_CH2.add(btn_CH2_Menos);
		
		JButton btn_CH2_Mais = new JButton("+");
		btn_CH2_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaCH2.setText(controle.atualizaEscalaTensao(2,1));
			}
		});
		btn_CH2_Mais.setBounds(121, 29, 41, 23);
		pnl_CH2.add(btn_CH2_Mais);
		
		lbl_EscalaCH2 = new JLabel("2 mV / div");
		lbl_EscalaCH2.setBounds(56, 48, 55, 14);
		pnl_CH2.add(lbl_EscalaCH2);
		
		JLabel label = new JLabel("Tens\u00E3o RMS:");
		label.setBounds(168, 22, 63, 14);
		pnl_CH2.add(label);
		
		JLabel label_1 = new JLabel("Tens\u00E3o Pico \u00E0 Pico:");
		label_1.setBounds(168, 43, 92, 14);
		pnl_CH2.add(label_1);
		
		JLabel label_2 = new JLabel("Frequ\u00EAncia:");
		label_2.setBounds(168, 64, 57, 14);
		pnl_CH2.add(label_2);
		
		JLabel lbl_FRQCH2 = new JLabel("0000 Hz");
		lbl_FRQCH2.setBounds(225, 64, 46, 14);
		pnl_CH2.add(lbl_FRQCH2);
		
		JLabel lbl_PPCH2 = new JLabel("0000 mV");
		lbl_PPCH2.setBounds(262, 43, 46, 14);
		pnl_CH2.add(lbl_PPCH2);
		
		JLabel lbl_RMSCH2 = new JLabel("0000 mV");
		lbl_RMSCH2.setBounds(234, 22, 46, 14);
		pnl_CH2.add(lbl_RMSCH2);
		
		JPanel panel_BT = new JPanel();
		panel_BT.setBorder(new TitledBorder(null, "Base de Tempo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_BT.setBounds(760, 417, 314, 78);
		contentPane.add(panel_BT);
		panel_BT.setLayout(null);
		
		JLabel lbl_Tempo = new JLabel("Tempo");
		lbl_Tempo.setBounds(10, 35, 46, 14);
		panel_BT.add(lbl_Tempo);
		
		JButton btn_BT_Mais = new JButton("+");
		btn_BT_Mais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_EscalaBT.setText(controle.atualizaEscalaTempo(1));
			}
		});
		btn_BT_Mais.setBounds(121, 18, 41, 23);
		panel_BT.add(btn_BT_Mais);
		
		JButton btn_BT_Menos = new JButton("-");
		btn_BT_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_EscalaBT.setText(controle.atualizaEscalaTempo(-1));
			}
		});
		btn_BT_Menos.setBounds(121, 44, 41, 23);
		panel_BT.add(btn_BT_Menos);
		
		lbl_EscalaBT = new JLabel();
		lbl_EscalaBT.setBounds(56, 35, 55, 14);
		panel_BT.add(lbl_EscalaBT);

		JPanel panel_Cursores = new JPanel();
		panel_Cursores.setBorder(new TitledBorder(null, "Cursores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Cursores.setBounds(760, 496, 314, 163);
		contentPane.add(panel_Cursores);
		panel_Cursores.setLayout(null);
		
		final JRadioButton rdbtn_Cursores = new JRadioButton("On");
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
		
		rdbtn_Cursor1 = new JRadioButton("Cursor1");
		rdbtn_Cursor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controle.ativaCursor(1);
			}
		});
		rdbtn_Cursor1.setBounds(47, 17, 63, 23);
		panel_Cursores.add(rdbtn_Cursor1);
		
		rdbtn_Cursor2 = new JRadioButton("Cursor2");
		rdbtn_Cursor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controle.ativaCursor(2);
			}
		});
		rdbtn_Cursor2.setBounds(112, 17, 63, 23);
		panel_Cursores.add(rdbtn_Cursor2);
		//Canal 2 selecionado
		
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
			@Override
			//########################
			//Conectar USB
			//########################
			public void mouseClicked(MouseEvent arg0)
			{
				JOptionPane.showMessageDialog(FrameProjeto.this, "Conectando... ");
			}
			//########################
		});
		
	
		JLabel lblCursor = new JLabel("Cursor1");
		lblCursor.setBounds(6, 39, 46, 14);
		panel_Cursores.add(lblCursor);
		
		JLabel lblCursor_1 = new JLabel("Cursor2");
		lblCursor_1.setBounds(112, 39, 46, 14);
		panel_Cursores.add(lblCursor_1);
		
		JLabel lblCursorCursor = new JLabel("Cursor2 - Cursor1");
		lblCursorCursor.setBounds(214, 39, 94, 14);
		panel_Cursores.add(lblCursorCursor);
		
		JLabel lblCanal = new JLabel("Tensao Canal 1");
		lblCanal.setBounds(6, 66, 74, 14);
		panel_Cursores.add(lblCanal);
		
		JLabel lblTensaoCanal = new JLabel("Tensao Canal 2");
		lblTensaoCanal.setBounds(6, 91, 74, 14);
		panel_Cursores.add(lblTensaoCanal);
		
		JLabel lblV = new JLabel("0.00 V");
		lblV.setBounds(6, 104, 46, 14);
		panel_Cursores.add(lblV);
		
		JLabel label_4 = new JLabel("0.00 V");
		label_4.setBounds(6, 79, 46, 14);
		panel_Cursores.add(label_4);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(112, 66, 1, 2);
		panel_Cursores.add(separator);
		
		JLabel label_5 = new JLabel("Tensao Canal 1");
		label_5.setBounds(112, 64, 74, 14);
		panel_Cursores.add(label_5);
		
		JLabel label_6 = new JLabel("0.00 V");
		label_6.setBounds(112, 77, 46, 14);
		panel_Cursores.add(label_6);
		
		JLabel label_7 = new JLabel("Tensao Canal 2");
		label_7.setBounds(112, 89, 74, 14);
		panel_Cursores.add(label_7);
		
		JLabel label_8 = new JLabel("0.00 V");
		label_8.setBounds(112, 102, 46, 14);
		panel_Cursores.add(label_8);
		
		JLabel label_11 = new JLabel("Tensao Canal 1");
		label_11.setBounds(214, 64, 74, 14);
		panel_Cursores.add(label_11);
		
		JLabel label_12 = new JLabel("0.00 V");
		label_12.setBounds(214, 77, 46, 14);
		panel_Cursores.add(label_12);
		
		JLabel label_13 = new JLabel("Tensao Canal 2");
		label_13.setBounds(214, 89, 74, 14);
		panel_Cursores.add(label_13);
		
		JLabel label_14 = new JLabel("0.00 V");
		label_14.setBounds(214, 102, 46, 14);
		panel_Cursores.add(label_14);
		
		JLabel label_15 = new JLabel("Tempo");
		label_15.setBounds(214, 123, 74, 14);
		panel_Cursores.add(label_15);
		
		JLabel label_16 = new JLabel("0.00 s");
		label_16.setBounds(214, 136, 46, 14);
		panel_Cursores.add(label_16);
		
		JPanel rdbtn_TCanal2 = new JPanel();
		rdbtn_TCanal2.setBorder(new TitledBorder(null, "Trigger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rdbtn_TCanal2.setLayout(null);
		rdbtn_TCanal2.setBounds(760, 97, 207, 107);
		contentPane.add(rdbtn_TCanal2);
		

	
		
		final JRadioButton rdbtn_T1 = new JRadioButton("Canal 1");
		rdbtn_T1.setSelected(true);
		rdbtn_T1.setBounds(58, 15, 69, 23);
		rdbtn_TCanal2.add(rdbtn_T1);
		
		final JRadioButton rdbtn_T2 = new JRadioButton("Canal 2");
		rdbtn_T2.setSelected(true);
		rdbtn_T2.setBounds(129, 15, 69, 23);
		rdbtn_TCanal2.add(rdbtn_T2);
		
		
		JLabel lblTensao = new JLabel("Posic\u00E3o");
		lblTensao.setBounds(10, 60, 46, 14);
		rdbtn_TCanal2.add(lblTensao);
		
		JButton button = new JButton("+");
		button.setBounds(58, 45, 41, 23);
		rdbtn_TCanal2.add(button);
		
		JButton button_1 = new JButton("-");
		button_1.setBounds(58, 71, 41, 23);
		rdbtn_TCanal2.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SingleShot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(977, 153, 97, 51);
		contentPane.add(panel_1);
		
		JRadioButton radioButton_1 = new JRadioButton("On");
		radioButton_1.setSelected(true);
		radioButton_1.setBounds(6, 15, 50, 23);
		panel_1.add(radioButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Ant-Aliasing", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(null);
		panel_2.setBounds(977, 97, 97, 51);
		contentPane.add(panel_2);
		
		JRadioButton radioButton_2 = new JRadioButton("On");
		radioButton_2.setSelected(true);
		radioButton_2.setBounds(6, 15, 50, 23);
		panel_2.add(radioButton_2);
		
		JRadioButton triggerRB = new JRadioButton("On");
		triggerRB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*if (arg0..isSelected())
				{
					rdbtn_T1.setEnabled(true);
					rdbtn_T2.setEnabled(true);
				}
				else{
					rdbtn_T1.setEnabled(false);
					rdbtn_T2.setEnabled(false);
				}*/
			}
			
		});
		
		// Configuração de início
		grupo = new ButtonGroup();
		grupo.add(rdbtn_Cursor1);
		grupo.add(rdbtn_Cursor2);
		rdbtn_Cursor1.setEnabled(false);
		rdbtn_Cursor2.setEnabled(false);	
		triggerRB.setSelected(true);
		triggerRB.setBounds(6, 15, 50, 23);
		rdbtn_TCanal2.add(triggerRB);
		triggerRB.setSelected(false);
		rdbtn_T1.setEnabled(false);
		rdbtn_T2.setEnabled(false);
		grupo2 = new ButtonGroup();
		grupo2.add(rdbtn_T1);
		grupo2.add(rdbtn_T2);
		lbl_EscalaBT.setText(Canal.escalaTempoStr[0]);
		lbl_EscalaBT.setText(Canal.escalaTempoStr[0]);
		lbl_EscalaCH1.setText(Canal.escalaTensaoStr[0]);
		lbl_EscalaCH2.setText(Canal.escalaTensaoStr[0]);
		
		controle.startAll();
		
	}
	public ChartPanel getChartPanel(){
		return chartPanel;
	}

}
