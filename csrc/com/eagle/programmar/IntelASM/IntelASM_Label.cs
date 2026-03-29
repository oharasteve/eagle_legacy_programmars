// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

namespace com.eagle.programmar.IntelASM
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using JumpMetrics = com.eagle.metrics.JumpMetrics;
	using IntelASM_Label_Definition = com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class IntelASM_Label : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition label;
		public IntelASM_Label_Definition label;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationColon colon;
		public  NOSPACE;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP JumpMetrics _jumpMetrics = null;
		public JumpMetrics _jumpMetrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
			string lbl = label.getValue().ToUpper();
			if (state._labels.ContainsKey(lbl))
			{
				throw new Exception("Duplicate label: " + lbl);
			}

			switch (state._section)
			{
			case com.eagle.programmar.IntelASM.IntelASM_StateMachine.IntelASM_Sections.RODATA:
			case com.eagle.programmar.IntelASM.IntelASM_StateMachine.IntelASM_Sections.DATA:
				EagleInteger val = new EagleInteger(state._memoryUsed);
				interpreter.setSymbol(label, lbl, val);
				break;
			default:
				// System.out.println("******** Setting label " + lbl + " to " +
				// state._currentLine);
				state._labels[lbl] = Convert.ToInt32(state._currentLine);
				break;
			}
		}
	}

}
