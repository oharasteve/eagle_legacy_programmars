// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

namespace com.eagle.programmar.Eaglish.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Eaglish_Statement = com.eagle.programmar.Eaglish.Eaglish_Statement;
	using Eaglish_Syntax = com.eagle.programmar.Eaglish.Eaglish_Syntax;
	using Eaglish_Function_Definition = com.eagle.programmar.Eaglish.Symbols.Eaglish_Function_Definition;
	using Eaglish_EndOfLine = com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_Function : TokenSequence, EagleRunnable, AbstractFunction, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword FUNCTION = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("FUNCTION");
		public Eaglish_Keyword FUNCTION = new Eaglish_Keyword("FUNCTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Symbols.Eaglish_Function_Definition id;
		public Eaglish_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln1;
		public Eaglish_EndOfLine eoln1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Eaglish_Parameter_Statement> parameterStatements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Eaglish_Returns_Statement returnsStatement;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.Eaglish.Eaglish_Statement> statements;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword END_FUNCTION = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("END_FUNCTION");
		public Eaglish_Keyword END_FUNCTION = new Eaglish_Keyword("END_FUNCTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln2;
		public Eaglish_EndOfLine eoln2;

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
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Eaglish.Eaglish_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Eaglish_Syntax.IS_CASE_SENSITIVE);

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
			generator.addMethod(newReturnType, fnName, this);
			generator.setMethodName(fnName);
			if (VERBOSE)
			{
				Console.WriteLine("** Found Eaglish function " + fnName);
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (parameterStatements != null && parameterStatements.isPresent())
			{
				int i = 0;
				foreach (Eaglish_Parameter_Statement param in parameterStatements._elements)
				{
					AbstractType paramType = null;

					if (argTypes != null && i < argTypes.Count)
					{
						EagleGenerator.TypeEnum metricArg = argTypes[i];
						paramType = generator.transformType(metricArg, null, param);
					}

					generator.addMethodParameter(paramType, param.param.getValue());
					i++;
				}
			}

			// addLocalVars(transformer, generator);

			foreach (Eaglish_Statement stmt in statements._elements)
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
	}

}
