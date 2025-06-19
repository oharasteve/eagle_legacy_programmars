// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Functions.Python_Print_Function;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.AssignmentEnum;

public class Python_PrintStatement extends TokenSequence
		implements AbstractStatement
{
	public @S(10) @NOSPACE Python_Keyword PRINT = new Python_Keyword("print");
	public @S(20) @OPT Python_Punctuation greaterGreater = new Python_Punctuation(">>");
	public @S(30) @OPT SeparatedList<Python_Expression, PunctuationComma> exprs;
	public @S(40) @OPT @NOSPACE @CURIOUS("Extra comma") PunctuationComma comma;

	public Python_ComplexStatement generatePrint1(Python_Expression line,
			boolean newLine, AbstractToken source)
	{
		Python_Print_Function func = new Python_Print_Function();
		func.leftParen = new PunctuationLeftParen();
		func.exprs = new SeparatedList<Python_Expression, PunctuationComma>();
		func.exprs.addPrimaryElement(line);
		
		if (! newLine)
		{
			func.exprs.addSecondaryElement(new PunctuationComma());
			Python_Expression emptyExpr1 = Python_Literal.generateLiteralExpression("", null);
			Python_Variable end = Python_Variable.newVariable("end");
			Python_Assignment_Expression asg1 = new Python_Assignment_Expression();
			Python_Expression asgExpr1 = asg1.generateAssignment(end, null,
					AssignmentEnum.EQUALS, emptyExpr1, source);
			func.exprs.addPrimaryElement(asgExpr1);
		}
		
		func.rightParen = new PunctuationRightParen();
		func.setTransformationSource(source);
		
		Python_ExpressionStatement stmt = new Python_ExpressionStatement();
		stmt.expression = Python_Generator.wrapExpression(func);
		
		stmt.setTransformationSource(source);
		return Python_Generator.wrapStatement(stmt);
	}
}
