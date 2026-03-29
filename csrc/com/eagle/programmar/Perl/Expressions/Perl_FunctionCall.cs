// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Perl.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Function = com.eagle.programmar.Perl.Perl_Function;
	using Perl_FunctionTypeAndVariable = com.eagle.programmar.Perl.Perl_Function.Perl_FunctionTypeAndVariable;
	using Perl_FunctionVariable = com.eagle.programmar.Perl.Perl_Function.Perl_FunctionVariable;
	using Perl_FunctionVariableOrTypeVariable = com.eagle.programmar.Perl.Perl_Function.Perl_FunctionVariableOrTypeVariable;
	using Perl_Identifier_Reference = com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
	using Perl_Comment = com.eagle.programmar.Perl.Terminals.Perl_Comment;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
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
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference fnName;
		public Perl_Identifier_Reference fnName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Perl_MoreFunctionName> moreName;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Perl_Method> perlMethods;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Perl_Punctuation at = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('@');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Perl_Expression argument;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<Perl_MoreFnArguments> moreArgs;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public class Perl_MoreFunctionName : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation backSlash = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('\\');
			public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference fnName;
			public Perl_Identifier_Reference fnName;
		}

		public class Perl_Method : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation colonColon = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("::");
			public Perl_Punctuation colonColon = new Perl_Punctuation("::");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference fnName;
			public Perl_Identifier_Reference fnName;
		}

		public class Perl_MoreFnArguments : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_Comment comment;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Perl_Punctuation at = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('@');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Perl.Perl_Expression argument;
			public Perl_Expression argument;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = fnName.getValue();

			// Look up the function
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}
			Perl_Function func = (Perl_Function) fn;

			// Make sure the function args match up
			int argCount = 0;
			if (argument != null && argument.isPresent())
			{
				argCount++;
			}
			if (moreArgs != null && moreArgs.isPresent())
			{
				argCount += moreArgs.size();
			}
			int paramCount = 0;
			if (func.@params.parameters != null && func.@params.parameters.isPresent())
			{
				paramCount = func.@params.parameters.getPrimaryCount();
			}

			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			Perl_Expression arg = argument;
			for (int i = 0; i < argCount; i++)
			{
				if (i > 0)
				{
					arg = moreArgs._elements.get(i - 1).argument;
				}
				Perl_Function.Perl_FunctionVariableOrTypeVariable param = func.@params.parameters.getPrimaryElement(i);
				Perl_Function.Perl_FunctionVariable fnVar;
				if (param.getWhich() is Perl_Function.Perl_FunctionVariable)
				{
					fnVar = (Perl_Function.Perl_FunctionVariable) param.getWhich();
				}
				else
				{
					Perl_Function.Perl_FunctionTypeAndVariable typedVar = (Perl_Function.Perl_FunctionTypeAndVariable) param.getWhich();
					fnVar = typedVar.var;
				}
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter.setSymbol(param, fnVar.param.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			interpreter.callingFunction(name, func);
			interpreter.tryToInterpret(func.block);

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, func);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = fnName.getValue();
			List<AbstractExpression> args = new List<AbstractExpression>();
			int argCount = 0;
			if (argument != null && argument.isPresent())
			{
				argCount++;
			}
			if (moreArgs != null && moreArgs.isPresent())
			{
				argCount += moreArgs.size();
			}
			for (int i = 0; i < argCount; i++)
			{
				Perl_Expression arg;
				if (i == 0)
				{
					arg = argument;
				}
				else
				{
					arg = moreArgs._elements.get(i - 1).argument;
				}
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.Add(newArg);
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, fnName);
		}
	}

}
