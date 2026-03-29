// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

namespace com.eagle.programmar.Powershell.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Powershell_ReturnStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("chapter-08?view=powershell-5.1#854-the-return-statement") com.eagle.programmar.Powershell.Terminals.Powershell_Keyword RETURN = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Return");
		public @DOC("chapter-08?view=powershell-5.1#854-the-return-statement") Powershell_Keyword RETURN = new Powershell_Keyword("Return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Powershell_Expression expression;
		public @OPT Powershell_Expression expression;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (expression != null && expression.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(expression);

				AbstractToken parent = this.getParent();
				while (parent != null)
				{
					if (parent is Powershell_Function)
					{
						Powershell_Function func = (Powershell_Function) parent;
						func._returnMetrics.returned(val.getType());
						break;
					}
					parent = parent.getParent();
				}

				interpreter.pushEagleValue(val);
			}
			return Eagle_Statement_Result.RETURN;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression retExpr = null;
			if (expression != null && expression.isPresent())
			{
				retExpr = transformer.transformExpression(generator, expression);
			}
			return generator.newReturnStatement(retExpr, this);
		}
	}

}
