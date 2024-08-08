// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_Simple_Statement;
import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_Statement_List;
import com.eagle.programmar.FSharp.Statements.FSharp_Function;
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
		// First pass, just collect all the method definitions
		for (FSharp_Statement element : elements._elements)
		{
			if (element.statementOrComment.getWhich() instanceof FSharp_Statement_List)
			{
				FSharp_Statement_List statements = (FSharp_Statement_List) element.statementOrComment.getWhich();
				for (int i = 0; i < statements.statements.getPrimaryCount(); i++)
				{
					FSharp_Simple_Statement stmt = statements.statements.getPrimaryElement(i);
					if (stmt.getWhich() instanceof FSharp_Function)
					{
						FSharp_Function func = (FSharp_Function) stmt.getWhich();
						interpreter.addFunction(func.id.getValue(), func);
					}
				}
			}
		}

		// Second pass, run any stuff in the outermost class
		for (FSharp_Statement element : elements._elements)
		{
			interpreter.tryToInterpret(element.statementOrComment.getWhich());
		}
	}
}
