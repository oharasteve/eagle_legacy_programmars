// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 27, 2015

package com.eagle.programmar.CMacro;

import com.eagle.core.EagleInterpreter;
import com.eagle.math.EagleSymbolTable;
import com.eagle.parsers.ParserManager;

public class CMacro_Interpreter extends EagleInterpreter
{
	public CMacro_Interpreter(ParserManager parser, EagleSymbolTable symbolTable)
	{
		super(parser, null, symbolTable);
	}
}
