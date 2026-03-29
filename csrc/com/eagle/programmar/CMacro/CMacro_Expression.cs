// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

namespace com.eagle.programmar.CMacro
{
	using CMacro_AdditiveExpression = com.eagle.programmar.CMacro.Expressions.CMacro_AdditiveExpression;
	using CMacro_BitwiseAndExpression = com.eagle.programmar.CMacro.Expressions.CMacro_BitwiseAndExpression;
	using CMacro_BitwiseOrExpression = com.eagle.programmar.CMacro.Expressions.CMacro_BitwiseOrExpression;
	using CMacro_ConcatenateExpression = com.eagle.programmar.CMacro.Expressions.CMacro_ConcatenateExpression;
	using CMacro_ConditionalAndExpression = com.eagle.programmar.CMacro.Expressions.CMacro_ConditionalAndExpression;
	using CMacro_ConditionalOrExpression = com.eagle.programmar.CMacro.Expressions.CMacro_ConditionalOrExpression;
	using CMacro_EqualityExpression = com.eagle.programmar.CMacro.Expressions.CMacro_EqualityExpression;
	using CMacro_ExclusiveOrExpression = com.eagle.programmar.CMacro.Expressions.CMacro_ExclusiveOrExpression;
	using CMacro_FunctionCall = com.eagle.programmar.CMacro.Expressions.CMacro_FunctionCall;
	using CMacro_IdentifierExpression = com.eagle.programmar.CMacro.Expressions.CMacro_IdentifierExpression;
	using CMacro_MultiplicativeExpression = com.eagle.programmar.CMacro.Expressions.CMacro_MultiplicativeExpression;
	using CMacro_NotExpression = com.eagle.programmar.CMacro.Expressions.CMacro_NotExpression;
	using CMacro_ParenthesizedExpression = com.eagle.programmar.CMacro.Expressions.CMacro_ParenthesizedExpression;
	using CMacro_RelationalExpression = com.eagle.programmar.CMacro.Expressions.CMacro_RelationalExpression;
	using CMacro_SignedExpression = com.eagle.programmar.CMacro.Expressions.CMacro_SignedExpression;
	using CMacro_SymbolExpression = com.eagle.programmar.CMacro.Expressions.CMacro_SymbolExpression;
	using CMacro_Character_Literal = com.eagle.programmar.CMacro.Terminals.CMacro_Character_Literal;
	using CMacro_HexNumber = com.eagle.programmar.CMacro.Terminals.CMacro_HexNumber;
	using CMacro_Literal = com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
	using CMacro_Number = com.eagle.programmar.CMacro.Terminals.CMacro_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class CMacro_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public CMacro_Expression() : base(_operators)
		{
		}

		public CMacro_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.CMacro.Terminals.CMacro_HexNumber hex;
		public CMacro_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.CMacro.Terminals.CMacro_Number number;
		public CMacro_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.CMacro.Terminals.CMacro_Literal literal;
		public CMacro_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.CMacro.Terminals.CMacro_Character_Literal characters;
		public CMacro_Character_Literal characters;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.CMacro.Expressions.CMacro_FunctionCall functionCall;
		public CMacro_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.CMacro.Expressions.CMacro_IdentifierExpression identifierExpression;
		public CMacro_IdentifierExpression identifierExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.CMacro.Expressions.CMacro_SignedExpression signedExpression;
		public CMacro_SignedExpression signedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.CMacro.Expressions.CMacro_NotExpression notExpression;
		public CMacro_NotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.CMacro.Expressions.CMacro_ParenthesizedExpression parenthesizedExpression;
		public CMacro_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.CMacro.Expressions.CMacro_SymbolExpression symbolExpression;
		public CMacro_SymbolExpression symbolExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.CMacro.Expressions.CMacro_MultiplicativeExpression multiplicativeExpression;
		public CMacro_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.CMacro.Expressions.CMacro_AdditiveExpression additiveExpression;
		public CMacro_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.CMacro.Expressions.CMacro_RelationalExpression relationalExpression;
		public CMacro_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.CMacro.Expressions.CMacro_EqualityExpression equalityExpression;
		public CMacro_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.CMacro.Expressions.CMacro_BitwiseAndExpression bitwiseAndExpression;
		public CMacro_BitwiseAndExpression bitwiseAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.CMacro.Expressions.CMacro_ExclusiveOrExpression exclusiveOrExpression;
		public CMacro_ExclusiveOrExpression exclusiveOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.CMacro.Expressions.CMacro_BitwiseOrExpression bitwiseOrExpression;
		public CMacro_BitwiseOrExpression bitwiseOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.CMacro.Expressions.CMacro_ConditionalAndExpression conditionalAndExpression;
		public CMacro_ConditionalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.CMacro.Expressions.CMacro_ConditionalOrExpression conditionalOrExpression;
		public CMacro_ConditionalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.CMacro.Expressions.CMacro_ConcatenateExpression concatenateExpression;
		public CMacro_ConcatenateExpression concatenateExpression;
	}

}
