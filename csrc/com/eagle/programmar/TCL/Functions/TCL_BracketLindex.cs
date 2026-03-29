// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.TCL.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_Variable = com.eagle.programmar.TCL.TCL_Variable;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_BracketLindex : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.TCL.Terminals.TCL_Keyword LINDEX = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("lindex");
		public TCL_Keyword LINDEX = new TCL_Keyword("lindex");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.TCL.TCL_Variable arrayVar;
		public TCL_Variable arrayVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.TCL.TCL_Expression index;
		public TCL_Expression index;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(arrayVar);
			EagleArray array = (EagleArray) value;
			int i = interpreter.getIntValue(index);
			interpreter.pushEagleValue(array.getValue(i));
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression indexExpr = transformer.transformExpression(generator, index);
			return generator.newVariableExpression(arrayVar.id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, indexExpr, this);
		}
	}

}
