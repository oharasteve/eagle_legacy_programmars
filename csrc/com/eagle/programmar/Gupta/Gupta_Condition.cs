// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

namespace com.eagle.programmar.Gupta
{
	using Gupta_Keyword = com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
	using Gupta_KeywordChoice = com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice;
	using Gupta_PunctuationChoice = com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Gupta_Condition : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Gupta_ConditionClause conditionClause;
		public Gupta_ConditionClause conditionClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Gupta_AndOr> clauses;
		public  OPT;

		public class Gupta_AndOr : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice andOr = new com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice("AND", "OR");
			public Gupta_KeywordChoice andOr = new Gupta_KeywordChoice("AND", "OR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Gupta_ConditionClause conditionClause;
			public Gupta_ConditionClause conditionClause;
		}

		public class Gupta_ConditionClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Gupta_Expression expr;
			public Gupta_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Gupta_OperatorExpression opExpr;
			public Gupta_OperatorExpression opExpr;

			public class Gupta_OperatorExpression : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Gupta_Relational_Operator operator;
				public Gupta_Relational_Operator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Gupta_Expression expr;
				public Gupta_Expression expr;

				public class Gupta_Relational_Operator : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Gupta_PunctuationChoice XXoperator = new com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice(">=", "<=", "<", ">", "=");
					public Gupta_PunctuationChoice XXoperator = new Gupta_PunctuationChoice(">=", "<=", "<", ">", "=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Gupta_KeywordChoice XXGT = new com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice("GT", "LT", "NE");
					public Gupta_KeywordChoice XXGT = new Gupta_KeywordChoice("GT", "LT", "NE");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Gupta_Not_Equals extends com.eagle.tokens.TokenSequence
					public class Gupta_Not_Equals : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword NOT = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("NOT");
						public Gupta_Keyword NOT = new Gupta_Keyword("NOT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
						public PunctuationEquals equals;
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Gupta_Less_Than extends com.eagle.tokens.TokenSequence
					public class Gupta_Less_Than : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword LESS = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("LESS");
						public Gupta_Keyword LESS = new Gupta_Keyword("LESS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword THAN = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("THAN");
						public Gupta_Keyword THAN = new Gupta_Keyword("THAN");
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Gupta_Greater_Than extends com.eagle.tokens.TokenSequence
					public class Gupta_Greater_Than : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword GREATER = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("GREATER");
						public Gupta_Keyword GREATER = new Gupta_Keyword("GREATER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword THAN = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("THAN");
						public Gupta_Keyword THAN = new Gupta_Keyword("THAN");
					}
				}
			}
		}
	}

}
