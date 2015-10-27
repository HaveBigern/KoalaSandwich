package com.strath;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class SimpleUMLJPv3 {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("test.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        new ClassDiagramVisitor().visit(cu, null);
    }

    /**
     * extracting more method information
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    	
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("Method name: " + n.getName());
            System.out.println("Method type: " + n.getType());
        	System.out.println("Method modifier: " + n.getModifiers() + " " + decodeModifiers(n.getModifiers()));
        	System.out.println("Method parameters: ");
        	for (Parameter p : n.getParameters()) {
                p.accept(this, arg);
            }
//        	n.getBody().accept(this, arg); // process rest of method body
        }
        
        public void visit(Parameter p, Object arg) {
        	System.out.println("Parameter type: " + p.getType());
        	System.out.println("Parameter name: " + p.getId().getName());
        }
        
        // Adapted from DumpVisitor
        private String decodeModifiers(final int modifiers) {
    		if (ModifierSet.isPrivate(modifiers)) {
    			return("private ");
    		}
    		if (ModifierSet.isProtected(modifiers)) {
    			return("protected ");
    		}
    		if (ModifierSet.isPublic(modifiers)) {
    			return("public ");
    		}
    		if (ModifierSet.isAbstract(modifiers)) {
    			return("abstract ");
    		}
    		if (ModifierSet.isStatic(modifiers)) {
    			return("static ");
    		}
    		if (ModifierSet.isFinal(modifiers)) {
    			return("final ");
    		}
    		if (ModifierSet.isNative(modifiers)) {
    			return("native ");
    		}
    		if (ModifierSet.isStrictfp(modifiers)) {
    			return("strictfp ");
    		}
    		if (ModifierSet.isSynchronized(modifiers)) {
    			return("synchronized ");
    		}
    		if (ModifierSet.isTransient(modifiers)) {
    			return("transient ");
    		}
    		if (ModifierSet.isVolatile(modifiers)) {
    			return("volatile ");
    		}
    		return "";
    	}
    }
}