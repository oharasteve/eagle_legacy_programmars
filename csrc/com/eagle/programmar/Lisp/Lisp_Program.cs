// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

namespace com.eagle.programmar.Lisp
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Lisp_Comment = com.eagle.programmar.Lisp.Terminals.Lisp_Comment;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Lisp_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string LISP = "Lisp";

		public Lisp_Program() : base(LISP, new Lisp_Syntax())
		{
		}

		public override string booleanName(bool flag)
		{
			if (flag)
			{
				return "T";
			}
			return "NIL";
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.lispworks.com/documentation/HyperSpec/Body/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.Lisp.Terminals.Lisp_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<Lisp_SExprOrComment> elements;
		public TokenList<Lisp_SExprOrComment> elements;

		public override void interpret(EagleInterpreter interpreter)
		{
			// Don't need two passes for Lisp. Functions cannot be called before defined.
			foreach (Lisp_SExprOrComment elt in elements._elements)
			{
				interpreter.tryToInterpret(elt);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			return null;
	//		// Don't need two passes for Lisp. Functions cannot be called before defined.
	//		for (Lisp_SExprOrComment elt : elements._elements)
	//		{
	//			transformer.transformExpression(generator, elt);
	//		}
	//
	//		return generator.getTransfomedProgram();
		}
	}

}
