// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada;

import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Ada_Type extends TokenSequence
{
	public @S(10) @OPT Ada_Keyword CONSTANT = new Ada_Keyword("constant");
	public @S(20) Ada_WhichType which;

	public static class Ada_WhichType extends TokenChooser
	{
		public @CHOICE Ada_KeywordChoice XXprimitives = new Ada_KeywordChoice(
				"Boolean", "Integer", "Unbounded_String");
		public @CHOICE Ada_ArrayType XXarrayType;
	}

	public static class Ada_ArrayType extends TokenSequence
	{
		public @S(10) Ada_Keyword ARRAY = new Ada_Keyword("array");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Ada_Expression range;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) Ada_Keyword OF = new Ada_Keyword("of");
		public @S(60) Ada_Type baseType;
	}

	public AbstractType convertType(EagleGenerator generator)
	{
		TypeEnum newType = null;
		String userType = null;
		AbstractToken whichType = which.getWhich();
		if (whichType instanceof Ada_KeywordChoice)
		{
			Ada_KeywordChoice kw1 = (Ada_KeywordChoice) whichType;
			switch (kw1.getValue())
			{
			case "Boolean":
				newType = TypeEnum.BOOLEAN;
				break;
			case "Integer":
				newType = TypeEnum.INTEGER;
				break;
			case "Unbounded_String":
				newType = TypeEnum.STRING;
				break;
			default:
				throw new RuntimeException("Unable to convert type: " + kw1.getValue());
			}
		}
		else if (whichType instanceof Ada_ArrayType)
		{
			Ada_ArrayType array = (Ada_ArrayType) whichType;
			if (array.baseType.which.getWhich() instanceof Ada_KeywordChoice)
			{
				Ada_KeywordChoice kw2 = (Ada_KeywordChoice) array.baseType.which.getWhich();
				if (kw2.getValue().equals("Unbounded_String"))
				{
					newType = TypeEnum.STRING_ARRAY;
				}
			}
		}

		if (newType == null)
		{
			throw new RuntimeException("Can't handle type yet: " + whichType);
		}
		return generator.transformType(newType, userType, this);
	}
}
