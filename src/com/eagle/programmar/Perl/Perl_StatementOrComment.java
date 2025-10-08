// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Perl_StatementOrComment extends TokenChooser
		implements EagleTransformableStatement
{
	public @CHOICE Perl_Statement XXstatement;
	public @CHOICE Perl_Comment XXcomment;
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		if (! (getWhich() instanceof Perl_Statement))
		{
			return null;
		}
		Perl_Statement stmt = (Perl_Statement) getWhich();
		return transformer.transformStatement1(generator, stmt.getWhich());
	}
}