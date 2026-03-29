// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Template
{
	using Template_AdditiveExpression = com.eagle.programmar.Template.Expressions.Template_AdditiveExpression;
	using Template_LogicalAndExpression = com.eagle.programmar.Template.Expressions.Template_LogicalAndExpression;
	using Template_MultiplicativeExpression = com.eagle.programmar.Template.Expressions.Template_MultiplicativeExpression;
	using Template_NegativeExpression = com.eagle.programmar.Template.Expressions.Template_NegativeExpression;
	using Template_LogicalNotExpression = com.eagle.programmar.Template.Expressions.Template_LogicalNotExpression;
	using Template_LogicalOrExpression = com.eagle.programmar.Template.Expressions.Template_LogicalOrExpression;
	using Template_ParenExpression = com.eagle.programmar.Template.Expressions.Template_ParenExpression;
	using Template_RelationalExpression = com.eagle.programmar.Template.Expressions.Template_RelationalExpression;
	using Template_Identifier_Reference = com.eagle.programmar.Template.Symbols.Template_Identifier_Reference;
	using Template_Literal = com.eagle.programmar.Template.Terminals.Template_Literal;
	using Template_Number = com.eagle.programmar.Template.Terminals.Template_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Template_Expression : PrecedenceChooser, AbstractExpression
	{
		protected internal static OperatorList _operators = new OperatorList();

		public Template_Expression() : base(_operators)
		{
		}

		public Template_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Template.Terminals.Template_Number number;
		public Template_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Template.Terminals.Template_Literal literal;
		public Template_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.Template.Symbols.Template_Identifier_Reference id;
		public Template_Identifier_Reference id;

		///////////////////////////////////////////////////////////////////////////
		// Primary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Template.Expressions.Template_NegativeExpression negativeExpression;
		public Template_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Template.Expressions.Template_LogicalNotExpression notExpression;
		public Template_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Template.Expressions.Template_ParenExpression parenExpression;
		public Template_ParenExpression parenExpression;

		///////////////////////////////////////////////////////////////////////////
		// Binary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Template.Expressions.Template_MultiplicativeExpression multiplicativeExpression;
		public Template_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Template.Expressions.Template_AdditiveExpression additiveExpression;
		public Template_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Template.Expressions.Template_RelationalExpression relationalExpression;
		public Template_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Template.Expressions.Template_LogicalAndExpression andExpression;
		public Template_LogicalAndExpression andExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Template.Expressions.Template_LogicalOrExpression orExpression;
		public Template_LogicalOrExpression orExpression;
	}

}
