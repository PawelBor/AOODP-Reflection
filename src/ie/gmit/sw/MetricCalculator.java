package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class MetricCalculator {
    //map to 
    private HashMap < String, Metric > classMap = new HashMap < > ();
    private String jPath;

    public MetricCalculator(String path) {
            // JAR Path: save the name of path from file explorer...
            this.jPath = path;
            //parse classes to map
            classMapParser();
            //calculate the class measurement from map
            metricCalculator();
        } //END constructor

    //2D Array: gets Class measurement + returns the object information...
    public Object[][] getData() {
            int i = 0;
            Object[][] objInfo = new Object[classMap.size()][4];
            for (String clsItem: classMap.keySet()) {
                // Gets all info: clsName,IN,OUT + Stability
                // Populates the Array with data
                objInfo[i][0] = classMap.get(clsItem).getClsName();
                objInfo[i][1] = classMap.get(clsItem).getInDegree();
                objInfo[i][2] = classMap.get(clsItem).getOutDegree();
                objInfo[i][3] = classMap.get(clsItem).getStability();
                i++;
            } //END for
            return objInfo;
        } //END GetData

    //Populate the Map with class Names
    public void classMapParser() {
            try {
                //Get the jar file(from user selection) -> InputStream for the jar file -> read JAR
                File file = new File(jPath);
                JarInputStream inputStr = new JarInputStream(new FileInputStream(file));
                JarEntry jrContent = inputStr.getNextJarEntry();

                //Loop: contents of the JAR File
                while (jrContent != null) {
                    //If file has .class extension
                    if (jrContent.getName().endsWith(".class")) {
                        //Format the class name
                        //Gets class name, replaces substring of string that matches given (/, \\) RegEx
                        String name = jrContent.getName().replaceAll("/", "\\.");
                        //replace .class extension with empty space
                        name = name.replaceAll(".class", "");
                        if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
                        //Add to map
                        classMap.put(name, new Metric());
                        //get class name -> Set class name
                        classMap.get(name).setClsName(name);
                    } //END if
                    //following reads the next jar entry...
                    jrContent = inputStr.getNextJarEntry();
                } //END  while

                //close stream (resource leak)
                inputStr.close();
            } catch (Exception e) {

                e.printStackTrace();
            }
        } //END classMapParser()


    public void metricCalculator() {
            try {
                //Get the jar file(from user selection)
                File file = new File(jPath);
                //Make URL, make file URI that represents jPath then make URL from URI
                URL url = file.toURI().toURL();
                URL[] urls = new URL[] {
                    url
                };

                //ClassLoader: Get classes from URLs[]
                ClassLoader clsLoader = new URLClassLoader(urls);

                // loop clsName(k) in classMap map
                for (String clsName: classMap.keySet()) {
                    Class < ? > cls = Class.forName(clsName, false, clsLoader);
                    //inspect class, calc in/out degrees + stab
                    inspectClass(cls);
                    /*test to see it works
                    System.out.println(clsName + " indeg: " + classMap.get(clsName).getInDegree());
                    System.out.println(clsName + " outdeg: " + classMap.get(clsName).getOutDegree());
                    System.out.println(clsName + " stab: " + classMap.get(clsName).getStability() + "\n");
                    */
                } // foreach
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // metricCalculator()

    //inspect class, calculate in/out degree
    @SuppressWarnings("rawtypes")
    public void inspectClass(Class < ? > cls) {

            int outDegree = 0;
            //Gets interfaces class implements
            Class[] interfaces = cls.getInterfaces();
            // for interface
            for (Class iFace: interfaces) {

                if (classMap.containsKey(iFace.getName())) {

                    //add to out-Degree
                    outDegree++;

                    //Add in-degree for interface
                    Metric m = classMap.get(iFace.getName());
                    m.setInDegree(m.getInDegree() + 1);
                } //END if

            } //END foreach

            //Get the constructors
            Constructor[] constructors = cls.getConstructors();
            Class[] constructorParams;

            //Gets parameters of each constructor
            for (Constructor cons: constructors) {
                //Get parameters
                constructorParams = cons.getParameterTypes();
                for (Class cParam: constructorParams) {

                    if (classMap.containsKey(cParam.getName())) {

                        //Add to outdegree
                        outDegree++;

                        //Add to in-degree
                        Metric m = classMap.get(cParam.getName());
                        m.setInDegree(m.getInDegree() + 1);

                    } //END if
                } //END foreach
            } //END foreach

            //Get the fields of class
            Field[] fields = cls.getFields();

            for (Field fld: fields) {

                if (classMap.containsKey(fld.getName())) {

                    //add to outdegree
                    outDegree++;

                    // add to indegree 
                    Metric m = classMap.get(fld.getName());
                    m.setInDegree(m.getInDegree() + 1);

                } //END if
            } //END foreach

            //Get all the methods in class
            Method[] methods = cls.getMethods();
            Class[] methodParams;

            //foreach type of methods
            for (Method mth: methods) {
                //Get report type of method
                Class methodReturnType = mth.getReturnType();

                if (classMap.containsKey(methodReturnType.getName())) {
                    //add outdegree
                    outDegree++;

                    //add to indegree
                    Metric bm = classMap.get(methodReturnType.getName());
                    bm.setInDegree(bm.getInDegree() + 1);
                }

                //Get param of methods
                methodParams = mth.getParameterTypes();
                for (Class mthParams: methodParams) {

                    if (classMap.containsKey(mthParams.getName())) {

                        //add to outdegree
                        outDegree++;

                        //add to indegree
                        Metric bm = classMap.get(mthParams.getName());
                        bm.setInDegree(bm.getInDegree() + 1);

                    } //END if
                } //END foreach
            } //END foreach

            classMap.get(cls.getName()).setOutDegree(outDegree);

        } // inspectClass()
} //metric calculator