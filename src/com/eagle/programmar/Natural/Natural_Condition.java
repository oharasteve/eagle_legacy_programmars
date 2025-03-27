// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Terminals.Natural_Comment;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_Condition extends TokenSequence
{
	public @S(10) Natural_Condition_Base baseExpr;
	public @S(20) @OPT Natural_OperatorExpression opExpr;
	public @S(30) @OPT TokenList<Natural_AndOr> clauses;

	public static class Natural_Condition_Base extends TokenChooser
	{
		public @CHOICE Natural_Expression XXexpr;

		public @CHOICE static class Natural_NotCondition extends TokenSequence
		{
			public @S(10) Natural_Keyword NOT = new Natural_Keyword("NOT");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) Natural_Condition cond;
			public @S(40) PunctuationRightParen rightParen;
		}
	}

	public static class Natural_AndOr extends TokenSequence
	{
		public @S(10) @OPT Natural_Comment comment;
		public @S(20) Natural_KeywordChoice andOr = new Natural_KeywordChoice("AND", "OR");
		public @S(30) Natural_ConditionClause conditionClause;
	}

	public static class Natural_ConditionClause extends TokenSequence
	{
		public @S(10) @OPT Natural_Condition_Base baseExpr;
		public @S(20) Natural_OperatorExpression opExpr;
	}

	public static class Natural_OperatorExpression extends TokenSequence
	{
		public @S(10) Natural_Relational_Operator operator;
		public @S(20) Natural_Expression expr;
		public @S(30) @OPT Natural_ThruCondition thru;

		public static class Natural_Relational_Operator extends TokenChooser
		{
			public @CHOICE Natural_PunctuationChoice XXoperator = new Natural_PunctuationChoice(
					">=", "<=", "<", ">", "=");
			public @CHOICE Natural_KeywordChoice XXGT = new Natural_KeywordChoice("GT", "LT", "NE");

			public @CHOICE static class Natural_Not_Equals extends TokenSequence
			{
				public @S(10) Natural_Keyword NOT = new Natural_Keyword("NOT");
				public @S(20) PunctuationEquals equals;
			}

			public @CHOICE static class Natural_Less_Than extends TokenSequence
			{
				public @S(10) Natural_Keyword LESS = new Natural_Keyword("LESS");
				public @S(20) Natural_Keyword THAN = new Natural_Keyword("THAN");
			}

			public @CHOICE static class Natural_Greater_Than extends TokenSequence
			{
				public @S(10) Natural_Keyword GREATER = new Natural_Keyword("GREATER");
				public @S(20) Natural_Keyword THAN = new Natural_Keyword("THAN");
			}
		}

		public static class Natural_ThruCondition extends TokenSequence
		{
			public @S(10) Natural_Keyword THRU = new Natural_Keyword("THRU");
			public @S(20) Natural_Expression expr;
			public @S(30) @OPT Natural_ButNotCondition butNot;
		}

		public static class Natural_ButNotCondition extends TokenSequence
		{
			public @S(10) Natural_Keyword BUT = new Natural_Keyword("BUT");
			public @S(20) Natural_Keyword NOT = new Natural_Keyword("NOT");
			public @S(30) Natural_Expression expr;
		}
	}
}
