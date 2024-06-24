// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenList;

public class FSharp_Program extends EagleLanguage implements EagleRunnable
{
	public static final String FSHARP = "FSharp";

	public FSharp_Program()
	{
		super(FSHARP, new FSharp_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.microsoft.com/en-us/dotnet/fsharp/language-reference/";
	}

	public @S(10) TokenList<FSharp_Statement> elements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (FSharp_Statement stmt : elements._elements)
		{
			interpreter.tryToInterpret(stmt.statementOrComment);
		}
	}
}
