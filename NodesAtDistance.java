import java.util.Scanner;


class Node {
    int key;
    Node right;
    Node left;
    public Node(int key){
        this.key = key;
    }
}

class NodesAtDistance 
{ 
    Node root;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the distance, k : ");
        int k = input.nextInt();
        NodesAtDistance  bt1 = new NodesAtDistance();
        bt1.root = new Node(20);
        bt1.root.left = new Node(8);
        bt1.root.right = new Node(22);
        bt1.root.left.left = new Node(4);
        bt1.root.left.right = new Node(12);
		bt1.root.left.right.left = new Node(10);
		bt1.root.left.right.right = new Node(14);
        Node target = bt1.root.left.right.right;
        printNodesAtDistanceK(bt1.root, target, k);
        input.close();
    }

	static void printNodesAtDistanceKDown(Node node, int k) { 
		if (node == null || k < 0) 
			return; 

		if (k == 0){
	        System.out.print(node.key + " "); 
            return; 
        }
		printNodesAtDistanceKDown(node.left, k - 1); 
		printNodesAtDistanceKDown(node.right, k - 1); 
	}
	static int printNodesAtDistanceK(Node node, Node target, int k) {  
		if (node == null) 
			return -1;
		if (node == target) { 
			printNodesAtDistanceKDown(node, k); 
			return 0; 
		}
		int distanceLeft = printNodesAtDistanceK(node.left, target, k); 
		if (distanceLeft != -1) { 
			if (distanceLeft + 1 == k) 
				System.out.print(node.key + " "); 
			else
            	printNodesAtDistanceKDown(node.right, k - distanceLeft - 2);  
			return 1 + distanceLeft; 
		} 
		int distanceRight = printNodesAtDistanceK(node.right, target, k); 
		if (distanceRight != -1) { 
			if (distanceRight + 1 == k) 
                System.out.print(node.key + " "); 
			else
                printNodesAtDistanceKDown(node.left, k - distanceRight - 2); 
			return 1 + distanceRight; 
		} 
		return -1; 
	} 
} 
