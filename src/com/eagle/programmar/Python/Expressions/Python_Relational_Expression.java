// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Python_Relational_Expression extends PrecedenceOperator 
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Relational_Operator relOp;
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	public static class Python_Relational_Operator extends TokenChooser
	{
		public @CHOICE Python_PunctuationChoice operator = new Python_PunctuationChoice(
				"==", "!=", "<>", "<=", ">=", "<", ">");
		
		public @CHOICE static class Python_IN_Operator extends TokenSequence
		{
			public @S(10) @OPT Python_Keyword NOT = new Python_Keyword("not");
			public @S(20) Python_Keyword IN = new Python_Keyword("in");
		}
		
		public @CHOICE static class Python_IS_Operator extends TokenSequence
		{
			public @S(10) Python_Keyword IS = new Python_Keyword("is");
			public @S(20) @OPT Python_Keyword NOT = new Python_Keyword("not");
		}
	}
}
