// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_WhileStatement<PS extends Python_Simple_Statement> extends TokenSequence
{
	public @NOSPACE Python_Keyword WHILE = new Python_Keyword("while");
	public Python_Expression condition;
	public @NOSPACE PunctuationColon colon;
	public Python_SingleOrMultiLineStatement<PS> statements;
	public @OPT Python_WhileElse<PS> whileElse;
	
	public static class Python_WhileElse<PS extends Python_Simple_Statement> extends TokenSequence
	{
		public Python_StartOfLine soln = new Python_StartOfLine();
		public Python_Keyword ELSE = new Python_Keyword("else");
		public PunctuationColon colon;
		public Python_SingleOrMultiLineStatement<PS> doWhat;
	}
}
