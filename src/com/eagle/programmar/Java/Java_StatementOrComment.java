// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Java;

import java.util.ArrayList;

import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Java_StatementOrComment extends TokenChooser
		implements EagleTransformableStatementList
{
	public @FIRST @NEWLINE Java_Comment XXcomment;
	public @CHOICE @NEWLINE Java_Statement XXstatement;

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (this.getWhich() instanceof Java_Statement)
		{
			Java_Statement stmt = (Java_Statement) this.getWhich();
			return transformer.transformStatement(generator, stmt.getWhich());
		}
		return null; // Must be a comment -- toss it
	}
}
