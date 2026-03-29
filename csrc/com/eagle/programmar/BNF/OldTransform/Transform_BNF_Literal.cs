// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2016

namespace com.eagle.programmar.BNF.OldTransform
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using DATA_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
	using PRIVACY = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
	using BNF_Literal = com.eagle.programmar.BNF.Terminals.BNF_Literal;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_BNF_Literal<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		private int _puncts = 0;

		public virtual string transformLiteral(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq, BNF_Literal literal, bool optional, bool isChoice, bool inTokenSequence)
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

			string originalTxt = literal.getValue();
			string txt = originalTxt;
			if (originalTxt.StartsWith("'", StringComparison.Ordinal) && originalTxt.EndsWith("'", StringComparison.Ordinal))
			{
				txt = txt.Substring(1, (txt.Length - 1) - 1);
			}
			else if (originalTxt.StartsWith("\"", StringComparison.Ordinal) && originalTxt.EndsWith("\"", StringComparison.Ordinal))
			{
				txt = txt.Substring(1, (txt.Length - 1) - 1);
			}

			string typeName;
			string token;
			if (txt.matches("[A-Za-z]+"))
			{
				token = trans.foldUp(txt);
				typeName = "BNF_Keyword";
			}
			else
			{
				_puncts++;
				typeName = "BNF_Punctuation";
				token = "punct_" + _puncts;
			}

			Type type = trans._target._createClass.createType(typeName);
			List<AbstractExpression> txtExpr = new List<AbstractExpression>();
			txtExpr.Add(trans._target._createExpression.createLiteral('"' + txt + '"'));
			Expr init = trans._target._createExpression.createNew(type, txtExpr);

			Stmt dataStmt = trans._target._createStatement.createData(PRIVACY.PUBLIC, qual, seq, token, typeName, init, null, literal);
			trans._target._createClass.addClassData(cls, dataStmt);
			return token;
		}
	}

}
