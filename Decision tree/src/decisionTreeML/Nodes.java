package decisionTreeML;

import java.io.*;
import java.util.*;

public class Nodes {
    
        ArrayList<Nodes> child;
        public Nodes parent;
	public int attribute,branch,leaf,prob_Class0,prob_Class1; 
        public Double NodeEntropyValue;
        
	public Nodes(double entropy) 
	{
		NodeEntropyValue = entropy;
		parent = null;
		child = new ArrayList<Nodes>();
	}
	public void add_Child(double name) 
	{
        this.add_Child(new Nodes(name));
        }

        public void add_Child(Nodes child) 
        {
            this.child.add(child);
        }
        public void set_node(int new_node)
        {
            attribute = new_node;
        }
        public void set_branch(int br)
        {
            branch = br;
        }
         public void set_Class0(int c0)
        {
            prob_Class0 = c0;
        }
        public void set_Class1(int c1)
        {
            prob_Class1 = c1;
        }
       

}
