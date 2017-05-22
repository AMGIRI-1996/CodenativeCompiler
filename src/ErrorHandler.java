import java.util.Scanner;
import java.util.Stack;

public class ErrorHandler {
	static SyntaxTable SB=new SyntaxTable();
	static LexicalAnalyser LA=new LexicalAnalyser();
	static String[] Tnames={"V","=","Exp","if","while","[","(","]",")","||","&&","!",">","<","==","+","-","*","/","%","immutable","numconst","charconst","false","true","else","label",
			"radio","Button","checkbox","textbox","break","continue","int","String","float","$"};
	static String[] GOTO={"S'","S","opener","closer","Expr","andExpr","UnaryRelExpr","relExpr","relop","sumExpr","sumop","term","mulop","unaryExpr","factor","mutable","GUI","Constant","Type"};
	///static String[] labels={"I","VAR",""};
	static int[] ruleSize={1,4,4,3,1,3,1,1,1,1,3,1,3,1,2,1,3,1,1,1,1,3,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,4,2,9,1,1,1,1,1,1,1,1,1,1,1,1};
	static String[][] Grammer={
			
			{"S'","S"},
			{"S","Type","V","=","Constant"},
			{"S","Type","V","=","Exp"},
			{"S","if","Expr","opener"},
			{"S","closer"},
			{"S","while","Expr","opener"},
			{"opener","["},
			{"opener","("},
			{"closer","]"},
			{"closer",")"},
			{"Expr","Expr","or","andExpr"},
			{"Expr","andExpr"},
			{"andExpr","andExpr","and","UnaryRelExpr"},
			{"andExpr","UnaryRelExpr"},
			{"UnaryRelExpr","not","UnaryRelExpr"},
			{"UnaryRelExpr","relExpr"},
			{"relExpr","sumExpr","relop","sumExpr"},
			{"relExpr","sumExpr"},
			{"relop",">"},
			{"relop","<"},
			{"relop","=="},
			{"sumExpr","sumExpr","sumop","term"},
			{"sumExpr","term"},
			{"sumop","+"},
			{"sumop","-"},
			{"term","term","mulop","unaryExpr"},
			{"term","unaryExpr"},
			{"mulop","*"},
			{"mulop","/"},
			{"mulop","%"},
			{"unaryExpr","factor"},
			{"factor","mutable"},
			{"factor","immutable"},
			{"mutable","V"},
			{"mutable","numconst"},
			{"mutable","charconst"},
			{"mutable","false"},
			{"mutable","true"},
			{"S","else","if","Expr","opener"},
				{"S","else","opener"},
				{"S","GUI","Expr","Expr","Expr","Expr","Expr","Expr","Expr","Expr"},
				{"GUI","label"},
				{"GUI","radio"},
				{"GUI","Button"},
				{"GUI","checkbox"},
				{"GUI","textbox"},
				{"S","break"},
				{"S","continue"},
				{"Constant","numconst"},
				{"Constant","charconst"},
				{"Type","int"},
				{"Type","string"},
				{"Type","float"},
			
	};
	public static String[][] M=	{
			{"err","err","err","s3","s5","err","err","s13","s14","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s6","s15","s16","s17","s18","s19","s8","s9","s10","s11","s12","err"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","acc"},
			{"s20","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","err","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r4"},
			{"s32","err","err","err","err","err","err","err","err","err","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","s38","err","s40","s41","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","err","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r46"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r47"},
			{"r50","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r51","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r52","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r8"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r9"},
			{"r41","err","err","err","err","err","err","err","err","err","err","r41","err","err","err","err","err","err","err","err","r41","r41","r41","r41","r41","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r42","err","err","err","err","err","err","err","err","err","err","r42","err","err","err","err","err","err","err","err","r42","r42","r42","r42","r42","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r43","err","err","err","err","err","err","err","err","err","err","r43","err","err","err","err","err","err","err","err","r43","r43","r43","r43","r43","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r44","err","err","err","err","err","err","err","err","err","err","r44","err","err","err","err","err","err","err","err","r44","r44","r44","r44","r44","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r45","err","err","err","err","err","err","err","err","err","err","r45","err","err","err","err","err","err","err","err","r45","r45","r45","r45","r45","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","s43","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","s40","s41","err","err","s45","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r11","err","err","err","err","r11","r11","err","err","r11","s46","r11","err","err","err","err","err","err","err","err","r11","r11","r11","r11","r11","err","err","err","err","err","err","err","err","err","err","err","r11"},
			{"r13","err","err","err","err","r13","r13","err","err","r13","r13","r13","err","err","err","err","err","err","err","err","r13","r13","r13","r13","r13","err","err","err","err","err","err","err","err","err","err","err","r13"},
			{"s32","err","err","err","err","err","err","err","err","err","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r15","err","err","err","err","r15","r15","err","err","r15","r15","r15","err","err","err","err","err","err","err","err","r15","r15","r15","r15","r15","err","err","err","err","err","err","err","err","err","err","err","r15"},
			{"r17","err","err","err","err","r17","r17","err","err","r17","r17","r17","s50","s51","s52","s53","s54","err","err","err","r17","r17","r17","r17","r17","err","err","err","err","err","err","err","err","err","err","err","r17"},
			{"r22","err","err","err","err","r22","r22","err","err","r22","r22","r22","r22","r22","r22","r22","r22","s56","s57","s58","r22","r22","r22","r22","r22","err","err","err","err","err","err","err","err","err","err","err","r22"},
			{"r26","err","err","err","err","r26","r26","err","err","r26","r26","r26","r26","r26","r26","r26","r26","r26","r26","r26","r26","r26","r26","r26","r26","err","err","err","err","err","err","err","err","err","err","err","r26"},
			{"r30","err","err","err","err","r30","r30","err","err","r30","r30","r30","r30","r30","r30","r30","r30","r30","r30","r30","r30","r30","r30","r30","r30","err","err","err","err","err","err","err","err","err","err","err","r30"},
			{"r31","err","err","err","err","r31","r31","err","err","r31","r31","r31","r31","r31","r31","r31","r31","r31","r31","r31","r31","r31","r31","r31","r31","err","err","err","err","err","err","err","err","err","err","err","r31"},
			{"r32","err","err","err","err","r32","r32","err","err","r32","r32","r32","r32","r32","r32","r32","r32","r32","r32","r32","r32","r32","r32","r32","r32","err","err","err","err","err","err","err","err","err","err","err","r32"},
			{"r33","err","err","err","err","r33","r33","err","err","r33","r33","r33","r33","r33","r33","r33","r33","r33","r33","r33","r33","r33","r33","r33","r33","err","err","err","err","err","err","err","err","err","err","err","r33"},
			{"r34","err","err","err","err","r34","r34","err","err","r34","r34","r34","r34","r34","r34","r34","r34","r34","r34","r34","r34","r34","r34","r34","r34","err","err","err","err","err","err","err","err","err","err","err","r34"},
			{"r35","err","err","err","err","r35","r35","err","err","r35","r35","r35","r35","r35","r35","r35","r35","r35","r35","r35","r35","r35","r35","r35","r35","err","err","err","err","err","err","err","err","err","err","err","r35"},
			{"r36","err","err","err","err","r36","r36","err","err","r36","r36","r36","r36","r36","r36","r36","r36","r36","r36","r36","r36","r36","r36","r36","r36","err","err","err","err","err","err","err","err","err","err","err","r37"},
			{"r37","err","err","err","err","r37","r37","err","err","r37","r37","r37","r37","r37","r37","r37","r37","r37","r37","r37","r37","r37","r37","r37","r37","err","err","err","err","err","err","err","err","err","err","err","r37"},
			{"err","err","err","err","err","s40","s41","err","err","s45","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","err","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r39"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r6"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r7"},
			{"s32","err","err","err","err","err","err","err","err","s45","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","s63","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s64","s65","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r3"},
			{"s32","err","err","err","err","err","err","err","err","err","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","err","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r14","err","err","err","err","r14","r14","err","err","r14","r14","r14","err","err","err","err","err","err","err","err","r14","r14","r14","r14","r14","err","err","err","err","err","err","err","err","err","err","err","r14"},
			{"s32","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r18","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r18","r18","r18","r18","r18","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r19","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r19","r19","r19","r19","r19","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r20","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r20","r20","r20","r20","r20","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r23","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r23","r23","r23","r23","r23","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r24","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r24","r24","r24","r24","r24","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r27","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r27","r27","r27","r27","r27","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r28","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r28","r28","r28","r28","r28","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"r29","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r29","r29","r29","r29","r29","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r5"},
			{"err","err","err","err","err","s40","s41","err","err","s45","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","s45","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r1"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r2"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r48"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r49"},
			{"r10","err","err","err","err","r10","r10","err","err","r10","s46","r10","err","err","err","err","err","err","err","err","r10","r10","r10","r10","r10","err","err","err","err","err","err","err","err","err","err","err","r10"},
			{"r12","err","err","err","err","r12","r12","err","err","r12","r12","r12","err","err","err","err","err","err","err","err","r12","r12","r12","r12","r12","err","err","err","err","err","err","err","err","err","err","err","r12"},
			{"r16","err","err","err","err","r16","r16","err","err","r16","r16","r16","err","err","err","s53","s54","err","err","err","r16","r16","r16","r16","r16","err","err","err","err","err","err","err","err","err","err","err","r16"},
			{"r21","err","err","err","err","r21","r21","err","err","r21","r21","r21","r21","r21","r21","r21","r21","s56","s57","s58","r21","r21","r21","r21","r21","err","err","err","err","err","err","err","err","err","err","err","r21"},
			{"r25","err","err","err","err","r25","r25","err","err","r25","r25","r25","r25","r25","r25","r25","r25","r25","r25","r25","r25","r25","r25","r25","r25","err","err","err","err","err","err","err","err","err","err","err","r25"},
			{"err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r38"},
			{"s32","err","err","err","err","err","err","err","err","s45","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","s45","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","s45","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","s45","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"s32","err","err","err","err","err","err","err","err","s45","err","s24","err","err","err","err","err","err","err","err","s31","s33","s34","s35","s36","err","err","err","err","err","err","err","err","err","err","err","err"},
			{"err","err","err","err","err","err","err","err","err","s45","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r40"}
			};


		public static int[][] G={
						{-1,1,-1,4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,7,-1,2},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,21,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,37,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,39,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,42,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,44,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,47,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,48,-1,49,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,55,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,59,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,60,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,61,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,62,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,66,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,67,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,68,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,69,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,70,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,71,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,72,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,49,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,55,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
						{-1,-1,-1,-1,73,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,74,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,75,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,76,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,77,22,23,25,-1,26,-1,27,-1,28,29,30,-1,-1,-1},
						{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
			};
	public static int getTI(String t){
		int i=0;
		for(String x:Tnames){
			if(x.equals(t)) return i;
			i++;
		}
		return -1;
	}
	
	public static int getGOTOI(String nt){
		int i=0;
		for(String x:GOTO){
			if(x.equals(nt)) return i;
			i++;
		}
		return -1;
	}
	
	
	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		String s;
		s=in.nextLine();
		Token[] sentence = new Token[1000];
		int Tokeni=0;
		try {
			int fl=0;
			String concat = null;
			for (String x : s.split(" ")){
				if(fl==1){
					if(x.charAt(x.length()-2)=='"'){
						fl=0;
						Token t=LA.detect(concat,SB);
						sentence[Tokeni]=t;
						Tokeni++;
					}
					concat.concat(x);
					
					continue;
				}else if(x.charAt(0)=='"'&& x.charAt(x.length()-1)!='"'){
					fl=1;
					concat=x;
					continue;
				}
				
				Token t=LA.detect(x,SB);
				sentence[Tokeni]=t;
				Tokeni++;
			}
			sentence[Tokeni++]=new Token("$");
			Stack<Integer> stack= new Stack();
			stack.push(0);
			IntermediateCode IC=new IntermediateCode();
			Token[] symbols = new Token[1000];
			int symboli=0;
			for(int i=0;i<Tokeni;i++){
				System.out.println(sentence[i].name+" is "+sentence[i].type);
				if(stack.isEmpty()){
					System.out.println("Stack empty Error");
					break;
				}
				int top = stack.peek();
				int index=getTI(sentence[i].type);
				if(index == -1){
					System.out.println(sentence[i].type+" is not a token");
				}else{
					String action=M[top][index];
					if(action.equals("err")){
						System.out.println("Error in action table");
						break;
					}else if(action.charAt(0)=='s'){
						
						String n=action.substring(1);
						stack.push(Integer.parseInt(n));
						System.out.println("Shift "+n);
					}else if(action.charAt(0)=='r'){
						String n=action.substring(1);
						int rule=Integer.parseInt(n);
						int np=ruleSize[rule];
						System.out.println("reduce "+rule);
						for(int j=0;j<np;j++){
							if(stack.isEmpty()){
								System.out.println("Stack empty Error");
								break;
							}
							System.out.println("poped "+stack.peek());
							stack.pop();
							
						}
						n=Grammer[rule][0];
						int nextindex=getGOTOI(n);
						if(stack.isEmpty()){
							System.out.println("Stack empty Error");
							break;
						}
						int tt=stack.peek();
						System.out.println("goto of "+tt+" to "+nextindex+"("+n+")");
						if(G[tt][nextindex]==-1){
							System.out.println("Error in goto");
							break;
						}else{
							System.out.println("pushed "+G[tt][nextindex]);
							stack.push(G[tt][nextindex]);
						}
						i--;
					}else if(action.equals("acc")){
						System.out.println("Accepted");
						System.out.println(IC.oneLine(sentence));
						break;
					}else{
						System.out.println("Error in Table not ");
						break;
					}
				}
				
				
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
