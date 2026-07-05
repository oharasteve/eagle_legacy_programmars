// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_MoveStatement extends COBOL_AbstractStatement
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("rlpsmove.htm") COBOL_Keyword MOVE = new COBOL_Keyword("MOVE");
	public @S(20) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	public @S(30) COBOL_Expression expr;
	public @S(40) COBOL_Keyword TO = new COBOL_Keyword("TO");
	public @S(50) @OPT COBOL_Variable var;
	public @S(60) @OPT TokenList<COBOL_MoveMore> more;
	public @S(70) @OPT @CURIOUS("MOVE: Extra comma") PunctuationComma comma;

	public static class COBOL_MoveMore extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) @OPT TokenList<COBOL_Comment> comments;
		public @S(30) COBOL_Variable var;
		public @S(40) @OPT COBOL_Subscript subscript;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (ALL.isPresent()) throw new RuntimeException("Can't handle MOVE ALL yet");
		if (more != null && more.isPresent() && more.size() > 0)
		{
			throw new RuntimeException("Can't handle multiple MOVEs yet");
		}

		EagleValue val = interpreter.getEagleValue(expr);
		AbstractToken which = var.getWhich();
		if (!(which instanceof COBOL_UserVariable))
		{
			throw new RuntimeException("Unable to handle " + which);
		}
		COBOL_UserVariable variable = (COBOL_UserVariable) which;
		interpreter.setSymbol(variable, variable.id.getValue(), val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (ALL != null && ALL.isPresent())
		{
			throw new RuntimeException("Can't handle MOVE ALL now: " + this);
		}
		if (more != null && more.size() > 0)
		{
			throw new RuntimeException("Can't handle MOVE TO many variables: " + this);
		}
		if (!(var.getWhich() instanceof COBOL_UserVariable))
		{
			throw new RuntimeException("Can only ADD to a Variable: " + this);
		}
		COBOL_UserVariable userVar = (COBOL_UserVariable) var.getWhich();
		if (userVar.subscript != null && userVar.subscript.size() > 0)
		{
			throw new RuntimeException("Can't handle subscripts here: " + this);
		}
		if (userVar.ofList != null && userVar.ofList.size() > 0)
		{
			throw new RuntimeException("Can't handle field OF variable: " + this);
		}

		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(
				COBOL_Variable.repairName(userVar.id.getValue()),
				SubscriptEnum.FIRST_IS_ONE, null, AssignmentEnum.EQUALS, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}
