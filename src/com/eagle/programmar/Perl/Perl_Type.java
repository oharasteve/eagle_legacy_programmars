// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 27, 2014

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Perl_Type extends TokenChooser
{
	public @CHOICE Perl_KeywordChoice XXbase = new Perl_KeywordChoice("array", "bool", "int", "string");
	public @CHOICE Perl_Variable_Definition XXtype;

	public @CHOICE static class Perl_CompoundType extends TokenSequence
	{
		public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @S(20) Perl_Variable_Definition type;
		public @S(30) @OPT TokenList<Perl_MoreTypeName> more;

		public static class Perl_MoreTypeName extends TokenSequence
		{
			public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public @S(20) Perl_Variable_Definition type;
		}
	}

	public static TypeEnum findType(Perl_Type type)
	{
		if (type.getWhich() instanceof Perl_KeywordChoice)
		{
			Perl_KeywordChoice typeName = (Perl_KeywordChoice) type.getWhich();
			switch (typeName.getValue())
			{
			case "array":
				return TypeEnum.STRING_ARRAY;
			case "bool":
				return TypeEnum.BOOLEAN;
			case "int":
				return TypeEnum.INTEGER;
			case "string":
				return TypeEnum.STRING;
			}
		}
		return TypeEnum.VOID;
	}
}
