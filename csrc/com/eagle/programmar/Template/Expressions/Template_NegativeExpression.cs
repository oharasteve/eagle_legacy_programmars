// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Template.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Template_Expression = com.eagle.programmar.Template.Template_Expression;
	using Template_Punctuation = com.eagle.programmar.Template.Terminals.Template_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Template_NegativeExpression : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Template.Terminals.Template_Punctuation negative = new com.eagle.programmar.Template.Terminals.Template_Punctuation('-');
		public Template_Punctuation negative = new Template_Punctuation('-');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Template.Template_Expression expr;
		public Template_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			int value = interpreter.getIntValue(expr);
			interpreter.pushInt(-value);
		}
	}

}
