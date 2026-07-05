// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.TCL.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.TCL.TCL_Element;
import com.eagle.programmar.TCL.TCL_Element.TCL_Statement;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class TCL_BlockStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TCL_EndOfLine endOfLine;
	public @S(30) @OPT TCL_GlobalVariables globals;
	public @S(40) @OPT TokenList<TCL_Element> statements;
	public @S(50) PunctuationRightBrace rightBrace;

	public static class TCL_GlobalVariables extends TokenSequence
	{
		public @S(10) TCL_Keyword GLOBAL = new TCL_Keyword("global");
		public @S(20) TokenList<TCL_Variable> vars;
		public @S(30) TCL_EndOfLine endOfLine;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (TCL_Element element : statements._elements)
		{
			result = interpreter.tryToInterpret(element);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> stmts = new ArrayList<AbstractStatement>();
		if (statements != null && statements.isPresent())
		{
			for (TCL_Element element : statements._elements)
			{
				int nstmts = element.statements.getPrimaryCount();
				for (int i = 0; i < nstmts; i++)
				{
					TCL_Statement stmt = element.statements.getPrimaryElement(i);
					ArrayList<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							stmts.add(newStmt);
						}
					}
				}
			}
		}

		return generator.newBlockStatement(stmts, this);
	}
}
