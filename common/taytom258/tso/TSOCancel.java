package taytom258.tso;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.lib.Collection;
import taytom258.window.Window;

public class TSOCancel {
	
	public static JPanel panel_Cancel = new JPanel();
	public static JLabel label_Tsr = new JLabel("TSR Number");
	public static JLabel label_RptDate = new JLabel("Report Date");
	public static final JTextField textField_Tsr = new JTextField();
	public static final JTextField textField_RptDate = new JTextField();
	public static JPanel panel_TsoState = new JPanel();
	public static final JTextArea textArea = new JTextArea();
	
	
	public static void buildPanel(){
		
		JPanel action = Window.getPanel_Action();
		
		panel_Cancel.setLayout(null);
		action.add(panel_Cancel, "panel_Cancel");
		
		label_Tsr.setBounds(12, 222, 71, 16);
		panel_Cancel.add(label_Tsr);
		
		label_RptDate.setBounds(12, 252, 67, 16);
		panel_Cancel.add(label_RptDate);
		
		textField_Tsr.setColumns(10);
		textField_Tsr.setBounds(171, 220, 114, 20);
		panel_Cancel.add(textField_Tsr);
		textField_Tsr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1){
					e.getComponent().requestFocus();
					textField_Tsr.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		textField_RptDate.setColumns(10);
		textField_RptDate.setBounds(171, 250, 114, 20);
		panel_Cancel.add(textField_RptDate);
		textField_RptDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1){
					e.getComponent().requestFocus();
					textField_RptDate.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		panel_TsoState.setLayout(null);
		panel_TsoState.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TsoState.setBounds(8, 282, 283, 113);
		panel_Cancel.add(panel_TsoState);
		
		textArea.setWrapStyleWord(true);
		textArea.setToolTipText("Enter Extra Comments Here");
		textArea.setLineWrap(true);
		textArea.setBounds(12, 23, 259, 78);
		panel_TsoState.add(textArea);
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1){
					e.getComponent().requestFocus();
					textArea.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});	
	}

	public static void collect(){
		
		Collection.cancelTsrNumber = textField_Tsr.getText().toUpperCase();
		Collection.cancelReportDate = textField_RptDate.getText().toUpperCase();
		Collection.cancelTsoStatement = textArea.getText().toUpperCase().trim().replaceAll("[\\t\\r\\v\\f\\s\\n]", " ").replaceAll("\\s{2,}", " ");
	
	}
}