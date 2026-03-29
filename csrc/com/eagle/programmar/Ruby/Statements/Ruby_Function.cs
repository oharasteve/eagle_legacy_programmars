// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Ruby_Statement = com.eagle.programmar.Ruby.Ruby_Statement;
	using Ruby_Syntax = com.eagle.programmar.Ruby.Ruby_Syntax;
	using Ruby_Variable = com.eagle.programmar.Ruby.Ruby_Variable;
	using Ruby_Function_Definition = com.eagle.programmar.Ruby.Symbols.Ruby_Function_Definition;
	using Ruby_EOLN = com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
	using Ruby_Keyword = com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ruby_Function : TokenSequence, EagleRunnable, AbstractFunction, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Terminals.Ruby_Keyword DEF = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("def");
		public Ruby_Keyword DEF = new Ruby_Keyword("def");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Symbols.Ruby_Function_Definition id;
		public Ruby_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Ruby_FunctionParams funcParamDefs;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln1;
		public Ruby_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Ruby.Ruby_Statement> statements;
		public TokenList<Ruby_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Ruby.Terminals.Ruby_Keyword END = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("end");
		public Ruby_Keyword END = new Ruby_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln2;
		public Ruby_EOLN eoln2;

		public class Ruby_FunctionParams : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<com.eagle.programmar.Ruby.Ruby_Variable, com.eagle.tokens.punctuation.PunctuationComma> parameters;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ReturnMetrics _returnMetrics = null;
		public ReturnMetrics _returnMetrics = null;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Ruby.Ruby_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Ruby_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
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

			// Don't do anything here.
			// We searched for all the functions in a preliminary pass
			// And we only evaluate when it is called
		}

		public override void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.TypeEnum metricRetType = transformer.findReturnMetric(id);
			AbstractType newReturnType = generator.transformType(metricRetType, null, id);

			string fnName = id.getValue();
			bool isMain = false;
			if (fnName.Equals("main"))
			{
				fnName = generator.mainName(); // Change from 'main' to 'Main' for C#
				isMain = true;
			}

			generator.addMethod(newReturnType, fnName, this);
			generator.setMethodName(fnName);
			if (VERBOSE)
			{
				Console.WriteLine("** Found Ruby function " + fnName);
			}

			if (isMain)
			{
				// Have to wait until addMethod is called
				generator.addMainArgs(); // For java and C# but not for Python
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (funcParamDefs != null && funcParamDefs.isPresent())
			{
				for (int i = 0; i < funcParamDefs.parameters.getPrimaryCount(); i++)
				{
					Ruby_Variable param = funcParamDefs.parameters.getPrimaryElement(i);
					AbstractType paramType = null;

					if (argTypes != null && i < argTypes.Count)
					{
						EagleGenerator.TypeEnum metricArg = argTypes[i];
						paramType = generator.transformType(metricArg, null, param);
					}

					generator.addMethodParameter(paramType, param.vars.first().getValue());
				}
			}

			addLocalVars(transformer, generator);

			foreach (Ruby_Statement stmt in statements._elements)
			{
				ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
				if (newStmts != null)
				{
					foreach (AbstractStatement newStmt in newStmts)
					{
						generator.addStatement(newStmt, stmt.getWhich());
					}
				}
			}

			generator.doneMethod();
		}

		private bool isFuncParam(string name)
		{
			int numParams = funcParamDefs.parameters.getPrimaryCount();
			for (int i = 0; i < numParams; i++)
			{
				Ruby_Variable var = funcParamDefs.parameters.getPrimaryElement(i);
				if (var.vars.first().getValue().equalsIgnoreCase(name))
				{
					return true;
				}
			}
			return false;
		}

		// Are there any local variables we need to declare?
		private void addLocalVars(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typ = met.uniqueType();
				if (typ != EagleGenerator.TypeEnum.VOID)
				{
					if (!isFuncParam(met._symbolName))
					{
						// System.err.println("****** Found local var " + met._symbolName);
						AbstractType absType = generator.transformType(typ, null, this);
						AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, absType, null, this);
						generator.addStatement(dataStmt, this);
					}
				}
			}
		}
	}

}
