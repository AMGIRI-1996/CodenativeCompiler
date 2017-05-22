
public class SyntaxTable {
	
	int[] valuesI;
	float[] valuesF;
	int valuesIi,valuesFi,Vari;
	
	class Variable{
		String name;
		String type;
		int IntVal;
		String StrVal;
		float FloatVal;
		
		Variable(String var){
			name=var;
			IntVal=0;
			FloatVal=(float)0.000;
			StrVal="";
			type="ND";
		}
		Variable(){
			name="";
			IntVal=0;
			FloatVal=(float)0.000;
			StrVal="";
			type="ND";
		}
		
	}
	Variable[] Var;
	SyntaxTable(){
		
		Vari = 0;
		valuesIi=0;
		valuesFi=0;
		valuesI= new int[10000];
		valuesF= new float[10000];
		Var=new Variable[1000];
				
	}
	//String[] Int= new String[10000];

	void insertVar(String var,int val){
		Var[Vari].name=var;
		Var[Vari].IntVal=val;
		//Var[Vari].FloatVal=(float)0.000;
		//Var[Vari].StrVal="";
		Var[Vari].type="INT";
		Vari++;
		
	}
	
	void insertVar(String var,float val){
		Var[Vari].name=var;
		//Var[Vari].IntVal=0;
		Var[Vari].FloatVal=val;
		//Var[Vari].StrVal="";
		Var[Vari].type="FLOAT";
		Vari++;
		
	}
	
	void insertVar(String var,String val){
		
		Var[Vari].name=var;
		//Var[Vari].IntVal=0;
		//Var[Vari].FloatVal=(float)0.000;
		Var[Vari].StrVal=val;
		Var[Vari].type="STRING";
		Vari++;
		
	}
	void insertVar(String var){
		
		Var[Vari]=new Variable(var);
		/*Var[Vari].name=var;
		Var[Vari].IntVal=0;
		Var[Vari].FloatVal=(float)0.000;
		Var[Vari].StrVal="";
		Var[Vari].type="ND";*/
		Vari++;
	}
	
	
	
	/*
	void insertValues(int val){
		valuesI[valuesIi]=val;
		valuesIi++;
	}
	void insertValues(float val){
		valuesF[valuesFi]=val;
		valuesFi++;
	}
	*/
	boolean isPresent(String var){
		for(int i=0;i<Vari;i++){
			if(Var[i].name.equals(var)) return true;
		}
		
		return false;
	}
	
	
	Token getToken(String var){
		for(int i=0;i<Vari;i++){
			if(Var[i].name.equals(var)){
				Token x=new Token("V",var,Var[i].type);
				return x;
			}
		}
		
		
		
		Token x;
		switch(var){
			
			case "[":
				x=new Token("[");
				return x;
			case "]":
				 x=new Token("]");
				return x;
			
			case "(":
				x=new Token("(");
				return x;
			case ")":
				 x=new Token(")");
				return x;
			case "अगर":
				 x=new Token("if");
				return x;
			case "तार":
				 x=new Token("String");
				return x;
			case "पूर्णांक":
				 x=new Token("int");
				return x;
			case "अपूर्णांक ":
				 x=new Token("float");
				return x;
			case "अन्यथा":
				 x=new Token("else");
				return x;
			
			case "जबतक":
				 x=new Token("while");
				return x;
			case "विराम":
				 x=new Token("break");
				return x;
			case "जारी":
				 x=new Token("continue");
				return x;
			case "नामपत्र":
				 x=new Token("label");
				return x;
			case "बटन":
				 x=new Token("Button");
				return x;
			case "डिब्बा":
				 x=new Token("textbox");
				return x;
			case "टिक":
				 x=new Token("checkbox");
				return x;
			case "चुनाव":
				 x=new Token("radio");
				return x;
			case "सत्य":
				 x=new Token("true");
				return x;
			case "असत्य":
				 x=new Token("false");
				return x;
			
			case "=" :
				x=new Token("=");
				return x;
			case "<" :
				x=new Token("<");
				return x;
			case ">" :
				x=new Token(">");
				return x;
			case "==" :
				x=new Token("==");
				return x;
			case "+" :
				x=new Token("+");
				return x;
			case "-" :
				x=new Token("-");
				return x;
			case "*" :
				x=new Token("*");
				return x;
			case "/" :
				x=new Token("/");
				return x;
			case "%" :
				x=new Token("%");
				return x;
			case "&&" :
				x=new Token("&&");
				return x;

			case "||" :
				x=new Token("||");
				return x;
			case "!" :
				x=new Token("!");
				return x;
				
				
			
		}
		if(var.charAt(0)=='"'){
			String n=var.substring(1, var.length()-2);
			x= new Token("charconst",var,"STRING");
			return x;
		}
		
		insertVar(var);
		
		
		return getToken(var);
	}
	
	Token getToken(int var){
		Token x= new Token("numconst",""+var,"INT");
		return x;
	}
	
	Token getToken(float var){
		Token x= new Token("numconst",""+var,"Float");
		return x;
	}
	
	
	void updateVal(String var, int v){
		for(int i=0;i<Vari;i++){
			if(Var[i].name.equals(var)){
				Var[i].IntVal=v;
				Var[i].type="INT";
				return;
			}
		}
		insertVar(var,v);//if not present
	}
	void updateVal(String var, float v){
		for(int i=0;i<Vari;i++){
			if(Var[i].name.equals(var)){
				Var[i].FloatVal=v;
				Var[i].type="FLOAT";
				return;
			}
		}
		insertVar(var,v);//if not present
		
		
	}
	void updateVal(String var, String v){
		for(int i=0;i<Vari;i++){
			if(Var[i].name.equals(var)){
				Var[i].StrVal=v;
				Var[i].type="STRING";
				return;
			}
		}
		insertVar(var,v);//if not present
	}
}
