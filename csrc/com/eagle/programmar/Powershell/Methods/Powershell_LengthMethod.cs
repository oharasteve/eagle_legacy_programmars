// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2025

namespace com.eagle.programmar.Powershell.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Powershell_LengthMethod : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Powershell_Expression expr = new com.eagle.programmar.Powershell.Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword LENGTH = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("length");
		public Powershell_Keyword LENGTH = new Powershell_Keyword("length");

		public override void interpret(EagleInterpreter interpreter)
		{
			string str = interpreter.getStrValue(expr);
			interpreter.pushInt(str.Length);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);
			return generator.newLengthFunction(theExpr, this);
		}
	}

}
