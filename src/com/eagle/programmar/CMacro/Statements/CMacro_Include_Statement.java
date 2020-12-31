// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import java.io.IOException;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_IncludeSys;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_Include_Statement extends TokenSequence implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#'); 
	public @S(20) @DOC("Include-Syntax.html") CMacro_Keyword INCLUDE = new CMacro_Keyword("include");
	public @S(30) CMacro_IncludeWhat what;
	public @S(40) @OPT TokenList<CMacro_Comment> comments;
	public @S(50) CMacro_EndOfLine endOfLine;
	
	public static class CMacro_IncludeWhat extends TokenChooser
	{
		public @CHOICE CMacro_Literal filename;
		public @CHOICE CMacro_IncludeSys sys;
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		AbstractToken which = what.getWhich(); 
		if (! (which instanceof CMacro_Literal)) return false;
		String fileName = ((CMacro_Literal) which).getValue();
		//System.out.println("#include " + macroName);
		EagleFileReader macro;
		try
		{
			if (fileName.startsWith("\"") && fileName.endsWith("\""))
			{
				int len = fileName.length();
				fileName = fileName.substring(1, len-1);
			}
			macro = preprocessor._findInclude.findFile("", fileName);
		}
		catch (IOException ex)
		{
			return false;
		}
		if (macro == null) return false;

		try
		{
			CMacro_Preprocess innerPreprocessor = new CMacro_Preprocess(preprocessor);
			EagleFileReader macroLines = innerPreprocessor.preprocessFile(preprocessor._parser, macro, preprocessor._depth+1);
			for (EagleLineReader line : macroLines.lines())
			{
				preprocessor.addLine(line);
			}
		}
		catch (Exception ex)
		{
			System.err.println("Failed parsing " + fileName);
			ex.printStackTrace(System.err);
			// Failed -- just leave the #include alone
			return true;
		}
		
		return true;
	}
}
