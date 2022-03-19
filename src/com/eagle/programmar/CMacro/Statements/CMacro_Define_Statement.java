// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.math.TokenValue;
import com.eagle.preprocess.CMacro.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Symbols.CMacro_Define_Definition;
import com.eagle.programmar.CMacro.Symbols.CMacro_Parameter_Definition;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.programmar.CMacro.Terminals.CMacro_RestOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Define_Statement extends TokenSequence implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#'); 
	public @S(20) @DOC("Macros.html") CMacro_Keyword DEFINE = new CMacro_Keyword("define");
	public @S(30) CMacro_Define_Definition var;
	public @S(40) @OPT CMacro_Comment comment1;
	public @S(50) @OPT CMacro_Parameters params;
	public @S(60) @OPT CMacro_RestOfLine value;		// Just keep it as a String
	public @S(70) @OPT CMacro_Comment comment2;

	public static class CMacro_Parameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<CMacro_Parameter_Definition,PunctuationComma> params;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		String macroName = var.getValue();
		//System.out.println("#define " + macroName + " ...");
		if (preprocessor._project == null || preprocessor._project.expandMacro(macroName))
		{
			preprocessor._symbolTable.setSymbol(macroName, new TokenValue(this));
		}
		return true;	// No need to add these to the file
	}
}
