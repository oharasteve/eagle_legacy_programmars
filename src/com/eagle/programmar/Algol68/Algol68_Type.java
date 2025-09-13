// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68;

import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Algol68_Type extends TokenChooser
{
	public @CHOICE Algol68_KeywordChoice XXprimitives = new Algol68_KeywordChoice(
			"BOOL", "INT", "STRING", "VOID");

	public @CHOICE static class Algol68_ArrayType extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
		public @S(30) Algol68_Type type;
	}
	
	public static AbstractType findType(EagleGenerator generator, Algol68_Type type)
	{
		TypeEnum newType;
		if (type.getWhich() instanceof Algol68_ArrayType)
		{
			newType = TypeEnum.STRING_ARRAY;
		}
		else
		{
			Algol68_KeywordChoice typeName = (Algol68_KeywordChoice) type.getWhich();
			switch (typeName.getValue().toUpperCase())
			{
			case "BOOL":
				newType = TypeEnum.BOOLEAN;
				break;
			case "INT":
				newType = TypeEnum.INTEGER;
				break;
			case "STRING":
				newType = TypeEnum.STRING;
				break;
			default:
				newType = TypeEnum.OTHER;
				break;
			}
		}
		
		return generator.transformType(newType, null, null);
	}
}
