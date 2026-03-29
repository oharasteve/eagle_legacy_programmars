// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2015

namespace com.eagle.programmar.COBOL
{
	using EagleProject = com.eagle.core.EagleProject;
	using EagleSymbolTable = com.eagle.math.EagleSymbolTable;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleTracer = com.eagle.parsers.EagleTracer;
	using ParserManager = com.eagle.parsers.ParserManager;
	using EagleInclude = com.eagle.preprocess.EagleInclude;
	using AbstractToken = com.eagle.tokens.AbstractToken;

	public class COBOL_CopyBook : EagleInclude
	{
		public COBOL_CopyBook(EagleProject project, EagleSymbolTable symbolTable, EagleTracer tracer) : base(project, symbolTable, tracer)
		{
		}

		// TODO: Handle COBOL Copybooks with REPLACEMENT

		public override EagleFileReader preprocessFile(ParserManager parser, EagleFileReader lines)
		{
			return null;
		}

		public override void copyElement(AbstractToken token)
		{
		}
	}

}
