// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 15, 2011

namespace com.eagle.programmar.VB.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Variable = com.eagle.programmar.VB.VB_Variable;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class VB_AssignmentStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.VB_Variable var;
		public VB_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.VB_Expression expr;
		public VB_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expr);
			interpreter.setSymbol(var.var, var.var.getValue(), value);

			// VB doesn't have a Return statement. It assigns a value to the function name
			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent is VB_Function)
				{
					VB_Function func = (VB_Function) parent;
					if (var.var.getValue().Equals(func.id.getValue()))
					{
						func._returnMetrics.returned(value.getType());
					}
					break;
				}
				parent = parent.getParent();
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// VB doesn't have a Return statement. It assigns a value to the function name
			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent is VB_Function)
				{
					VB_Function func = (VB_Function) parent;
					if (var.var.getValue().Equals(func.id.getValue()))
					{
						AbstractExpression retExpr = transformer.transformExpression(generator, expr);
						return generator.newReturnStatement(retExpr, this);
					}
					break;
				}
				parent = parent.getParent();
			}

			// Normal assignment ...
			AbstractExpression subscrExpr = null;
			if (var.subscript != null && var.subscript.isPresent())
			{
				subscrExpr = transformer.transformExpression(generator, var.subscript.exprs.first());
			}
			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(var.var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, EagleGenerator.AssignmentEnum.EQUALS, value, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}
