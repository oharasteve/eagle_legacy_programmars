// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Statements.Python_FunctionDefinition.Python_Decorator;
import com.eagle.programmar.Python.Symbols.Python_Class_Definition;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_ClassDeclaration extends TokenSequence implements AbstractClass
{
	public @S(10) @OPT TokenList<Python_Decorator> decorator;
	public @S(20) @OPT Python_EndOfLine eoln;
	public @S(30) @DOC("compound_stmts.html#class-definitions") @NOSPACE Python_Keyword CLASS = new Python_Keyword("class");
	public @S(40) Python_Class_Definition name;
	public @S(50) @OPT Python_ClassSuper superClass;
	public @S(60) @NOSPACE PunctuationColon colon;
	public @S(70) Python_SingleOrMultiLineStatement statements;
	
	public static class Python_ClassSuper extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT Python_EndOfLine eoln;
		public @S(30) @OPT Python_Type type;
		public @S(40) @OPT Python_ClassSuper superClass;
		public @S(50) @OPT @SYNTAX(Python_Multiline_Syntax.class) TokenList<Python_MoreTypes> moreTypes; 
		public @S(60) PunctuationRightParen rightParen;

		public static class Python_MoreTypes extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) Python_Type type;
		}
	}
}
