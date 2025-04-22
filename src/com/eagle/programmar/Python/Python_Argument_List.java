// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 22, 2025

package com.eagle.programmar.Python;

import java.util.Collection;

import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_Argument_List
{
	public static SeparatedList<Python_Expression, PunctuationComma> createArgumentList(Collection<AbstractExpression> args)
	{
		SeparatedList<Python_Expression, PunctuationComma> argList =
				new SeparatedList<Python_Expression, PunctuationComma>();

		if (args != null)
		{
			boolean first = true;
			for (AbstractExpression arg0 : args)
			{
				if (! first)
				{
					argList.addSecondaryElement(new PunctuationComma());
				}
				first = false;
				argList.addPrimaryElement((Python_Expression) arg0);
			}
		}
		
		return argList;
	}
}
