// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenList;

public class VB_Program extends EagleLanguage implements EagleRunnable
{
	public static final String VB = "VB";

	public VB_Program()
	{
		super(VB, new VB_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://msdn.microsoft.com/en-us/library/";
	}

	public @S(10) @OPT TokenList<VB_Statement> elements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (VB_Statement stmt : elements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
