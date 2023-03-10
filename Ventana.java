import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class Ventana {

	private JFrame frame;
	private JButton calcBoton;
	private JTextField campoReal1;
	private JTextField campoReal2;
	private JButton campoExtra;
	private JTextArea resultado;
	private JScrollPane controlResultado;	
	private Plano plano;
	private JTextField campoImag1;
	private JTextField campoImag2;
	private JButton btnI;
	private JButton btnI2;
	private JButton btnMas;
	private JButton btnMas2;
	private int modo;
	private Complejo res;
	
	private static boolean esDouble(String s){
		double d;
		try{
			d = Double.parseDouble(s);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
	
	private static boolean esEntero(String s){
		int i;
		try{
			i = Integer.parseInt(s);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Calculadora de complejos");
		frame.setBounds(100, 100, 700, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		
		plano = new Plano();
		frame.add(plano);
		plano.setBounds(350, 33, 300, 300);
		
		String[] modos = {"BINÓMICA","POLAR (º)","POLAR (rad)"};
		JComboBox<String> modoBox = new JComboBox<String>(modos);
		modoBox.setBounds(10, 11, 290, 22);
		frame.add(modoBox);
		modoBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(modoBox.getSelectedItem().equals(modos[0])){
					modo = 0;
					campoReal1.setText("");
					campoReal2.setText("");
					campoImag1.setText("");
					campoImag2.setText("");
					btnMas.setText("+");
					btnI.setText("i");
					btnMas2.setText("+");
					btnI2.setText("i");
					if(!resultado.getText().equals("")){
						res.cambioForma(modo);
						resultado.setText(res.toString());
					}
				}
				if(modoBox.getSelectedItem().equals(modos[1])){
					modo = 1;
					campoReal1.setText("");
					campoReal2.setText("");
					campoImag1.setText("");
					campoImag2.setText("");
					btnMas.setText("|");
					btnI.setText("º");
					btnMas2.setText("|");
					btnI2.setText("º");
					if(!resultado.getText().equals("")){
						res.cambioForma(modo);
						resultado.setText(res.toString());
					}
				}
				if(modoBox.getSelectedItem().equals(modos[2])){
					modo = 2;
					campoReal1.setText("");
					campoReal2.setText("");
					campoImag1.setText("");
					campoImag2.setText("");
					btnMas.setText("|");
					btnI.setText("rad");
					btnMas2.setText("|");
					btnI2.setText("rad");
					if(!resultado.getText().equals("")){
						res.cambioForma(modo);
						resultado.setText(res.toString());
					}
				}
			}
		});
		
		String[] calculos = {"SUMAR","RESTAR","MULTIPLICAR","DIVIDIR","ELEVAR A N","N-RAÍZ","OPUESTO","CONJUGADO"};
		JComboBox<String> opBox = new JComboBox<String>(calculos);
		opBox.setBounds(10, 58, 290, 22);
		frame.add(opBox);
		opBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(!opBox.getSelectedItem().equals(calculos[5]))
					controlResultado.setBounds(10, 293, 290, 58);
				if(opBox.getSelectedItem().equals(calculos[6]) || opBox.getSelectedItem().equals(calculos[7])){
					campoReal2.setEnabled(false);
					campoImag2.setEnabled(false);
				}else{
					campoReal2.setEnabled(true);
					campoImag2.setEnabled(true);
				}
				if(opBox.getSelectedItem().equals(calculos[4])){
					campoExtra.setVisible(true);
					btnMas2.setVisible(false);
					btnI2.setVisible(false);
					campoImag2.setVisible(false);
				}else if(opBox.getSelectedItem().equals(calculos[5])){
					campoExtra.setVisible(true);
					btnMas2.setVisible(false);
					btnI2.setVisible(false);
					campoImag2.setVisible(false);
				}else{
					campoExtra.setVisible(false);
					btnMas2.setVisible(true);
					btnI2.setVisible(true);
					campoImag2.setVisible(true);
				}
			}
		});
		
		campoReal1 = new JTextField();
		campoReal1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		campoReal1.setHorizontalAlignment(SwingConstants.RIGHT);
		campoReal1.setBounds(10, 110, 91, 35);
		frame.add(campoReal1);
		campoReal1.setColumns(10);
		
		campoImag1 = new JTextField();
		campoImag1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		campoImag1.setHorizontalAlignment(SwingConstants.RIGHT);
		campoImag1.setBounds(137, 110, 100, 35);
		frame.add(campoImag1);
		campoImag1.setColumns(10);
		
		campoExtra = new JButton("Exponente: ");
		campoExtra.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		campoExtra.setEnabled(false);
		campoExtra.setBorder(null);
		campoExtra.setBounds(5, 145, 110, 35);
		campoExtra.setVisible(false);
		frame.add(campoExtra);
		
		campoImag2 = new JTextField();
		campoImag2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		campoImag2.setHorizontalAlignment(SwingConstants.RIGHT);
		campoImag2.setBounds(137, 181, 100, 35);
		frame.add(campoImag2);
		campoImag2.setColumns(10);
		
		campoReal2 = new JTextField();
		campoReal2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		campoReal2.setHorizontalAlignment(SwingConstants.RIGHT);
		campoReal2.setBounds(10, 181, 91, 35);
		frame.add(campoReal2);
		campoReal2.setColumns(10);
		
		calcBoton = new JButton("CALCULAR");
		calcBoton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		calcBoton.setBounds(10, 237, 290, 45);
		frame.add(calcBoton);
		calcBoton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String aux = "";
				for(int i=0;i<campoReal1.getText().length();i++){
					if(!(campoReal1.getText().charAt(i)==','))
						aux+=campoReal1.getText().charAt(i);
					else
						aux+='.';
				}
				campoReal1.setText(aux);
				aux="";
				for(int i=0;i<campoReal2.getText().length();i++){
					if(!(campoReal2.getText().charAt(i)==','))
						aux+=campoReal2.getText().charAt(i);
					else
						aux+='.';
				}
				campoReal2.setText(aux);
				aux="";
				for(int i=0;i<campoImag1.getText().length();i++){
					if(!(campoImag1.getText().charAt(i)==','))
						aux+=campoImag1.getText().charAt(i);
					else
						aux+='.';
				}
				campoImag1.setText(aux);
				aux="";
				for(int i=0;i<campoImag2.getText().length();i++){
					if(!(campoImag2.getText().charAt(i)==','))
						aux+=campoImag2.getText().charAt(i);
					else
						aux+='.';
				}
				campoImag2.setText(aux);
				aux="";
				if(opBox.getSelectedItem().equals(calculos[0])){
					resultado.setText("");
					boolean malo = false;
					Complejo c1;
					if(esDouble(campoReal1.getText()) && esDouble(campoImag1.getText())){
						c1 = new Complejo(Double.parseDouble(campoReal1.getText()),Double.parseDouble(campoImag1.getText()),modo);
					}else{
						c1 = new Complejo();
						malo = true;
					}
					Complejo c2;
					if(esDouble(campoReal2.getText()) && esDouble(campoImag2.getText())){
						c2 = new Complejo(Double.parseDouble(campoReal2.getText()),Double.parseDouble(campoImag2.getText()),modo);
					}else{
						c2 = new Complejo();
						malo = true;
					}
					if(!malo){
						res = c1.sumaComplejo(c2);
						res.cambioForma(modo);
						resultado.setText(res.toString());
						double[] reales = {c1.getN1(),c2.getN1(),res.getN1()};
						double[] imags = {c1.getN2(),c2.getN2(),res.getN2()};
						plano = new Plano(reales,imags);						
						frame.add(plano);
						plano.setBounds(350, 33, 300, 300);
					}else{
						res = new Complejo();
						resultado.setText("");
					}
				}	
				if(opBox.getSelectedItem().equals(calculos[1])){
					if(plano!=null) frame.remove(plano);
					resultado.setText("");
					boolean malo = false;
					Complejo c1;
					if(esDouble(campoReal1.getText()) && esDouble(campoImag1.getText())){
						c1 = new Complejo(Double.parseDouble(campoReal1.getText()),Double.parseDouble(campoImag1.getText()),modo);
					}else{
						c1 = new Complejo();
						malo = true;
					}
					Complejo c2;
					if(esDouble(campoReal2.getText()) && esDouble(campoImag2.getText())){
						c2 = new Complejo(Double.parseDouble(campoReal2.getText()),Double.parseDouble(campoImag2.getText()),modo);
					}else{
						c2 = new Complejo();
						malo = true;
					}
					if(!malo){
						res = c1.restaComplejo(c2);
						res.cambioForma(modo);
						resultado.setText(res.toString());
						double[] reales = {c1.getN1(),c2.getN1(),res.getN1()};
						double[] imags = {c1.getN2(),c2.getN2(),res.getN2()};
						plano = new Plano(reales,imags);						
						frame.add(plano);
						plano.setBounds(350, 33, 300, 300);
					}else{
						res = new Complejo();
						resultado.setText("");
					}
				}
				if(opBox.getSelectedItem().equals(calculos[2])){
					if(plano!=null) frame.remove(plano);
					resultado.setText("");
					boolean malo = false;
					Complejo c1;
					if(esDouble(campoReal1.getText()) && esDouble(campoImag1.getText())){
						c1 = new Complejo(Double.parseDouble(campoReal1.getText()),Double.parseDouble(campoImag1.getText()),modo);
					}else{
						c1 = new Complejo();
						malo = true;
					}
					Complejo c2;
					if(esDouble(campoReal2.getText()) && esDouble(campoImag2.getText())){
						c2 = new Complejo(Double.parseDouble(campoReal2.getText()),Double.parseDouble(campoImag2.getText()),modo);
					}else{
						c2 = new Complejo();
						malo = true;
					}
					if(!malo){
						res = c1.prodComplejo(c2);
						res.cambioForma(modo);
						resultado.setText(res.toString());
						double[] reales = {c1.getN1(),c2.getN1(),res.getN1()};
						double[] imags = {c1.getN2(),c2.getN2(),res.getN2()};
						plano = new Plano(reales,imags);						
						frame.add(plano);
						plano.setBounds(350, 33, 300, 300);
					}else{
						res = new Complejo();
						resultado.setText("");
					}
				}
				if(opBox.getSelectedItem().equals(calculos[3])){
					if(plano!=null) frame.remove(plano);
					resultado.setText("");
					boolean malo = false;
					Complejo c1;
					if(esDouble(campoReal1.getText()) && esDouble(campoImag1.getText())){
						c1 = new Complejo(Double.parseDouble(campoReal1.getText()),Double.parseDouble(campoImag1.getText()),modo);
					}else{
						c1 = new Complejo();
						malo = true;
					}
					Complejo c2;
					if(esDouble(campoReal2.getText()) && esDouble(campoImag2.getText())){
						c2 = new Complejo(Double.parseDouble(campoReal2.getText()),Double.parseDouble(campoImag2.getText()),modo);
					}else{
						c2 = new Complejo();
						malo = true;
					}
					if(!malo){
						res = c1.divComplejo(c2);
						res.cambioForma(modo);
						resultado.setText(res.toString());
						double[] reales = {c1.getN1(),c2.getN1(),res.getN1()};
						double[] imags = {c1.getN2(),c2.getN2(),res.getN2()};
						plano = new Plano(reales,imags);						
						frame.add(plano);
						plano.setBounds(350, 33, 300, 300);
					}else{
						res = new Complejo();
						resultado.setText("");
					}
				}
				if(opBox.getSelectedItem().equals(calculos[4])){
					if(plano!=null) frame.remove(plano);
					resultado.setText("");
					boolean malo = false;
					Complejo c1;
					if(esDouble(campoReal1.getText()) && esDouble(campoImag1.getText())){
						c1 = new Complejo(Double.parseDouble(campoReal1.getText()),Double.parseDouble(campoImag1.getText()),modo);
					}else{
						c1 = new Complejo();
						malo = true;
					}
					if(!malo){
						if(esEntero(campoReal2.getText())){
							if(Integer.parseInt(campoReal2.getText())>0){
								res = c1.potenciaNesima(Integer.parseInt(campoReal2.getText()));
								res.cambioForma(modo);
								resultado.setText(res.toString());
								double[] reales = {c1.getN1(),res.getN1()};
								double[] imags = {c1.getN2(),res.getN2()};
								plano = new Plano(reales,imags);						
								frame.add(plano);
								plano.setBounds(350, 33, 300, 300);
							}else{
								res = new Complejo();
								resultado.setText("El exponente debe ser positivo");
							}
						}else{
							res = new Complejo();
							resultado.setText("");
						}
					}else{
						res = new Complejo();
						resultado.setText("");
					}
				}
				if(opBox.getSelectedItem().equals(calculos[5])){
					if(plano!=null) frame.remove(plano);
					resultado.setText("");
					boolean malo = false;
					Complejo[] resultados;
					Complejo c1;
					if(esDouble(campoReal1.getText()) && esDouble(campoImag1.getText())){
						c1 = new Complejo(Double.parseDouble(campoReal1.getText()),Double.parseDouble(campoImag1.getText()),modo);
					}else{
						c1 = new Complejo();
						malo = true;
					}
					if(!malo){
						if(esEntero(campoReal2.getText())){
							if(Integer.parseInt(campoReal2.getText())>0){
								resultados = new Complejo[Integer.parseInt(campoReal2.getText())];
								resultados = c1.raizNesima(Integer.parseInt(campoReal2.getText()));
								double[] reales = new double[resultados.length+1];
								double[] imags = new double[resultados.length+1];
								reales[0] = c1.getN1();
								imags[0] = c1.getN2();
								resultado.setText("");
								controlResultado.setBounds(10, 293, 290, 116);
								for(int i=0;i<resultados.length;i++){
									resultados[i].cambioForma(modo);
									if(i<resultados.length-1)
										resultado.setText(resultado.getText() +resultados[i].toString() +"\n");
									else
										resultado.setText(resultado.getText() +resultados[i].toString());
									reales[i+1] = resultados[i].getN1();
									imags[i+1] = resultados[i].getN2();
								}
								res = new Complejo();
								plano = new Plano(reales,imags);						
								frame.add(plano);
								plano.setBounds(350, 33, 300, 300);
							}else{
								res = new Complejo();
								resultado.setText("El exponente debe ser positivo");
							}
						}else{
							res = new Complejo();
							resultado.setText("");
						}
					}else{
						res = new Complejo();
						resultado.setText("");
					}				
				}
				if(opBox.getSelectedItem().equals(calculos[6])){
					if(plano!=null) frame.remove(plano);
					resultado.setText("");
					boolean malo = false;
					Complejo c1;
					if(esDouble(campoReal1.getText()) && esDouble(campoImag1.getText())){
						c1 = new Complejo(Double.parseDouble(campoReal1.getText()),Double.parseDouble(campoImag1.getText()),modo);
					}else{
						c1 = new Complejo();
						malo = true;
					}
					if(!malo){
						res = c1.opuesto();
						res.cambioForma(modo);
						resultado.setText(res.toString());
						double[] reales = {c1.getN1(),res.getN1()};
						double[] imags = {c1.getN2(),res.getN2()};
						plano = new Plano(reales,imags);
						frame.add(plano);
						plano.setBounds(350, 33, 300, 300);						
					}else{
						res = new Complejo();
						resultado.setText("");
					}
				}
				if(opBox.getSelectedItem().equals(calculos[7])){
					if(plano!=null) frame.remove(plano);
					resultado.setText("");
					boolean malo = false;
					Complejo c1;
					if(esDouble(campoReal1.getText()) && esDouble(campoImag1.getText())){
						c1 = new Complejo(Double.parseDouble(campoReal1.getText()),Double.parseDouble(campoImag1.getText()),modo);
					}else{
						c1 = new Complejo();
						malo = true;
					}
					if(!malo){
						res = c1.conjugado();
						res.cambioForma(modo);
						resultado.setText(res.toString());
						double[] reales = {c1.getN1(),res.getN1()};
						double[] imags = {c1.getN2(),res.getN2()};
						plano = new Plano(reales,imags);
						frame.add(plano);
						plano.setBounds(350, 33, 300, 300);
					}else{
						res = new Complejo();
						resultado.setText("");
					}
				}
				campoReal1.setText("");
				campoReal2.setText("");
				campoImag1.setText("");
				campoImag2.setText("");				
			}
		});
		
		resultado = new JTextArea();
		resultado.setBounds(10, 293, 290, 116);
		resultado.setEditable(false);
		resultado.setColumns(10);
		resultado.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		controlResultado = new JScrollPane(resultado);
		controlResultado.setBounds(10, 293, 290, 58);
		controlResultado.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(controlResultado);
		
		btnI = new JButton("i");
		btnI.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnI.setBorder(null);
		btnI.setEnabled(false);
		btnI.setBounds(237, 110, 35, 35);
		frame.add(btnI);
		
		btnI2 = new JButton("i");
		btnI2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnI2.setBorder(null);
		btnI2.setEnabled(false);
		btnI2.setBounds(237, 181, 35, 35);
		frame.add(btnI2);
		
		btnMas = new JButton("+");
		btnMas.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnMas.setEnabled(false);
		btnMas.setBorder(null);
		btnMas.setBounds(102, 110, 35, 35);
		frame.add(btnMas);
		
		btnMas2 = new JButton("+");
		btnMas2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnMas2.setEnabled(false);
		btnMas2.setBorder(null);
		btnMas2.setBounds(102, 181, 35, 35);
		frame.add(btnMas2);
	}
}
