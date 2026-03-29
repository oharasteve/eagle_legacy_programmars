// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

namespace com.eagle.programmar.IntelASM
{
	using IntelASM_AdditiveExpression = com.eagle.programmar.IntelASM.Expressions.IntelASM_AdditiveExpression;
	using IntelASM_Brackets = com.eagle.programmar.IntelASM.Expressions.IntelASM_Brackets;
	using IntelASM_BytePtr = com.eagle.programmar.IntelASM.Expressions.IntelASM_BytePtr;
	using IntelASM_Dollar = com.eagle.programmar.IntelASM.Expressions.IntelASM_Dollar;
	using IntelASM_DwordPtr = com.eagle.programmar.IntelASM.Expressions.IntelASM_DwordPtr;
	using IntelASM_MultiplicativeExpression = com.eagle.programmar.IntelASM.Expressions.IntelASM_MultiplicativeExpression;
	using IntelASM_RegisterExpr = com.eagle.programmar.IntelASM.Expressions.IntelASM_RegisterExpr;
	using IntelASM_VariableExpr = com.eagle.programmar.IntelASM.Expressions.IntelASM_VariableExpr;
	using IntelASM_HexNumber = com.eagle.programmar.IntelASM.Terminals.IntelASM_HexNumber;
	using IntelASM_Literal = com.eagle.programmar.IntelASM.Terminals.IntelASM_Literal;
	using IntelASM_Number = com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class IntelASM_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public IntelASM_Expression() : base(_operators)
		{
		}

		public IntelASM_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All operators should stay in @P(#) order.
		// This determines operator precedence.
		//

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_HexNumber hex;
		public IntelASM_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_Number number;
		public IntelASM_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.IntelASM.Terminals.IntelASM_Literal literal;
		public IntelASM_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.IntelASM.Expressions.IntelASM_RegisterExpr register;
		public IntelASM_RegisterExpr register;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.IntelASM.Expressions.IntelASM_VariableExpr var;
		public IntelASM_VariableExpr var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.IntelASM.Expressions.IntelASM_Brackets brackets;
		public IntelASM_Brackets brackets;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.IntelASM.Expressions.IntelASM_BytePtr bytePtr;
		public IntelASM_BytePtr bytePtr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.IntelASM.Expressions.IntelASM_DwordPtr dwordPtr;
		public IntelASM_DwordPtr dwordPtr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.IntelASM.Expressions.IntelASM_Dollar dollar;
		public IntelASM_Dollar dollar;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.IntelASM.Expressions.IntelASM_MultiplicativeExpression multExpr;
		public IntelASM_MultiplicativeExpression multExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.IntelASM.Expressions.IntelASM_AdditiveExpression addExpr;
		public IntelASM_AdditiveExpression addExpr;
	}

}
