// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Command.CMD_Statement;
import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class CMD_If_Statement extends TokenSequence
{
	public @S(10) @DOC("if.mspx") CMD_Keyword IF = new CMD_Keyword("if");
	public @S(20) @OPT CMD_Keyword NOT = new CMD_Keyword("not");
	public @S(30) CMD_IfWhat what;
	public @S(40) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public @S(50) CMD_Statement stmt;
	
	public static class CMD_IfWhat extends TokenChooser
	{
		public @LAST CMD_Literal literal;
		
		public @CHOICE static class CMD_IfDefined extends TokenSequence
		{
			public @S(10) CMD_Keyword DEFINED = new CMD_Keyword("defined");
			public @S(20) CMD_Argument var;
		}

		public @CHOICE static class CMD_IfErrorLevel extends TokenSequence
		{
			public @S(10) CMD_Keyword ERRORLEVEL = new CMD_Keyword("errorlevel");
			public @S(20) CMD_Number level;
		}

		public @CHOICE static class CMD_IfExist extends TokenSequence
		{
			public @S(10) CMD_Keyword EXIST = new CMD_Keyword("exist");
			public @S(20) CMD_Argument file;
		}
		
		public @CHOICE static class CMD_IfEqual extends TokenSequence
		{
			public @S(10) CMD_Argument expr1;
			public @S(20) CMD_IfOperator operator;
			public @S(30) CMD_Argument expr2;
			
			public static class CMD_IfOperator extends TokenChooser
			{
				public @CHOICE CMD_KeywordChoice operator = new CMD_KeywordChoice(
						"equ",
						"geq",
						"gtr",
						"leq",
						"lss",
						"neq");
				
				public @CHOICE static class CMD_EqualsEquals extends TokenSequence
				{
					public @S(10) CMD_Punctuation equals = new CMD_Punctuation("==");
					public @S(20) @OPT PunctuationHyphen minus;
				}
			}
		}

		public @LAST static class CMD_IfCondition extends TokenSequence
		{
			public @S(10) CMD_Argument condition;
		}
	}
}
