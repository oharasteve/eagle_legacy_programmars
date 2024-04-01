// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Lambda_Expression extends PrimaryOperator 
{
	public @S(10) Python_Keyword LAMBDA = new Python_Keyword("lambda");
	public @S(20) @OPT PunctuationLeftParen leftParen;
	public @S(30) @OPT Python_Variable_List parameters;
	public @S(40) @OPT PunctuationRightParen rightParen;
	public @S(50) PunctuationColon colon;
	public @S(60) Python_Expression expr;
	
	public static class Python_Variable_List extends TokenSequence
	{
		public @S(10) @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
		public @S(20) Python_Variable var;
		public @S(30) @OPT Python_Variable_Default defaultValue;
		public @S(40) @OPT TokenList<Python_MoreVariablesInList> moreVars;

		public static class Python_MoreVariablesInList extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT TokenList<Python_Comment> comments;
			public @S(30) @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
			public @S(40) Python_Variable var;
			public @S(50) @OPT Python_Variable_Default defaultValue;
		}
		
		public static class Python_Variable_Default extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Python_Expression defaultValue;
		}
	}
}
