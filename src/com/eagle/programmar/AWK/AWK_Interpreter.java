// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 3, 2024

package com.eagle.programmar.AWK;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleSymbolTable;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;

public class AWK_Interpreter extends EagleInterpreter
{
	public AWK_Interpreter(ParserManager parser, EagleLanguage lang, EagleSymbolTable symbolTable)
	{
		super(parser, lang, symbolTable);
	}

	public Eagle_Statement_Result interpretBlock(AWK_Action block)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (AWK_StatementOrComment stmt : block.statements._elements)
		{
			result = tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}
}
