// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using Rexx_Expression = com.eagle.programmar.Rexx.Rexx_Expression;
	using Rexx_Keyword = com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rexx_ReturnStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("instructions-return") com.eagle.programmar.Rexx.Terminals.Rexx_Keyword RETURN = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("RETURN");
		public @DOC("instructions-return") Rexx_Keyword RETURN = new Rexx_Keyword("RETURN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Rexx_Expression expr;
		public @OPT Rexx_Expression expr;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (expr != null && expr.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(expr);

				AbstractToken parent = this.getParent();
				while (parent != null)
				{
					if (parent is Rexx_Function)
					{
						Rexx_Function func = (Rexx_Function) parent;
						func._returnMetrics.returned(val.getType());
						break;
					}
					parent = parent.getParent();
				}

				interpreter.pushEagleValue(val);
			}
			return Eagle_Statement_Result.BREAK;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression retExpr = null;
			if (expr != null && expr.isPresent())
			{
				retExpr = transformer.transformExpression(generator, expr);
			}
			return generator.newReturnStatement(retExpr, this);
		}
	}

}
