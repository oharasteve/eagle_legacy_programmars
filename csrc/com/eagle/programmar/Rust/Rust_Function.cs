// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

namespace com.eagle.programmar.Rust
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using Rust_TypePrimitive = com.eagle.programmar.Rust.Rust_Type.Rust_TypePrimitive;
	using Rust_Block_Statement = com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
	using Rust_Function_Definition = com.eagle.programmar.Rust.Symbols.Rust_Function_Definition;
	using Rust_Variable_Definition = com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition;
	using Rust_Comment = com.eagle.programmar.Rust.Terminals.Rust_Comment;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
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
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_Function : TokenSequence, EagleRunnable, AbstractFunction, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Rust_Keyword PUB = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("pub");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("items/functions.html") com.eagle.programmar.Rust.Terminals.Rust_Keyword FN = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("fn");
		public @DOC("items/functions.html") Rust_Keyword FN = new Rust_Keyword("fn");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Symbols.Rust_Function_Definition id;
		public Rust_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public @NOSPACE PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT @NOSPACE SeparatedList<Rust_Parameter, com.eagle.tokens.punctuation.PunctuationComma> funcParamDefs;
		public @OPT SeparatedList<Rust_Parameter, PunctuationComma> funcParamDefs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public @NOSPACE PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Rust_FunctionReturns returns;
		public @OPT Rust_FunctionReturns returns;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Rust.Statements.Rust_Block_Statement block;
		public Rust_Block_Statement block;

		public static class Rust_FunctionReturns extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Punctuation arrow = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("->");
			public Rust_Punctuation arrow = new Rust_Punctuation("->");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Rust_Type returnType;
			public Rust_Type returnType;
		}

		public static class Rust_Parameter extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition var;
			public Rust_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Rust_Type type;
			public Rust_Type type;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, Rust_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Rust_Syntax.IS_CASE_SENSITIVE);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;

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

			// Don't do anything here.
			// We searched for all the functions in a preliminary pass
			// And we only evaluate when it is called

			// Except the function called 'main'
			if (id.getValue().Equals("main"))
			{
				interpreter.callingFunction("main", this);
				interpreter.tryToInterpret(block);
				interpreter.completedFunction("main", this);
			}
		}

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newReturnType = null;
			if (returns != null && returns.isPresent())
			{
				if (returns.returnType.getWhich() is Rust_TypePrimitive)
				{
					Rust_TypePrimitive primitive = (Rust_TypePrimitive) returns.returnType.getWhich();
					newReturnType = Rust_Type.findType(generator, primitive.PRIMITIVE.ToString());
				}
			}

			if (newReturnType == null)
			{
				EagleGenerator.TypeEnum metricRetType = transformer.findReturnMetric(id);
				newReturnType = generator.transformType(metricRetType, null, id);
			}

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
				Console.WriteLine("** Found Rust function " + fnName);
			}

			if (isMain)
			{
				// Have to wait until addMethod is called
				generator.addMainArgs(); // For java and C# but not for Python or Rust
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (funcParamDefs != null && funcParamDefs.isPresent())
			{
				for (int i = 0; i < funcParamDefs.getPrimaryCount(); i++)
				{
					Rust_Parameter param = funcParamDefs.getPrimaryElement(i);
					AbstractType paramType = null;

					if (argTypes != null && i < argTypes.Count)
					{
						EagleGenerator.TypeEnum metricArg = argTypes[i];
						paramType = generator.transformType(metricArg, null, param);
					}

					// System.err.println("****** paramType = " + paramType + " value = " +
					// param.getValue());
					generator.addMethodParameter(paramType, param.var.getValue());
				}
			}

			///////// addLocalVars(transformer, generator);

			ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, block);
			if (newStmts != null)
			{
				foreach (AbstractStatement newStmt in newStmts)
				{
					generator.addStatement(newStmt, block);
				}
			}

			generator.doneMethod();
		}

		public void newRustFunction(Rust_Type returnType, string methodName)
		{
			this.leftParen = new PunctuationLeftParen();
			this.rightParen = new PunctuationRightParen();

			if (returnType != null)
			{
				this.returns = new Rust_FunctionReturns();
				this.returns.setPresent(true);
				this.returns.returnType = returnType;
			}

			this.block = new Rust_Block_Statement();
			this.block.leftBrace = new PunctuationLeftBrace();
			this.block.statements = new TokenList<Rust_Statement>();
			this.block.rightBrace = new PunctuationRightBrace();

			this.id = new Rust_Function_Definition();
			this.id.setValue(methodName);
		}

		public void addFunctionParameter(AbstractType type, string name)
		{
			Rust_Parameter param = new Rust_Parameter();
			param.var = new Rust_Variable_Definition();
			param.var.setValue(name);
			param.type = (Rust_Type) type;

			if (funcParamDefs == null)
			{
				this.funcParamDefs = new SeparatedList<Rust_Parameter, PunctuationComma>();
				this.funcParamDefs.setPresent(true);
			}

			if (this.funcParamDefs.size() > 0)
			{
				this.funcParamDefs.addSecondaryElement(new PunctuationComma());
			}
			this.funcParamDefs.addPrimaryElement(param);
		}

		public void addComment(Rust_Comment comm)
		{
			Rust_Statement stmt = new Rust_Statement();
			stmt.setWhich(comm);
			this.block.statements._elements.add(stmt);
		}
	}

}
