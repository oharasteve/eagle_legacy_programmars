// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Symbols.CMD_Variable_Definition;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Set_Statement extends TokenSequence
{
	public @S(10) @DOC("set.mspx") CMD_Keyword SET = new CMD_Keyword("set");
	public @S(20) CMD_Set_What setWhat;
	
	public static class CMD_Set_What extends TokenChooser
	{
		public @CHOICE static class CMS_Set_Regular extends TokenSequence
		{
			public @S(10) CMD_Variable_Definition var;
			public @S(20) PunctuationEquals equals;
			public @S(30) CMD_RestOfLine value;
		}
		
		public @CHOICE static class CMD_Set_Assigment extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword A = new CMD_Keyword("a");
			public @S(30) CMD_Variable_Definition var;
			public @S(40) PunctuationEquals equals;
			public @S(50) CMD_Expression expr;
		}
		
		public @CHOICE static class CMD_Set_Prompt extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword P = new CMD_Keyword("p");
			public @S(30) CMD_Variable_Definition var;
			public @S(40) PunctuationEquals equals;
			public @S(50) CMD_RestOfLine value;
		}
	}
}
