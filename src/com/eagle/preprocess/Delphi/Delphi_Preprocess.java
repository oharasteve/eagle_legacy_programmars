// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2017

package com.eagle.preprocess.Delphi;

import java.io.IOException;

import com.eagle.core.EagleProject;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.parsers.EagleTracer;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleInclude;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.preprocess.FindIncludeFile;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalEndOfLine;

public class Delphi_Preprocess extends EagleInclude
{
	private FindIncludeFile _findInclude;
	private static final boolean DEBUG = false;
	
    private static final String StartInclude = "{$I ";
	private static final String EndInclude = "}";
	
	public Delphi_Preprocess(EagleProject project, FindIncludeFile findInclude, EagleSymbolTable symbolTable, EagleTracer tracer)
	{
		super(project, symbolTable, tracer);
		_findInclude = findInclude;
	}
	
	@Override // Recursive
	public EagleFileReader preprocessFile(ParserManager parser, EagleFileReader lines)
	{
		if (DEBUG)
		{
			System.out.println("===================================================");
			System.out.println("================ Pre-processing " + lines.getFileName() + " lines=" + lines.numberLines());
			System.out.println();
		}

		if (_depth > 0)
		{
			if (_project != null)
			{
				// The outermost #include file has already been repaired -- don't try to do it twice
				_project.performRepairs(lines.getFileName(), lines);
			}
		}	
		
		// Save origin information for every line
		for (int i = 0; i < lines.numberLines(); i++)
		{
			EagleLineReader line = lines.get(i);
			String fname = line.getOriginalFileName();
			if (fname == null)
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
				String trimmedLine = line.trim();
				if (trimmedLine.startsWith(StartInclude))
				{
					int ec = trimmedLine.indexOf(EndInclude);
					if (ec > 0)
					{
						try
						{
							String includeFile = trimmedLine.substring(StartInclude.length(), ec);
							EagleFileReader incFile = _findInclude.findFile(null, includeFile);
							incFile.setFileName(includeFile);
							preprocessFile(parser, incFile);
						}
						catch (IOException ex)
						{
							// Skip any {$I include.file } that we can't find
						}
					}
				}
			}
		}
		
		return _newLines;
	}

	@Override
	public void copyElement(AbstractToken token)
	{
		if (token instanceof TerminalEndOfLine) return;
		
		//System.out.println("******************* token = " + token.getClass().getName());
		for (int seq = token.getStartLine(); seq <= token.getEndLine(); seq++)
		{
			if (seq == token.getEndLine() && token.getEndChar() < 0) break;	// Went a little too far with EOLN
			EagleLineReader oldLine = _oldLines.get(seq);
			_newLines.addLine(oldLine);
		}
	}
}
