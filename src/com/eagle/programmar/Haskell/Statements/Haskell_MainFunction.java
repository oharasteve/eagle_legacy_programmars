// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2026

package com.eagle.programmar.Haskell.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement;
import com.eagle.programmar.Haskell.Haskell_Syntax;
import com.eagle.programmar.Haskell.Haskell_Type;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Haskell_MainFunction extends TokenSequence
		implements AbstractFunction, EagleTransformableFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) @OPT Haskell_MainPrototype prototype;
	public @S(20) Haskell_Keyword MAIN = new Haskell_Keyword("main");
	public @S(30) PunctuationEquals equals;
	public @S(40) Haskell_ComplexStatement statement;

	public static class Haskell_MainPrototype extends TokenSequence
	{
		public @S(10) Haskell_Keyword MAIN = new Haskell_Keyword("main");
		public @S(20) Haskell_Punctuation colonColon = new Haskell_Punctuation("::");
		public @S(30) Haskell_Keyword IO = new Haskell_Keyword("IO");
		public @S(40) Haskell_Type type;
		public @S(50) Haskell_EndOfLine eoln;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, Haskell_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.callingFunction("main", this);
		interpreter.tryToInterpret(statement);
		interpreter.completedFunction("main", this);
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractType noType = generator.transformType(TypeEnum.VOID, null, null);
		generator.addMethod(noType, "main", this);

		addLocalVars(transformer, generator);
		
		Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, statement.statementOrComment);
		for (AbstractStatement newStmt : newStmts)
		{
			generator.addStatement(newStmt, this);
		}

		generator.doneMethod();
	}
	
	// Are there any local variables we need to declare?
	private void addLocalVars(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typ = met.uniqueType();
			if (typ != TypeEnum.VOID)
			{
				// System.err.println("****** Found var " + met._symbolName);
				AbstractType absType = generator.transformType(typ, null, this);
				AbstractStatement dataStmt = generator.newDataDeclaration(StaticEnum.NONE,
						met._symbolName, null, absType, null, this);
				generator.addStatement(dataStmt, this);
			}
		}
	}
}
