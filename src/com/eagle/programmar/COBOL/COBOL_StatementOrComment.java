// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.COBOL;

import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_StatementOrComment extends TokenChooser
{
	public @CHOICE COBOL_Comment XXcomment;
	public @CHOICE COBOL_Statement XXstatement;

	public AbstractStatement transform(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractToken which = this.getWhich();
		if (which instanceof COBOL_Statement)
		{
			COBOL_Statement stmt = (COBOL_Statement) which;
			return transformer.transformStatement1(generator, stmt.getWhich());
		}
		return null;	// Toss comments for now
	}
}