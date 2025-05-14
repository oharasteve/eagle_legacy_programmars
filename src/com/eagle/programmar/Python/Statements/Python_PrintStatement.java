// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.Statements.Eagle_Generate_Print;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Functions.Python_Print_Function;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_PrintStatement extends TokenSequence
		implements AbstractStatement,
				Eagle_Generate_Print<Python_ComplexStatement, Python_Expression>
{
	public @S(10) @NOSPACE Python_Keyword PRINT = new Python_Keyword("print");
	public @S(20) @OPT Python_Punctuation greaterGreater = new Python_Punctuation(">>");
	public @S(30) @OPT SeparatedList<Python_Expression, PunctuationComma> exprs;
	public @S(40) @OPT @NOSPACE @CURIOUS("Extra comma") PunctuationComma comma;

	@Override
	public Python_ComplexStatement generatePrint(ArrayList<Python_Expression> pieces,
			boolean newLine, AbstractToken source)
	{
		Python_Print_Function func = new Python_Print_Function();
		func.leftParen = new PunctuationLeftParen();
		func.exprs = new SeparatedList<Python_Expression, PunctuationComma>();
		
		// ''.join(str(x) for x in ['abc',4,5'xys])
		boolean first = true;
		for (AbstractExpression piece : pieces)
		{
			if (first)
			{
				first = false;
			}
			else
			{
				func.exprs.addSecondaryElement(new PunctuationComma());
			}
			func.exprs.addPrimaryElement((Python_Expression) piece);
		}
		
		if (! newLine)
		{
			func.exprs.addSecondaryElement(new PunctuationComma());
			Python_Expression emptyExpr1 = Python_Literal.generateLiteralExpression("", null);
			Python_VariableExpression endVar = new Python_VariableExpression();
			Python_Expression end = endVar.generateVarExpr("end", null, null);
			Python_Assignment_Expression asg1 = new Python_Assignment_Expression();
			Python_Expression asgExpr1 = asg1.generateAssignment(end,
					AssignmentEnum.EQUALS, emptyExpr1, source);
			func.exprs.addPrimaryElement(asgExpr1);
		}
		
		if (pieces.size() > 1)
		{
			func.exprs.addSecondaryElement(new PunctuationComma());
			Python_Expression emptyExpr2 = Python_Literal.generateLiteralExpression("", null);
			Python_VariableExpression sepVar = new Python_VariableExpression();
			Python_Expression sep = sepVar.generateVarExpr("sep", null, null);
			Python_Assignment_Expression asg2 = new Python_Assignment_Expression();
			Python_Expression asgExpr2 = asg2.generateAssignment(sep,
					AssignmentEnum.EQUALS, emptyExpr2, source);
			func.exprs.addPrimaryElement(asgExpr2);
		}

		func.rightParen = new PunctuationRightParen();
		func.setTransformationSource(source);
		
		Python_ExpressionStatement stmt = new Python_ExpressionStatement();
		stmt.expression = Python_Generator.wrapExpression(func);
		
		stmt.setTransformationSource(source);
		return Python_Generator.wrapStatement(stmt);
	}
	
	@Override
	public Python_ComplexStatement generatePrint1(Python_Expression line,
			boolean newLine, AbstractToken source)
	{
		ArrayList<Python_Expression> pieces = new ArrayList<Python_Expression>();
		pieces.add(line);
		return generatePrint(pieces, newLine, source);
	}
}
