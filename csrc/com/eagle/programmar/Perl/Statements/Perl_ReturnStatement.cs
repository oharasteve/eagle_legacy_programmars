// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

namespace com.eagle.programmar.Perl.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Function = com.eagle.programmar.Perl.Perl_Function;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_ReturnStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("function.return.php") com.eagle.programmar.Perl.Terminals.Perl_Keyword RETURN = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("return");
		public @DOC("function.return.php") Perl_Keyword RETURN = new Perl_Keyword("return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_Expression expression;
		public @OPT Perl_Expression expression;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (expression != null && expression.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(expression);
				interpreter.pushEagleValue(val);

				AbstractToken parent = this.getParent();
				while (parent != null)
				{
					if (parent is Perl_Function)
					{
						Perl_Function func = (Perl_Function) parent;
						func._returnMetrics.returned(val.getType());
						break;
					}
					parent = parent.getParent();
				}
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
