// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_TypePrimitive extends TokenSequence
{
	public @S(10) @OPT C_Keyword CONST = new C_Keyword("const");
	public @S(20) @OPT C_KeywordChoice UNSIGNED = new C_KeywordChoice("signed", "unsigned");
	public @S(30) C_KeywordChoice primitive = new C_KeywordChoice(C_Program.getPrimitives());
	public @S(40) @OPT C_Keyword INT = new C_Keyword("int");
	public @S(50) @OPT TokenList<C_TypeStar> stars;

	public static class C_TypeStar extends TokenSequence
	{
		public @S(10) C_PunctuationChoice starAmpersand = new C_PunctuationChoice("*", "&&", "&");
	}
}