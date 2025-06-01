// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 23, 2010

package com.eagle.programmar.COBOL.Transform;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.COBOL_IdentificationDivision;
import com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_IdentificationEntry;
import com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_IdentificationPresent;
import com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_IdentificationSimple;
import com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_ProgramId;
import com.eagle.programmar.COBOL.COBOL_Program_Complete;
import com.eagle.programmar.COBOL.Symbols.COBOL_Program_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_COBOL_Identification<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public void transformIdentificationDivision(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_Program_Complete program)
	{
		COBOL_IdentificationDivision div = program.identificationDiv;

		if (div.comments1 != null)
		{
			for (COBOL_Comment comment : div.comments1._elements)
			{
				trans._target._createProgram.addProgramComment(trans._target._mainPgm, comment.toString(), comment);
			}
		}

		AbstractToken which = div.header.getWhich();
		COBOL_Program_Definition id;
		if (which instanceof COBOL_ProgramId)
		{
			COBOL_ProgramId pgm = (COBOL_ProgramId) which;
			id = pgm.programDef;
		}
		else if (which instanceof COBOL_IdentificationPresent)
		{
			COBOL_IdentificationPresent pgm = (COBOL_IdentificationPresent) which;
			id = pgm.programId.programDef;
		}
		else
			throw new RuntimeException("Excepted ID Division header, not " + which.getClass().getCanonicalName());
		trans._target._createProgram.addProgramComment(trans._target._mainPgm, "PROGRAM " + id.toString(), id);

		// AUTHOR, DATE_WRITTEN, etc.
		if (div.entries != null)
		{
			for (COBOL_IdentificationEntry entry : div.entries._elements)
			{
				which = entry.getWhich();
				if (which instanceof COBOL_IdentificationSimple)
				{
					COBOL_IdentificationSimple simple = (COBOL_IdentificationSimple) which;
					for (COBOL_CommentToEndOfLine comment : simple.comments._elements)
					{
						trans._target._createProgram.addProgramComment(trans._target._mainPgm,
								simple.entryWord + " " + comment.getValue(), comment);
					}
				}
			}
		}

		if (div.comments2 != null)
		{
			for (COBOL_Comment comment : div.comments2._elements)
			{
				trans._target._createProgram.addProgramComment(trans._target._mainPgm, comment.toString(), comment);
			}
		}
	}
}
