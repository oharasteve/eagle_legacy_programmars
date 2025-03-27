// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IntelASM_DB extends TokenSequence
{
	public @S(10) @OPT IntelASM_DBtimes times;
	public @S(20) IntelASM_Keyword DB = new IntelASM_Keyword("DB");
	public @S(30) SeparatedList<IntelASM_Expression,PunctuationComma> args;
	
	public static class IntelASM_DBtimes extends TokenSequence
	{
		public @S(10) IntelASM_Keyword TIMES = new IntelASM_Keyword("TIMES");
		public @S(20) IntelASM_Number num;
	}
}
