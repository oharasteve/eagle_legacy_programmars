// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2024

package com.eagle.programmar.PHP;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.PHP.PHP_Program.PHP_EndTag;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class PHP_Body extends TokenChooser
{
	// Really wasteful ... frequently parses twice
	public @CHOICE static class PHP_MissingEnd extends TokenSequence
	{
		public @S(10) TokenList<PHP_Element> elements;
		public @S(20) PHP_EndOfFile eof; // Can't be inside another class ...
	}

	public @CHOICE static class PHP_NormalBlock extends TokenSequence
			implements EagleRunnable, EagleTransformableStatementList
	{
		public @S(10) TokenList<PHP_Element> elements;
		public @S(20) PHP_EndTag endTag;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			for (PHP_Element entry : elements._elements)
			{
				interpreter.tryToInterpret(entry);
			}
		}

		@Override
		public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
			for (PHP_Element entry : elements._elements)
			{
				ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, entry.getWhich());
				if (stmts != null)
				{
					for (AbstractStatement stmt : stmts)
					{
						result.add(stmt);
					}
				}
			}
			return result;
		}
	}
}
