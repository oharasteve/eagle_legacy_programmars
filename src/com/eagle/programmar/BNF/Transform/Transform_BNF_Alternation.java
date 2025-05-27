// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2016

package com.eagle.programmar.BNF.Transform;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.Old_Generate_Eagle_Class.CLASS_QUALIFIERS;
import com.eagle.generate.Old_Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.BNF.BNF_Expression;
import com.eagle.programmar.BNF.BNF_Expression.BNF_ExpressionTerm;
import com.eagle.programmar.BNF.Expressions.BNF_Alternation;
import com.eagle.programmar.BNF.Expressions.BNF_Rulename;
import com.eagle.programmar.BNF.Terminals.BNF_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_BNF_Alternation<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	private int _sequences = 0;

	public void transformAlternation(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq,
			String className, BNF_Expression expr)
	{
		Cls innerClass = trans._target._createClass.addInnerClass(PRIVACY.PUBLIC, cls, className, CLASS_QUALIFIERS.NONE,
				expr);
		trans._target._createClass.setClassExtends(innerClass, TokenChooser.class.getSimpleName());

		writeChoice(trans, innerClass, seq, expr.terms);
		if (expr.choices != null)
		{
			for (BNF_Alternation alternate : expr.choices._elements)
			{
				writeChoice(trans, innerClass, seq, alternate.terms);
			}
		}
	}

	private void writeChoice(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq,
			TokenList<BNF_ExpressionTerm> terms)
	{
		if (terms.size() == 1)
		{
			AbstractToken first = terms.first().getWhich();
			if (first instanceof BNF_Literal)
			{
				trans._transformLiteral.transformLiteral(trans, cls, seq, (BNF_Literal) first, false, true, false);
				return;
			}
			if (first instanceof BNF_Rulename)
			{
				trans._transformName.transformName(trans, cls, seq, (BNF_Rulename) first, false, true, false);
				return;
			}
		}

		_sequences++;
		String className = Transform_BNF.fixClassName("Sequence_" + _sequences);
		Cls innerClass = trans._target._createClass.addInnerClass(PRIVACY.PUBLIC, cls, className,
				CLASS_QUALIFIERS.CHOICE, terms);
		trans._target._createClass.setClassExtends(innerClass, TokenSequence.class.getSimpleName());
		int count = 0;
		for (BNF_ExpressionTerm term : terms._elements)
		{
			count += 10;
			trans.writeTerm(innerClass, true, count, term);
		}
	}
}
