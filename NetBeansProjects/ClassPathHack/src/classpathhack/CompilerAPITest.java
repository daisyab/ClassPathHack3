/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classpathhack;

/**
 *
 * @author guestu
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classpathhack;

/**
 *
 * @author guestu
 */
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;


 // A test class to test dynamic compilation API.
 
 
public class CompilerAPITest {

     static final Logger logger = Logger.getLogger(CompilerAPITest.class.getName());

     public static void main(String args[]) {
        new CompilerAPITest().doCompilation();

     }
     // Does the required object initialisation and compilation.
     
    public void doCompilation() {
        //Creating dynamic java source code file object
        File[] files = {new File("/home/guestu/GeneratedClass.java")};
        
        //Instantiating the java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager stdFileManager = compiler.getStandardFileManager(null, Locale.getDefault(), null);

        Iterable<? extends JavaFileObject> compilationUnits = stdFileManager.getJavaFileObjects(files);

        //Create a diagnostic controller, which holds the compilation problems
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        CompilationTask compilerTask = compiler.getTask(null, stdFileManager, diagnostics, null, null, compilationUnits);

        //Perform the compilation by calling the call method on compilerTask object.
        boolean status = compilerTask.call();

        if (!status) {
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                System.out.format("Error on line %d in %s", diagnostic.getLineNumber(), diagnostic);
            }
        }
        try {
            stdFileManager.close();//Close the file manager
        } catch (IOException e) {
        }
    }

    
}
