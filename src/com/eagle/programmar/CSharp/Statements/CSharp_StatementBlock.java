// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.generate.Statements.Eagle_Generate_Block;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.CSharp_Syntax;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CSharp_StatementBlock extends TokenSequence
		implements EagleRunnableWithResult, EagleScopeInterface,
				Eagle_Generate_Block<CSharp_Statement>
{
	public @S(10) @INDENT PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<CSharp_StatementOrComment> statements;
	public @S(30) @OUTDENT PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (CSharp_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	@Override
	public CSharp_Statement generateBlock(ArrayList<AbstractStatement> statements,
			AbstractToken source)
	{
		this.leftBrace = new PunctuationLeftBrace();
		this.rightBrace = new PunctuationRightBrace();
		this.statements = new TokenList<CSharp_StatementOrComment>();
		for (AbstractStatement stmt : statements)
		{
			CSharp_StatementOrComment stmtComm = new CSharp_StatementOrComment();
			stmtComm.setWhich((CSharp_Statement) stmt);
			this.statements.addToken(stmtComm);
		}
		return CSharp_Generator.wrapStatement(this);
	}
}
