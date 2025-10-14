// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Algol68_Syntax;
import com.eagle.programmar.Algol68.Algol68_Type;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Symbols.Algol68_Procedure_Definition;
import com.eagle.programmar.Algol68.Symbols.Algol68_Variable_Definition;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Algol68_Procedure extends TokenSequence
		implements EagleRunnable, AbstractFunction, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) Algol68_Keyword PROCEDURE = new Algol68_Keyword("PROC");
	public @S(20) Algol68_Procedure_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) @OPT Algol68_ProcedureParams params;
	public @S(50) @OPT Algol68_ProcedureReturns returns;
	public @S(60) PunctuationLeftParen leftParen;
	public @S(70) TokenList<Algol68_Statement> statements;
	public @S(80) @OPT Algol68_Variable returnValue;
	public @S(90) PunctuationRightParen rightParen;
	public @S(100) PunctuationSemicolon semicolon;

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	public static class Algol68_ProcedureParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Algol68_Parameter, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Algol68_Parameter extends TokenSequence
	{
		public @S(10) Algol68_Type type;
		public @S(20) Algol68_Variable_Definition param;
	}

	public static class Algol68_ProcedureReturns extends TokenSequence
	{
		public @S(10) Algol68_Type type;
		public @S(20) PunctuationColon colon;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Algol68_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_callMetrics == null)
		{
			_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
		}
		
		// Nothing to do here -- just defining the procedure
	}
	
	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		TypeEnum typRet = TypeEnum.VOID;
		if (returns != null && returns.isPresent())
		{
			typRet = Algol68_Type.findType(returns.type);
		}
		AbstractType newReturnType = generator.transformType(typRet, null, null);

		String newName = id.getValue();
		if (newName.equals("main"))
		{
			newName = generator.mainName();
		}
		generator.addMethod(newReturnType, newName, this);
		generator.setMethodName(id.getValue());
		if (VERBOSE)
		{
			System.out.println("** Found Algol68 function " + id.getValue());
		}
		
		if (params != null && params.isPresent())
		{
			int nParams = params.parameters.getPrimaryCount();
			for (int i = 0; i < nParams; i++)
			{
				Algol68_Parameter param = params.parameters.getPrimaryElement(i);
				TypeEnum typParam = Algol68_Type.findType(param.type);
				AbstractType paramType = generator.transformType(typParam, null, null);
				generator.addMethodParameter(paramType, param.param.getValue());
			}
		}
		
		int numElts = statements._elements.size();
		int eltNum = 0;
		for (Algol68_Statement stmt : statements._elements)
		{
			AbstractToken which = stmt.getWhich();

			eltNum++;
			if (eltNum == numElts)
			{
				// Last line in a procedure *might* be an implied RETURN
				if (which instanceof Algol68_ExpressionStatement)
				{
					Algol68_ExpressionStatement exprStmt = (Algol68_ExpressionStatement) which;
					AbstractExpression newExpr = transformer.transformExpression(generator, exprStmt.expr);
					AbstractStatement retStmt = generator.newReturnStatement(newExpr, which);
					generator.addStatement(retStmt, stmt);
					break;	// Only gets here for the last statement in the PROC
				}
			}
			
			ArrayList<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, stmt);
				}
			}
		}
		
		generator.doneMethod();
	}
}
