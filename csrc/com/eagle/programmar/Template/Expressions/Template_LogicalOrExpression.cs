// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2024

namespace com.eagle.programmar.Template.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Template_Expression = com.eagle.programmar.Template.Template_Expression;
	using Template_Keyword = com.eagle.programmar.Template.Terminals.Template_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Template_LogicalOrExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Template.Template_Expression left = new com.eagle.programmar.Template.Template_Expression(this, AllowedPrecedence.ATLEAST);
		public Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Template.Terminals.Template_Keyword OR = new com.eagle.programmar.Template.Terminals.Template_Keyword("or");
		public Template_Keyword OR = new Template_Keyword("or");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Template.Template_Expression right = new com.eagle.programmar.Template.Template_Expression(this, AllowedPrecedence.HIGHER);
		public Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			bool rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue || rightValue);
		}
	}

}
