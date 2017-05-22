import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.Format.Field;
import java.util.Scanner;

public class LexicalAnalyser {
	
	static String[] keywords ={"पूर्णांक","अक्षर","अगर","अन्यथाअगर","अन्यथा","जबतक","विराम","जारी","नामपत्र","बटन","डिब्बा","टिक","चुनाव"};
			
	
	static void enterInST(String x){
		
	}
	static Token detect(String x,SyntaxTable SB){
		
		LanguageHelper lh= new LanguageHelper();
		x=lh.devanagariToLatin(x);
		String y=lh.latinToDevanagari(x);
		/*int flag=0;
		for(String keyword:keywords){
			if(y.equals(keyword)){
				flag=1;
				break;
			}
			//System.out.print(y+" "+keyword+" , ");
		}
		if(flag==0){
			try{
				Double val=Double.parseDouble(x);
				System.out.println( y+" is a number");
				
			}catch (Exception e){
				if(y.equals("â‚¹")){
					System.out.println( y+" is a Loop variable");
				}else{
					System.out.println( y+" is a variable");
				}
				//enterInST(y);
			}
			
		}else{
			
			System.out.println( y+" is Keyword");
		}*/
		try{
			int val=Integer.parseInt(x);
			System.out.println( y+" is a integer");
			return SB.getToken(val);

			
		}catch (Exception e){
			try{
				float val=Float.parseFloat(x);
				System.out.println( y+" is a float");
				return SB.getToken(val);
			}catch (Exception e2){
				System.out.println( y+" is a string");
				return SB.getToken(y);

					
			}
				
		}
		
	}
	public static void main(String args[]){
		String s = "चुनाव";
		Scanner in=new Scanner(System.in);
		
		s=in.nextLine();
		
		try {
			for (String x : s.split(" ")){
				//System.out.println(detect(x));
			}
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
