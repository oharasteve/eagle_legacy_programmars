// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

namespace com.eagle.programmar.IntelASM.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_Expression = com.eagle.programmar.IntelASM.IntelASM_Expression;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class IntelASM_Brackets : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE IntelASM_Expression expr;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			int index = interpreter.getIntValue(expr);
			int value = state.getMemory4(index);
			// System.out.println("******* brackets index = " + index + " value = " +
			// value);
			interpreter.pushInt(value);
		}
	}

}
