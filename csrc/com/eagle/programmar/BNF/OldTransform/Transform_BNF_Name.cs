// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2016

namespace com.eagle.programmar.BNF.OldTransform
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using DATA_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
	using PRIVACY = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
	using BNF_Rulename = com.eagle.programmar.BNF.Expressions.BNF_Rulename;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_BNF_Name<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		public virtual string transformName(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq, BNF_Rulename name, bool optional, bool isChoice, bool inTokenSequence)
		{
			int qual = 0;
			if (optional)
			{
				qual |= DATA_QUALIFIERS.OPTIONAL._value;
			}
			if (isChoice)
			{
				qual |= DATA_QUALIFIERS.CHOICE._value;
			}
			if (inTokenSequence)
			{
				qual |= DATA_QUALIFIERS.SEQUENCE._value;
			}

			// Three cases:
			// I - token becomes just token
			// II - token+ becomes TokenList<token>
			// III - token* becomes @OPT TokenList<token>

			string id = name.@ref.getValue();
			string varName = Transform_BNF.fixVarName(id);

			string className;
			int len = id.Length;
			char last = id[len - 1];
			if (char.IsDigit(last))
			{
				className = Transform_BNF.fixClassName(id.Substring(0, len - 1)); // use 'form' instead of 'form1' for class
																					// name
			}
			else
			{
				className = Transform_BNF.fixClassName(id);
			}

			string typeName = className;
			if (name.starOrPlus.isPresent())
			{
				typeName = "TokenList<" + className + ">";
				string starOrPlus = name.starOrPlus.getValue();
				switch (starOrPlus)
				{
				case "*":
					qual |= DATA_QUALIFIERS.OPTIONAL._value;
					break;
				case "+":
					break;
				default:
					throw new Exception("Expected '*' or '+', not " + starOrPlus);
				}
			}

			Stmt dataStmt = trans._target._createStatement.createData(PRIVACY.PUBLIC, qual, seq, varName, typeName, null, null, name);
			trans._target._createClass.addClassData(cls, dataStmt);
			return className;
		}
	}

}
