// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2024

namespace com.eagle.preprocess.CMacro
{
	using EagleProject = com.eagle.core.EagleProject;
	using EagleSymbolTable = com.eagle.math.EagleSymbolTable;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using EagleTracer = com.eagle.parsers.EagleTracer;
	using ParserManager = com.eagle.parsers.ParserManager;
	using EagleInclude = com.eagle.preprocess.EagleInclude;
	using FindIncludeFile = com.eagle.preprocess.FindIncludeFile;
	using AbstractToken = com.eagle.tokens.AbstractToken;

	public class CMacro_PostPreprocess : EagleInclude
	{
		public FindIncludeFile _findInclude;

		public CMacro_PostPreprocess(EagleProject project, FindIncludeFile findInclude, EagleSymbolTable symbolTable, EagleTracer tracer) : base(project, symbolTable, tracer)
		{
			_findInclude = findInclude;
		}

		public CMacro_PostPreprocess(CMacro_PostPreprocess preprocessor) : this(preprocessor._project, preprocessor._findInclude, preprocessor._symbolTable, preprocessor._tracer)
		{
		}

		public override EagleFileReader preprocessFile(ParserManager parser, EagleFileReader lines)
		{
			foreach (EagleLineReader line in lines.lines())
			{
				// Looks like # 28 "/usr/include/stdio.h" 2 3 4
				// Could probably extract both the file name and the line number
				if (!line.startsWith("#"))
				{
					_newLines.addLine(line);
				}
			}
			return _newLines;
		}

		public override void copyElement(AbstractToken token)
		{
			throw new Exception("Should not need to call this.");
		}
	}

}
