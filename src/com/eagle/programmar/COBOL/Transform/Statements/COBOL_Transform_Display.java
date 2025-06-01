// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

package com.eagle.programmar.COBOL.Transform.Statements;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayOptions;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayOptions.COBOL_DisplayWithNoAdvancing;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement.COBOL_DisplayClause;
import com.eagle.programmar.COBOL.Transform.Transform_COBOL;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class COBOL_Transform_Display<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_DisplayStatement displayStatement)
	{
		ArrayList<AbstractExpression> whatToPrint = new ArrayList<AbstractExpression>();

		boolean advancing = true;
		for (COBOL_DisplayClause clause : displayStatement.clauses._elements)
		{
			for (AbstractToken token : clause.what.exprs._elements)
			{
				if (token instanceof COBOL_Expression)
				{
					COBOL_Expression expr = (COBOL_Expression) token;
					Expr piece = trans._transCobolExpr.transformExpression(expr);
					whatToPrint.add(piece);
				}
			}

			if (clause.options != null)
			{
				for (COBOL_DisplayOptions option : clause.options._elements)
				{
					if (option.getWhich() instanceof COBOL_DisplayWithNoAdvancing)
					{
						advancing = false;
					}
				}
			}
//				if (what instanceof COBOL_Literal)
//				{
//					COBOL_Literal lit = (COBOL_Literal) what;
//					if (msg.length() > 0) msg += " + ";
//					msg += lit.getValue();
//				}
//				else if (what instanceof COBOL_DisplayIdentifier)
//				{
//					COBOL_DisplayIdentifier var = (COBOL_DisplayIdentifier) what;
//					String varName = COBOL_Transform_Data.getFullVariableName(var.id, var.subscript, (COBOL_Transform_Expression) gen.transExpr);
//					COBOL_Data_Definition def = (COBOL_Data_Definition) var.id.getDefinition();
//	
//					// Terminal field
//					COBOL_DataDeclaration decl = (COBOL_DataDeclaration) def.parentDef;
//					String pic = decl.pictureClause.picture.toString();
//					boolean isNumeric;
//					if (pic.startsWith("9"))
//					{
//						isNumeric = true;
//					}
//					else if (pic.startsWith("X"))
//					{
//						isNumeric = false;
//					}
//					else throw new EagleTransformException("Can't handle picture yet: " + pic);
//					
//					int nc;
//					if (pic.length() > 1 && pic.substring(1, 2).equals("("))
//					{
//						nc = Integer.parseInt(pic.substring(2, pic.length()-1));
//					}
//					else
//					{
//						nc = pic.length();
//					}
//					
//					if (msg.length() > 0) msg += " + ";
//					msg += gen.fns.getStringFormat() + "(\"";
//					if (isNumeric)
//					{
//						if (gen.fns.stringFormatUsesPercent())
//						{
//							msg += "%0" + nc + "d";
//						}
//						else
//						{
//							msg += "{0:";
//							for (int i = 0; i < nc; i++) msg += '0';
//							msg += "}";
//						}
//					}
//					else
//					{
//						if (gen.fns.stringFormatUsesPercent())
//						{
//							msg += "%." + nc + "s";
//						}
//						else
//						{
//							msg += "{0,-" + nc + "}";
//						}
//					}
//					msg += "\", " + varName + ")";
//				}
//				else if (what instanceof COBOL_KeywordChoice)
//				{
//					if (msg.length() > 0) msg += " + ";
//					msg += "\" \"";
//				}
//			}
		}

		Stmt printStatement = trans._target._createStatement.createPrintStatement(
				whatToPrint, advancing, displayStatement);
		return printStatement;
	}
}
