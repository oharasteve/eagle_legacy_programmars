// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Subscript = com.eagle.programmar.Python.Python_Subscript;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;

	public class Python_SubscriptExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression expr = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Python_Subscript subscr;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expr);
			Python_Subscript.evaluateSubscript(interpreter, value, subscr.body);
		}

		public static Python_Expression generateExpression(AbstractExpression theExpr, AbstractExpression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression ecOrnc, bool ncMightBeTooBig, AbstractToken source)
		{
			// Note: python doesn't care if nc is too big
			// if a = 'abc', then a[1:40] is 'bc'
			Python_SubscriptExpression expr = new Python_SubscriptExpression();
			expr.expr = (Python_Expression) theExpr;
			expr.subscr = Python_Subscript.generateExpression(sc, whichSC, whichEC, ecOrnc, source);
			expr.setTransformationSource(source);
			return Python_Generator.wrapExpression(expr);
		}
	}

}
