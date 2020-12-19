// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_PercentStatement extends TokenSequence
{
	public @S(10) PLI_Punctuation percent = new PLI_Punctuation('%');
	public @S(20) PLI_PercentWhat what;
	public @S(30) PunctuationSemicolon semicolon1;
	public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;
	
	public static class PLI_PercentWhat extends TokenChooser
	{
		public @CHOICE static class PLI_PercentAssignment extends TokenSequence
		{
			public @S(10) PLI_Variable_Definition var;
			public @S(20) PunctuationEquals equals;
			public @S(30) PLI_Expression expr;
		}
		
		public @CHOICE static class PLI_PercentProcess extends TokenSequence
		{
			public @S(10) @DOC("7.43") PLI_Keyword PROCESS = new PLI_Keyword("PROCESS");
			public @S(20) PLI_Keyword GOSTMT = new PLI_Keyword("GOSTMT");
		}
		
		public @CHOICE static class PLI_PercentInclude extends TokenSequence
		{
			public @S(10) @DOC("7.29") PLI_Keyword INCLUDE = new PLI_Keyword("INCLUDE");
			public @S(20) PLI_ProcessIncludeWhat what;
			
			public static class PLI_ProcessIncludeWhat extends TokenChooser
			{
				public @CHOICE PLI_Literal literal;
				public @CHOICE PLI_Identifier_Reference var;
			}
		}
		
		public @CHOICE static class PLI_PercentDeclare extends TokenSequence
		{
			public @S(10) @DOC("7.10") PLI_Keyword DECLARE = new PLI_Keyword("DECLARE");
			public @S(20) @OPT PunctuationLeftParen leftParen;
			public @S(30) SeparatedList<PLI_Variable_Definition,PunctuationComma> vars;
			public @S(40) @OPT PunctuationRightParen rightParen;
			public @S(50) PLI_KeywordChoice type = new PLI_KeywordChoice("FIXED", "CHARACTER");
		}
		
		public @CHOICE static class PLI_PercentActivate extends TokenSequence
		{
			public @S(10) PLI_Keyword ACTIVATE = new PLI_Keyword("ACTIVATE");
			public @S(20) SeparatedList<PLI_Identifier_Reference,PunctuationComma> vars;
			public @S(30) @OPT PLI_Keyword NORESCAN = new PLI_Keyword("NORESCAN");
		}
		
		public @CHOICE static class PLI_PercentDeactivate extends TokenSequence
		{
			public @S(10) @DOC("7.8") PLI_Keyword DEACTIVATE = new PLI_Keyword("DEACTIVATE");
			public @S(20) PLI_Identifier_Reference var;
		}
		
		public @CHOICE static class PLI_PercentSkip extends TokenSequence
		{
			public @S(10) PLI_Keyword SKIP = new PLI_Keyword("SKIP");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) PLI_Number number;
			public @S(40) PunctuationRightParen rightParen;
		}
	}
}
