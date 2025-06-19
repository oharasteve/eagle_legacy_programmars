// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 28, 2010

package com.eagle.programmar.COBOL.OldTransform.Statements;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.OldTransform.Transform_COBOL;
import com.eagle.programmar.COBOL.Statements.COBOL_AcceptStatement;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class COBOL_Transform_Accept<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	protected static class Info
	{
		public int _sc;
		public AbstractStatement _action;
	}

	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_AcceptStatement acceptStatement)
	{
		throw new RuntimeException("Need to implement");
//		Info info = new Info();
//		
//		COBOL_Identifier_Reference var = acceptStatement.var;
//		COBOL_Data_Definition def = (COBOL_Data_Definition) var.getDefinition();
//		if (def == null)
//		{
//			throw new RuntimeException("No definition found for " + var.toString());
//		}
//		COBOL_DataDeclaration decl = def.getDeclaration();
//		if (decl == null)
//		{
//			throw new RuntimeException("Definition not connected to its declaration: " + var.toString());
//		}
//		COBOL_Data_Definition ddef = (COBOL_Data_Definition) decl.fieldName.getWhich();
//		String tempString = "temp_" + ddef.toString();
//		
//		boolean gotTime = false;
//		if (acceptStatement.options != null)
//		{
//			for (COBOL_AcceptOption option : acceptStatement.options._elements)
//			{
//				AbstractToken which = option.getWhich();
//				if (which instanceof COBOL_AcceptFrom)
//				{
//					COBOL_AcceptFrom from = (COBOL_AcceptFrom) which;
//					if (from.time._present)
//					{
//						gotTime = true;
//						// Reading from DATE / DAY or TIME
//						String whichTime = from.time.toString();
//						String whichFormat = from.format.toString();
//						String fmt;
//						if (whichTime.equals("TIME"))
//						{
//							fmt = "_DateFormat._TIME";
//						}
//						else if (whichTime.equals("DATE") && whichFormat.equals("YYYYMMDD"))
//						{
//							fmt = "_DateFormat._DATE_YYYYMMDD";
//						}
//						else if (whichTime.equals("DAY") && whichFormat.equals("YYYYDDD"))
//						{
//							fmt = "_DateFormat._DATE_YYYYDDD";
//						}
//						else throw new EagleTransformException("Can't handle Accept time string yet: " + whichTime);
//			
//						gen._fns._needsGetDate = true;
//						info._action = "{ " + gen._kw.getString() + " " + tempString + " = _GetDate(" + fmt + ");";
//					}
//				}
//			}
//		}
//
//		if (!gotTime)
//		{
//			info._sc = 0;	// All this just to get the minimum size
//			matchPieces(info, decl, gen._kw, "", tempString);
//			
//			gen._fns._needsSystemInReadLine = true;
//			info._action = "{ " + gen._kw.getString() + " " + tempString + " = " + gen._kw.getReadLine() + "(" + info._sc + "); ";
//		}
//			
//		
//		// _tempString is always set here.
//		// Need to extract all the pieces, in order, to match up with the data definition
//		info._sc = 0;
//		matchPieces(info, decl, gen._kw, "", tempString);
//
//		AbstractToken blockStatement = block.makeStatementBlock();
//		gen.parseWithSource(info._action + "}", blockStatement, acceptStatement);
//		return blockStatement;
	}

//	// Careful, recursive
//	private static void matchPieces(Info info, COBOL_DataDeclaration decl, Generate_Keywords kw, String prefix, String tempString)
//	{
//		AbstractToken what = decl.fieldName.getWhich();
//		String fieldName = null;	// In case it is a FILLER
//		if (what instanceof COBOL_Data_Definition)
//		{
//			COBOL_Data_Definition def = (COBOL_Data_Definition) what;
//			fieldName = def.toString();
//		}
//
//		if (decl.clauses != null)
//		{
//			for (COBOL_DataClause clause : decl.clauses._elements)
//			{
//				AbstractToken whichClause = clause.getWhich();
//				if (whichClause instanceof COBOL_PictureClause)
//				{
//					COBOL_PictureClause pictureClause = (COBOL_PictureClause) whichClause;
//					if (decl.children.size() == 0)
//					{
//						doPicture(info, pictureClause, kw, prefix, fieldName, tempString);
//					}
//					else if (pictureClause._present)
//					{
//						// Probably a container for 88's
//						doPicture(info, pictureClause, kw, fieldName + "_Class.", fieldName, tempString);
//					}
//					else
//					{
//						// Regular old data 01, 05, 10, etc
//						for (COBOL_CopyOrDataDeclaration child : decl.children._elements)
//						{
//							AbstractToken which = child.getWhich();
//							if (which instanceof COBOL_DataDeclaration)
//							{
//								COBOL_DataDeclaration subDecl = (COBOL_DataDeclaration) which;
//								matchPieces(info, subDecl, kw, prefix + fieldName + ".", tempString);	// Recursive call
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//	
//	private static void doPicture(Info info, COBOL_PictureClause pictureClause, Generate_Keywords kw,
//			String prefix, String fieldName, String tempString)
//	{
//		// Terminal field
//		String pic = pictureClause.picture.toString();
//		if (pic == null) return;		// Probably an 88
//		
//		boolean isNumeric;
//		if (pic.startsWith("9"))
//		{
//			isNumeric = true;
//		}
//		else if (pic.startsWith("X"))
//		{
//			isNumeric = false;
//		}
//		else throw new EagleTransformException("Can't handle picture yet: " + pic);
//		
//		int nc;
//		if (pic.length() > 1 && pic.substring(1, 2).equals("("))
//		{
//			nc = Integer.parseInt(pic.substring(2, pic.length()-1));
//		}
//		else
//		{
//			nc = pic.length();
//		}
//		
//		if (fieldName != null)	// null means FILLER
//		{
//			int ecNc = (kw.subStringUsesNCinsteadOfEC() ? nc : (info._sc+nc));	// java uses EC, C# uses NC
//			if (isNumeric)
//			{
//				info._action += prefix + fieldName + " = " + kw.getIntParse() + "(" + tempString + "." + kw.getSubString() +
//				"(" + info._sc + "," + ecNc + ")." + kw.getTrim() + "()); ";
//			}
//			else
//			{
//				info._action += prefix + fieldName + " = " + tempString + "." + kw.getSubString() + "(" + info._sc + "," + ecNc + "); ";
//			}
//		}
//		info._sc += nc;	// Get 'er ready for the next field
//	}
}
