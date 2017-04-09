
package decisionTreeML;

public class DataSet {

	private int a1,a2,a3,a4,a5,a6,class_label;
	
	
	public DataSet(int a1, int a2, int a3, int a4, int a5, int a6, int class_label)
	{
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.a5 = a5;
		this.a6 = a6;
		this.class_label = class_label;
	}
	
	public int getA(int a) 
	{
		if(a==1)
			return a1;
		else if(a==2)
			return a2;
		else if(a==3)
			return a3;
		else if(a==4)
			return a4;
		else if(a==5)
			return a5;
		else 
			return a6;
	}

	public void seta1(int a1) {
		this.a1 = a1;
	}

	public void seta2(int a2) {
		this.a2 = a2;
	}

	public void seta3(int a3) {
		this.a3 = a3;
	}

	public void seta4(int a4) {
		this.a4 = a4;
	}

	public void seta5(int a5) {
		this.a5 = a5;
	}

	public void seta6(int a6) {
		this.a6 = a6;
	}
        public void setClass(int class_label) {
		this.class_label = class_label;
	}
	public int getClas() {
		return class_label;
	}
	
	
}
