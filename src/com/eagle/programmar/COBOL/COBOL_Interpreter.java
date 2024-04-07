// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 6, 2024

package com.eagle.programmar.COBOL;

import java.util.ArrayList;
import java.util.HashMap;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleSymbolTable;
import com.eagle.parsers.ParserManager;

public class COBOL_Interpreter extends EagleInterpreter
{
	public HashMap<String, COBOL_Paragraph> _paragraphs = null;
	
	public COBOL_Interpreter(ParserManager parser, EagleSymbolTable symbolTable)
	{
		super(parser, symbolTable);
	}

	public Eagle_Statement_Result interpretBlock(ArrayList<COBOL_StatementOrComment> stmts)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (COBOL_StatementOrComment stmt : stmts)
		{
			result = tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}
}