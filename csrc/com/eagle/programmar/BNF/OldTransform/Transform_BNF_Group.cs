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
	using BNF_Group = com.eagle.programmar.BNF.Expressions.BNF_Group;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_BNF_Group<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		private int _groups = 0;

		public virtual void transformGroup(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq, BNF_Group group, bool inTokenSequence)
		{
			_groups++;
			int qual = 0;
			if (inTokenSequence)
			{
				qual |= DATA_QUALIFIERS.SEQUENCE._value;
			}

			// Three cases:
			// I - (group) becomes just GroupClass
			// II - (group)+ becomes TokenList<GroupClass>
			// III - (group)* becomes @OPT TokenList<GroupClass>

			string className = Transform_BNF.fixClassName("Group_" + _groups);
			string varName = "group_" + _groups;

			trans.writeExpression(cls, className, group.expression);

			string typeName = className;
			if (group.starOrPlus.isPresent())
			{
				string starOrPlus = group.starOrPlus.getValue();
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
				typeName = "TokenList<" + className + ">";
			}

			Stmt dataStmt = trans._target._createStatement.createData(PRIVACY.PUBLIC, qual, seq, varName, typeName, null, null, group);
			trans._target._createClass.addClassData(cls, dataStmt);
		}
	}

}
