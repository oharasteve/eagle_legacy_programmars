// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Decorators;
import com.eagle.programmar.Python.Python_Statement.Python_StatementBlock;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Python_Type;
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
	public @S(10) @OPT TokenList<Python_Decorators> decorators;
	public @S(20) @OPT Python_EndOfLine eoln;
	public @S(30) @DOC("compound_stmts.html#class-definitions") @NOSPACE Python_Keyword CLASS = new Python_Keyword(
			"class");
	public @S(40) Python_Class_Definition name;
	public @S(50) @OPT Python_ClassSuper superClass;
	public @S(60) @NOSPACE PunctuationColon colon;
	public @S(70) Python_StatementBlock statements;

	public static class Python_ClassSuper extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT Python_EndOfLine eoln;
		public @S(30) @OPT Python_WhatSuper whatSuper;
		public @S(40) PunctuationRightParen rightParen;

		public static class Python_WhatSuper extends TokenSequence
		{
			public @S(10) Python_Type type;
			public @S(20) @OPT Python_ClassSuper superClass;
			public @S(30) @OPT @SYNTAX(Python_Multiline_Syntax.class) TokenList<Python_MoreTypes> moreTypes;
		}

		public static class Python_MoreTypes extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) Python_Type type;
		}
	}
}
