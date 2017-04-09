
package decisionTreeML;

import java.util.ArrayList;

public class BuildTree {
        ArrayList<DataSet> children;
        double minEnt = 0;
	double[][] entropy; 
	private int[] arr2; 
	int newN;
	BuildTree bt;
	private int temp;
	int c1 =0, c0 =0;
	public BuildTree(int[] arr) 
	{
		this.arr2 = arr;
	}
	
	public void newNode(ArrayList<DataSet> datset, Nodes t_root) 
	{
		

		Entropy ent = new Entropy();
		entropy = new double[6][];
		int depth=0;
		
		for (int i=0;i<6;i++)
		{
			if(this.arr2[i] != -1)
			{
				entropy[i] = ent.entropyCalc(datset,i+1,arr2[i]);
				
			}
			else
			{
	
				depth ++;
			}
		}
    	
    	
    	int newNode=1;
    	minEnt = 1000;
    	

    	for(int i = 0; i<6; i++)
    	{
    		if(this.arr2[i] != -1)
    		{
    			if(entropy[i][0]<minEnt)
    			{
    				minEnt = entropy[i][0];
    				newNode = i+1;
    			}
    		}	
    	}

    	for(int i=1; i< ((arr2[newNode-1])+1) ; i++) 
    	{	


    		children = new ArrayList<DataSet>(); 
    		for(int j=0;j<datset.size(); j++)
    		{
    			if(datset.get(j).getA(newNode) == entropy[newNode-1][i] && entropy[newNode-1][i]!=-1)
    			{
    				children.add(datset.get(j)); 
    			}
    		}

    		Nodes tn = new Nodes(entropy[newNode-1][0]);
    		t_root.add_Child(tn);
    		tn.parent = t_root;
    		tn.parent.set_node(newNode);
    		
    		tn.set_branch((int)(entropy[newNode-1][i]));
    		c1=0;
                c0=0;
    		for(int k=0;k<children.size();k++)
    		{
    			if(children.get(k).getClas() == 0)
    			{
    				c1 ++;
    			}
    			else
    			{
    				c0++;
    			}
    		}
                tn.set_Class0(c0);
    		tn.set_Class1(c1);
    		
 
    		

    		if(minEnt>0 && depth < 5 )
    		{

    			this.temp = arr2[newNode-1]; 
    			this.arr2[newNode-1] = -1;   
    			bt = new BuildTree(this.arr2);  

    			bt.newNode(children,tn); 

    			this.arr2[newNode-1] = this.temp;
    		}
    		
    	
    	}
   	}
}
