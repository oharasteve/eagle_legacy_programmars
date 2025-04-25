// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 23, 2017

package com.eagle.programmar.Java.Generate;

public class Generate_Java
//		extends Generate_Eagle<Java_Program, Java_Class, Java_Statement,
//				Java_Method, Java_Expression, Java_Variable, Java_Type>
{
//	public Generate_Java(String targetFile, Date forceDate)
//	{
//		super(targetFile, forceDate);
//
//		_createProgram = new Generate_Java_Program(this);
//		_createClass = new Generate_Java_Class(this);
//		_createMethod = new Generate_Java_Method(this);
//		_createStatement = new Generate_Java_Statement(this);
//		_createExpression = new Generate_Java_Expression(this);
//
//		_mainPgm = new Java_Program();
//		_mainPgm.classOrEnumList = new TokenList<Java_ClassOrEnum>();
//	}
//
//	public Generate_Java(String targetFile)
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
//		Java_ClassOrEnum clsOrEnum = new Java_ClassOrEnum();
//		clsOrEnum.setWhich(_mainClass);
//		_mainPgm.classOrEnumList.addToken(clsOrEnum);
//	}
//
//	@Override
//	public void addLanguageSpecificMain(String mainName, String entryPoint)
//	{
//		Java_Method mainMethod = new Java_Method();
//		String line = "public static void main(String[] args) { " + mainName + " mainProgram = new " + mainName + "(); "
//				+ "mainProgram." + entryPoint + "(); " + "}";
//		parseLine(mainMethod, line);
//		_createClass.addMethod(_mainClass, mainMethod);
//
//		// In case they are trying to read fmor stdin
//		if (_needsScanner)
//		{
//			Java_Data scannerData = new Java_Data();
//			String scanner = "private java.util.Scanner _scanner = new java.util.Scanner(System.in);";
//			parseLine(scannerData, scanner);
//
//			Java_Statement scannerStmt = new Java_Statement();
//			scannerStmt.setWhich(scannerData);
//			_createClass.addClassData(_mainClass, scannerStmt);
//		}
//	}
//
//	public static Java_ArgumentList createArgumentList(Collection<AbstractExpression> args)
//	{
//		if (args == null || args.size() == 0) return null;
//
//		Java_ArgumentList argList = new Java_ArgumentList();
//
//		boolean first = true;
//		for (AbstractExpression arg0 : args)
//		{
//			Java_Expression arg = (Java_Expression) arg0;
//
//			if (first)
//			{
//				first = false;
//				argList.arg = arg;
//			}
//			else
//			{
//				Java_MoreArguments more = new Java_MoreArguments();
//				more.comma = new PunctuationComma();
//				more.arg = arg;
//				if (argList.moreArgs == null) argList.moreArgs = new TokenList<Java_MoreArguments>();
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
//			return "boolean";
//		case INT:
//			return "int";
//		case LONG:
//			return "long";
//		case STRING:
//			return "String";
//		case PRT:
//			return "java.io.PrintWriter";
//		case USER:
//			return userType;
//		case VOID:
//			return "void";
//		default:
//			throw new RuntimeException("Can't handle type yet: " + type);
//		}
//	}
//
//	public String getPrivacyString(PRIVACY priv)
//	{
//		switch (priv)
//		{
//		case PUBLIC:
//			return "public";
//		case PRIVATE:
//			return "private";
//		case PROTECTED:
//			return "protected";
//		case PACKAGE:
//			return "";
//		case NONE:
//			return "";
//		}
//		throw new RuntimeException("Unexpected PRIVACY value: " + priv);
//	}
//
//	public Java_Annotation createAnnotation(String name)
//	{
//		Java_Annotation annotation = new Java_Annotation();
//		Java_AnnotationCallList annot = new Java_AnnotationCallList();
//		Java_Identifier id = new Java_Identifier();
//		id.setValue(name);
//		annot.idList = new SeparatedList<Java_Identifier, PunctuationPeriod>();
//		annot.idList.addPrimaryElement(id);
//		annotation.setWhich(annot);
//		return annotation;
//	}
}
