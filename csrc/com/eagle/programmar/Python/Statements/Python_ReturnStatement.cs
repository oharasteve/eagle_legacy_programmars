// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

namespace com.eagle.programmar.Python.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_ExpressionList = com.eagle.programmar.Python.Python_ExpressionList;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_ReturnStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("compound_stmts.html#function-definitions") @NOSPACE Python_Keyword RETURN = new com.eagle.programmar.Python.Terminals.Python_Keyword("return");
		public @DOC("compound_stmts.html#function-definitions") Python_Keyword RETURN = new Python_Keyword("return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_Keyword AWAIT = new com.eagle.programmar.Python.Terminals.Python_Keyword("await");
		public @OPT Python_Keyword AWAIT = new Python_Keyword("await");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_ExpressionList expressionList;
		public @OPT Python_ExpressionList expressionList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Python_Comment comment;
		public @OPT Python_Comment comment;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expressionList.expressions.first());

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent is Python_Function)
				{
					Python_Function func = (Python_Function) parent;
					func._returnMetrics.returned(val.getType());
					break;
				}
				parent = parent.getParent();
			}

			interpreter.pushEagleValue(val);
			return Eagle_Statement_Result.RETURN;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression retExpr = null;
			if (expressionList != null && expressionList.isPresent())
			{
				if (expressionList.expressions.size() > 1)
				{
					throw new Exception("Can't handle multi-value Returns yet");
				}
				Python_Expression expr = expressionList.expressions.first();
				retExpr = transformer.transformExpression(generator, expr);
			}
			return generator.newReturnStatement(retExpr, this);
		}

		public static Python_ComplexStatement generateReturn(Python_Expression ret, AbstractToken source)
		{
			Python_ReturnStatement retStmt = new Python_ReturnStatement();
			if (ret != null)
			{
				retStmt.expressionList = new Python_ExpressionList();
				retStmt.expressionList.expressions = new SeparatedList<Python_Expression, PunctuationComma>();
				retStmt.expressionList.expressions.addPrimaryElement(ret);
				retStmt.expressionList.setPresent(true);
			}
			retStmt.setTransformationSource(source);
			return Python_Generator.wrapStatement(retStmt);
		}
	}

}
