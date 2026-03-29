// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2017

namespace com.eagle.preprocess.Delphi
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
	using TerminalEndOfLine = com.eagle.tokens.terminals.TerminalEndOfLine;

	public class Delphi_Preprocess : EagleInclude
	{
		private FindIncludeFile _findInclude;
		private const bool DEBUG = true;

		private const string StartInclude = "{$I ";
		private const string EndInclude = "}";

		public Delphi_Preprocess(EagleProject project, FindIncludeFile findInclude, EagleSymbolTable symbolTable, EagleTracer tracer) : base(project, symbolTable, tracer)
		{
			_findInclude = findInclude;
		}

		public override EagleFileReader preprocessFile(ParserManager parser, EagleFileReader lines)
		{
			if (DEBUG)
			{
				Console.WriteLine("===================================================");
				Console.WriteLine("================ Pre-processing " + lines.getFileName() + " lines=" + lines.numberLines());
				Console.WriteLine();
			}

			if (_depth > 0)
			{
				if (_project != null)
				{
					// The outermost #include file has already been repaired -- don't try to do it
					// twice
					_project.performRepairs(lines.getFileName(), lines);
				}
			}

			// Save origin information for every line
			for (int i = 0; i < lines.numberLines(); i++)
			{
				EagleLineReader line = lines.get(i);
				string fname = line.getOriginalFileName();
				if (string.ReferenceEquals(fname, null))
				{
					line.setOriginalLocation(lines.getFileName());
				}
			}

			for (int i = 0; i < lines.numberLines(); i++)
			{
				EagleLineReader line = lines.get(i);
				_newLines.addLine(line);

				if (line.indexOf(StartInclude) >= 0)
				{
					string trimmedLine = line.trim();
					if (trimmedLine.StartsWith(StartInclude, StringComparison.Ordinal))
					{
						int ec = trimmedLine.IndexOf(EndInclude, StringComparison.Ordinal);
						if (ec > 0)
						{
							try
							{
																var tempVar = StartInclude.Length;
								string includeFile = trimmedLine.Substring(tempVar, ec - tempVar);
								EagleFileReader incFile = _findInclude.findIncludeFile(null, includeFile);
								incFile.setFileName(includeFile);
								preprocessFile(parser, incFile);
							}
							catch (IOException)
							{
								// Skip any {$I include.file } that we can't find
							}
						}
					}
				}
			}

			return _newLines;
		}

		public override void copyElement(AbstractToken token)
		{
			if (token is TerminalEndOfLine)
			{
				return;
			}

			// System.out.println("******************* token = " +
			// token.getClass().getName());
			for (int seq = token.getStartLine(); seq <= token.getEndLine(); seq++)
			{
				if (seq == token.getEndLine() && token.getEndChar() < 0)
				{
					break; // Went a little too far with EOLN
				}
				EagleLineReader oldLine = _oldLines.get(seq);
				_newLines.addLine(oldLine);
			}
		}
	}

}
