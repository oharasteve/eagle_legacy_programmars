// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Punctuation = com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class CSharp_LogicalNotExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("operators/boolean-logical-operators") com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation notOperator = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation('!');
		public @DOC("operators/boolean-logical-operators") CSharp_Punctuation notOperator = new CSharp_Punctuation('!');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.CSharp_Expression expr;
		public CSharp_Expression expr;

		public void interpret(EagleInterpreter interpreter)
		{
			bool value = interpreter.getBoolValue(expr);
			interpreter.pushBool(!value);
		}

		public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);
			return generator.newLogicalNotExpression(theExpr, this);
		}

		public static CSharp_Expression generateLogicalNot(CSharp_Expression theExpr, AbstractToken source)
		{
			CSharp_LogicalNotExpression notExpr = new CSharp_LogicalNotExpression();
			notExpr.expr = theExpr;
			notExpr.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(notExpr);
		}
	}

}
