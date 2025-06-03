// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 5, 2017

package com.eagle.programmar.CSharp.Generate_Unused;

public class Generate_CSharp_Method
//		implements Generate_Eagle_Method<CSharp_Method, CSharp_Statement>
{
//	private Generate_CSharp _target;
//
//	public Generate_CSharp_Method(Generate_CSharp target)
//	{
//		_target = target;
//	}
//
//	private static void addMethodModifier(CSharp_Method meth, String mod)
//	{
//		if (meth.modifiers == null)
//		{
//			meth.modifiers = new TokenList<CSharp_MethodModifier>();
//			meth.modifiers.setPresent(true);
//		}
//		CSharp_MethodModifier modifier = new CSharp_MethodModifier();
//		modifier.modifier.setValue(mod);
//		meth.modifiers.addToken(modifier);
//	}
//	
//	@Override
//	public CSharp_Method createMethod(PRIVACY privacy, METHOD_QUALIFIERS qual,
//			TYPES type, String userType, String methodName,
//			ArrayList<MethodArgument> args, AbstractToken source)
//	{
//		// Create the new method skeleton
//		CSharp_Method method = new CSharp_Method();
//		method.methodName = new CSharp_Method_Definition();
//		method.methodName.setValue(methodName);
//		method.setTransformationSource(source);
//
//		// Add public or private
//		switch (privacy)
//		{
//		case NONE:
//			break;
//		case PUBLIC:
//			addMethodModifier(method, "public");
//			break;
//		case PRIVATE:
//			addMethodModifier(method, "private");
//			break;
//		case PROTECTED:
//			addMethodModifier(method, "protected");
//			addMethodModifier(method, "internal");
//			break;
//		case PACKAGE:
//			break;
//		default:
//			throw new RuntimeException("Can't handle method privacy: " + privacy);
//		}
//		
//		// Add static
//		switch (qual)
//		{
//		case NONE:
//			break;
//		case STATIC:
//			addMethodModifier(method, "static");
//			break;
//		default:
//			throw new RuntimeException("Can't handle method qualifier: " + qual);
//		}
//		
//		// Collect arguments, if any
//		method.parameters = new CSharp_MethodParameters();
//		method.parameters.setPresent(true);
//		method.parameters.leftParen = new PunctuationLeftParen();
//		method.parameters.rightParen = new PunctuationRightParen();
//		if (args != null)
//		{
//			boolean first = true;
//			for (MethodArgument arg : args)
//			{
//				CSharp_MethodParameter param = new CSharp_MethodParameter();
//				param.id = new CSharp_Variable_Definition();
//				param.id.setValue(arg.varName);
//				
//				param.cstype = new CSharp_Type();
//				param.cstype.typeName = new CSharp_TypeName();
//				if (arg.isArray)
//				{
//					CSharp_ArrayType arrayType = new CSharp_ArrayType();
//					arrayType.leftBracket = new PunctuationLeftBracket();
//					arrayType.rightBracket = new PunctuationRightBracket();
//					param.cstype.arrayTypes = new TokenList<CSharp_ArrayType>();
//					param.cstype.arrayTypes.addToken(arrayType);
//				}
//				
//				String typeName = _target.getTypeString(arg.type, arg.userType);
//				CSharp_KeywordChoice kw = new CSharp_KeywordChoice(typeName);
//				param.cstype.typeName.setWhich(kw);
//
//				if (first)
//				{
//					first = false;
//					method.parameters.param = param;
//					method.parameters.param.setPresent(true);
//				}
//				else
//				{
//					if (method.parameters.moreParams == null)
//						method.parameters.moreParams = new TokenList<CSharp_MoreParameters>();
//					CSharp_MoreParameters more = new CSharp_MoreParameters();
//					more.param = param;
//					more.comma = new PunctuationComma();
//					method.parameters.moreParams.addToken(more);
//				}
//			}
//		}
//
//		// Add return type
//		String typeName = _target.getTypeString(type, userType);
//		method.returnType = new CSharp_Type();
//		method.returnType.typeName = new CSharp_TypeName();
//		CSharp_KeywordChoice choice = new CSharp_KeywordChoice(typeName);
//		method.returnType.typeName.setWhich(choice);
//
//		// Create method body
//		method.body = new CSharp_MethodBody();
//		CSharp_MethodImplementation impl = new CSharp_MethodImplementation();
//		impl.block = new CSharp_StatementBlock();
//		impl.block.leftBrace = new PunctuationLeftBrace();
//		impl.block.rightBrace = new PunctuationRightBrace();
//		method.body.setWhich(impl);
//
//		return method;
//	}
//	
//	@Override
//	public void addMethodComment(CSharp_Method method, String comment, AbstractToken source)
//	{
//		CSharp_Comment comm = new CSharp_Comment("// " + comment, false);
//		addStatementOrComment(method, comm, source);
//	}
//
//	@Override
//	public void addMethodStatement(CSharp_Method method, CSharp_Statement statement, AbstractToken source)
//	{
//		addStatementOrComment(method, statement, source);
//	}
//
//	private static void addStatementOrComment(CSharp_Method method, AbstractToken token, AbstractToken source)
//	{
//		if (token == null) throw new RuntimeException("Token cannot be null here");
//		
//		CSharp_StatementOrComment stmtOrComment = new CSharp_StatementOrComment();
//		stmtOrComment.setWhich(token);
//		stmtOrComment.setTransformationSource(source);
//
//		CSharp_MethodImplementation impl = (CSharp_MethodImplementation) method.body.getWhich();
//		if (impl.block == null) impl.block = new CSharp_StatementBlock();
//		if (impl.block.statements == null) impl.block.statements = new TokenList<CSharp_StatementOrComment>();
//		impl.block.statements.addToken(stmtOrComment);
//	}
}
