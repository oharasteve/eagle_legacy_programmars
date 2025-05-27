// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

package com.eagle.programmar.COBOL.Transform;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.Old_Generate_Eagle_Expression.TYPES;
import com.eagle.generate.Old_Generate_Eagle_Method.METHOD_QUALIFIERS;
import com.eagle.generate.Old_Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.COBOL.COBOL_Paragraph;
import com.eagle.programmar.COBOL.COBOL_Paragraph.COBOL_SentenceOrComment;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision;
import com.eagle.programmar.COBOL.COBOL_Program_Complete;
import com.eagle.programmar.COBOL.COBOL_Section;
import com.eagle.programmar.COBOL.COBOL_Sentence;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_COBOL_Procedure<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public void transformProcedureDivision(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_Program_Complete program)
	{
		COBOL_ProcedureDivision proc = program.procedureDiv;
		for (COBOL_Section section : proc.sections._elements)
		{
			for (COBOL_Paragraph paragraph : section.paragraphs._elements)
			{
//				Generate_Method method = gen._mainClassGen.addMethod(methodLine, paragraph);
//				Generate_Block block = method._topBlock;
				String paraName = Transform_COBOL.fixName(paragraph.paragraphHeaders.first().paragraphName.toString());
				Meth method = trans._target._createMethod.createMethod(PRIVACY.PUBLIC, METHOD_QUALIFIERS.NONE,
						TYPES.VOID, null, paraName, null, paragraph);
				trans._target._createClass.addMethod(trans._target._mainClass, method);

				// Now go through each statement in the paragraph ...
				for (COBOL_SentenceOrComment sentenceOrComment : paragraph.sentences._elements)
				{
					AbstractToken whichSentComm = sentenceOrComment.getWhich();
					if (whichSentComm instanceof COBOL_Comment)
					{
						COBOL_Comment comment = (COBOL_Comment) whichSentComm;
						trans._target._createMethod.addMethodComment(method, comment.getValue(), comment);
					}
					else if (whichSentComm instanceof COBOL_Sentence)
					{
						COBOL_Sentence sentence = (COBOL_Sentence) whichSentComm;
						for (COBOL_StatementOrComment statementOrComment : sentence.statements._elements)
						{
							AbstractToken whichStmtComm = statementOrComment.getWhich();
							if (whichStmtComm instanceof COBOL_Comment)
							{
								COBOL_Comment comment = (COBOL_Comment) whichStmtComm;
								trans._target._createMethod.addMethodComment(method, comment.getValue(), comment);
							}
							else if (whichStmtComm instanceof COBOL_Statement)
							{
								COBOL_Statement oldStatement = (COBOL_Statement) whichStmtComm;
								Stmt newStatement = trans.transformStatement(oldStatement);
								trans._target._createMethod.addMethodStatement(method, newStatement, oldStatement);
							}
						}
					}
				}
			}
		}
	}
}