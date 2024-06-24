// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 24, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Extern_C;
import com.eagle.programmar.C.C_TypeDef;
import com.eagle.programmar.C.Statements.C_StatementBlock;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CPlus_Extern extends TokenSequence
{
	public @S(10) C_Extern_C externC;
	public @S(20) CPlus_Extern_What what;

	public static class CPlus_Extern_What extends TokenChooser
	{
		public @CHOICE CPlus_Method method;
		public @CHOICE C_StatementBlock block;
		public @CHOICE C_TypeDef typedef;
	}
}
