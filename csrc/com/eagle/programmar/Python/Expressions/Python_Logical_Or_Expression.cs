// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_Punctuation = com.eagle.programmar.Python.Terminals.Python_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using LogicalOrEnum = com.eagle.transform.EagleGenerator.LogicalOrEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Logical_Or_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression left = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Python_Or_Operation operator;
		public Python_Or_Operation @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_Comment> comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Python.Python_Expression right = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.HIGHER);
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

		public class Python_Or_Operation : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Keyword XXOR = new com.eagle.programmar.Python.Terminals.Python_Keyword("or");
			public Python_Keyword XXOR = new Python_Keyword("or");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Punctuation XXcaret = new com.eagle.programmar.Python.Terminals.Python_Punctuation("^");
			public Python_Punctuation XXcaret = new Python_Punctuation("^");
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			switch (@operator.getWhich().ToString())
			{
			case "or":
				if (leftValue)
				{
					// Short circuit, don't bother with RHS
					interpreter.pushBool(true);
				}
				else
				{
					bool rightValue = interpreter.getBoolValue(right);
					interpreter.pushBool(rightValue);
				}
				return;
			case "^":
				bool rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(leftValue ^ rightValue); // Exclusive or, XOR
				return;
			}
			throw new Exception("Unexpected OR operator: " + @operator);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			EagleGenerator.LogicalOrEnum oper;
			switch (@operator.getWhich().ToString())
			{
			case "or":
				oper = EagleGenerator.LogicalOrEnum.OR;
				break;
			case "^":
				oper = EagleGenerator.LogicalOrEnum.XOR;
				break;
			default:
				throw new Exception("Unexpected OR operator: " + @operator);
			}
			return generator.newLogicalOrExpression(leftExpr, oper, rightExpr, this);
		}

		public static Python_Expression generateLogicalOr(Python_Expression leftExpr, EagleGenerator.LogicalOrEnum oper, Python_Expression rightExpr, AbstractToken source)
		{
			Python_Logical_Or_Expression orExpr = new Python_Logical_Or_Expression();
			orExpr.left = leftExpr;
			orExpr.right = rightExpr;
			orExpr.@operator = new Python_Or_Operation();
			switch (oper)
			{
			case OR:
				orExpr.@operator.setWhich(orExpr.@operator.XXOR);
				break;
			case XOR:
				orExpr.@operator.setWhich(orExpr.@operator.XXcaret);
				break;
			default:
				throw new Exception("Unable to handle: " + oper);
			}
			orExpr.setTransformationSource(source);
			return Python_Generator.wrapExpression(orExpr);
		}
	}

}
