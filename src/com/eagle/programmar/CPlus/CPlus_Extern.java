// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 24, 2022

package com.eagle.programmar.CPlus;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.C.C_Statement.C_StatementBlock;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CPlus_Extern extends TokenSequence
{
	public @S(10) CPlus_Extern_C externC;
	public @S(20) CPlus_Extern_What what;
	
	public static class CPlus_Extern_C extends TokenSequence
	{
		public @S(10) C_Keyword EXTERN = new C_Keyword("extern");
		public @S(20) C_Literal_C C;
		
		public static class C_Literal_C extends C_Literal
		{
			@Override
			public boolean parse(EagleFileReader lines)
			{
				if (! super.parse(lines)) return false;
				return _txt.equals("\"C\"");
			}
		}
	}
	
	public static class CPlus_Extern_What extends TokenChooser
	{
		public @CHOICE CPlus_Method method;
		public @CHOICE C_StatementBlock block;
	}
}
