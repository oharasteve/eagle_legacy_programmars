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
	using Template_Keyword = com.eagle.programmar.Template.Terminals.Template_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Template_LogicalNotExpression : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Template.Terminals.Template_Keyword NOT = new com.eagle.programmar.Template.Terminals.Template_Keyword("not");
		public Template_Keyword NOT = new Template_Keyword("not");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Template.Template_Expression expr;
		public Template_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			bool value = interpreter.getBoolValue(expr);
			interpreter.pushBool(!value);
		}
	}

}
