// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class VB_Type extends TokenChooser
{
	public @CHOICE VB_KeywordChoice XXbase = new VB_KeywordChoice(
			"boolean", "byte", "sbyte", "char", "date",
			"short", "integer", "long", "ushort", "uinteger", "ulong",
			"single", "double", "decimal", "range", "string", "worksheet");

	public static AbstractType findType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, String typeName)
	{
		TypeEnum newType;
		switch (typeName)
		{
		case "boolean":
			newType = TypeEnum.BOOLEAN;
			break;
		case "short":
		case "integer":
			newType = TypeEnum.INTEGER;
			break;
		case "single":
		case "double":
			newType = TypeEnum.DOUBLE;
			break;
		case "string":
			newType = TypeEnum.STRING;
			break;
		default:
			newType = TypeEnum.OTHER;
			break;
		}
		return generator.transformType(newType, null, null);
	}
}
