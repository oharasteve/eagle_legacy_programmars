// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Delphi.Delphi_Statement_List;
import com.eagle.programmar.Delphi.Delphi_Statement_List.Delphi_NextStatement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_BeginEnd extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("Programs_and_Units_(Delphi)#The_Block") Delphi_Keyword BEGIN = new Delphi_Keyword("Begin");
	public @S(20) @OPT TokenList<Delphi_Comment> comments;
	public @S(30) @OPT Delphi_Statement_List statements;
	public @S(40) Delphi_Keyword END = new Delphi_Keyword("End");

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = interpreter.tryToInterpret(statements.stmt);
		if (result != Eagle_Statement_Result.NORMAL) return result;
		if (statements.stmts != null)
		{
			for (Delphi_NextStatement more : statements.stmts._elements)
			{
				result = interpreter.tryToInterpret(more.stmt);
				if (result != Eagle_Statement_Result.NORMAL) return result;
			}
		}
		return result;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		ArrayList<AbstractStatement> stmts = new ArrayList<AbstractStatement>();
		AbstractStatement newStmt = transformer.transformStatement1(generator,
				this.statements.stmt.getWhich());
		if (newStmt != null)
		{
			stmts.add(newStmt);
		}
		
		if (this.statements.stmts != null)
		{
			for (Delphi_NextStatement more : this.statements.stmts._elements)
			{
				newStmt = transformer.transformStatement1(generator, more.stmt.getWhich());
				if (newStmt != null)
				{
					stmts.add(newStmt);
				}
			}
		}

		return generator.newBlockStatement(stmts, this);
	}
}
