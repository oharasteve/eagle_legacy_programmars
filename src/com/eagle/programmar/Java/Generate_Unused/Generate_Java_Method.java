// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 5, 2017

package com.eagle.programmar.Java.Generate_Unused;

public class Generate_Java_Method
//		implements Generate_Eagle_Method<Java_Method, Java_Statement>
{
//	private Generate_Java _target;
//
//	public Generate_Java_Method(Generate_Java target)
//	{
//		_target = target;
//	}
//
//	@Override
//	public Java_Method createMethod(PRIVACY privacy, METHOD_QUALIFIERS qual, TYPES type, String userType,
//			String methodName, ArrayList<MethodArgument> args, AbstractToken source)
//	{
//		// Create the new method skeleton
//		Java_MethodType methodType = new Java_MethodType();
//		methodType.methodName = new Java_Method_Definition();
//		methodType.methodName.setValue(methodName);
//
//		Java_MethodTypeAndName typeAndName = new Java_MethodTypeAndName();
//		typeAndName.setWhich(methodType);
//
//		Java_Method method = new Java_Method();
//		method.typeAndName = typeAndName;
//
//		method.setTransformationSource(source);
//
//		// Add @Override if necessary
//		if (qual == METHOD_QUALIFIERS.OVERRIDES)
//		{
//			Java_Annotation annotation = _target.createAnnotation("Override");
//
//			if (method.modifiers == null) method.modifiers = new TokenList<Java_MethodModifier>();
//			Java_MethodModifier modifier = new Java_MethodModifier();
//			modifier.setWhich(annotation);
//			method.modifiers.addToken(modifier);
//		}
//
//		// Add public or private
//		String priv = _target.getPrivacyString(privacy);
//		if (method.modifiers == null) method.modifiers = new TokenList<Java_MethodModifier>();
//		Java_KeywordChoice privacyChoice = new Java_KeywordChoice(priv);
//		Java_MethodModifier mod = new Java_MethodModifier();
//		mod.setWhich(privacyChoice);
//		method.modifiers.addToken(mod);
//
//		// Collect arguments, if any
//		methodType.parameters = new Java_ParameterList();
//		methodType.parameters.leftParen = new PunctuationLeftParen();
//		methodType.parameters.rightParen = new PunctuationRightParen();
//		if (args != null)
//		{
//			boolean first = true;
//			for (MethodArgument arg : args)
//			{
//				Java_MethodParameter param = new Java_MethodParameter();
//				param.id = new Java_Variable_Definition();
//				param.id.setValue(arg.varName);
//				param.jtype = new Java_Type();
//				param.jtype.typeName = new Java_TypeName();
//				String typeName = _target.getTypeString(arg.type, arg.userType);
//				Java_KeywordChoice kw = new Java_KeywordChoice(typeName);
//				param.jtype.typeName.setWhich(kw);
//
//				if (first)
//				{
//					first = false;
//					methodType.parameters.param = param;
//					methodType.parameters.param.setPresent(true);
//				}
//				else
//				{
//					if (methodType.parameters.moreParams == null)
//					{
//						methodType.parameters.moreParams = new TokenList<Java_MoreParameters>();
//					}
//					Java_MoreParameters more = new Java_MoreParameters();
//					more.param = param;
//					more.comma = new PunctuationComma();
//					methodType.parameters.moreParams.addToken(more);
//				}
//			}
//		}
//
//		// Add return type
//		String typeName = _target.getTypeString(type, userType);
//		methodType.jtype = new Java_Type();
//		methodType.jtype.typeName = new Java_TypeName();
//		Java_KeywordChoice choice = new Java_KeywordChoice(typeName);
//		methodType.jtype.typeName.setWhich(choice);
//
//		// Create method body
//		method.body = new Java_MethodBody();
//		Java_MethodImplementation impl = new Java_MethodImplementation();
//		impl.block = new Java_StatementBlock();
//		impl.block.leftBrace = new PunctuationLeftBrace();
//		impl.block.rightBrace = new PunctuationRightBrace();
//		method.body.setWhich(impl);
//
//		return method;
//	}
//
//	@Override
//	public void addMethodComment(Java_Method method, String comment, AbstractToken source)
//	{
//		Java_Comment comm = new Java_Comment("// " + comment, false);
//		addStatementOrComment(method, comm, source);
//	}
//
//	@Override
//	public void addMethodStatement(Java_Method method, Java_Statement statement, AbstractToken source)
//	{
//		addStatementOrComment(method, statement, source);
//	}
//
//	private static void addStatementOrComment(Java_Method method, AbstractToken token, AbstractToken source)
//	{
//		Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
//		stmtOrComment.setWhich(token);
//		stmtOrComment.setTransformationSource(source);
//
//		Java_MethodImplementation impl = (Java_MethodImplementation) method.body.getWhich();
//		if (impl.block == null) impl.block = new Java_StatementBlock();
//		if (impl.block.statements == null) impl.block.statements = new TokenList<Java_StatementOrComment>();
//		impl.block.statements.addToken(stmtOrComment);
//	}
}
