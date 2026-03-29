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
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using IntelASM_Expression = com.eagle.programmar.IntelASM.IntelASM_Expression;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using IntelASM_Number = com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class IntelASM_DB : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT IntelASM_DBtimes times;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword DB = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("DB");
		public IntelASM_Keyword DB = new IntelASM_Keyword("DB");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.IntelASM.IntelASM_Expression, com.eagle.tokens.punctuation.PunctuationComma> args;
		public SeparatedList<IntelASM_Expression, PunctuationComma> args;

		public class IntelASM_DBtimes : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword TIMES = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("TIMES");
			public IntelASM_Keyword TIMES = new IntelASM_Keyword("TIMES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_Number num;
			public IntelASM_Number num;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			int numTimes = 1;
			if (times != null && times.isPresent())
			{
				numTimes = int.Parse(times.num.getValue());
			}

			for (int i = 0; i < numTimes; i++)
			{
				int numArgs = args.getPrimaryCount();
				for (int j = 0; j < numArgs; j++)
				{
					IntelASM_Expression expr = args.getPrimaryElement(j);
					EagleValue val = interpreter.getEagleValue(expr);
					if (val is EagleInteger)
					{
						int k = val.forceIntegerValue();
						if (k < 0 || k >= 256)
						{
							throw new Exception("int value on DB has to be 0 to 255, not " + k);
						}
						switch (state._section)
						{
						case IntelASM_StateMachine.IntelASM_Sections.RODATA:
						case IntelASM_StateMachine.IntelASM_Sections.DATA:
							state.setMemory1(state._memoryUsed, k);
							state._memoryUsed++;
							break;
						default:
							throw new Exception("DB must be in .rodata or .data");
						}
					}
					else
					{
						string str = val.forceStringValue();
						foreach (sbyte ch in str.GetBytes())
						{
							// This doesn't handle embedded quote marks
							if (ch != (sbyte)'"')
							{
								switch (state._section)
								{
								case IntelASM_StateMachine.IntelASM_Sections.RODATA:
								case IntelASM_StateMachine.IntelASM_Sections.DATA:
									state.setMemory1(state._memoryUsed, ch);
									state._memoryUsed++;
									break;
								default:
									throw new Exception("DB must be in .rodata or .data");
								}
							}
						}
					}
				}
			}
		}
	}

}
