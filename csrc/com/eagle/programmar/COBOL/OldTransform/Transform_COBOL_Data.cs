// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 24, 2010

namespace com.eagle.programmar.COBOL.OldTransform
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using CLASS_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Class.CLASS_QUALIFIERS;
	using TYPES = com.eagle.oldGenerate.Old_Generate_Eagle_Expression.TYPES;
	using DATA_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
	using PRIVACY = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
	using COBOL_DataDeclaration = com.eagle.programmar.COBOL.COBOL_DataDeclaration;
	using COBOL_DataClause = com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_DataClause;
	using COBOL_DataDivision = com.eagle.programmar.COBOL.COBOL_DataDivision;
	using COBOL_Picture_Value_Keyword = com.eagle.programmar.COBOL.COBOL_Picture_Value.COBOL_Picture_Value_Keyword;
	using COBOL_Program_Complete = com.eagle.programmar.COBOL.COBOL_Program_Complete;
	using COBOL_Subscript = com.eagle.programmar.COBOL.COBOL_Subscript;
	using COBOL_RegularSubscript = com.eagle.programmar.COBOL.COBOL_Subscript.COBOL_RegularSubscript;
	using COBOL_WorkingStorage = com.eagle.programmar.COBOL.COBOL_WorkingStorage;
	using COBOL_CopyOrDataDeclaration = com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
	using COBOL_PictureClause = com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
	using COBOL_ValueClause = com.eagle.programmar.COBOL.Picture.COBOL_ValueClause;
	using COBOL_Data_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		private Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> _trans;

	//	private String _action;
	//	private String _prefix;

		public Transform_COBOL_Data(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans)
		{
			_trans = trans;
		}

		public virtual void transformDataDivision(COBOL_Program_Complete program)
		{
			COBOL_DataDivision div = program.dataDiv;
			if (div == null)
			{
				return;
			}
			Cls cls = _trans._target._mainClass;
			foreach (COBOL_DataDivision.COBOL_DataSection section in div.sections._elements)
			{
				AbstractToken whichSection = section.getWhich();
				if (whichSection is COBOL_WorkingStorage)
				{
					COBOL_WorkingStorage workingStorage = (COBOL_WorkingStorage) whichSection;

					if (workingStorage.dataDeclarations != null)
					{
						foreach (COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration copyDecl in workingStorage.dataDeclarations._elements)
						{
							AbstractToken whichCopy = copyDecl.getWhich();
							if (whichCopy is COBOL_DataDeclaration)
							{
								COBOL_DataDeclaration decl = (COBOL_DataDeclaration) whichCopy;
								AbstractToken which = decl.fieldName.getWhich();
								if (which is COBOL_Data_Definition)
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
			if (!(which is COBOL_Data_Definition))
			{
				return;
			}
			COBOL_Data_Definition def = (COBOL_Data_Definition) which;

			Cls dataClass = _trans._target._createClass.addInnerClass(PRIVACY.PUBLIC, parentClass, def.getValue(), CLASS_QUALIFIERS.NONE, decl);

			string pic = null;
			if (decl.clauses != null)
			{
				foreach (COBOL_DataDeclaration.COBOL_DataClause clause in decl.clauses._elements)
				{
					AbstractToken whichClause = clause.getWhich();
					if (whichClause is COBOL_PictureClause)
					{
						COBOL_PictureClause pictureClause = (COBOL_PictureClause) whichClause;
						pic = pictureClause.picture.ToString();
					}
				}
			}

			if (decl.level._level == 88)
			{
				processPic88(decl);
			}
			else if (!string.ReferenceEquals(pic, null))
			{
				addData(dataClass, decl);
			}
			else
			{
				// Non-terminal, has no PIC clause
				if (decl.children != null)
				{
					foreach (COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration subDecl in decl.children._elements)
					{
						AbstractToken whichSub = subDecl.getWhich();
						if (whichSub is COBOL_DataDeclaration)
						{
							COBOL_DataDeclaration data = (COBOL_DataDeclaration) whichSub;
							AbstractToken which2 = data.fieldName.getWhich();
							if (which2 is COBOL_Data_Definition)
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
			string pic = null;
			COBOL_ValueClause valueClause = null;
			if (decl.clauses != null)
			{
				foreach (COBOL_DataDeclaration.COBOL_DataClause clause in decl.clauses._elements)
				{
					AbstractToken whichClause = clause.getWhich();
					if (whichClause is COBOL_PictureClause)
					{
						COBOL_PictureClause pictureClause = (COBOL_PictureClause) whichClause;
						pic = pictureClause.picture.ToString();
					}
					if (whichClause is COBOL_ValueClause)
					{
						valueClause = (COBOL_ValueClause) whichClause;
					}
				}
			}
			if (string.ReferenceEquals(pic, null))
			{
				throw new Exception("PICTURE is required.");
			}

			if (pic.StartsWith("9", StringComparison.Ordinal))
			{
				Expr init = null;
				// Is there an initial value?
				if (valueClause != null && valueClause.values != null && valueClause.values.size() == 1)
				{
					AbstractToken val = valueClause.values.first().getWhich();
					if (val is COBOL_Picture_Value_Keyword)
					{
						COBOL_Picture_Value_Keyword keyWord = (COBOL_Picture_Value_Keyword) val;
						string kw = keyWord.constants.ToString();
						if (kw.Equals("ZERO") || kw.Equals("ZEROS"))
						{
							init = _trans._target._createExpression.createNumber(0);
						}
					}
				}

				string varName = Transform_COBOL.fixName(decl.fieldName.getWhich().ToString());
				Stmt dataStmt = _trans._target._createStatement.createData(PRIVACY.PUBLIC, DATA_QUALIFIERS.STATIC._value, 0, varName, TYPES.INT, null, init, null, decl);
				if (dataStmt != null)
				{
					_trans._target._createClass.addClassData(cls, dataStmt);
				}
				return;
			}

			throw new Exception("Don't how to handle " + pic + " yet");
		}

		private static void processPic88(COBOL_DataDeclaration decl)
		{
			throw new Exception("Need to implement: " + decl);
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

		public virtual string getFullVariableName(COBOL_Identifier_Reference id, COBOL_Subscript subscript)
		{
			COBOL_Data_Definition def = (COBOL_Data_Definition) id.searchForDefinition();
			if (def == null)
			{
				throw new Exception("No definition found for " + id.ToString());
			}
			COBOL_DataDeclaration decl = def.Declaration;
			bool is88 = (decl.level._level == 88);
			string varName = def.ToString();

			// If a container for 88's, then need to prefix it by the class name
			if (decl.children != null && decl.children.size() > 0)
			{
				COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration firstChild = decl.children.first();
				AbstractToken which = firstChild.getWhich();
				if (which is COBOL_DataDeclaration)
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
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: if (parent instanceof com.eagle.tokens.TokenList<?>)
				if (parent is TokenList<object>)
				{
					parent = parent.getParent();
					continue;
				}

				if (parent is COBOL_DataDeclaration)
				{
					decl = (COBOL_DataDeclaration) parent;
					string parentName = decl.fieldName.getWhich().ToString();
					if (is88)
					{
						parentName += "_Class";
					}
					varName = parentName + "." + varName;
				}
				parent = parent.getParent();
			}

			// 88 level's need parentheses on them
			if (is88)
			{
				varName += "()";
			}

			if (subscript != null)
			{
				AbstractToken which = subscript.type.getWhich();
				if (which is COBOL_Subscript.COBOL_RegularSubscript)
				{
					COBOL_Subscript.COBOL_RegularSubscript sub = (COBOL_Subscript.COBOL_RegularSubscript) which;
					varName += "[" + _trans.transformExpression(sub.expr) + "]";
				}
			}

			return varName;
		}

		public static int getMaximumValue(COBOL_Identifier_Reference id)
		{
			COBOL_Data_Definition def = (COBOL_Data_Definition) id.searchForDefinition();
			foreach (COBOL_DataDeclaration.COBOL_DataClause clause in def.Declaration.clauses._elements)
			{
				AbstractToken whichClause = clause.getWhich();
				if (whichClause is COBOL_PictureClause)
				{
					COBOL_PictureClause pictureClause = (COBOL_PictureClause) whichClause;
					string pic = pictureClause.picture.getValue();

					int nc;
					if (!string.ReferenceEquals(pic, null) && pic.StartsWith("9", StringComparison.Ordinal))
					{
						if (pic.Length > 1 && pic.Substring(1, 1).Equals("("))
						{
							nc = int.Parse(pic.Substring(2, (pic.Length - 1) - 2));
						}
						else
						{
							nc = pic.Length;
						}
					}
					else
					{
						throw new Exception("Can't handle picture yet: " + pic);
					}

					int power = 1;
					for (int i = 0; i < nc; i++)
					{
						power *= 10;
					}
					return power;
				}
			}
			return 0;
		}
	}

}
