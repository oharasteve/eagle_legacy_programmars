// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Gupta_Statement;
import com.eagle.programmar.Gupta.Gupta_Type;
import com.eagle.programmar.Gupta.Gupta_Variable_Declaration;
import com.eagle.programmar.Gupta.Symbols.Gupta_Function_Definition;
import com.eagle.programmar.Gupta.Terminals.Gupta_CommentToEndOfLine;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Gupta_Function extends Gupta_Declaration
{
	public @S(10) Gupta_Keyword Function = new Gupta_Keyword("Function");
	public @S(20) PunctuationColon colon;
	public @S(30) Gupta_Function_Definition functionName;
	
	public @S(40) Gupta_Function_Description description;
	public @S(50) Gupta_Function_Returns returns;
	public @S(60) Gupta_Function_Parameters parameters;
	public @S(70) Gupta_Function_Static_Variables staticVariables;
	public @S(80) Gupta_Function_Local_Variables localVariables;
	public @S(90) Gupta_Function_Actions actions;
	
	public static class Gupta_Function_Description extends Gupta_Declaration
	{
		public @S(10) Gupta_Keyword Description = new Gupta_Keyword("Description");
		public @S(20) Gupta_CommentToEndOfLine description;
	}
	
	public static class Gupta_Function_Returns extends Gupta_Declaration
	{
		public @S(10) Gupta_Keyword Returns = new Gupta_Keyword("Returns");
		public @S(20) @OPT Gupta_Function_Return_Type returnType;
		
		public static class Gupta_Function_Return_Type extends TokenSequence
		{
			public @S(10) Gupta_Type returnType;
			public @S(20) PunctuationColon colon;
		}
	}
	
	public static class Gupta_Function_Parameters extends Gupta_Declaration
	{
		public @S(10) Gupta_Keyword Parameters = new Gupta_Keyword("Parameters");
		public @S(20) @OPT TokenList<Gupta_Variable_Declaration> variables;
	}
	
	public static class Gupta_Function_Static_Variables extends Gupta_Declaration
	{
		public @S(10) Gupta_Keyword Static = new Gupta_Keyword("Static");
		public @S(20) Gupta_Keyword Variables = new Gupta_Keyword("Variables");
		public @S(30) @OPT TokenList<Gupta_Variable_Declaration> variables;
	}
	
	public static class Gupta_Function_Local_Variables extends Gupta_Declaration
	{
		public @S(10) Gupta_Keyword Local = new Gupta_Keyword("Local");
		public @S(20) Gupta_Keyword Variables = new Gupta_Keyword("Variables");
		public @S(30) @OPT TokenList<Gupta_Variable_Declaration> variables;
	}
	
	public static class Gupta_Function_Actions extends Gupta_Declaration
	{
		public @S(10) Gupta_Keyword Actions = new Gupta_Keyword("Actions");
		public @S(20) TokenList<Gupta_Statement> statements;
	}
}
