// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice;
import com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Gupta_Condition extends TokenSequence
{
	public @S(10) Gupta_ConditionClause conditionClause;
	public @S(20) @OPT TokenList<Gupta_AndOr> clauses;

	public static class Gupta_AndOr extends TokenSequence
	{
		public @S(10) Gupta_KeywordChoice andOr = new Gupta_KeywordChoice("AND", "OR");
		public @S(20) Gupta_ConditionClause conditionClause;
	}

	public static class Gupta_ConditionClause extends TokenSequence
	{
		public @S(10) Gupta_Expression expr;
		public @S(20) Gupta_OperatorExpression opExpr;

		public static class Gupta_OperatorExpression extends TokenSequence
		{
			public @S(10) Gupta_Relational_Operator operator;
			public @S(20) Gupta_Expression expr;

			public static class Gupta_Relational_Operator extends TokenChooser
			{
				public @CHOICE Gupta_PunctuationChoice operator = new Gupta_PunctuationChoice(">=", "<=", "<", ">",
						"=");
				public @CHOICE Gupta_KeywordChoice GT = new Gupta_KeywordChoice("GT", "LT", "NE");

				public @CHOICE static class Gupta_Not_Equals extends TokenSequence
				{
					public @S(10) Gupta_Keyword NOT = new Gupta_Keyword("NOT");
					public @S(20) PunctuationEquals equals;
				}

				public @CHOICE static class Gupta_Less_Than extends TokenSequence
				{
					public @S(10) Gupta_Keyword LESS = new Gupta_Keyword("LESS");
					public @S(20) Gupta_Keyword THAN = new Gupta_Keyword("THAN");
				}

				public @CHOICE static class Gupta_Greater_Than extends TokenSequence
				{
					public @S(10) Gupta_Keyword GREATER = new Gupta_Keyword("GREATER");
					public @S(20) Gupta_Keyword THAN = new Gupta_Keyword("THAN");
				}
			}
		}
	}
}
