// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran;

import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice;
import com.eagle.programmar.Fortran.Terminals.Fortran_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Fortran_Type extends TokenSequence
{
	public @S(10) Fortran_DataType dataType;
	public @S(20) @OPT Fortran_Dimension dimension;

	public static class Fortran_DataType extends TokenChooser
	{
		public @CHOICE Fortran_KeywordChoice XXINTEGER = new Fortran_KeywordChoice(
				"INTEGER", "LOGICAL");
		public @CHOICE Fortran_CharacterType XXcharType;
	}

	public static class Fortran_CharacterType extends TokenSequence
	{
		public @S(10) @DOC("6j4m0vn7r/index.html") Fortran_Keyword CHARACTER = new Fortran_Keyword("CHARACTER");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Fortran_Keyword LEN = new Fortran_Keyword("LEN");
		public @S(40) PunctuationEquals equals;
		public @S(50) Fortran_Number len;
		public @S(60) PunctuationRightParen rightParen;
	}

	public static class Fortran_Dimension extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @DOC("6j4m0vn8a/index.html") Fortran_Keyword DIMENSION = new Fortran_Keyword("DIMENSION");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) Fortran_Number len;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static AbstractType findType(EagleGenerator generator, Fortran_Type type)
	{
		TypeEnum newType = TypeEnum.OTHER;

		AbstractToken which = type.dataType.getWhich();
		if (which instanceof Fortran_CharacterType)
		{
			newType = TypeEnum.STRING;
		}
		else if (which instanceof Fortran_KeywordChoice)
		{
			Fortran_KeywordChoice base = (Fortran_KeywordChoice) which;
			switch (base.getValue().toUpperCase())
			{
			case "LOGICAL":
				newType = TypeEnum.BOOLEAN;
				break;
			case "INTEGER":
				newType = TypeEnum.INTEGER;
				break;
			}
		}

		return generator.transformType(newType, null, null);
	}
}
