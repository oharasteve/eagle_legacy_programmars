// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

namespace com.eagle.programmar.SQL.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Punctuation = com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_LogicalNotExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Punctuation notOperator = new com.eagle.programmar.SQL.Terminals.SQL_Punctuation('!');
		public SQL_Punctuation notOperator = new SQL_Punctuation('!');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Expression expr;
		public SQL_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			bool value = interpreter.getBoolValue(expr);
			interpreter.pushBool(!value);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);
			return generator.newLogicalNotExpression(theExpr, this);
		}
	}

}
