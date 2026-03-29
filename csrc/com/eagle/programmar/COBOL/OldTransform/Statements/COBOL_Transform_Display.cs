// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

namespace com.eagle.programmar.COBOL.OldTransform.Statements
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using com.eagle.programmar.COBOL.OldTransform;
	using COBOL_DisplayOptions = com.eagle.programmar.COBOL.Statements.COBOL_DisplayOptions;
	using COBOL_DisplayWithNoAdvancing = com.eagle.programmar.COBOL.Statements.COBOL_DisplayOptions.COBOL_DisplayWithNoAdvancing;
	using COBOL_DisplayStatement = com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement;
	using COBOL_DisplayClause = com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement.COBOL_DisplayClause;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class COBOL_Transform_Display<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		public virtual Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_DisplayStatement displayStatement)
		{
			List<AbstractExpression> whatToPrint = new List<AbstractExpression>();

			bool advancing = true;
			foreach (COBOL_DisplayStatement.COBOL_DisplayClause clause in displayStatement.clauses._elements)
			{
				foreach (AbstractToken token in clause.what.exprs._elements)
				{
					if (token is COBOL_Expression)
					{
						COBOL_Expression expr = (COBOL_Expression) token;
						Expr piece = trans._transCobolExpr.transformExpression(expr);
						whatToPrint.Add(piece);
					}
				}

				if (clause.options != null)
				{
					foreach (COBOL_DisplayOptions option in clause.options._elements)
					{
						if (option.getWhich() is COBOL_DisplayOptions.COBOL_DisplayWithNoAdvancing)
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

			Stmt printStatement = trans._target._createStatement.createPrintStatement(whatToPrint, advancing, displayStatement);
			return printStatement;
		}
	}

}
