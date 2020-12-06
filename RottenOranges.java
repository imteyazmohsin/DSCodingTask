
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RottenOranges 
{
    private static int rowCount = 0;
    private static int colCount = 0;
    private static int freshOranges = 0;
    private static int totalOranges = 0;
    
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter no of rows of matrix: ");
        rowCount = input.nextInt();
        System.out.println("Enter no of columns of matrix: ");
        colCount = input.nextInt();
        int[][] arr = new int[rowCount][colCount];
        System.out.println("Enter the values (0 or 1 or 2) of Orange matrix: ");
        for (int i = 0; i < rowCount; i++) {
              for (int j = 0; j < colCount; j++) {
                System.out.println("Row [" + i + "]:  Column " + j + " :");
                arr[i][j] = input.nextInt();
               }
        }
        int ans = getTimeToRotOranges(arr);
        calculateTotalAndFreshOrangesCount(arr);
        System.out.println("time frames: " + ans);
        System.out.println("fresh oranges: " + freshOranges);
        System.out.println("Rotten oranges: " + (totalOranges - freshOranges));
        input.close();
	}
	
	static class Element{
		int xPos = 0;
		int yPos = 0;
		Element(int x,int y){
			this.xPos = x;
			this.yPos = y;
		}
	}
	
	public static boolean isValid(int i, int j){
		return (i >= 0 && j >= 0 && i < rowCount && j < colCount);
	}
	
	public static boolean isDelimeter(Element temp){
		return (temp.xPos == -1 && temp.yPos == -1);
	}
	
	public static int getTimeToRotOranges(int arr[][])
	{
		Queue<Element> Q = new LinkedList<>();
		Element temp; 
		int time = 0;
		for (int i=0; i < rowCount; i++){
		    for (int j=0; j < colCount; j++){
			    if (arr[i][j] == 2)
				    Q.add(new Element(i,j));
            }
        }	
		Q.add(new Element(-1,-1));
		while(!Q.isEmpty()){
		    boolean flag = false;
			while(!isDelimeter(Q.peek())){
				temp = Q.peek();
				if(isValid(temp.xPos + 1, temp.yPos) && arr[temp.xPos + 1][temp.yPos] == 1){
					if(!flag){
						time++;
						flag = true;
					}
					arr[temp.xPos + 1][temp.yPos] = 2;
					temp.xPos++;
					Q.add(new Element(temp.xPos, temp.yPos));
					temp.xPos--;
				}
				if (isValid(temp.xPos - 1, temp.yPos) && arr[temp.xPos - 1][temp.yPos] == 1){
						if (!flag){
							time++;
							flag = true;
						}
						arr[temp.xPos - 1][temp.yPos] = 2;
						temp.xPos--;
						Q.add(new Element(temp.xPos, temp.yPos)); 
						temp.xPos++;
				}
				if (isValid(temp.xPos, temp.yPos + 1) && arr[temp.xPos][temp.yPos + 1] == 1) {
						if(!flag){
							time++;
							flag = true;
						}
						arr[temp.xPos][temp.yPos + 1] = 2;
						temp.yPos++;
						Q.add(new Element(temp.xPos, temp.yPos)); 
						temp.yPos--;
					}
				if (isValid(temp.xPos, temp.yPos - 1) && arr[temp.xPos][temp.yPos - 1] == 1) {
						if (!flag){
							time++;
							flag = true;
						}
						arr[temp.xPos][temp.yPos - 1] = 2;
						temp.yPos--;
						Q.add(new Element(temp.xPos, temp.yPos)); 
				}
				Q.remove();	
			}
			Q.remove();
			if (!Q.isEmpty()) {
				Q.add(new Element(-1,-1));
			}
        }
		return time;
    }
    
    public static void calculateTotalAndFreshOrangesCount(int arr[][]){
         for (int i=0; i<rowCount; i++){
               for (int j=0; j<colCount; j++){
                  if (arr[i][j] == 1)
                     freshOranges++;
                  if (arr[i][j] != 0)
                    totalOranges++;
               }
        }
    }
}