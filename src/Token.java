
public class Token {
	String type,name,type2;
	
	
	Token(String t,String v){
		type=t;name=v;type2="ND";
	}
	Token(String t,String v,String t2){
		type=t;name=v;type2=t2;
	}
	
	Token(String t){
		type=t;
	}
	
}
