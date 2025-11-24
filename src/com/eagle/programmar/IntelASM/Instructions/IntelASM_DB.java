// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IntelASM_DB extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT IntelASM_DBtimes times;
	public @S(20) IntelASM_Keyword DB = new IntelASM_Keyword("DB");
	public @S(30) SeparatedList<IntelASM_Expression, PunctuationComma> args;

	public static class IntelASM_DBtimes extends TokenSequence
	{
		public @S(10) IntelASM_Keyword TIMES = new IntelASM_Keyword("TIMES");
		public @S(20) IntelASM_Number num;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

		int numTimes = 1;
		if (times != null && times.isPresent())
		{
			numTimes = Integer.parseInt(times.num.getValue());
		}

		for (int i = 0; i < numTimes; i++)
		{
			int numArgs = args.getPrimaryCount();
			for (int j = 0; j < numArgs; j++)
			{
				IntelASM_Expression expr = args.getPrimaryElement(j);
				EagleValue val = interpreter.getEagleValue(expr);
				if (val instanceof EagleInteger)
				{
					int k = val.forceIntegerValue();
					if (k < 0 || k >= 256)
					{
						throw new RuntimeException("int value on DB has to be 0 to 255, not " + k);
					}
					switch (state._section)
					{
					case RODATA:
					case DATA:
						state.setMemory1(state._memoryUsed, k);
						state._memoryUsed++;
						break;
					default:
						throw new RuntimeException("DB must be in .rodata or .data");
					}
				}
				else
				{
					String str = val.forceStringValue();
					for (byte ch : str.getBytes())
					{
						// This doesn't handle embedded quote marks
						if (ch != '"')
						{
							switch (state._section)
							{
							case RODATA:
							case DATA:
								state.setMemory1(state._memoryUsed, ch);
								state._memoryUsed++;
								break;
							default:
								throw new RuntimeException("DB must be in .rodata or .data");
							}
						}
					}
				}
			}
		}
	}
}
