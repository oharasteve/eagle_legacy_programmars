// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.CMacro.Statements.CMacro_Pragma_Statement;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_MultiLineText;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_Program extends AbstractLanguage
{
	public static final String CMACRO = "CMacro";

	public CMacro_Program()
	{
		super(CMACRO, new CMacro_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://gcc.gnu.org/onlinedocs/cpp/";
	}

//	@Override
//	public void findClassOverrides(EagleOverrideManager overrideManager)
//	{
//		// Instead of creating a bunch of real C statements inside a #if, just use this simple class
//		overrideManager.override(CMacro_IfElement.class, CMacro_Element.class);
//		overrideManager.override(CMacro_IfDefElement.class, CMacro_Element.class);
//	}

	public @S(10) TokenList<CMacro_Element> elements;

	public static class CMacro_Element extends TokenChooser
	{
		public @CHOICE CMacro_Pragma_Statement XXpragma;
		public @CHOICE CMacro_CommentLine XXcomment;
		public @CHOICE CMacro_StatementOrComment XXstmt;
		public @CHOICE CMacro_MultiLineText XXtextLine;
		public @LAST CMacro_EndOfLine XXendOfLine;
	}

	public static class CMacro_CommentLine extends TokenSequence
	{
		public @S(10) CMacro_Comment comment;
		public @S(20) CMacro_EndOfLine endOfLine;
	}
}
