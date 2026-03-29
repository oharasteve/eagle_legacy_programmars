// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2022

namespace com.eagle.programmar.Powershell.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using Powershell_Element = com.eagle.programmar.Powershell.Powershell_Element;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_FunctionParam = com.eagle.programmar.Powershell.Statements.Powershell_Function.Powershell_FunctionParam;
	using Powershell_Function_Reference = com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
	using Powershell_Filename = com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
	using Powershell_Punctuation = com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
	using Powershell_VerbNoun = com.eagle.programmar.Powershell.Terminals.Powershell_VerbNoun;
	using Powershell_Word = com.eagle.programmar.Powershell.Terminals.Powershell_Word;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationAmpersand = com.eagle.tokens.punctuation.PunctuationAmpersand;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Powershell_Command : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Powershell_WhichCommand whichCommand;
		public Powershell_WhichCommand whichCommand; // Like Get-Content or javac
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Powershell_CommandArg> argList;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;

		public class Powershell_WhichCommand : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationAmpersand XXampersand;
			public PunctuationAmpersand XXampersand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationPeriod XXdot;
			public PunctuationPeriod XXdot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Punctuation XXdotDot = new com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation("..");
			public Powershell_Punctuation XXdotDot = new Powershell_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationColon XXcolon;
			public PunctuationColon XXcolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_VerbNoun XXverbNoun;
			public Powershell_VerbNoun XXverbNoun; // Like Get-Content for example
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Powershell_Function_Reference XXid;
			public Powershell_Function_Reference XXid;
		}

		public class Powershell_CommandArg : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Powershell_CommandOneArg arg;
			public Powershell_CommandOneArg arg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationComma comma;
			public  OPT;

			public class Powershell_CommandOneArg : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Powershell_Filename XXfilename;
				public Powershell_Filename XXfilename; // Tricky because the dot is already taken
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Expression XXexpr;
				public Powershell_Expression XXexpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Powershell_Word XXword;
				public Powershell_Word XXword;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (whichCommand.getWhich() is Powershell_Function_Reference)
			{
				Powershell_Function_Reference fnName = (Powershell_Function_Reference) whichCommand.getWhich();

				// Is it one of the defined Functions?
				AbstractFunction fn = interpreter.findFunction(fnName.getValue());
				if (fn == null)
				{
					throw new Exception("Unable to find a function named " + fnName.getValue());
				}
				Powershell_Function func = (Powershell_Function) fn;
				string name = func.id.getValue();

				if (_callMetrics == null)
				{
					_callMetrics = new CallMetrics(interpreter._metrics, name, func.id);
				}
				if (_argumentsMetrics == null)
				{
					_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, name, func.id);
				}

				// Call the function
				// Make sure the function args match up
				int argCount = argList.size();
				int paramCount = func.@params.@params.getPrimaryCount();
				if (argCount != paramCount)
				{
					throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
				}

				interpreter.callingFunction(name, func);

				// Now assign all the parameters
				List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
				for (int i = 0; i < argCount; i++)
				{
					Powershell_CommandArg arg = argList._elements.get(i);
					Powershell_FunctionParam param = func.@params.@params.getPrimaryElement(i);

					if (arg.arg.getWhich() is Powershell_Expression)
					{
						Powershell_Expression expr = (Powershell_Expression) arg.arg.getWhich();
						EagleValue val = interpreter.getEagleValue(expr);
						interpreter.setSymbol(param, param.var.id.getValue(), val);
						argTypes.Add(val.getType());
					}
				}

				// Prepare to evaluate the method
				long startTime = System.nanoTime();

				// And transfer control to the method
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				foreach (Powershell_Element stmt in func.stmts._elements)
				{
					result = interpreter.tryToInterpret(stmt.element);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}

				// The result was already put on the runtime stack
				long elapsedTime = System.nanoTime() - startTime;
				func._callMetrics.addCallFrom(this, elapsedTime);
				func._argumentsMetrics.calledWith(argTypes);

				// Now remove all those parameters
				interpreter.completedFunction(name, func);
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(whichCommand.getWhich() is Powershell_Function_Reference))
			{
				throw new Exception("Can only transform user functions");
			}
			Powershell_Function_Reference fnName = (Powershell_Function_Reference) whichCommand.getWhich();
			List<AbstractExpression> newArgs = new List<AbstractExpression>();
			if (argList != null && argList.isPresent())
			{
				foreach (Powershell_CommandArg arg in argList._elements)
				{
					AbstractToken which = arg.arg.getWhich();
					if (!(which is Powershell_Expression))
					{
						throw new Exception("Can only transform expression arguments");
					}
					Powershell_Expression expr = (Powershell_Expression) which;
					newArgs.Add(transformer.transformExpression(generator, expr));
				}
			}

			AbstractVariable var = generator.newVariable(fnName.getValue());
			AbstractExpression fnCall = generator.newMethodInvocation(var, newArgs, this);
			return generator.newExpressionStatement(fnCall, this);
		}
	}

}
