// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 23, 2010

namespace com.eagle.programmar.COBOL.OldTransform
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using COBOL_IdentificationDivision = com.eagle.programmar.COBOL.COBOL_IdentificationDivision;
	using COBOL_IdentificationEntry = com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_IdentificationEntry;
	using COBOL_IdentificationPresent = com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_IdentificationPresent;
	using COBOL_IdentificationSimple = com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_IdentificationSimple;
	using COBOL_ProgramId = com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_ProgramId;
	using COBOL_Program_Complete = com.eagle.programmar.COBOL.COBOL_Program_Complete;
	using COBOL_Program_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Program_Definition;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_CommentToEndOfLine = com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_COBOL_Identification<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		public virtual void transformIdentificationDivision(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_Program_Complete program)
		{
			COBOL_IdentificationDivision div = program.identificationDiv;

			if (div.comments1 != null)
			{
				foreach (COBOL_Comment comment in div.comments1._elements)
				{
					trans._target._createProgram.addProgramComment(trans._target._mainPgm, comment.ToString(), comment);
				}
			}

			AbstractToken which = div.header.getWhich();
			COBOL_Program_Definition id;
			if (which is COBOL_IdentificationDivision.COBOL_ProgramId)
			{
				COBOL_IdentificationDivision.COBOL_ProgramId pgm = (COBOL_IdentificationDivision.COBOL_ProgramId) which;
				id = pgm.programDef;
			}
			else if (which is COBOL_IdentificationDivision.COBOL_IdentificationPresent)
			{
				COBOL_IdentificationDivision.COBOL_IdentificationPresent pgm = (COBOL_IdentificationDivision.COBOL_IdentificationPresent) which;
				id = pgm.programId.programDef;
			}
			else
			{
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
				throw new Exception("Excepted ID Division header, not " + which.GetType().FullName);
			}
			trans._target._createProgram.addProgramComment(trans._target._mainPgm, "PROGRAM " + id.ToString(), id);

			// AUTHOR, DATE_WRITTEN, etc.
			if (div.entries != null)
			{
				foreach (COBOL_IdentificationDivision.COBOL_IdentificationEntry entry in div.entries._elements)
				{
					which = entry.getWhich();
					if (which is COBOL_IdentificationDivision.COBOL_IdentificationSimple)
					{
						COBOL_IdentificationDivision.COBOL_IdentificationSimple simple = (COBOL_IdentificationDivision.COBOL_IdentificationSimple) which;
						foreach (COBOL_CommentToEndOfLine comment in simple.comments._elements)
						{
							trans._target._createProgram.addProgramComment(trans._target._mainPgm, simple.entryWord + " " + comment.getValue(), comment);
						}
					}
				}
			}

			if (div.comments2 != null)
			{
				foreach (COBOL_Comment comment in div.comments2._elements)
				{
					trans._target._createProgram.addProgramComment(trans._target._mainPgm, comment.ToString(), comment);
				}
			}
		}
	}

}
