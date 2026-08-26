// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.math.EagleToken;
import com.eagle.preprocess.CMacro.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Symbols.CMacro_Define_Definition;
import com.eagle.programmar.CMacro.Symbols.CMacro_Parameter_Definition;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.programmar.CMacro.Terminals.CMacro_RestOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Define_Statement extends TokenSequence
		implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#');
	public @S(20) @DOC("Macros.html") CMacro_Keyword DEFINE = new CMacro_Keyword("define");
	public @S(30) CMacro_Define_Definition var;
	public @S(40) @OPT CMacro_Comment comment1;
	public @S(50) @OPT CMacro_Parameters params;
	public @S(60) @OPT CMacro_RestOfLine value; // Just keep it as a String
	public @S(70) @OPT CMacro_Comment comment2;

	public static class CMacro_EagleToken extends EagleToken
	{
		public CMacro_EagleToken(CMacro_Define_Statement token)
		{
			super(token);
		}
		
		@Override
		public String forceStringValue()
		{
			CMacro_Define_Statement token = (CMacro_Define_Statement) getTokenValue();
			return token.value.getValue();
		}
	}
	
	public static class CMacro_Parameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<CMacro_Param, PunctuationComma> params;
		public @S(30) PunctuationRightParen rightParen;

		public static class CMacro_Param extends TokenChooser
		{
			public @CHOICE CMacro_Parameter_Definition XXvar;
			public @CHOICE CMacro_Punctuation XXdotDotDot = new CMacro_Punctuation("...");
		}
	}

	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		String macroName = var.getValue();
		if (CMacro_Preprocess.VERBOSE)
		{
			System.out.print("#define " + macroName + " ... ");
			System.out.flush();
		}
		if (preprocessor._project == null || preprocessor._project.expandMacro(macroName))
		{
			// The -1 means no subscript
			preprocessor._symbolTable.setSymbol(var, macroName, -1, new CMacro_EagleToken(this));
			if (CMacro_Preprocess.VERBOSE)
			{
				System.out.println(this.value.getValue());
			}
		}
		return true; // No need to add these to the file
	}
}
