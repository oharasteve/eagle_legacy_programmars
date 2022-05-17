// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Symbols.Delphi_Program_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Include;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Program extends EagleLanguage
{
	public static final String DELPHI = "Delphi";
	
	public Delphi_Program()
	{
		super(DELPHI, new Delphi_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://docwiki.embarcadero.com/RADStudio/en/Declarations_and_Statements/";
	}
	
	public @S(10) Delphi_Full_or_Partial fullOrPartial;
	
	public static class Delphi_Full_or_Partial extends TokenChooser
	{
		public @FIRST static class Delphi_Full extends TokenSequence
		{
			public @S(10) @OPT TokenList<Delphi_Comment> comments1;
			public @S(20) Delphi_KeywordChoice programOrUnit = new Delphi_KeywordChoice("Program", "Unit");
			public @S(30) Delphi_Program_Definition id;
			public @S(40) PunctuationSemicolon semicolon;
			public @S(50) @OPT TokenList<Delphi_Header> headers;
			public @S(60) @OPT Delphi_BeginEnd beginEnd;
			public @S(70) @OPT Delphi_Keyword END = new Delphi_Keyword("End");
			public @S(80) PunctuationPeriod dot;
			public @S(90) @OPT TokenList<Delphi_Comment> comments2;
		}
		
		public @CHOICE static class Delphi_Partial extends TokenSequence
		{
			public @S(10) TokenList<Delphi_Header> headers;
		}
	}
	
	public static class Delphi_Header extends TokenChooser
	{
		public @CHOICE Delphi_KeywordChoice INTERFACE = new Delphi_KeywordChoice("Interface", "Implementation");
		
		public @CHOICE Delphi_Comment comment;
		
		public @CHOICE Delphi_Uses uses;
		public @CHOICE Delphi_Types types;
		public @CHOICE Delphi_Consts consts;
		public @CHOICE Delphi_Vars vars;
		public @CHOICE Delphi_Procedure proc;
		public @CHOICE Delphi_Function func;
		public @CHOICE @NEWLINE Delphi_Include include;
		
		public @CHOICE static class Delphi_Initialization extends TokenSequence
		{
			public @S(10) Delphi_KeywordChoice INITIALIZATION = new Delphi_KeywordChoice("Initialization", "Finalization");
			public @S(20) Delphi_Statement stmt;
			public @S(30) PunctuationSemicolon semicolon;
		}
	}
}
