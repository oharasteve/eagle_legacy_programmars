// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68;

import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Algol68_Type extends TokenChooser
{
	public @CHOICE Algol68_KeywordChoice XXprimitives = new Algol68_KeywordChoice(
			"BOOL", "INT", "DOUBLE", "STRING", "VOID");

	public @CHOICE static class Algol68_ArrayType extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
		public @S(30) Algol68_Type type;
	}

	public static TypeEnum findType(Algol68_Type type)
	{
		if (type.getWhich() instanceof Algol68_ArrayType)
		{
			return TypeEnum.STRING_ARRAY;
		}

		Algol68_KeywordChoice typeName = (Algol68_KeywordChoice) type.getWhich();
		switch (typeName.getValue().toUpperCase())
		{
		case "BOOL":
			return TypeEnum.BOOLEAN;
		case "INT":
			return TypeEnum.INTEGER;
		case "DOUBLE":
			return TypeEnum.DOUBLE;
		case "STRING":
			return TypeEnum.STRING;
		case "VOID":
			return TypeEnum.VOID;
		default:
			return TypeEnum.OTHER;
		}
	}
}
