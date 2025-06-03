// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 23, 2017

package com.eagle.programmar.CSharp.Generate_Unused;

public class Generate_CSharp
//		extends Generate_Eagle<CSharp_Program, CSharp_Class, CSharp_Statement,
//				CSharp_Method, CSharp_Expression, CSharp_Variable, CSharp_Type>
{
//	public Generate_CSharp(String targetFile, Date forceDate)
//	{
//		super(targetFile, forceDate);
//
//		_createProgram = new Generate_CSharp_Program(this);
//		_createClass = new Generate_CSharp_Class(this);
//		_createMethod = new Generate_CSharp_Method(this);
//		_createStatement = new Generate_CSharp_Statement(this);
//		_createExpression = new Generate_CSharp_Expression(this);
//
//		_mainPgm = new CSharp_Program();
//		_mainPgm.myClasses = new TokenList<CSharp_NamespaceOrClassEntry>();
//	}
//
//	public Generate_CSharp(String targetFile)
//	{
//		this(targetFile, null);
//	}
//
//	@Override
//	public void createEmptyClass(String targetFile)
//	{
//		int dotPos = targetFile.lastIndexOf('.');
//		if (dotPos < 0) throw new RuntimeException("Missing dot in filename: " + targetFile);
//		int slashPos = targetFile.lastIndexOf('/'); // -1 is ok here
//		if (slashPos < 0) slashPos = targetFile.lastIndexOf('\\'); // here too
//		String clsName = targetFile.substring(slashPos + 1, dotPos);
//		_mainClass = _createClass.addInnerClass(PRIVACY.PUBLIC, null, clsName, CLASS_QUALIFIERS.NONE, null);
//
//		CSharp_NamespaceOrClassEntry entry = new CSharp_NamespaceOrClassEntry();
//		entry.setWhich(_mainClass);
//		_mainPgm.myClasses.addToken(entry);
//	}
//
//	public static CSharp_ArgumentList createArgumentList(
//			Collection<AbstractExpression> args)
//	{
//		if (args == null || args.size() == 0) return null;
//
//		CSharp_ArgumentList argList = new CSharp_ArgumentList();
//		boolean first = true;
//		for (AbstractExpression arg0 : args)
//		{
//			CSharp_Argument arg = new CSharp_Argument();
//			CSharp_ArgumentOut out = new CSharp_ArgumentOut();
//			out.arg = (CSharp_Expression) arg0;
//			arg.setWhich(out);
//
//			if (first)
//			{
//				first = false;
//				argList.arg = arg;
//				argList.arg.setPresent(true);
//			}
//			else
//			{
//				CSharp_MoreArguments more = new CSharp_MoreArguments();
//				more.comma = new PunctuationComma();
//				more.arg = arg;
//				more.arg.setPresent(true);
//				if (argList.moreArgs == null) argList.moreArgs = new TokenList<CSharp_MoreArguments>();
//				argList.moreArgs.addToken(more);
//			}
//		}
//		return argList;
//	}
//
//	public String getTypeString(TYPES type, String userType)
//	{
//		switch (type)
//		{
//		case NONE:
//			return "";
//		case BOOLEAN:
//			return "bool";
//		case INT:
//			return "int";
//		case LONG:
//			return "long";
//		case FLOAT:
//			return "float";
//		case DOUBLE:
//			return "double";
//		case STRING:
//			return "string";
//		case PRT:
//			return "cs.io.PrintWriter";
//		case USER:
//			return userType;
//		case VOID:
//			return "void";
//		}
//		throw new RuntimeException("Unexpected TYPE value: " + type);
//	}
//
//	public CSharp_Annotation createAnnotation(String name)
//	{
//		CSharp_Annotation annotation = new CSharp_Annotation();
//		annotation.leftBracket = new PunctuationLeftBracket();
//		annotation.rightBracket = new PunctuationRightBracket();
//
//		annotation.item = new CSharp_AnnotationItem();
//		annotation.item.ids = new SeparatedList<CSharp_Identifier, PunctuationPeriod>();
//
//		CSharp_Identifier id = new CSharp_Identifier();
//		id.setValue(name);
//		annotation.item.ids.addPrimaryElement(id);
//
//		return annotation;
//	}
}
