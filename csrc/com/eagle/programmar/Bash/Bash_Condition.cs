// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

namespace com.eagle.programmar.Bash
{
	using Bash_BracketCondition = com.eagle.programmar.Bash.Conditions.Bash_BracketCondition;
	using Bash_BracketsCondition = com.eagle.programmar.Bash.Conditions.Bash_BracketsCondition;
	using Bash_ConditionConstants = com.eagle.programmar.Bash.Conditions.Bash_ConditionConstants;
	using Bash_ExistsCondition = com.eagle.programmar.Bash.Conditions.Bash_ExistsCondition;
	using Bash_ExpressionCondition = com.eagle.programmar.Bash.Conditions.Bash_ExpressionCondition;
	using Bash_GrepCondition = com.eagle.programmar.Bash.Conditions.Bash_GrepCondition;
	using Bash_LogicalAndCondition = com.eagle.programmar.Bash.Conditions.Bash_LogicalAndCondition;
	using Bash_LogicalOrCondition = com.eagle.programmar.Bash.Conditions.Bash_LogicalOrCondition;
	using Bash_NotCondition = com.eagle.programmar.Bash.Conditions.Bash_NotCondition;
	using Bash_ReadCondition = com.eagle.programmar.Bash.Conditions.Bash_ReadCondition;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class Bash_Condition : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		//
		// Note: All operators should stay in @P(#) order.
		// This determines operator precedence.
		//

		public Bash_Condition() : base(_operators)
		{
		}

		public Bash_Condition(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Bash.Conditions.Bash_ExpressionCondition expressionCondition;
		public Bash_ExpressionCondition expressionCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Bash.Conditions.Bash_BracketCondition bracketCondition;
		public Bash_BracketCondition bracketCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Bash.Conditions.Bash_BracketsCondition bracketsCondition;
		public Bash_BracketsCondition bracketsCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Bash.Conditions.Bash_ExistsCondition existsCondition;
		public Bash_ExistsCondition existsCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Bash.Conditions.Bash_NotCondition notCondition;
		public Bash_NotCondition notCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Bash.Conditions.Bash_ConditionConstants conditionConstants;
		public Bash_ConditionConstants conditionConstants;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Bash.Conditions.Bash_GrepCondition grepCondition;
		public Bash_GrepCondition grepCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Bash.Conditions.Bash_ReadCondition readCondition;
		public Bash_ReadCondition readCondition;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Bash.Conditions.Bash_LogicalAndCondition logicalAndCondition;
		public Bash_LogicalAndCondition logicalAndCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Bash.Conditions.Bash_LogicalOrCondition logicalOrCondition;
		public Bash_LogicalOrCondition logicalOrCondition;
	}
}
