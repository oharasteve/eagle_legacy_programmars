// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.math.EagleValue;
import com.eagle.preprocess.CMacro.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CMacro_IfDef_Statement extends TokenSequence implements CMacro_Processable
{
	private static final String IFDEF = "ifdef";
	private static final String IFNDEF = "ifndef";
	
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#'); 
	public @S(20) @DOC("Ifdef.html") CMacro_KeywordChoice IFDEFNDEF = new CMacro_KeywordChoice(IFDEF, IFNDEF);
	public @S(30) CMacro_Identifier_Reference ref;
	public @S(40) @OPT CMacro_Comment comment1;
	public @S(50) CMacro_EndOfLine eoln1;
	public @S(60) @OPT TokenList<CMacro_Element> elements;
	public @S(70) @OPT TokenList<CMacro_IfDefElif> ifElif;
	public @S(80) @OPT CMacro_IfDefElse ifElse;
	public @S(90) @OPT CMacro_EndOfLine eoln2;
	public @S(100) CMacro_Punctuation pound2 = new CMacro_Punctuation('#'); 
	public @S(110) CMacro_Keyword ENDIF = new CMacro_Keyword("endif");
	public @S(120) @OPT CMacro_Comment comment2;
	
	public static class CMacro_IfDefElif extends TokenSequence
	{
		public @S(10) CMacro_Punctuation pound1 = new CMacro_Punctuation('#');
		public @S(20) CMacro_Keyword ELIF = new CMacro_Keyword("elif");
		public @S(30) CMacro_Expression expr;
		public @S(40) @OPT CMacro_Comment comment;
		public @S(50) @OPT CMacro_EndOfLine eoln;
		public @S(60) @OPT TokenList<CMacro_Element> elements;
	}

	public static class CMacro_IfDefElse extends TokenSequence
	{
		public @S(10) @OPT CMacro_EndOfLine eoln1;
		public @S(20) CMacro_Punctuation pound1 = new CMacro_Punctuation('#'); 
		public @S(30) CMacro_Keyword ELSE = new CMacro_Keyword("else");
		public @S(40) @OPT CMacro_Comment comment;
		public @S(50) CMacro_EndOfLine eoln2;
		public @S(60) @OPT TokenList<CMacro_Element> elements;
	}
	
	public static class CMacro_IfDefCPlusPlus extends TokenSequence
	{
		public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#'); 
		public @S(20) @DOC("Ifdef.html") CMacro_Keyword ifdef1 = new CMacro_Keyword("ifdef");
		public @S(30) CMacro_Keyword CPLUSPLUS1 = new CMacro_Keyword("__cplusplus");
		public @S(40) CMacro_EndOfLine eoln1;
		public @S(50) CMacro_Keyword EXTERN = new CMacro_Keyword("extern");
		public @S(60) CMacro_Literal C;
		public @S(70) PunctuationLeftBrace leftBrace;
		public @S(80) CMacro_EndOfLine eoln2;
		public @S(90) CMacro_Punctuation pound2 = new CMacro_Punctuation('#'); 
		public @S(100) CMacro_Keyword ENDIF1 = new CMacro_Keyword("endif");
		public @S(110) CMacro_EndOfLine eoln3;
		
		public @S(120) @OPT TokenList<CMacro_Element> elements;

		public @S(130) CMacro_Punctuation pound3 = new CMacro_Punctuation('#'); 
		public @S(140) @DOC("Ifdef.html") CMacro_Keyword ifdef2 = new CMacro_Keyword("ifdef");
		public @S(150) CMacro_Keyword CPLUSPLUS2 = new CMacro_Keyword("__cplusplus");
		public @S(160) CMacro_EndOfLine eoln4;
		public @S(170) PunctuationRightBrace rightBrace;
		public @S(180) @OPT CMacro_Comment comment;
		public @S(190) CMacro_EndOfLine eoln5;
		public @S(200) CMacro_Punctuation pound4 = new CMacro_Punctuation('#'); 
		public @S(210) CMacro_Keyword ENDIF2 = new CMacro_Keyword("endif");
	}
	
	// Need this for switching languages from CMacro to C
//	public static class CMacro_IfDefElement extends TokenSequence
//	{
//		public @S(10) @SYNTAX(C_Syntax.class) C_StatementOrComment element;
//	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		String macroName = ref.getValue();
		//System.out.println("#" + IFDEFNDEF + " " + macroName + " ...");
		boolean isIfDef = IFDEFNDEF.toString().equals(IFDEF);
		EagleValue value = preprocessor._symbolTable.findSymbol(macroName);
		
		TokenList<CMacro_Element> whichElements;
		if (value == null ^ isIfDef)	// XOR
		{
			whichElements = elements;
		}
		else if (ifElse.isPresent())
		{
			whichElements = ifElse.elements;
		}
		else return true;	// No "#else" given.
		
		for (AbstractToken token : whichElements._elements)
		{
			if (token instanceof CMacro_Element)
			{
				CMacro_Element element = (CMacro_Element) token;
				preprocessor.preprocessCMacroElement(preprocessor._parser, element);
			}
		}

		return true;	// Always change the file
	}
}
