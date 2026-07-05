// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformer;

public class Delphi_Vars extends TokenSequence implements EagleRunnable
{
	public @S(10) Delphi_Keyword VAR = new Delphi_Keyword("Var");
	public @S(20) TokenList<Delphi_Var> vars;

	public static class Delphi_Var extends TokenSequence
	{
		public @S(10) SeparatedList<Delphi_Variable_Definition, PunctuationComma> vars;
		public @S(20) PunctuationColon colon;
		public @S(30) Delphi_Type type;
		public @S(40) PunctuationSemicolon semicolon;
		public @S(50) @OPT TokenList<Delphi_Comment> comments;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here without initializers
	}

	public void transformVars(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		for (Delphi_Var var : this.vars._elements)
		{
			for (Delphi_Comment comment : var.comments._elements)
			{
				generator.addComment(comment.getValue(), comment);
			}

			AbstractType newType = var.type.convertType(generator);

			for (int i = 0; i < var.vars.getPrimaryCount(); i++)
			{
				Delphi_Variable_Definition def = var.vars.getPrimaryElement(i);
				String varName = def.getValue();
				AbstractStatement data = generator.newDataDeclaration(StaticEnum.NONE, varName, null,
						newType, null, def);
				if (data != null)
				{
					generator.addStatement(data, def);
				}
			}
		}
	}
}
