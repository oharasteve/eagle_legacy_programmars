// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Python_CommentEoln = com.eagle.programmar.Python.Python_CommentEoln;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_List = com.eagle.programmar.Python.Python_List;
	using Python_MoreListItem = com.eagle.programmar.Python.Python_List.Python_MoreListItem;
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Parenthesized_Expression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) com.eagle.tokens.TokenList<com.eagle.programmar.Python.Python_CommentEoln> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @NOSPACE @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) com.eagle.programmar.Python.Python_List list;
		public @OPT @SYNTAX(typeof(Python_Multiline_Syntax)) Python_List list;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public @NOSPACE PunctuationRightParen rightParen;

		public void interpret(EagleInterpreter interpreter)
		{
			if (list.moreItems != null && list.moreItems.isPresent() && list.moreItems._elements.size() > 0)
			{
				// It is an array declaration
				EagleArray values = new EagleArray();
				EagleValue val = interpreter.getEagleValue(list.expr);
				values.addValue(val);
				foreach (Python_List.Python_MoreListItem item in list.moreItems._elements)
				{
					val = interpreter.getEagleValue(item.expr);
					values.addValue(val);
				}

				interpreter.pushEagleValue(values);
			}
			else
			{
				// Just plain parens, like (1+2)
				interpreter.tryToInterpret(list.expr);
			}
		}

		public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (list.moreItems != null && list.moreItems.isPresent() && list.moreItems._elements.size() > 0)
			{
				// It is an array declaration
				List<AbstractExpression> exprs = new List<AbstractExpression>();
				exprs.Add(transformer.transformExpression(generator, list.expr));

				foreach (Python_List.Python_MoreListItem more in list.moreItems._elements)
				{
					exprs.Add(transformer.transformExpression(generator, more.expr));
				}
				return generator.newArrayExpression(exprs, this);
			}

			// Just plain parens, like (1+2)
			AbstractExpression theExpr = transformer.transformExpression(generator, list.expr);
			return generator.newParenthesizedExpression(theExpr, this);
		}

		public static Python_Expression generateParentheses(Python_Expression theExpr, AbstractToken source)
		{
			Python_Parenthesized_Expression parens = new Python_Parenthesized_Expression();
			parens.leftParen = new PunctuationLeftParen();
			parens.list = new Python_List();
			parens.list.setPresent(true);
			parens.list.expr = theExpr;
			parens.rightParen = new PunctuationRightParen();
			parens.setTransformationSource(source);
			return Python_Generator.wrapExpression(parens);
		}
	}

}
