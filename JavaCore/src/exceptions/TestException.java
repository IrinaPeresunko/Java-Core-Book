package exceptions;

public class TestException {
	public static void f(){
		System.out.println("2.in");
		try{
			g();
		}
		catch(Error e){
			System.err.println("2.catch");
		}
		System.out.println("2.out");
	}
	public static void g(){
		System.out.println("3.in");
		h();
		System.out.println("3.out");
	}
	public static void h(){
		System.out.println("4.in");
		if(true){
			System.out.println("4.RETURN");
			throw new Error();
		}
		//System.err.println("4.out");
	}
	public static void main(String[] args) {
		System.out.println("1.in");
		try{
			f();
		}
		catch(Error e){
			System.err.println("1.catch");
		}
		System.out.println("1.out");
	
	}

}
