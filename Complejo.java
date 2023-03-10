import java.text.DecimalFormat;

public class Complejo{
	private double n1;
	private double n2;
	private int tipo; //0 para forma binómica, 1 para polar con módulo en grados sexagesimales, 2 para polar con módulo en radianes
	public Complejo(){
		this.n1 = 0;
		this.n2 = 0;
		this.tipo = 0;
	}
	public Complejo(double n1, double n2, int tipo){
		this.n1 = n1;
		this.n2 = n2;
		this.tipo = tipo;
		if(this.tipo==1){
			while(this.n2>180)
				this.n2-=180;
			while(this.n2<-180)
				this.n2+=180;
		}else if(this.tipo==2){
			while(this.n2>Math.PI)
				this.n2-=Math.PI;
			while(this.n2<-Math.PI)
				this.n2+=Math.PI;
		}
	}
	public Complejo(Complejo c){
		this.n1 = c.n1;
		this.n2 = c.n2;
		this.tipo = c.tipo;
		if(this.tipo==1){
			while(this.n2>180)
				this.n2-=180;
			while(this.n2<-180)
				this.n2+=180;
		}else if(this.tipo==2){
			while(this.n2>Math.PI)
				this.n2-=Math.PI;
			while(this.n2<-Math.PI)
				this.n2+=Math.PI;
		}
	}
	public boolean esValido(){
		if(this.tipo==1){
			while(this.n2>180)
				this.n2-=180;
			while(this.n2<-180)
				this.n2+=180;
			return this.n2>=-180 && this.n2<=180;
		}else if(this.tipo==2){
			while(this.n2>Math.PI)
				this.n2-=Math.PI;
			while(this.n2<-Math.PI)
				this.n2+=Math.PI;
			return this.n2>=-Math.PI && this.n2<=Math.PI;
		}
		return this.tipo==0;
	}
	public double getN1(){
		return this.n1;
	}
	public double getN2(){
		return this.n2;
	}
	public int getTipo(){
		return this.tipo;
	}
	public void setN1(double n1){
		this.n1 = n1;
	}
	public void setN2(double n2){
		if(this.tipo==1){
			while(n2>180)
				n2-=180;
			while(n2<-180)
				n2+=180;
		}else if(this.tipo==2){
			while(n2>Math.PI)
				n2-=Math.PI;
			while(n2<-Math.PI)
				n2+=Math.PI;
		}
		this.n2 = n2;
	}
	public void cambioForma(int nuevo){
		if(this.esValido()){
			if(nuevo==0){
				if(this.tipo==1){
					double mod = this.n1;
					double arg = this.n2;
					this.n1 = mod*Math.cos(Math.toRadians(arg));
					this.n2 = mod*Math.sin(Math.toRadians(arg));
					this.tipo = nuevo;
				}else if(this.tipo==2){
					double mod = this.n1;
					double arg = this.n2;
					this.n1 = mod*Math.cos(arg);
					this.n2 = mod*Math.sin(arg);
					this.tipo = nuevo;
				}
			}else if(nuevo==1){
				if(this.tipo==0){
					double a = this.n1;
					double b = this.n2;
					this.n1 = Math.sqrt(a*a+b*b);
					this.n2 = Math.atan(b/a);
					this.n2 = Math.toDegrees(this.n2);
					while(this.n2>180)
						this.n2-=180;
					while(this.n2<-180)
						this.n2+=180;
					this.tipo = nuevo;
				}else if(this.tipo==2){
					this.n2 = Math.toDegrees(this.n2);
					this.tipo = nuevo;
				}
			}else if(nuevo==2){
				if(this.tipo==0){
					double a = this.n1;
					double b = this.n2;
					this.n1 = Math.sqrt(a*a+b*b);
					this.n2 = Math.atan(b/a);
					while(this.n2>Math.PI)
						this.n2-=Math.PI;
					while(this.n2<-Math.PI)
						this.n2+=Math.PI;
					this.tipo = nuevo;
				}else if(this.tipo==1){
					this.n2 = Math.toRadians(this.n2);
					this.tipo = nuevo;
				}
			}
		}
	}
	public Complejo sumaComplejo(Complejo c){
		this.cambioForma(0);
		c.cambioForma(0);
		return new Complejo(this.n1+c.n1,this.n2+c.n2,0);
	}
	public Complejo restaComplejo(Complejo c){
		this.cambioForma(0);
		c.cambioForma(0);
		return new Complejo(this.n1-c.n1,this.n2-c.n2,0);
	}
	public Complejo prodComplejo(Complejo c){
		this.cambioForma(1);
		c.cambioForma(1);
		return new Complejo(this.n1*c.n1,this.n2+c.n2,1);
	}
	public Complejo divComplejo(Complejo c){
		this.cambioForma(1);
		c.cambioForma(1);
		if(c.n1==0 || c.n2==0)
			return new Complejo();
		else
			return new Complejo(this.n1/c.n1,this.n2-c.n2,1);
	}
	public Complejo potenciaNesima(int n){
		if(n>=0){
			this.cambioForma(1);
			return new Complejo(Math.pow(this.n1,n),this.n2*n,1);
		}
		return null;
	}
	public Complejo[] raizNesima(int n){
		if(n>=2){
			Complejo[] raices = new Complejo[n];
			this.cambioForma(2);
			Complejo unidadImag = new Complejo(0,1,0);		
			for(int i=0;i<raices.length;i++){
				Complejo aux = new Complejo(Math.pow(Math.E, (this.n2+2*i*Math.PI)/n),0,0);
				Complejo miembro1 = new Complejo(Math.cos((this.n2+2*i*Math.PI)/n),0,0).divComplejo(aux.prodComplejo(unidadImag));
				Complejo miembro2 = new Complejo(Math.sin((this.n2+2*i*Math.PI)/n),0,0).divComplejo(aux.prodComplejo(unidadImag)).prodComplejo(unidadImag);
				raices[i] = miembro1.sumaComplejo(miembro2);
				raices[i].cambioForma(0);
			}
			return raices;
		}
		return null;
	}
	public Complejo conjugado(){
		this.cambioForma(0);
		return new Complejo(this.n1,-this.n2,this.tipo);
	}
	public Complejo opuesto(){
		this.cambioForma(0);
		return new Complejo(-this.n1,-this.n2,this.tipo);
	}
	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		if(this.tipo==0){
			if(this.n1!=0){
				if(this.n2>0)
					return df.format(this.n1) +"+" +df.format(this.n2) +"i";
				else if(this.n2<0)
					return df.format(this.n1) +""+df.format(this.n2) +"i";
				else
					return df.format(this.n1) +"";	
			}else{
				if(this.n2>0 || this.n2<0)
					return df.format(this.n2) +"i";
				else
					return "0";	
			}
		}else if(this.tipo==1){
			if(this.n2>0 || this.n2<0)
				return df.format(this.n1) +" | " +df.format(this.n2) +"º";
			else
				return df.format(this.n1) +"";	
		}else if(this.tipo==2){
			String argumento = "PI";
			if(this.n2>0 || this.n2<0){
				if(this.n2<Math.PI)
					argumento += "/" +df.format(1/(this.n2/Math.PI));				
				return df.format(this.n1) +" | " +argumento +" rad";
			}else{
				return this.n1 +"";
			}
		}
		return "Valor del complejo anomalo";
	}
}