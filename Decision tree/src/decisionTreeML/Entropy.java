
package decisionTreeML;

import java.util.*;

public class Entropy {
        int a=0;
        double E1=0,E2=0, p = 0;
	double entropyValue=0, vTotal= 0;
	
	public double[] entropyCalc(ArrayList<DataSet> datset, int attr, int b)
	{
		
		int total = datset.size();
		int[][] t = new int[b][3];
		this.a = a;
		for (int i = 0; i< b; i++)
		{
			t[i][0] = -1;
			t[i][1] = 0;
			t[i][2] = 0;
		}

		for (int i = 0; i< datset.size(); i++)
		{

			if(datset.get(i).getClas()==1)
			{
				
				for(int j = 0; j< b;j++)
				{
					if(t[j][0] == -1 )
					{
						t[j][0]= datset.get(i).getA(a);
						t[j][2] = 1;
						break;
					}
					else if(datset.get(i).getA(a)== t[j][0])
					{
						t[j][2]++;
						break;
					}
				}
			}					
			else
			{
				
				for(int j = 0; j< b;j++)
				{
					if(t[j][0] == -1 )
					{
						t[j][0]= datset.get(i).getA(a);
						t[j][1] = 1;
						break;
					}
					else if(datset.get(i).getA(a)== t[j][0])
					{
						t[j][1]++;
						break;
					}
				}
			}
		}
		
		
		double attrData[] = new double[b+1];
		for(int i =0; i< b;i++)
		{
			
			vTotal = t[i][1]+t[i][2];
			p = (vTotal/total);
			if(t[i][1]==0)
			{
				E1 = 0;
			}
			else
			{
				E1 = (t[i][1]/vTotal)*(Math.log(t[i][1]/vTotal)/Math.log(2));
			}

			if(t[i][2]==0)
			{
				E2=0;
			}
			else
			{
				E2 = (t[i][2]/vTotal)*(Math.log(t[i][2]/vTotal)/Math.log(2));
			}


			entropyValue = entropyValue - p*(E1+E2);
			
			
		}

		attrData [0]= entropyValue;
		for(int i=0;i<b;i++)
		{
			attrData[i+1] = t[i][0];
		}
		
		return attrData;
	}
}
