// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.COBOL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_Sentence extends TokenSequence implements EagleRunnable
{
	public @S(10) TokenList<COBOL_StatementOrComment> statements;
	public @S(20) @OPT PunctuationPeriod dot1;
	public @S(30) @CURIOUS("SENTENCE: Extra dot") @OPT PunctuationPeriod dot2;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_StatementOrComment statement : statements._elements)
		{
			interpreter.tryToInterpret(statement);
		}
	}

	public void transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		for (COBOL_StatementOrComment stmtOrComm : statements._elements)
		{
			if (stmtOrComm.getWhich() instanceof COBOL_Statement)
			{
				COBOL_Statement stmt = (COBOL_Statement) stmtOrComm.getWhich();
				if (stmt.getWhich() instanceof EagleTransformableStatement)
				{
					EagleTransformableStatement trans = (EagleTransformableStatement) stmt.getWhich();
					AbstractStatement newStmt = trans.transformStatement(transformer, generator);
					generator.addStatement(newStmt, stmt);
				}
				else
				{
					throw new RuntimeException("Unable to transform " + stmt.getWhich());
				}
			}
		}
	}
}