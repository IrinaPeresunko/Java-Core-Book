package exceptions;

public class TestException2 {

	public static void main(String[] args) {
		try{
			throw new RuntimeException();
		}
		catch(Exception e){
			if(e instanceof RuntimeException){
				RuntimeException re = (RuntimeException) e;
				System.err.println("RuntimeException");
				re.toString();
			}
			else{
				System.out.println("NotRuntimeException");
			}
		}
	}

}
