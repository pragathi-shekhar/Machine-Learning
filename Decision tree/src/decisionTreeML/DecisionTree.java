/*
* Has main function,display method to display decision tree of training data set and accuracy method to calculate the accuracy
Functions:
* Passes on the training data to class "Tree"
* Prints the decision tree using displayTree method 
* Prints the accuracy of training and test data using class.
* Input:Takes file name input along with path of the .dat file from user.
* Output: 
    (A)Displays decision tree.
    (B)Prints accuracy of training data set
    (C)Prints accuracy of test data set 
*/
package decisionTreeML;

import java.io.*;
import java.util.*;
/**
 *
 * @author Pragathi
 */
public class DecisionTree {
    
    
	        
	public static void main(String[] args) 
	{
                double class_zeroCount=0 , class_oneCount=0 , initial_Entropy;
                ArrayList<DataSet> datset = new ArrayList<DataSet>(); //ArrayList to store training file data
                int[] attributes = new int [6]; //Array to store flags to keep record of the processed attributes. 
                
		Scanner input = new Scanner(System.in);
		System.out.println("Enter training file with its path:  ");
		String train_file= input.nextLine();
		System.out.println("Enter testing file with its path ");
		String test_file = input.nextLine();
		File tf = new File(train_file);
    	
                
            try
            {
    		
        	Scanner scan = new Scanner(tf);
                for(int i=0; i<6; i++)
        	{
        		//scan.next();
        		attributes[i] = scan.nextInt();
        	}
        	
        	while (scan.hasNextInt())  //storing file input into an ArrayList.
        	{
        		datset.add(new DataSet(scan.nextInt(),scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
        		
                }
		scan.close();
		
    	     }
    	
        catch(FileNotFoundException filenotfound)
    	{
       		System.out.println("File not found");
    	}
            
    	for(int i=0;i<datset.size();i++)
    	{
    		if(datset.get(i).getClas()==1)
    		{
    			class_oneCount++;
    		}
    		else
    		{
    			class_zeroCount++;
    		}
    	}
    	
    	initial_Entropy = -((class_oneCount/(class_oneCount+class_zeroCount))*(Math.log(class_oneCount/(class_oneCount+class_zeroCount))/Math.log(2))+(class_zeroCount/(class_oneCount+class_zeroCount))*(Math.log(class_zeroCount/(class_oneCount+class_zeroCount)))/Math.log(2)); //Initial entropy 
    	
    	int c0 =0,c1 =0;
    	
    	Nodes t_root = new Nodes(initial_Entropy);
    	
    	
		for(int k=0;k<datset.size();k++)
		{
			if(datset.get(k).getClas() == 0)
			{
				c0 ++;
			}
			else
			{
				c1++;
			}
		}
		t_root.set_Class0(c0); 
		t_root.set_Class1(c1);
	
                
        BuildTree bt = new BuildTree(attributes);
  
    	bt.newNode(datset, t_root);
    	
    	t_root.set_branch(-1);
    	
  
    	System.out.println(" ---------------- Decision Tree ----------------");
    	displayTree(t_root, 0);	
        train_Accuracy(datset, t_root);
    	test_Accuracy(t_root, test_file);
    	      
	}
        
         
	
        public static void train_Accuracy(ArrayList<DataSet> d, Nodes tn) // method to calculate accuracy of the Training data.
	{
        Nodes x, currentNode;
	int b;
	double accuracy=0;
	ArrayList<DataSet> data ;
		
		data = d;
		x = tn;
		

		int correct_class =0, wrong_class=0;
		for(int i=0;i<(data.size());i++)
		{	
			
			tn = x;
			for(int j=0;j<6;j++)
			{
				
				
				for(int k=0; k< (tn.child.size());k++)
				{
				
					if(tn.child.get(k).branch == data.get(i).getA(tn.attribute))
					{
						tn = tn.child.get(k);
				
						break;
					}
				
				}
				
				
			}
		
			if((tn.prob_Class1 > tn.prob_Class0 && data.get(i).getClas() == 0) || (tn.prob_Class1 < tn.prob_Class0 && data.get(i).getClas() == 1))
			{
				correct_class++; 
			}
			else 
			{
				wrong_class++;
				
			}
				
		}
		//System.out.println();
                int total_train = correct_class+wrong_class; 
		accuracy = (correct_class*100.00)/(wrong_class + correct_class);
                accuracy = Math.round(accuracy * 100);
                accuracy = accuracy/100;
                System.out.println(" (B) Accuracy on training set ("+total_train+" instances) : "+accuracy+"%");
		
	}
	
	public static void test_Accuracy(Nodes tn, String fileName) 
	{
                Nodes x;
                double accuracy=0;
		x = tn;

		int correct_class =0, wrong_class=0;
		int[] arr = new int[6];
		File test_file = new File(fileName);
		ArrayList<DataSet> datset1 = new ArrayList<DataSet>();
		try{
    		Scanner scan = new Scanner(test_file);
        	
        	for(int i=0; i<6; i++)
        	{
        		scan.next();

        		arr[i] = scan.nextInt();

        	}
        	
        	while (scan.hasNextInt())
        	{
        		datset1.add(new DataSet(scan.nextInt(),scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
        		
        	}
       
		scan.close();
		
    	}
    	
        catch(FileNotFoundException filenotfound)
    	{
       		System.out.println("File not found");
    	}
	/*catch (IOException exception)
        {
            System.out.println("Error");
        }*/
		for(int i=0;i<(datset1.size());i++)
		{	
			
			tn = x;
			for(int j=0;j<6;j++)
			{
				
				
				for(int m=0; m< (tn.child.size());m++)
				{

					if(tn.child.get(m).branch == datset1.get(i).getA(tn.attribute))
					{
						tn = tn.child.get(m);
						break;
					}
				
				}
                        }

			if((tn.prob_Class1 > tn.prob_Class0 && datset1.get(i).getClas() == 0) || (tn.prob_Class1 < tn.prob_Class0 && datset1.get(i).getClas() == 1))
			{
				correct_class++; 
			}
			else 
			{
				wrong_class++;
				
			}
				
		}
		
                int total_test = correct_class+wrong_class;
		accuracy = (correct_class*100.00)/(wrong_class + correct_class); 
                accuracy = Math.round(accuracy * 100);
                accuracy = accuracy/100;
                System.out.println(" (C) Accuracy on test set (" + total_test+ " instances): " +accuracy+"%");
		
	}
        
        
        
        	public static void displayTree(Nodes n, int p)
	{
		
		if((n.branch != -1))
		{
			for(int i=0;i<p-1;i++)
			{
				System.out.print("| ");
			}
		}
		/*if(n.branch != -1)
		{
			System.out.print( "attr " + n.parent+ " = " +n.branch);
		}*/
                
		if(n.branch != -1)
		{
			System.out.print( "attr " + n.parent.attribute + " = " +n.branch +" : ");
		}
			
		
		if(n.attribute == 0)
		{
			if(n.branch != -1)
			{
                            if(n.prob_Class1>n.prob_Class0)
				{
					System.out.print(0);
				}
				else
				{
					System.out.print(1);
				}
			}
			
		}
		if((n.branch != -1))
		{
			System.out.println();
		}
		
		for(int i=0;i<n.child.size();i++)
		{
			displayTree(n.child.get(i), p+1);
		}
	}
	
	
}
