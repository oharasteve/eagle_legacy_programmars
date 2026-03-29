// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

namespace com.eagle.programmar.COBOL.OldTransform
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using TYPES = com.eagle.oldGenerate.Old_Generate_Eagle_Expression.TYPES;
	using METHOD_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Method.METHOD_QUALIFIERS;
	using PRIVACY = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
	using COBOL_Paragraph = com.eagle.programmar.COBOL.COBOL_Paragraph;
	using COBOL_SentenceOrComment = com.eagle.programmar.COBOL.COBOL_Paragraph.COBOL_SentenceOrComment;
	using COBOL_ProcedureDivision = com.eagle.programmar.COBOL.COBOL_ProcedureDivision;
	using COBOL_Program_Complete = com.eagle.programmar.COBOL.COBOL_Program_Complete;
	using COBOL_Section = com.eagle.programmar.COBOL.COBOL_Section;
	using COBOL_Sentence = com.eagle.programmar.COBOL.COBOL_Sentence;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_StatementOrComment = com.eagle.programmar.COBOL.COBOL_StatementOrComment;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_COBOL_Procedure<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		public virtual void transformProcedureDivision(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_Program_Complete program)
		{
			COBOL_ProcedureDivision proc = program.procedureDiv;
			foreach (COBOL_Section section in proc.sections._elements)
			{
				foreach (COBOL_Paragraph paragraph in section.paragraphs._elements)
				{
	//				Generate_Method method = gen._mainClassGen.addMethod(methodLine, paragraph);
	//				Generate_Block block = method._topBlock;
					string paraName = Transform_COBOL.fixName(paragraph.paragraphHeaders.first().paragraphName.ToString());
					Meth method = trans._target._createMethod.createMethod(PRIVACY.PUBLIC, METHOD_QUALIFIERS.NONE, TYPES.VOID, null, paraName, null, paragraph);
					trans._target._createClass.addMethod(trans._target._mainClass, method);

					// Now go through each statement in the paragraph ...
					foreach (COBOL_Paragraph.COBOL_SentenceOrComment sentenceOrComment in paragraph.sentences._elements)
					{
						AbstractToken whichSentComm = sentenceOrComment.getWhich();
						if (whichSentComm is COBOL_Comment)
						{
							COBOL_Comment comment = (COBOL_Comment) whichSentComm;
							trans._target._createMethod.addMethodComment(method, comment.getValue(), comment);
						}
						else if (whichSentComm is COBOL_Sentence)
						{
							COBOL_Sentence sentence = (COBOL_Sentence) whichSentComm;
							foreach (COBOL_StatementOrComment statementOrComment in sentence.statements._elements)
							{
								AbstractToken whichStmtComm = statementOrComment.getWhich();
								if (whichStmtComm is COBOL_Comment)
								{
									COBOL_Comment comment = (COBOL_Comment) whichStmtComm;
									trans._target._createMethod.addMethodComment(method, comment.getValue(), comment);
								}
								else if (whichStmtComm is COBOL_Statement)
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
}
