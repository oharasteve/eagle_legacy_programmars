// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Scala.Scala_Statement;
import com.eagle.programmar.Scala.Scala_Syntax;
import com.eagle.programmar.Scala.Scala_Type;
import com.eagle.programmar.Scala.Symbols.Scala_Function_Definition;
import com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Scala_Function extends TokenSequence
		implements EagleRunnable, AbstractFunction, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) @OPT Scala_Keyword OVERRIDE = new Scala_Keyword("override");
	public @S(20) @DOC("taste-methods.html") Scala_Keyword DEF = new Scala_Keyword("def");
	public @S(30) Scala_Function_Definition id;
	public @S(40) @OPT Scala_FunctionParams params;
	public @S(50) @OPT Scala_FunctionReturns returns;
	public @S(60) @OPT PunctuationEquals equals;
	public @S(70) Scala_Statement stmt;

	public static class Scala_FunctionReturns extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Scala_Type returnType;
	}

	public static class Scala_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Scala_FunctionParameter, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Scala_FunctionParameter extends TokenSequence
	{
		public @S(10) Scala_Variable_Definition var;
		public @S(20) PunctuationColon colon;
		public @S(30) Scala_Type type;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Scala_Syntax.IS_CASE_SENSITIVE);

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

		if (id.getValue().equals("main"))
		{
			// Run the main program
			interpreter.callingFunction("main", this);
			interpreter.tryToInterpret(stmt);
			interpreter.completedFunction("main", this);
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractType newReturnType = null;
		if (returns != null && returns.isPresent())
		{
			newReturnType = Scala_Type.findType(generator, returns.returnType);
		}

		String newName = id.getValue();
		if (newName.equals("main"))
		{
			newName = generator.mainName();
		}
		generator.addMethod(newReturnType, newName, this);
		generator.addMethodName(id.getValue());
		if (VERBOSE)
		{
			System.out.println("** Found Scala function " + id.getValue());
		}

		if (params != null && params.isPresent())
		{
			int nParams = params.parameters.getPrimaryCount();
			for (int i = 0; i < nParams; i++)
			{
				Scala_FunctionParameter param = params.parameters.getPrimaryElement(i);
				AbstractType paramType = Scala_Type.findType(generator, param.type);
				generator.addMethodParameter(paramType, param.var.getValue());
			}
		}

		ArrayList<AbstractStatement> newStmts = Scala_BlockStatement.collectStatements(transformer, generator, stmt);
		if (newStmts != null)
		{
			for (AbstractStatement newStmt : newStmts)
			{
				generator.addStatement(newStmt, stmt);
			}
		}

		generator.doneMethod();
	}
}
