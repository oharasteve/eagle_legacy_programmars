// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural
{
	using Natural_Comment = com.eagle.programmar.Natural.Terminals.Natural_Comment;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using Natural_PunctuationChoice = com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Natural_Condition : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Natural_Condition_Base baseExpr;
		public Natural_Condition_Base baseExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_OperatorExpression opExpr;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Natural_AndOr> clauses;
		public  OPT;

		public class Natural_Condition_Base : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Expression XXexpr;
			public Natural_Expression XXexpr;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_NotCondition extends com.eagle.tokens.TokenSequence
			public class Natural_NotCondition : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword NOT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("NOT");
				public Natural_Keyword NOT = new Natural_Keyword("NOT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Natural_Condition cond;
				public Natural_Condition cond;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}
		}

		public class Natural_AndOr : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Comment comment;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice andOr = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("AND", "OR");
			public Natural_KeywordChoice andOr = new Natural_KeywordChoice("AND", "OR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Natural_ConditionClause conditionClause;
			public Natural_ConditionClause conditionClause;
		}

		public class Natural_ConditionClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Condition_Base baseExpr;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Natural_OperatorExpression opExpr;
			public Natural_OperatorExpression opExpr;
		}

		public class Natural_OperatorExpression : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Natural_Relational_Operator operator;
			public Natural_Relational_Operator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Natural_Expression expr;
			public Natural_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_ThruCondition thru;
			public  OPT;

			public class Natural_Relational_Operator : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_PunctuationChoice XXoperator = new com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice(">=", "<=", "<", ">", "=");
				public Natural_PunctuationChoice XXoperator = new Natural_PunctuationChoice(">=", "<=", "<", ">", "=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_KeywordChoice XXGT = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("GT", "LT", "NE");
				public Natural_KeywordChoice XXGT = new Natural_KeywordChoice("GT", "LT", "NE");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_Not_Equals extends com.eagle.tokens.TokenSequence
				public class Natural_Not_Equals : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword NOT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("NOT");
					public Natural_Keyword NOT = new Natural_Keyword("NOT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
					public PunctuationEquals equals;
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_Less_Than extends com.eagle.tokens.TokenSequence
				public class Natural_Less_Than : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword LESS = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("LESS");
					public Natural_Keyword LESS = new Natural_Keyword("LESS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword THAN = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("THAN");
					public Natural_Keyword THAN = new Natural_Keyword("THAN");
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_Greater_Than extends com.eagle.tokens.TokenSequence
				public class Natural_Greater_Than : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword GREATER = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("GREATER");
					public Natural_Keyword GREATER = new Natural_Keyword("GREATER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword THAN = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("THAN");
					public Natural_Keyword THAN = new Natural_Keyword("THAN");
				}
			}

			public class Natural_ThruCondition : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword THRU = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("THRU");
				public Natural_Keyword THRU = new Natural_Keyword("THRU");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Natural_Expression expr;
				public Natural_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_ButNotCondition butNot;
				public  OPT;
			}

			public class Natural_ButNotCondition : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword BUT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("BUT");
				public Natural_Keyword BUT = new Natural_Keyword("BUT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword NOT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("NOT");
				public Natural_Keyword NOT = new Natural_Keyword("NOT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Natural_Expression expr;
				public Natural_Expression expr;
			}
		}
	}

}
