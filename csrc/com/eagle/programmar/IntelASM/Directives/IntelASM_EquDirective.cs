// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

namespace com.eagle.programmar.IntelASM.Directives
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using IntelASM_Expression = com.eagle.programmar.IntelASM.IntelASM_Expression;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Label_Definition = com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class IntelASM_EquDirective : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition label;
		public IntelASM_Label_Definition label;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @CURIOUS("Extra colon") com.eagle.tokens.punctuation.PunctuationColon colon;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword EQU = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("EQU");
		public IntelASM_Keyword EQU = new IntelASM_Keyword("EQU");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.IntelASM.IntelASM_Expression expr;
		public IntelASM_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
			string lbl = label.getValue().ToUpper();
			if (state._labels.ContainsKey(lbl))
			{
				throw new Exception("Duplicate label: " + lbl);
			}

			int k = interpreter.getIntValue(expr);
			EagleInteger val = new EagleInteger(k);
			interpreter.setSymbol(label, lbl, val);
		}
	}

}
