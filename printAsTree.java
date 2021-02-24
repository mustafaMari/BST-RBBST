
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import Class7.BST.Node;

public class printAsTree {
	Comparator<Integer> comp =  new Comparator<Integer>(){
		 public int compare(Integer o1, Integer o2) {
		 return o1-o2;
		 }}; 
	 BST<Integer> tree=new BST<Integer>(comp); 
		
	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> void print(Node node) { 
		 List<List<T>> spaces = new ArrayList<List<T>>(); 
		 //storing each level in a list 
		 List<Node> level = new ArrayList<Node>();
		 List<Node> next = new ArrayList<Node>();
		 level.add(node);
		 int nn = 1; 
		 int width = 0; 
		 while ( nn != 0) {
			 List<T> space = new ArrayList<T>();
			 nn = 0; 
			 for (Node n : level) {
				 if (n == null) {
					 space.add(null); 
					 // one for each child 
					 next.add(null);
					 next.add(null);
				 }else {
					 T aa = (T) n.value; 
					 space.add(aa);
					 
				 }
			 }
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //task 2; 
//		public void display() {
//			display1(root);
//		}
//		private void display(Node node, int space, int height) {
//			if (node == null )
//				return;
//			space += height; 
//			display(node.right, space, height); 
//			System.out.println();
//			for (int x = height; x<space; x++) 
//				System.out.print(' ');
//			System.out.print(node.value); 
//			System.out.println(); 
//			display(node.left, space, height);
//		} 
//		private void display1(Node node) {
//			if (node == null )
//				return; 
//			int height = height(root);
//			for (int x = 0; x<height; x++);
//			leftSpace = leftSpace(root);
//			rightSpace = rightSpace (root);
//			System.out.println(leftSpace + node.value + rightSpace);
//			display1(node.left);
//		}
//		private String rightSpace(Node node) { 
//			if (node == null )
//				return" "; 
//			for (int x=0; x<height(node.right)-1; x++)
//				rightSpace+="     ";
//			return rightSpace; 
//		}
//		private String leftSpace(Node node) { 
//			if (node == null )
//				return " "; 
//			for (int x=0; x<height(node.left)-1; x++)
//				leftSpace+="     ";
//			return leftSpace;
//		}
//		public String printTree(Node node) {
//			if (node  == null )
//				return "";
//			StringBuilder sb = new StringBuilder();
//			sb.append(node.value);
//			String pointerRight = "└──";
//			String pointerLeft = (node.right != null) ? "├──" : "└──"; 
//			printChild(sb,"",pointerLeft,node.left,node.right != null);
//			printChild(sb,"",pointerRight,node.right,false); 
//			return sb.toString();
//		}
//		private void printChild(StringBuilder sb, String padding,
//				String pointer, Node node, boolean hasRightSibling) {
//				    if (node != null) {
//				        sb.append("\n");
//				        sb.append(padding);
//				        sb.append(pointer);
//				        sb.append(node.value);
//				 
//				        StringBuilder paddingBuilder = new StringBuilder(padding);
//				        if (hasRightSibling) {
//				            paddingBuilder.append("│  ");
//				        } else {
//				            paddingBuilder.append("   ");
//				        }
//				 
//				        String paddingForBoth = paddingBuilder.toString();
//				        String pointerRight = "└──";
//				        String pointerLeft = (node.right != null) ? "├──" : "└──";
//				 
//				        printChild(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
//				        printChild(sb, paddingForBoth, pointerRight, node.right, false);
//				    }
//		}
//		public void print(PrintStream os) {
//		    os.print(printTree(root));
//		}
//		public void drawTree(Node root) 
//	    {
//	        int height = height(root);
//	        setLevels (root, 0);
//	        
//	        int depthChildCount[] = new int [height+1];
//	        
//	        
//	        LinkedList<Node> q = new  LinkedList<Node> ();
//	        q.add(root.left);
//	        q.add(root.right);
//	        
//	        // draw root first
//	        root.drawPos = (int)Math.pow(2, height-1)*H_SPREAD;
//	        currDrawLevel = root.level;
//	        currSpaceCount = root.drawPos;
//	        System.out.print(getSpace(root.drawPos) + root.value);
//	        
//	        while (!q.isEmpty())
//	        {
//	            Node ele = q.pollFirst();
//	            drawElement (ele, depthChildCount, height, q);
//	            if (ele == null)
//	                continue;
//	            q.add(ele.left);
//	            q.add(ele.right);
//	        }
//	        System.out.println();
//	    }
//	    static int currDrawLevel  = -1;
//	    static int currSpaceCount = -1;
//	    static final int H_SPREAD = 3; 
//		private void setLevels (Node r, int level)
//	    {
//	        if (r == null)
//	            return;
//	        r.level = level;
//	        setLevels (r.left, level+1);
//	        setLevels (r.right, level+1);
//	    }
//		private String getSpace (int i)
//	    {
//	        String s = "";
//	        while (i-- > 0)
//	            s += " ";
//	        return s;
//	    }
//		private void drawElement(Node ele, int depthChildCount[], int depth, LinkedList<Node> q) 
//	    {
//	        if (ele == null)
//	            return;
//	        
//	        if (ele.level != currDrawLevel)
//	        {
//	            currDrawLevel = ele.level;
//	            currSpaceCount = 0;
//	            System.out.println();
//	            for (int i=0; i<(depth-ele.level+1); i++)
//	            {
//	                int drawn = 0;
//	                
//	                if (parent(ele.value).left != null)
//	                {
//	                	
//	                    drawn = parent(ele.value).drawPos - 2*i - 2;
//	                    System.out.print(getSpace(drawn));
//	                }
//	                if (parent(ele.value).right != null)
//	                {
//	                    int drawn2 = parent(ele.value).drawPos + 2*i + 2;
//	                    System.out.print(getSpace(drawn2 - drawn));
//	                    drawn = drawn2;
//	                }
//	                
//	                Node doneParent = parent(ele.value);
//	                for (Node sibling: q)
//	                {
//	                    if (sibling == null)
//	                        continue;
//	                    if (parent(sibling.value) == doneParent)
//	                        continue;
//	                    doneParent = parent(sibling.value);
//	                    if (parent(sibling.value).left != null)
//	                    {
//	                        int drawn2 = parent(sibling.value).drawPos - 2*i - 2;
//	                        System.out.print(getSpace(drawn2-drawn-1));
//	                        drawn = drawn2;
//	                    }
//	                    
//	                    if (parent(sibling.value).right != null)
//	                    {
//	                        int drawn2 = parent(sibling.value).drawPos + 2*i + 2;
//	                        System.out.print(getSpace(drawn2-drawn-1));
//	                        drawn = drawn2;
//	                    }
//	                }
//	                System.out.println();
//	            }
//	        }
//	        int offset=0;
//	        int numDigits = (int)Math.ceil(Math.log10(ele.idata));
//	        if (parent(ele.value).left == ele)
//	        {
//	            ele.drawPos = parent(ele.value).drawPos - H_SPREAD*(depth-currDrawLevel+1);
//	            //offset = 2;
//	            offset += numDigits/2;
//	        }
//	        else
//	        {
//	            ele.drawPos = parent(ele.value).drawPos + H_SPREAD*(depth-currDrawLevel+1);
//	            //offset = -2;
//	            offset -= numDigits;
//	        }
//	        
//	        System.out.print (getSpace(ele.drawPos - currSpaceCount + offset) +ele.idata +ele.value);
//	        currSpaceCount = ele.drawPos + numDigits/2;
//	    }
	//	
//		private Node parent(T elem) {
//		return findParent(elem, root, null);
	//}
	//

}
