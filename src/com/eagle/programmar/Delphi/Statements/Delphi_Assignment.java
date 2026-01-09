// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Function;
import com.eagle.programmar.Delphi.Delphi_Variable;
import com.eagle.programmar.Delphi.Delphi_Variable.Delphi_Extended_Variable;
import com.eagle.programmar.Delphi.Delphi_Variable.Delphi_Subscript;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class Delphi_Assignment extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Delphi_Variable var;
	public @S(20) Delphi_Punctuation colonEquals = new Delphi_Punctuation(":=");
	public @S(30) Delphi_Expression expr;
	public @S(40) @OPT Delphi_Comment comment;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var, var.var.getValue(), val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression newExpr = transformer.transformExpression(generator, expr);
		String varName = var.var.getValue();

		// Delphi doesn't have a Return statement.
		// It assigns a value to the function name
		AbstractToken parent = var.var;
		while (parent != null)
		{
			if (parent instanceof Delphi_Function)
			{
				Delphi_Function fn = (Delphi_Function) parent;
				if (fn.forward.id.getValue().equals(varName))
				{
					// Is a function return: function cube(a) begin cube := a*a*a end
					return generator.newReturnStatement(newExpr, this);
				}
				break;
			}
			parent = parent.getParent();
		}

		AbstractExpression subscrExpr = null;
		if (this.var.extensions != null)
		{
			for (Delphi_Extended_Variable extension : this.var.extensions._elements)
			{
				AbstractToken which = extension.getWhich();
				if (which instanceof Delphi_Subscript)
				{
					Delphi_Subscript delphiSubscr = (Delphi_Subscript) which;
					if (delphiSubscr.exprs.size() > 1)
					{
						throw new RuntimeException("Cannot handle multiple subscripts yet: " + delphiSubscr.exprs);
					}
					Delphi_Expression delphiExpr = delphiSubscr.exprs.first();
					subscrExpr = transformer.transformExpression(generator, delphiExpr);
				}
				else
					throw new RuntimeException("Can only handle Delphi_Subscript now, not " + which);
			}
		}

		AbstractExpression asgStmt = generator.newAssignmentExpression(varName,
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.EQUALS, newExpr, null);
		return generator.newExpressionStatement(asgStmt, this);
	}
}
