// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Interpreter;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_IfStatement extends COBOL_AbstractStatement implements EagleRunnableWithResult
{
	public @S(10) COBOL_Keyword IF = new COBOL_Keyword("IF");
	public @S(20) COBOL_Expression condition;
	public @S(30) @OPT COBOL_Keyword THEN = new COBOL_Keyword("THEN");
	public @S(40) TokenList<COBOL_StatementOrComment> thenActions;
	public @S(50) @OPT COBOL_Else elseClause;
	public @S(60) @OPT COBOL_Keyword ENDIF = new COBOL_Keyword("END-IF");
	
	public static class COBOL_Else extends TokenSequence
	{
		public @S(10) COBOL_Keyword ELSE = new COBOL_Keyword("ELSE");
		public @S(20) TokenList<COBOL_StatementOrComment> elseActions;
	}
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interp)
	{
		COBOL_Interpreter interpreter = (COBOL_Interpreter) interp;
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		
		boolean cond = interpreter.getBoolValue(condition);
		if (cond)
		{
			result = interpreter.interpretBlock(thenActions._elements);
		}
		else if (elseClause.isPresent())
		{
			result = interpreter.interpretBlock(elseClause.elseActions._elements);
		}
		
		return result;
	}
}
