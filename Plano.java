import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
public class Plano extends JPanel{
	private double[] x;
	private double[] y;
	private int n;
	public Plano(){
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.WHITE);
		this.n = 0;
		this.x = new double[1];
		this.x[0] = 0;
		this.y = new double[1];
		this.y[0] = 0;
	}
	public Plano(double[] x, double[] y){
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.WHITE);
		this.n = x.length;
		this.x = new double[n];
		for(int i=0;i<n;i++)
			this.x[i] = x[i];
		this.y = new double[n];
		for(int i=0;i<n;i++)
			this.y[i] = y[i];
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.drawLine(0,this.getHeight()/2,this.getWidth(),this.getHeight()/2);
		g.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight());		
		g.setFont(g.getFont().deriveFont(0.04f*this.getWidth()));
		g.drawString("Im",(int) (this.getWidth()*0.52),(int) (this.getHeight()*0.05));
		g.drawString("Re",(int) (this.getWidth()*0.92),(int) (this.getHeight()*0.48));
		this.redimensionaComplejos();
		for(int i=0;i<this.n;i++)
			pintaPunto(g,this.x[i],this.y[i]);
	}
	public void pintaPunto(Graphics g, double x, double y){
		g.setColor(Color.BLACK);
		if(x!=0 && y!=0)
			g.drawLine((int) xReal(0),(int) yReal(0),(int) xReal(x),(int) yReal(y));
	}
	private double xReal(double x){
		return x+this.getWidth()/2;
	}
	private double yReal(double y){
		return -y+this.getHeight()/2;
	}
	private void redimensionaComplejos(){
		for(int i=0;i<this.x.length;i++){
			if(this.x[i]!=0 && this.y[i]!=0){
				while(Math.abs(this.x[i])<this.getWidth()*0.05) this.x[i]*=10;
				while(Math.abs(this.y[i])<this.getHeight()*0.05) this.y[i]*=10;
				while(Math.abs(this.x[i])>this.getWidth()) this.x[i]/=10;
				while(Math.abs(this.y[i])>this.getHeight()) this.y[i]/=10;
			}
		}
	}
}