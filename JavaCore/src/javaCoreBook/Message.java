package javaCoreBook;

//call the program from the command line:
// java Message -g cruel world
public class Message {
	
public static void main(String[] args){
	if(args[0].equals("-h")) System.out.print("Hello,");
	else if(args[0].equals("-g")) System.out.print("Goodbye,");
	
	for(int i=0;i<args.length;i++) System.out.print(" "+args[i]);
	
	System.out.println("!");
}
}
