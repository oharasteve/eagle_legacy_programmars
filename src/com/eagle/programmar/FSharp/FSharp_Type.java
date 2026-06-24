// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class FSharp_Type extends TokenSequence implements AbstractType
{
	public @S(10) FSharp_KeywordChoice PRIMITIVE = new FSharp_KeywordChoice("bool", "int", "string");
	public @S(20) @OPT FSharp_Keyword ARRAY = new FSharp_Keyword("array");

	public TypeEnum findType()
	{
		if (ARRAY != null && ARRAY.isPresent())
		{
			switch (PRIMITIVE.getValue())
			{
			case "string":
				return TypeEnum.ARRAY;
			default:
				return TypeEnum.VOID;
			}
		}
		
		switch (PRIMITIVE.getValue())
		{
		case "bool":
			return TypeEnum.BOOLEAN;
		case "int":
			return TypeEnum.INTEGER;
		case "string":
			return TypeEnum.STRING;
		default:
			return TypeEnum.VOID;
		}
	}
}
