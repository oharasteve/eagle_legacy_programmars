// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2024

package com.eagle.programmar.Scala;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Scala.Terminals.Scala_Comment;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Scala_CommentEoln extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Scala_Comment comment;
	public @S(20) Scala_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return null; // Might want to keep comment statements somehow.
	}
}