// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class FSharp_Type extends TokenChooser implements AbstractType
{
	public @CHOICE FSharp_KeywordChoice XXTYPES = new FSharp_KeywordChoice("bool", "int", "string");

	public static TypeEnum findType(FSharp_Type type)
	{
		FSharp_KeywordChoice typeName = (FSharp_KeywordChoice) type.getWhich();
		switch (typeName.getValue())
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
