// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Delphi;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Symbols.Delphi_Program_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformer;

public class Delphi_Full extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT TokenList<Delphi_Comment> comments1;
	public @S(20) @DOC("Programs_and_Units_(Delphi)#The_Program_Heading") Delphi_KeywordChoice programOrUnit = new Delphi_KeywordChoice(
			"Program", "Unit");
	public @S(30) Delphi_Program_Definition id;
	public @S(40) PunctuationSemicolon semicolon;
	public @S(50) @OPT TokenList<Delphi_Header> headers;
	public @S(60) @OPT Delphi_BeginEnd beginEnd;
	public @S(70) @OPT Delphi_Keyword END = new Delphi_Keyword("End");
	public @S(80) PunctuationPeriod dot;
	public @S(90) @OPT TokenList<Delphi_Comment> comments2;

	public Delphi_Full()
	{
		super();
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Delphi_Header element : headers._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Delphi_Procedure)
			{
				Delphi_Procedure proc = (Delphi_Procedure) which;
				interpreter.addFunction(proc.forward.name.getValue(), proc);
			}
			else if (which instanceof Delphi_Function)
			{
				Delphi_Function fn = (Delphi_Function) which;
				interpreter.addFunction(fn.forward.name.getValue(), fn);
			}
		}

		// Second pass, execute the program
		for (Delphi_Header header : headers._elements)
		{
			interpreter.tryToInterpret(header.getWhich());
		}
		if (beginEnd.isPresent())
		{
			interpreter.tryToInterpret(beginEnd);
		}
	}

	public void transformFull(EagleTransformer transformer,
			EagleGenerator generator)
	{
		for (Delphi_Header header : this.headers._elements)
		{
			header.processHeader(transformer, generator);
		}
//		String programId = this.id.getValue();
//		generator.addMain(programId, this);

		AbstractStatement body = transformer.transformStatement1(
				generator, this.beginEnd);
		generator.addStatement(body, this);
//		
//		Meth method = generator.createMethod(PrivacyEnum.PUBLIC,
//				StaticEnum.NONE, TypeEnum.VOID, null,
//				entryPoint, null, this.beginEnd);
//		generator.addMethod(method);
//		_transDelphiBody.transformBody(this, method, this.beginEnd);
	}
}
