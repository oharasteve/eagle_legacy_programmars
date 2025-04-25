// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Delphi;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Include;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.Delphi.Transform_Delphi;

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
	
	public void transformHeader(EagleGenerator generator)
	{
		AbstractToken which = this.getWhich();
		if (which instanceof Delphi_Comment)
		{
			String comment = ((Delphi_Comment) which).getValue();
			generator._mainClass.addComment(comment, which);
		}
		else if (which instanceof Delphi_Include)
		{
			String comment = ((Delphi_Include) which).getValue();
			generator._mainClass.addComment(comment, which);
		}
		else if (which instanceof Delphi_Uses)
		{
			Delphi_Uses uses = (Delphi_Uses) which;
			uses.transform(generator);
		}
		else if (which instanceof Delphi_Types)
		{
			Delphi_Types types = (Delphi_Types) which;
			types.transform(generator);
		}
		else if (which instanceof Delphi_Consts)
		{
			Delphi_Consts consts = (Delphi_Consts) which;
			consts.transform(generator);
		}
		else if (which instanceof Delphi_Vars)
		{
			Delphi_Vars vars = (Delphi_Vars) which;
			vars.transform(generator);
		}
		else if (which instanceof Delphi_Procedure)
		{
			_transformProcedure.transform(trans, (Delphi_Procedure) which);
		}
		else if (which instanceof Delphi_Function)
		{
			_transformFunction.transform(trans, (Delphi_Function) which);
		}
		else
			throw new RuntimeException("Cannot handle " + which + " yet.");
	}
}
