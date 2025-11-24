// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala;

import com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Scala_Type extends TokenChooser
{
	public @CHOICE Scala_KeywordChoice XXtype = new Scala_KeywordChoice(
			"Bool", "Boolean", "Int", "String");

	public @CHOICE static class Scala_TypeArray extends TokenSequence
	{
		public @S(10) Scala_KeywordChoice LIST = new Scala_KeywordChoice("Array", "List");
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) Scala_Type subtype;
		public @S(40) PunctuationRightBracket rightBracket;
	}

	public static AbstractType findType(EagleGenerator generator, Scala_Type type)
	{
		TypeEnum newType;
		if (type.getWhich() instanceof Scala_TypeArray)
		{
			newType = TypeEnum.STRING_ARRAY;
		}
		else
		{
			Scala_KeywordChoice typeName = (Scala_KeywordChoice) type.getWhich();
			switch (typeName.getValue().toLowerCase())
			{
			case "bool":
			case "boolean":
				newType = TypeEnum.BOOLEAN;
				break;
			case "int":
				newType = TypeEnum.INTEGER;
				break;
			case "string":
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
