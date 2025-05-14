// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_StatementOrComment;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Python_StatementBlock extends TokenChooser
{
	public @CHOICE Python_Punctuation XXdots = new Python_Punctuation("...");

	public @CHOICE static class Python_SameLineStatement extends TokenSequence implements EagleRunnableWithResult
	{
		public @S(10) SeparatedList<Python_Statement, PunctuationSemicolon> statements;
		public @S(20) @OPT Python_Comment comment;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				Python_Statement stmt = statements.getPrimaryElement(i);
				result = interpreter.tryToInterpret(stmt.getWhich());
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			return result;
		}
	}

	public @CHOICE static class Python_MultilineStatement extends TokenSequence
			implements EagleRunnableWithResult
	{
		public @S(10) @OPT Python_Comment comment;
		public @S(20) Python_EndOfLine eoln;
		public @S(30) TokenList<Python_ComplexStatement> statements;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (Python_ComplexStatement stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			return result;
		}
	}
	
	public Python_ComplexStatement addStatements(ArrayList<? extends AbstractStatement> statements)
	{
		Python_MultilineStatement multi = new Python_MultilineStatement();
		multi.statements = new TokenList<Python_ComplexStatement>();
		for (AbstractStatement stmt : statements)
		{
			multi.statements.addToken((Python_ComplexStatement) stmt);
		}
		this.setWhich(multi);
		Python_ComplexStatement wrapper = new Python_ComplexStatement();
		wrapper.statementOrComment = new Python_StatementOrComment();
		wrapper.statementOrComment.setWhich(multi);
		return wrapper;
	}
}
