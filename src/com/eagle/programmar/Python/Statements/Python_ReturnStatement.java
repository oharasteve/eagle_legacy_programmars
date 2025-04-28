// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.generate.Statements.Eagle_Generate_Return;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
			Eagle_Generate_Return<Python_Statement, Python_Expression>
{
	public @S(10) @DOC("compound_stmts.html#function-definitions") @NOSPACE Python_Keyword RETURN =
			new Python_Keyword("return");
	public @S(20) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(30) @OPT Python_ExpressionList expressionList;
	public @S(40) @OPT Python_Comment comment;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expressionList.expressions.first());
		interpreter.pushEagleValue(val);
		return Eagle_Statement_Result.RETURN;
	}
	
	@Override
	public Python_Statement generateReturn(Python_Expression ret, AbstractToken source)
	{
		if (ret != null && ret.isPresent())
		{
			this.expressionList = new Python_ExpressionList();
			this.expressionList.expressions =
					new SeparatedList<Python_Expression, PunctuationComma>();
			this.expressionList.expressions.addPrimaryElement(ret);
		}
		this.setTransformationSource(source);
		return Python_Generator.wrapStatement(this);
	}
}
