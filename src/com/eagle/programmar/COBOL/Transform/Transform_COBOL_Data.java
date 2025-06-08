// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 24, 2010

package com.eagle.programmar.COBOL.Transform;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.Old_Generate_Eagle_Class.CLASS_QUALIFIERS;
import com.eagle.generate.Old_Generate_Eagle_Expression.TYPES;
import com.eagle.generate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
import com.eagle.generate.Old_Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_DataClause;
import com.eagle.programmar.COBOL.COBOL_DataDivision;
import com.eagle.programmar.COBOL.COBOL_Picture_Value.COBOL_Picture_Value_Keyword;
import com.eagle.programmar.COBOL.COBOL_Program_Complete;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Subscript.COBOL_RegularSubscript;
import com.eagle.programmar.COBOL.COBOL_WorkingStorage;
import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
import com.eagle.programmar.COBOL.Picture.COBOL_ValueClause;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_COBOL_Data<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	private Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> _trans;

//	private String _action;
//	private String _prefix;

	public Transform_COBOL_Data(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans)
	{
		_trans = trans;
	}

	public void transformDataDivision(COBOL_Program_Complete program)
	{
		COBOL_DataDivision div = program.dataDiv;
		if (div == null) return;
		Cls cls = _trans._target._mainClass;
		for (COBOL_DataDivision.COBOL_DataSection section : div.sections._elements)
		{
			AbstractToken whichSection = section.getWhich();
			if (whichSection instanceof COBOL_WorkingStorage)
			{
				COBOL_WorkingStorage workingStorage = (COBOL_WorkingStorage) whichSection;

				if (workingStorage.dataDeclarations != null)
				{
					for (COBOL_CopyOrDataDeclaration copyDecl : workingStorage.dataDeclarations._elements)
					{
						AbstractToken whichCopy = copyDecl.getWhich();
						if (whichCopy instanceof COBOL_DataDeclaration)
						{
							COBOL_DataDeclaration decl = (COBOL_DataDeclaration) whichCopy;
							AbstractToken which = decl.fieldName.getWhich();
							if (which instanceof COBOL_Data_Definition)
							{
								if (decl.children == null || decl.children.size() == 0)
								{
									// Just an 01 variable, don't need an inner class for it
									addData(cls, decl);
								}
								else
								{
									buildClass(cls, decl);
								}
							}
						}
					}
				}
			}
		}
	}

	// Recursive, careful!
	private void buildClass(Cls parentClass, COBOL_DataDeclaration decl)
	{
		AbstractToken which = decl.fieldName.getWhich();
		if (!(which instanceof COBOL_Data_Definition)) return;
		COBOL_Data_Definition def = (COBOL_Data_Definition) which;

		Cls dataClass = _trans._target._createClass.addInnerClass(PRIVACY.PUBLIC, parentClass, def.getValue(),
				CLASS_QUALIFIERS.NONE, decl);

		String pic = null;
		if (decl.clauses != null)
		{
			for (COBOL_DataClause clause : decl.clauses._elements)
			{
				AbstractToken whichClause = clause.getWhich();
				if (whichClause instanceof COBOL_PictureClause)
				{
					COBOL_PictureClause pictureClause = (COBOL_PictureClause) whichClause;
					pic = pictureClause.picture.toString();
				}
			}
		}

		if (decl.level._level == 88)
		{
			processPic88(decl);
		}
		else if (pic != null)
		{
			addData(dataClass, decl);
		}
		else
		{
			// Non-terminal, has no PIC clause
			if (decl.children != null)
			{
				for (COBOL_CopyOrDataDeclaration subDecl : decl.children._elements)
				{
					AbstractToken whichSub = subDecl.getWhich();
					if (whichSub instanceof COBOL_DataDeclaration)
					{
						COBOL_DataDeclaration data = (COBOL_DataDeclaration) whichSub;
						AbstractToken which2 = data.fieldName.getWhich();
						if (which2 instanceof COBOL_Data_Definition)
						{
							if (data.children == null || data.children.size() == 0)
							{
								// Just a variable, don't need an inner class for it
								addData(dataClass, data);
							}
							else
							{
								buildClass(dataClass, data);
							}
						}
					}
				}
			}
		}

		// Python is not happy with an empty class, needs a "pass"
		_trans._target._createClass.finalize(dataClass);
	}

