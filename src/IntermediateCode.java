
public class IntermediateCode {
	String ans="";
	String oneLine(Token[] x){
		LanguageHelper lh= new LanguageHelper();
		
		
		for(Token t:x){
			System.out.println(t.type);
			if(t.type.equals("$")){
				ans+="\n";
				return ans;
			}
			switch(t.type){
				case "charconst":
					
				case "numconst":
					
				
				case "V":
					String y=lh.devanagariToLatin(t.name);
					ans+=y;
					ans+=" ";
					break;
					
				case "while":
				case "if":
					ans+=t.type;
					ans+="( ";
					break;
				
				case "[":
					
					ans+="){ ";
					break;
				case "]":
					
					ans+=" }";
					
					break;
					
				default:
					ans+=t.type;
					ans+=" ";
					break;
			}
		}
		ans+="\n";
		return ans;
	}
}
