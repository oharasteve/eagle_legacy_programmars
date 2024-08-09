// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2024

package com.eagle.preprocess.CMacro;

import com.eagle.core.EagleProject;
import com.eagle.math.EagleSymbolTable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.parsers.EagleTracer;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleInclude;
import com.eagle.preprocess.FindIncludeFile;
import com.eagle.tokens.AbstractToken;

public class CMacro_PostPreprocess extends EagleInclude
{
	public FindIncludeFile _findInclude;

	public CMacro_PostPreprocess(EagleProject project, FindIncludeFile findInclude, EagleSymbolTable symbolTable,
			EagleTracer tracer)
	{
		super(project, symbolTable, tracer);
		_findInclude = findInclude;
	}

	public CMacro_PostPreprocess(CMacro_PostPreprocess preprocessor)
	{
		this(preprocessor._project, preprocessor._findInclude, preprocessor._symbolTable, preprocessor._tracer);
	}

	@Override // Recursive
	public EagleFileReader preprocessFile(ParserManager parser, EagleFileReader lines)
	{
		for (EagleLineReader line : lines.lines())
		{
			if (! line.startsWith("#"))
			{
				_newLines.addLine(line);
			}
		}
		return _newLines;
	}

	@Override
	public void copyElement(AbstractToken token)
	{
		throw new RuntimeException("Should not need to call this.");
	}
}
