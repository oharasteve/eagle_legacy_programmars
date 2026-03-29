// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Fortran_Expression = com.eagle.programmar.Fortran.Fortran_Expression;
	using Fortran_Variable = com.eagle.programmar.Fortran.Fortran_Variable;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
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

	public class Fortran_Assignment : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Fortran.Fortran_Variable variable;
		public Fortran_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Fortran_Expression expression;
		public Fortran_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln;
		public Fortran_EOLN eoln;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expression);
			string varName = variable.var.ToString();

			AbstractFunction abstractFunc = interpreter.findFunction(varName);
			if (abstractFunc != null && abstractFunc is Fortran_Function)
			{
				// Return value from a function
				Fortran_Function func = (Fortran_Function) abstractFunc;
				func._returnMetrics.returned(val.getType());
			}

			interpreter.setSymbol(variable, varName, val);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Fortran doesn't have a Return statement for Functions.
			// It assigns a value to the function name
			// Returns are allowed in Subroutines.

			AbstractExpression newExpr = transformer.transformExpression(generator, expression);
			string varName = variable.var.getValue();

			AbstractToken parent = variable;
			while (parent != null)
			{
				if (parent is Fortran_Function)
				{
					Fortran_Function fn = (Fortran_Function) parent;
					if (fn.id.getValue().Equals(varName))
					{
						// It is a function return: function cube(a) begin cube := a*a*a end
						return generator.newReturnStatement(newExpr, this);
					}
					break;
				}
				parent = parent.getParent();
			}

			AbstractExpression subscrExpr = null;
			AbstractExpression value = transformer.transformExpression(generator, expression);
			AbstractExpression asgExpr = generator.newAssignmentExpression(variable.var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, EagleGenerator.AssignmentEnum.EQUALS, value, this);
			return generator.newExpressionStatement(asgExpr, this);
		}
	}

}
