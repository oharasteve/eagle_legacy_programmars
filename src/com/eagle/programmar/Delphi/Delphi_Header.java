// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Delphi;

import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Include;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformer;

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

	public void processHeader(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = this.getWhich();
		if (which instanceof Delphi_Comment)
		{
			String comment = ((Delphi_Comment) which).getValue();
			generator.addComment(comment, which);
		}
		else if (which instanceof Delphi_Include)
		{
			String comment = ((Delphi_Include) which).getValue();
			generator.addComment(comment, which);
		}
		else if (which instanceof Delphi_Uses)
		{
			Delphi_Uses uses = (Delphi_Uses) which;
			uses.transformUses(transformer, generator);
		}
		else if (which instanceof Delphi_Types)
		{
			Delphi_Types types = (Delphi_Types) which;
			types.transformTypes(transformer, generator);
		}
		else if (which instanceof Delphi_Consts)
		{
			Delphi_Consts consts = (Delphi_Consts) which;
			consts.transformConsts(transformer, generator);
		}
		else if (which instanceof Delphi_Vars)
		{
			Delphi_Vars vars = (Delphi_Vars) which;
			vars.transformVars(transformer, generator);
		}
		else if (which instanceof Delphi_Procedure)
		{
			Delphi_Procedure proc = (Delphi_Procedure) which;
			proc.transformProcedure(transformer, generator);
		}
		else if (which instanceof Delphi_Function)
		{
			Delphi_Function func = (Delphi_Function) which;
			func.transformFunction(transformer, generator);
		}
		else
			throw new RuntimeException("Cannot handle " + which + " yet.");
	}
}
