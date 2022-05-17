package com.eagle.programmar.C;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.tokens.TokenSequence;

public class C_Extern_C extends TokenSequence
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
