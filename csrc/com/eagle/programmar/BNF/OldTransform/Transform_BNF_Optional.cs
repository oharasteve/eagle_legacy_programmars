// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2016

namespace com.eagle.programmar.BNF.OldTransform
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using DATA_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
	using PRIVACY = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
	using BNF_Expression = com.eagle.programmar.BNF.BNF_Expression;
	using BNF_Optional = com.eagle.programmar.BNF.Expressions.BNF_Optional;
	using BNF_Rulename = com.eagle.programmar.BNF.Expressions.BNF_Rulename;
	using BNF_Literal = com.eagle.programmar.BNF.Terminals.BNF_Literal;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_BNF_Optional<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		private int _optionals = 0;

		public virtual void transformOptional(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq, BNF_Optional optional, bool isChoice, bool inTokenSequence)
		{
			BNF_Expression expr = optional.expression;
			if (expr.terms.size() == 1 && expr.choices == null)
			{
				AbstractToken first = expr.terms.first().getWhich();
				if (first is BNF_Literal)
				{
					trans._transformLiteral.transformLiteral(trans, cls, seq, (BNF_Literal) first, true, isChoice, inTokenSequence);
					return;
				}
				if (first is BNF_Rulename)
				{
					trans._transformName.transformName(trans, cls, seq, (BNF_Rulename) first, true, isChoice, inTokenSequence);
					return;
				}
			}

			int qual = DATA_QUALIFIERS.OPTIONAL._value;
			if (isChoice)
			{
				qual |= DATA_QUALIFIERS.CHOICE._value;
			}
			if (inTokenSequence)
			{
				qual |= DATA_QUALIFIERS.SEQUENCE._value;
			}

			_optionals++;
			string className = Transform_BNF.fixClassName("Optional_" + _optionals);
			string varName = "optional_" + _optionals;
			trans.writeExpression(cls, className, expr);

			Stmt dataStmt = trans._target._createStatement.createData(PRIVACY.PUBLIC, qual, seq, varName, className, null, null, optional);
			trans._target._createClass.addClassData(cls, dataStmt);
		}
	}

}
