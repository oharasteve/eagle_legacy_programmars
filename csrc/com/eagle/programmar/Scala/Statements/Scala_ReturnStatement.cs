// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using Scala_Expression = com.eagle.programmar.Scala.Scala_Expression;
	using Scala_EOLN = com.eagle.programmar.Scala.Terminals.Scala_EOLN;
	using Scala_Keyword = com.eagle.programmar.Scala.Terminals.Scala_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Scala_ReturnStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Terminals.Scala_Keyword RETURN = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("return");
		public Scala_Keyword RETURN = new Scala_Keyword("return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Scala_Expression expr;
		public Scala_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Terminals.Scala_EOLN eoln;
		public Scala_EOLN eoln;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.pushEagleValue(val);
			return Eagle_Statement_Result.RETURN;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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
