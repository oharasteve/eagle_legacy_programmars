// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

namespace com.eagle.programmar.IntelASM.Instructions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using IntelASM_Expression = com.eagle.programmar.IntelASM.IntelASM_Expression;
	using IntelASM_Variable = com.eagle.programmar.IntelASM.IntelASM_Variable;
	using IntelASM_KeywordChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class IntelASM_TwoArgs : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice CMD = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice("ADD", "AND", "LEA", "MOV", "MOVSX", "MOVZX", "OR", "SHL", "SHR", "SUB", "TEST", "XOR");
		public IntelASM_KeywordChoice CMD = new IntelASM_KeywordChoice("ADD", "AND", "LEA", "MOV", "MOVSX", "MOVZX", "OR", "SHL", "SHR", "SUB", "TEST", "XOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.IntelASM_Variable var;
		public IntelASM_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationComma comma;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.IntelASM.IntelASM_Expression expr;
		public IntelASM_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue ev = interpreter.getEagleValue(expr);
			int val;
			if (ev.isInteger())
			{
				val = ev.forceIntegerValue();
			}
			else
			{
				// Convert "0" to 48 (hex 32, octal 60)
				string s = ev.forceStringValue();
				if (s.Length != 1)
				{
					throw new Exception("Characters must be length 1, not " + s);
				}
				val = s[0];
			}

			switch (CMD.ToString().ToUpper())
			{
			case "ADD":
				int oldVal3 = var.getValue(interpreter);
				var.setValue(interpreter, oldVal3 + val);
				break;
			case "MOV":
				var.setValue(interpreter, val);
				break;
			case "SUB":
				int oldVal2 = var.getValue(interpreter);
				var.setValue(interpreter, oldVal2 - val);
				break;
			default:
				throw new Exception("Unable to run command: " + CMD);
			}
		}
	}
}
