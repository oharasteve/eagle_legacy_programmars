// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Parenthesized_Expression = com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Str_Function : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword STR = new com.eagle.programmar.Python.Terminals.Python_Keyword("str");
		public Python_Keyword STR = new Python_Keyword("str");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Python_Expression expression;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			string str = interpreter.getStrValue(expression);
			interpreter.pushStr(str);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expression);
			return generator.newStringFunction(null, theExpr, this);
		}

		public static Python_Expression generateString(Oper1Types types, Python_Expression expr, AbstractToken source)
		{
			Python_Str_Function str = new Python_Str_Function();
			str.leftParen = new PunctuationLeftParen();
			str.rightParen = new PunctuationRightParen();
			if (expr.getWhich() is Python_Parenthesized_Expression)
			{
				// Don't create a second set of parens
				Python_Parenthesized_Expression parens = (Python_Parenthesized_Expression) expr.getWhich();
				str.expression = parens.list.expr;
			}
			else
			{
				str.expression = expr;
			}

			str.setTransformationSource(source);
			return Python_Generator.wrapExpression(str);
		}
	}

}
