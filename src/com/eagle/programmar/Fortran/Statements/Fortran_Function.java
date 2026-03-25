// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Fortran_Syntax;
import com.eagle.programmar.Fortran.Fortran_Type;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Definition;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Fortran_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) Fortran_Type type;
	public @S(20) @DOC("6j4m0vn9h/index.html") Fortran_Keyword FUNCTION1 = new Fortran_Keyword("FUNCTION");
	public @S(30) Fortran_Function_Definition id;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) Fortran_EOLN eoln1;

	public @S(80) TokenList<Fortran_Statement> statements;

	public @S(90) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(100) Fortran_Keyword FUNCTION2 = new Fortran_Keyword("FUNCTION");
	public @S(110) Fortran_Function_Reference fnName2;
	public @S(120) Fortran_EOLN eoln2;

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Fortran_Syntax.IS_CASE_SENSITIVE);

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
		if (_returnMetrics == null)
		{
			_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
		}

		// Nothing to do here -- only act when it is called
	}

	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Collect all the COMMON variables first
		HashSet<String> commons = Fortran_Common.collectCommons(statements._elements);
		
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);

		String fnName = id.getValue();
		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Fortran function " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

		if (parameters != null && parameters.isPresent())
		{
			for (int i = 0; i < parameters.getPrimaryCount(); i++)
			{
				Fortran_Variable_Reference param = parameters.getPrimaryElement(i);
				AbstractType paramType = null;

				if (argTypes != null && i < argTypes.size())
				{
					TypeEnum metricArg = argTypes.get(i);
					paramType = generator.transformType(metricArg, null, param);
				}

				generator.addMethodParameter(paramType, param.getValue());
			}
		}

		for (Fortran_Statement stmt : statements._elements)
		{
			if (stmt.getWhich() instanceof Fortran_Data)
			{
				Fortran_Data dataStmt = (Fortran_Data) stmt.getWhich();
				String var = dataStmt.variables.first().getValue();
				if (commons.contains(var))
				{
					// This is broken if a single DATA line has both COMMON and non-COMMON variables
					// It only checks the first variable and if it is COMMON, it assumes all of them are COMMON
					continue;	// Skip all the COMMON variables
				}
			}
			
			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, stmt.getWhich());
				}
			}
		}

		generator.doneMethod();
	}
}
