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
	using CLASS_QUALIFIERS = com.eagle.oldGenerate.Old_Generate_Eagle_Class.CLASS_QUALIFIERS;
	using PRIVACY = com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
	using BNF_Expression = com.eagle.programmar.BNF.BNF_Expression;
	using BNF_ExpressionTerm = com.eagle.programmar.BNF.BNF_Expression.BNF_ExpressionTerm;
	using BNF_Alternation = com.eagle.programmar.BNF.Expressions.BNF_Alternation;
	using BNF_Rulename = com.eagle.programmar.BNF.Expressions.BNF_Rulename;
	using BNF_Literal = com.eagle.programmar.BNF.Terminals.BNF_Literal;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_BNF_Alternation<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		private int _sequences = 0;

		public virtual void transformAlternation(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq, string className, BNF_Expression expr)
		{
			Cls innerClass = trans._target._createClass.addInnerClass(PRIVACY.PUBLIC, cls, className, CLASS_QUALIFIERS.NONE, expr);
			trans._target._createClass.setClassExtends(innerClass, typeof(TokenChooser).Name);

			writeChoice(trans, innerClass, seq, expr.terms);
			if (expr.choices != null)
			{
				foreach (BNF_Alternation alternate in expr.choices._elements)
				{
					writeChoice(trans, innerClass, seq, alternate.terms);
				}
			}
		}

		private void writeChoice(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq, TokenList<BNF_Expression.BNF_ExpressionTerm> terms)
		{
			if (terms.size() == 1)
			{
				AbstractToken first = terms.first().getWhich();
				if (first is BNF_Literal)
				{
					trans._transformLiteral.transformLiteral(trans, cls, seq, (BNF_Literal) first, false, true, false);
					return;
				}
				if (first is BNF_Rulename)
				{
					trans._transformName.transformName(trans, cls, seq, (BNF_Rulename) first, false, true, false);
					return;
				}
			}

			_sequences++;
			string className = Transform_BNF.fixClassName("Sequence_" + _sequences);
			Cls innerClass = trans._target._createClass.addInnerClass(PRIVACY.PUBLIC, cls, className, CLASS_QUALIFIERS.CHOICE, terms);
			trans._target._createClass.setClassExtends(innerClass, typeof(TokenSequence).Name);
			int count = 0;
			foreach (BNF_Expression.BNF_ExpressionTerm term in terms._elements)
			{
				count += 10;
				trans.writeTerm(innerClass, true, count, term);
			}
		}
	}

}
