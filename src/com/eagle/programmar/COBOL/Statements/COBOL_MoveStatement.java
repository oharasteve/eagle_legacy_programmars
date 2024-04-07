// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_MoveStatement extends COBOL_AbstractStatement implements EagleRunnable
{
	public @S(10) @DOC("rlpsmove.htm") COBOL_Keyword MOVE = new COBOL_Keyword("MOVE");
	public @S(20) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	public @S(30) COBOL_Expression expr;
	public @S(40) COBOL_Keyword TO = new COBOL_Keyword("TO");
	public @S(50) @OPT COBOL_Variable var;
	public @S(60) @OPT TokenList<COBOL_MoveMore> more;
	public @S(70) @OPT @CURIOUS("MOVE: Extra comma") PunctuationComma comma;
	
	public static class COBOL_MoveMore extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) @OPT TokenList<COBOL_Comment> comments;
		public @S(30) COBOL_Variable var;
		public @S(40) @OPT COBOL_Subscript subscript;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (ALL.isPresent()) throw new RuntimeException("Can't handle MOVE ALL yet");
		if (more.isPresent() && more.size() > 0) throw new RuntimeException("Can't handle multiple MOVEs yet");
		
		EagleValue val = interpreter.getEagleValue(expr);
		AbstractToken which = var.getWhich();
		if (! (which instanceof COBOL_UserVariable))
		{
			throw new RuntimeException("Unable to handle " + which);
		}
		COBOL_UserVariable variable = (COBOL_UserVariable) which;
		interpreter._symbolTable.setSymbol(variable.id.getValue(), val);
	}
}
