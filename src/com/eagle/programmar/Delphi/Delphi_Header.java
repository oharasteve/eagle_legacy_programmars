// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Include;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Header extends TokenChooser
{
	public @CHOICE Delphi_KeywordChoice XXINTERFACE = new Delphi_KeywordChoice("Interface", "Implementation");

	public @CHOICE Delphi_Comment XXcomment;

	public @CHOICE Delphi_Uses XXuses;
	public @CHOICE Delphi_Types XXtypes;
	public @CHOICE Delphi_Consts XXconsts;
	public @CHOICE Delphi_Vars XXvars;
	public @CHOICE Delphi_Procedure XXproc;
	public @CHOICE Delphi_Function XXfunc;
	public @CHOICE Delphi_Include XXinclude;

	public @CHOICE static class Delphi_Initialization extends TokenSequence
	{
		public @S(10) Delphi_KeywordChoice INITIALIZATION = new Delphi_KeywordChoice("Initialization",
				"Finalization");
		public @S(20) Delphi_Statement stmt;
		public @S(30) PunctuationSemicolon semicolon;
	}
}
