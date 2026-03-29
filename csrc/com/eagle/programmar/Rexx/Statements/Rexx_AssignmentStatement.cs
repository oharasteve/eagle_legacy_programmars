// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleHash = com.eagle.math.EagleHash;
	using EagleValue = com.eagle.math.EagleValue;
	using Rexx_Expression = com.eagle.programmar.Rexx.Rexx_Expression;
	using Rexx_Subscript = com.eagle.programmar.Rexx.Rexx_Subscript;
	using Rexx_Variable = com.eagle.programmar.Rexx.Rexx_Variable;
	using Rexx_Number = com.eagle.programmar.Rexx.Terminals.Rexx_Number;
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

	public class Rexx_AssignmentStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("concepts-assignments-symbols") com.eagle.programmar.Rexx.Rexx_Variable variable;
		public @DOC("concepts-assignments-symbols") Rexx_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rexx.Rexx_Expression expr;
		public Rexx_Expression expr;

		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue var = interpreter.findSymbol(variable.var.getValue());
			EagleValue val = interpreter.getEagleValue(expr);

			if (variable.subscript != null && variable.subscript.isPresent())
			{
				EagleHash hash = (EagleHash) var;
				if (hash == null)
				{
					hash = new EagleHash();
				}
				Rexx_Subscript sub = variable.subscript;
				int? key = Convert.ToInt32(interpreter.getIntValue(sub.subscr));

				interpreter.setSymbol(variable, variable.var.getValue(), key.Value, hash);
				hash.putValue(key, val);
			}
			else
			{
				interpreter.setSymbol(variable, variable.var.getValue(), val);
			}
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = variable.var.getValue();
			AbstractExpression value = transformer.transformExpression(generator, expr);

			// Rexx doesn't have a Return statement. It assigns a value to the function name
			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent is Rexx_Function)
				{
					Rexx_Function func = (Rexx_Function) parent;
					if (name.Equals(func.id.getValue()))
					{
						AbstractExpression retExpr = transformer.transformExpression(generator, expr);
						return generator.newReturnStatement(retExpr, this);
					}
					break;
				}
				parent = parent.getParent();
			}

			// Normal assignment ... maybe with a subscript
			if (variable.subscript != null && variable.subscript.isPresent())
			{
				AbstractExpression subscrExpr = null;
				AbstractToken which = variable.subscript.subscr.getWhich();
				if (which is Rexx_Number)
				{
					Rexx_Number number = (Rexx_Number) which;
					subscrExpr = generator.newNumberExpression(number.getValue(), variable.subscript.subscr);
				}
				else if (which is Rexx_Variable)
				{
					Rexx_Variable var = (Rexx_Variable) which;
					subscrExpr = generator.newVariableExpression(var.var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, variable.subscript.subscr);
				}
				else
				{
					throw new Exception("Unexpected subscript: " + which);
				}

				AbstractExpression hashExpr = generator.newHashAssignment(name, subscrExpr, value, this);
				return generator.newExpressionStatement(hashExpr, this);
			}

			if (name.Equals("true", StringComparison.OrdinalIgnoreCase) || name.Equals("false", StringComparison.OrdinalIgnoreCase))
			{
				// Sorry, cannot redefine true or false
				return null;
			}

			// No subscript given
			AbstractExpression asgExpr1 = generator.newAssignmentExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, value, this);
			return generator.newExpressionStatement(asgExpr1, this);
		}
	}

}
