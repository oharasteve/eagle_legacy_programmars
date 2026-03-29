// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using Scala_Statement = com.eagle.programmar.Scala.Scala_Statement;
	using Scala_Syntax = com.eagle.programmar.Scala.Scala_Syntax;
	using Scala_Type = com.eagle.programmar.Scala.Scala_Type;
	using Scala_Function_Definition = com.eagle.programmar.Scala.Symbols.Scala_Function_Definition;
	using Scala_Variable_Definition = com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition;
	using Scala_Keyword = com.eagle.programmar.Scala.Terminals.Scala_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Scala_Function : TokenSequence, EagleRunnable, AbstractFunction, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Scala_Keyword OVERRIDE = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("override");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("taste-methods.html") com.eagle.programmar.Scala.Terminals.Scala_Keyword DEF = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("def");
		public @DOC("taste-methods.html") Scala_Keyword DEF = new Scala_Keyword("def");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Symbols.Scala_Function_Definition id;
		public Scala_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Scala_FunctionParams params;
		public @OPT Scala_FunctionParams @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Scala_FunctionReturns returns;
		public @OPT Scala_FunctionReturns returns;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PunctuationEquals equals;
		public @OPT PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Scala.Scala_Statement stmt;
		public Scala_Statement stmt;

		public static class Scala_FunctionReturns extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Scala_Type returnType;
			public Scala_Type returnType;
		}

		public static class Scala_FunctionParams extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<Scala_FunctionParameter, com.eagle.tokens.punctuation.PunctuationComma> parameters;
			public @OPT SeparatedList<Scala_FunctionParameter, PunctuationComma> parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class Scala_FunctionParameter extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition var;
			public Scala_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Scala_Type type;
			public Scala_Type type;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Scala.Scala_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Scala_Syntax.IS_CASE_SENSITIVE);

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

			if (id.getValue().Equals("main"))
			{
				// Run the main program
				interpreter.callingFunction("main", this);
				interpreter.tryToInterpret(stmt);
				interpreter.completedFunction("main", this);
			}
		}

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newReturnType = null;
			if (returns != null && returns.isPresent())
			{
				newReturnType = Scala_Type.findType(generator, returns.returnType);
			}

			string newName = id.getValue();
			if (newName.Equals("main"))
			{
				newName = generator.mainName();
			}
			generator.addMethod(newReturnType, newName, this);
			generator.setMethodName(id.getValue());
			if (VERBOSE)
			{
				Console.WriteLine("** Found Scala function " + id.getValue());
			}

			if (@params != null && @params.isPresent())
			{
				int nParams = @params.parameters.getPrimaryCount();
				for (int i = 0; i < nParams; i++)
				{
					Scala_FunctionParameter param = @params.parameters.getPrimaryElement(i);
					AbstractType paramType = Scala_Type.findType(generator, param.type);
					generator.addMethodParameter(paramType, param.var.getValue());
				}
			}

			List<AbstractStatement> newStmts = Scala_BlockStatement.collectStatements(transformer, generator, stmt);
			if (newStmts != null)
			{
				foreach (AbstractStatement newStmt in newStmts)
				{
					generator.addStatement(newStmt, stmt);
				}
			}

			generator.doneMethod();
		}
	}

}
