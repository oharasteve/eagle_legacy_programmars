// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 5, 2024

package com.eagle.programmar.Java;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleSymbolTable;
import com.eagle.parsers.ParserManager;

public class Java_Interpreter extends EagleInterpreter
{
	public Java_Interpreter(ParserManager parser, EagleSymbolTable symbolTable)
	{
		super(parser, symbolTable);
	}

	public Eagle_Statement_Result interpretBlock(ArrayList<Java_Statement> stmts)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Java_Statement stmt : stmts)
		{
			result = tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}
}
