// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

namespace com.eagle.programmar.Powershell.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Powershell_Element = com.eagle.programmar.Powershell.Powershell_Element;
	using Powershell_EndOfLine = com.eagle.programmar.Powershell.Powershell_EndOfLine;
	using Powershell_Syntax = com.eagle.programmar.Powershell.Powershell_Syntax;
	using Powershell_Type = com.eagle.programmar.Powershell.Powershell_Type;
	using Powershell_Variable = com.eagle.programmar.Powershell.Powershell_Variable;
	using Powershell_Function_Definition = com.eagle.programmar.Powershell.Symbols.Powershell_Function_Definition;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Powershell_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("chapter-08?view=powershell-5.1#810-function-definitions") com.eagle.programmar.Powershell.Terminals.Powershell_Keyword FUNCTION = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Function");
		public @DOC("chapter-08?view=powershell-5.1#810-function-definitions") Powershell_Keyword FUNCTION = new Powershell_Keyword("Function");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Symbols.Powershell_Function_Definition id;
		public Powershell_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Powershell_FunctionParams params;
		public @OPT Powershell_FunctionParams @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Powershell_EndOfLine eoln1;
		public @OPT Powershell_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Powershell_EndOfLine eoln2;
		public @OPT Powershell_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.Powershell.Powershell_Element> stmts;
		public @OPT TokenList<Powershell_Element> stmts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public static class Powershell_FunctionParams extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<Powershell_FunctionParam, com.eagle.tokens.punctuation.PunctuationComma> params;
			public @OPT SeparatedList<Powershell_FunctionParam, PunctuationComma> @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class Powershell_FunctionParam extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Powershell_CastParameter cast;
			public @OPT Powershell_CastParameter cast;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Powershell_Variable var;
			public Powershell_Variable var;

			public static class Powershell_CastParameter extends PrimaryOperator
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
				public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Powershell_Type type;
				public Powershell_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
				public PunctuationRightBracket rightBracket;
			}
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
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Powershell.Powershell_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Powershell_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

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
			// We searched for all the function in a preliminary pass
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
				Console.WriteLine("** Found Powershell function " + fnName);
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (@params != null && @params.isPresent())
			{
				for (int i = 0; i < @params.@params.getPrimaryCount(); i++)
				{
					Powershell_FunctionParam paramVar = @params.@params.getPrimaryElement(i);
					AbstractType paramType = null;

					if (argTypes != null && i < argTypes.Count)
					{
						EagleGenerator.TypeEnum metricArg = argTypes[i];
						paramType = generator.transformType(metricArg, null, paramVar);
					}

					// System.err.println("****** paramType = " + paramType + " value = " +
					// param.getValue());
					generator.addMethodParameter(paramType, paramVar.var.id.getValue());
				}
			}

			findGlobalVars(transformer, generator);

			addLocalVars(transformer, generator);

			foreach (Powershell_Element stmt in stmts._elements)
			{
				AbstractToken which = stmt.element.getWhich();

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
					Powershell_FunctionParam var = @params.@params.getPrimaryElement(i);
					if (var.var.id.getValue().equalsIgnoreCase(name))
					{
						return true;
					}
				}
			}
			return false;
		}

		// Anything to declare as 'global' in python?
		// E.g., $script:emsg
		private void findGlobalVars(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			HashSet<string> added = new HashSet<string>();
			foreach (AssignMetrics var in transformer._metrics.findAllAssignments())
			{
				if (var._startingLine >= this._currentLine && var._startingLine <= this._endLine)
				{
					if (var._scopeStart == 0)
					{
						// Scope of this variable is outside the current function.
						// I.e., it is a $script:emsg or similar 
						string varName = var._symbolName;
						if (!added.Contains(varName))
						{
							AbstractStatement newStmt = generator.newGlobalVariable(varName, null);
							generator.addStatement(newStmt, null);
							added.Add(varName);
						}
					}
				}
			}
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
