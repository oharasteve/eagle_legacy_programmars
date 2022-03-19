// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.CMacro.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_If_Statement extends TokenSequence implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound1 = new CMacro_Punctuation('#'); 
	public @S(20) @DOC("If.html") CMacro_Keyword IF = new CMacro_Keyword("if");
	public @S(30) CMacro_Expression expr;
	public @S(40) @OPT CMacro_Comment comment1;
	public @S(50) CMacro_EndOfLine eoln1;
	
	public @S(60) @OPT TokenList<CMacro_Element> elements;
	public @S(70) @OPT TokenList<CMacro_IfElif> ifElif;
	public @S(80) @OPT CMacro_IfElse ifElse;
	public @S(90) @OPT CMacro_EndOfLine eoln2;
	
	public @S(100) CMacro_Punctuation pound2 = new CMacro_Punctuation('#'); 
	public @S(110) CMacro_Keyword ENDIF = new CMacro_Keyword("endif");
	public @S(120) @OPT CMacro_Comment comment2;

	public static class CMacro_IfElif extends TokenSequence
	{
		public @S(10) CMacro_Punctuation pound1 = new CMacro_Punctuation('#');
		public @S(20) CMacro_Keyword ELIF = new CMacro_Keyword("elif");
		public @S(30) CMacro_Expression expr;
		public @S(40) @OPT CMacro_Comment comment;
		public @S(50) @OPT CMacro_EndOfLine eoln;
		public @S(60) @OPT TokenList<CMacro_Element> elements;
	}
	
	public static class CMacro_IfElse extends TokenSequence
	{
		public @S(10) CMacro_Punctuation pound1 = new CMacro_Punctuation('#');
		public @S(20) CMacro_Keyword ELSE = new CMacro_Keyword("else");
		public @S(30) @OPT CMacro_Comment comment;
		public @S(40) @OPT CMacro_EndOfLine eoln;
		public @S(50) @OPT TokenList<CMacro_Element> elements;
	}
	
//	// Need this for switching languages from CMacro to C
//	public static class CMacro_IfElement extends TokenSequence
//	{
//		public @S(10) @SYNTAX(C_Syntax.class) C_StatementOrComment element;
//	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		boolean isTrue = expr.getBooleanValue(preprocessor);
		TokenList<CMacro_Element> whichElements = null;
		if (isTrue)
		{
			whichElements = elements;	// Use the "then" clause
		}
		else
		{
			// Check for #elif clauses
			for (CMacro_IfElif elif : ifElif._elements)
			{
				if (elif.expr.getBooleanValue(preprocessor))
				{
					whichElements = elif.elements;
					break;
				}
			}
		}
		
		// Well, maybe there is a #else clause
		if (whichElements == null && ifElse.isPresent())
		{
			whichElements = ifElse.elements;
		}
		
		// Dang, nothing matches, and no "else" clause
		if (whichElements == null) return true;
		
		for (AbstractToken token : whichElements._elements)
		{
			if (token instanceof CMacro_Element)
			{
				CMacro_Element element = (CMacro_Element) token;
				preprocessor.preprocessCMacroElement(preprocessor._parser, element);
			}
			else throw new RuntimeException("Didn't expect " + token + " here");
		}
		return true;	// Always change the file
	}
}
