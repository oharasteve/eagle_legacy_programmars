// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 5, 2024

package com.eagle.programmar.Eaglish;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleSymbolTable;
import com.eagle.parsers.ParserManager;

public class Eaglish_Interpreter extends EagleInterpreter
{
	public Eaglish_Interpreter(ParserManager parser, EagleLanguage lang, EagleSymbolTable symbolTable)
	{
		super(parser, lang, symbolTable);
	}

	public Eagle_Statement_Result interpretBlock(ArrayList<Eaglish_Statement> stmts)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Eaglish_Statement stmt : stmts)
		{
			result = tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}
}