//	private void processPic(COBOL_DataDeclaration decl, String pic)
//	{
//		boolean topLevel = false;
//		if (_action.length() == 0)
//		{
//			// Dang, probably a container for 88's
//			topLevel = true;
//			_action += "private static class " + fieldName + "_Class { ";
//		}
//		
//		// Terminal field
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
//		if (fieldName != null)	// null means FILLER
//		{
//			if (isNumeric)
//			{
//				_action += "public static int " + fieldName + " = 0; ";
//			}
//			else
//			{
//				_action += "public static " + kw.getString() + " " + fieldName + " = \"\"; ";
//			}
//		}
//
//		// In case there is a picture AND some 88's underneath it
//		if (decl.children != null)
//		{
//			for (COBOL_CopyOrDataDeclaration subDecl : decl.children._elements)
//			{
//				AbstractToken whichSub = subDecl.getWhich();
//				if (whichSub instanceof COBOL_DataDeclaration)
//				{
//					COBOL_DataDeclaration data = (COBOL_DataDeclaration) whichSub;
//					buildClass(cls, data);
//				}
//			}
//		}
//		
//		if (topLevel) _action += " }";
//	}

	private void addData(Cls cls, COBOL_DataDeclaration decl)
	{
		String pic = null;
		COBOL_ValueClause valueClause = null;
		if (decl.clauses != null)
		{
			for (COBOL_DataClause clause : decl.clauses._elements)
			{
				AbstractToken whichClause = clause.getWhich();
				if (whichClause instanceof COBOL_PictureClause)
				{
					COBOL_PictureClause pictureClause = (COBOL_PictureClause) whichClause;
					pic = pictureClause.picture.toString();
				}
				if (whichClause instanceof COBOL_ValueClause)
				{
					valueClause = (COBOL_ValueClause) whichClause;
				}
			}
		}
		if (pic == null)
		{
			throw new RuntimeException("PICTURE is required.");
		}

		if (pic.startsWith("9"))
		{
			Expr init = null;
			// Is there an initial value?
			if (valueClause != null && valueClause.values != null && valueClause.values.size() == 1)
			{
				AbstractToken val = valueClause.values.first().getWhich();
				if (val instanceof COBOL_Picture_Value_Keyword)
				{
					COBOL_Picture_Value_Keyword keyWord = (COBOL_Picture_Value_Keyword) val;
					String kw = keyWord.constants.toString();
					if (kw.equals("ZERO") || kw.equals("ZEROS"))
					{
						init = _trans._target._createExpression.createNumber(0);
					}
				}
			}

			String varName = Transform_COBOL.fixName(decl.fieldName.getWhich().toString());
			Stmt dataStmt = _trans._target._createStatement.createData(PRIVACY.PUBLIC, DATA_QUALIFIERS.STATIC._value, 0,
					varName, TYPES.INT, null, init, null, decl);
			if (dataStmt != null)
			{
				_trans._target._createClass.addClassData(cls, dataStmt);
			}
			return;
		}

		throw new RuntimeException("Don't how to handle " + pic + " yet");
	}

	private static void processPic88(COBOL_DataDeclaration decl)
	{
		throw new RuntimeException("Need to implement: " + decl);
//		COBOL_CopyOrDataDeclaration copyOrData = (COBOL_CopyOrDataDeclaration) decl.getParent();
//		COBOL_DataDeclaration parent = (COBOL_DataDeclaration) copyOrData.getWhich();
//		COBOL_Data_Definition ddef = (COBOL_Data_Definition) parent.fieldName.getWhich();
//		String parentVar = ddef.toString();
//		
//		// 88 levels are funny beasts
//		_action += "public static " + kw.getBoolean() + " " + fieldName + "() { if (";
//		boolean first = true;
//		for (COBOL_Picture_Value val : valueClause.values._elements)
//		{
//			which = val.getWhich();
//			if (which instanceof COBOL_Picture_Value_Literal)
//			{
//				COBOL_Picture_Value_Literal valueLiteral = (COBOL_Picture_Value_Literal) which;
//				if (!first) _action += " || ";
//				first = false;
//				
//				// Has a THRU clause?
//				if (valueLiteral.thru != null && valueLiteral.thru._present)
//				{
//					_action += "(" + parentVar + "." + kw.getCompareTo() + "(" + valueLiteral.literal.getValue() + ") >= 0 &&";
//					_action += parentVar + "." + kw.getCompareTo() + "(" + valueLiteral.thru.literal.getValue() + ") <= 0 ) ";
//				}
//				else
//				{
//					_action += parentVar + "." + kw.getEquals() + "(" + valueLiteral.literal.getValue() + ") ";
//				}
//			}
//			else if (which instanceof COBOL_Picture_Value_Number)
//			{
//				COBOL_Picture_Value_Number valueNumber = (COBOL_Picture_Value_Number) which;
//				if (!first) _action += " || ";
//				first = false;
//				
//				_action += parentVar + " == " + "" + valueNumber.number.getValue() + " ";
//			}
//			else if (which instanceof COBOL_Picture_Value_Keyword)
//			{
//				COBOL_Picture_Value_Keyword valueKeyword = (COBOL_Picture_Value_Keyword) which;
//				if (!first) _action += " || ";
//				first = false;
//				
//				String key = valueKeyword.constants.toString();
//				if (key.equals("ZEROS"))
//				{
//					_action += parentVar + " == 0 ";
//				}
//				else throw new EagleTransformException("Can't handle 88 level keyword yet: " + key);
//			}
//			else throw new EagleTransformException("Can't handle 88 level value: " + which);
//		}
//		_action += ") { return true; } return false; } ";
	}

	public String getFullVariableName(COBOL_Identifier_Reference id, COBOL_Subscript subscript)
	{
		COBOL_Data_Definition def = (COBOL_Data_Definition) id.searchForDefinition();
		if (def == null)
		{
			throw new RuntimeException("No definition found for " + id.toString());
		}
		COBOL_DataDeclaration decl = def.getDeclaration();
		boolean is88 = (decl.level._level == 88);
		String varName = def.toString();

		// If a container for 88's, then need to prefix it by the class name
		if (decl.children != null && decl.children.size() > 0)
		{
			COBOL_CopyOrDataDeclaration firstChild = decl.children.first();
			AbstractToken which = firstChild.getWhich();
			if (which instanceof COBOL_DataDeclaration)
			{
				COBOL_DataDeclaration data = (COBOL_DataDeclaration) which;
				if (data.level._level == 88)
				{
					varName = varName + "_Class." + varName;
				}
			}
		}

		AbstractToken parent = decl.getParent();
		while (parent != null)
		{
			if (parent instanceof TokenList<?>)
			{
				parent = parent.getParent();
				continue;
			}

			if (parent instanceof COBOL_DataDeclaration)
			{
				decl = (COBOL_DataDeclaration) parent;
				String parentName = decl.fieldName.getWhich().toString();
				if (is88) parentName += "_Class";
				varName = parentName + "." + varName;
			}
			parent = parent.getParent();
		}

		// 88 level's need parentheses on them
		if (is88) varName += "()";

		if (subscript != null)
		{
			AbstractToken which = subscript.type.getWhich();
			if (which instanceof COBOL_RegularSubscript)
			{
				COBOL_RegularSubscript sub = (COBOL_RegularSubscript) which;
				varName += "[" + _trans.transformExpression(sub.expr) + "]";
			}
		}

		return varName;
	}

	public static int getMaximumValue(COBOL_Identifier_Reference id)
	{
		COBOL_Data_Definition def = (COBOL_Data_Definition) id.searchForDefinition();
		for (COBOL_DataClause clause : def.getDeclaration().clauses._elements)
		{
			AbstractToken whichClause = clause.getWhich();
			if (whichClause instanceof COBOL_PictureClause)
			{
				COBOL_PictureClause pictureClause = (COBOL_PictureClause) whichClause;
				String pic = pictureClause.picture.getValue();

				int nc;
				if (pic != null && pic.startsWith("9"))
				{
					if (pic.length() > 1 && pic.substring(1, 2).equals("("))
					{
						nc = Integer.parseInt(pic.substring(2, pic.length() - 1));
					}
					else
					{
						nc = pic.length();
					}
				}
				else
					throw new RuntimeException("Can't handle picture yet: " + pic);

				int power = 1;
				for (int i = 0; i < nc; i++) power *= 10;
				return power;
			}
		}
		return 0;
	}
}
