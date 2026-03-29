// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Rexx_Element = com.eagle.programmar.Rexx.Rexx_Element;
	using Rexx_Syntax = com.eagle.programmar.Rexx.Rexx_Syntax;
	using Rexx_Function_Definition = com.eagle.programmar.Rexx.Symbols.Rexx_Function_Definition;
	using Rexx_Variable_Definition = com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition;
	using Rexx_EndOfLine = com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
	using Rexx_Keyword = com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rexx_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("reference-functions") com.eagle.programmar.Rexx.Symbols.Rexx_Function_Definition id;
		public @DOC("reference-functions") Rexx_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine eoln;
		public Rexx_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Rexx_Parameters params;
		public @OPT Rexx_Parameters @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Rexx.Rexx_Element> stmts;
		public TokenList<Rexx_Element> stmts;

		public static class Rexx_Parameters extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Terminals.Rexx_Keyword PARSE = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("PARSE");
			public Rexx_Keyword PARSE = new Rexx_Keyword("PARSE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rexx.Terminals.Rexx_Keyword ARG = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("ARG");
			public Rexx_Keyword ARG = new Rexx_Keyword("ARG");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition, com.eagle.tokens.punctuation.PunctuationComma> params;
			public SeparatedList<Rexx_Variable_Definition, PunctuationComma> @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine eoln;
			public Rexx_EndOfLine eoln;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Rexx.Rexx_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Rexx_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
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

			// Don't do anything here.
			// We searched for all the functions in a preliminary pass
			// And we only evaluate when it is called
		}

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.TypeEnum metricRetType = transformer.findReturnMetric(id);
			AbstractType newReturnType = generator.transformType(metricRetType, null, id);

			string fnName = id.getValue();
			generator.addMethod(newReturnType, fnName, this);
			generator.setMethodName(fnName);
			if (VERBOSE)
			{
				Console.WriteLine("** Found Rexx function " + fnName);
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (@params != null && @params.isPresent())
			{
				for (int i = 0; i < @params.@params.getPrimaryCount(); i++)
				{
					Rexx_Variable_Definition param = @params.@params.getPrimaryElement(i);
					AbstractType paramType = null;

					if (argTypes != null && i < argTypes.Count)
					{
						EagleGenerator.TypeEnum metricArg = argTypes[i];
						paramType = generator.transformType(metricArg, null, param);
					}

					// System.err.println("****** paramType = " + paramType + " value = " +
					// param.getValue());
					generator.addMethodParameter(paramType, param.getValue());
				}
			}

			addLocalVars(transformer, generator);

			foreach (Rexx_Element stmt in stmts._elements)
			{
				AbstractToken which = stmt.baseStatement.getWhich();

				ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
				if (newStmts != null)
				{
					foreach (AbstractStatement newStmt in newStmts)
					{
						generator.addStatement(newStmt, stmt);
					}
				}
			}

			generator.doneMethod();
		}

		private bool isFuncParam(string name)
		{
			if (@params != null && @params.isPresent())
			{
				int numParams = @params.@params.getPrimaryCount();
				for (int i = 0; i < numParams; i++)
				{
					Rexx_Variable_Definition var = @params.@params.getPrimaryElement(i);
					if (var.getValue().equalsIgnoreCase(name))
					{
						return true;
					}
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
						// System.err.println("****** Found var " + met._symbolName);
						AbstractType absType = generator.transformType(typ, null, this);
						AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, absType, null, this);
						generator.addStatement(dataStmt, this);
					}
				}
			}
		}
	}

}
