// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 8, 2024

// So,
//
// I don't think this is the right way to do this.
// Point-to-point seems wrong.
// Need to use an abstraction in the middle.
//
// The original idea was to use Reflection and assume that Java and C#
// matched up 90% of the time, or more.
// But I'm not so sure that really works.

package com.eagle.transform;

import java.io.File;

import com.eagle.programmar.Java.Java_Class;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Method;
import com.eagle.programmar.Java.Java_Program;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.utils.EagleEnvironment;
import com.eagle.utils.EaglePath;

public class Java_CSharp
{
	private final String JAVA_ROOT = EaglePath.combinePaths(
			EagleEnvironment.TOPDIR + "/");
	private final String CS_ROOT = EaglePath.combinePaths(
			EagleEnvironment.TOPDIR + "/");
	private final String XML_ROOT = EaglePath.combinePaths(
			EagleEnvironment.ARTIFACTDIR, "Eagle", EagleEnvironment.PARSED + "/");
	private final String HTML_ROOT = EaglePath.combinePaths(
			EagleEnvironment.ARTIFACTDIR, "Eagle", EagleEnvironment.HTML + "/");

	private int _count = 0;
	private int _failed = 0;

	private void processDir(File dir, String javaDir, String csDir,
			String xmlDir, String htmlDir)
	{
		for (File fil : dir.listFiles())
		{
			String name = fil.getName();
			if (name.startsWith(".")) continue;

			if (fil.isDirectory())
			{
				// Process sub-directories
				String slashName = name + "/";
				processDir(fil, javaDir + slashName,
						(csDir + slashName).replace("/src/", "/csrc/"),
						xmlDir + slashName, htmlDir + slashName);
			}
			else if (name.endsWith(".java") && name.startsWith("DeTabber"))
			{
				// Process java files
				String base = name.replace(".java", "");
				_count++;

				String javaFile = javaDir + base + ".java";
				String xmlFile = xmlDir + base + "_java.xml";
				String htmlFile = htmlDir + base + ".html";
				String csFile = csDir + base + ".cs";

				System.out.println(_count + ". Converting " + javaFile +
						"\n    to " + csFile);

				try
				{
					File csDirFile = new File(csDir);
					if (!csDirFile.exists())
					{
						System.out.println("Creating directory " + csDirFile.getCanonicalPath());
						csDirFile.mkdirs();
					}

					File htmlDirFile = new File(htmlDir);
					if (!htmlDirFile.exists())
					{
						System.out.println("Creating directory " + htmlDirFile.getCanonicalPath());
						htmlDirFile.mkdirs();
					}

					EagleTransformMain<Java_Program, Java_Class, Java_Statement, Java_Method, Java_Expression, Java_Variable, Java_Type> etm = new EagleTransformMain<Java_Program, Java_Class, Java_Statement, Java_Method, Java_Expression, Java_Variable, Java_Type>();
					etm.doTransform(xmlFile, javaFile, csFile, htmlFile);
				}
				catch (Exception ex)
				{
					_failed++;
					System.err.println("Error converting " + javaFile + "\n" + ex.getMessage());
				}
			}
		}
	}

	public static void main(String args[])
	{
		Java_CSharp jcs = new Java_CSharp();
		jcs.processDir(new File(jcs.JAVA_ROOT), jcs.JAVA_ROOT,
				jcs.CS_ROOT, jcs.XML_ROOT, jcs.HTML_ROOT);
		System.out.println("Transformed " + jcs._count + " files from Java to C#");
		if (jcs._failed > 0)
		{
			System.out.println("  Failures: " + jcs._failed);
		}
	}
}
