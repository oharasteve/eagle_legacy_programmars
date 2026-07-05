// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.COBOL;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class COBOL_StatementOrComment extends TokenChooser
		implements EagleTransformableStatementList
{
	public @CHOICE COBOL_Comment XXcomment;
	public @CHOICE COBOL_Statement XXstatement;

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = this.getWhich();
		if (which instanceof COBOL_Statement)
		{
			COBOL_Statement stmt = (COBOL_Statement) which;
			if (stmt instanceof EagleTransformableStatementList)
			{
				return transformer.transformStatement(generator, stmt.getWhich());
			}

			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, stmt.getWhich());
			return stmts;
		}

		return null; // Toss comments for now
	}
}