(1) There is a separate directory for each package.
That directory contains all of the files (.java, 
.class, .mft, .txt, .jgp, .gif, ...) that are 
associated with that example.  The .jar file for
an example is also located there.

(2) To run an example, you need to copy the 
.jar file to c:\bdk\jars.  Don't copy everything
there at once.  It will slow the BDK startup.

(3) There is a subdirectory under 'beantool' 
named 'jars'.  That is where the .jar files for
that example are located.

(4) The mgaussian Beans don't work with jdk1.2beta2.
Worked fine with jdk1.1.5.  I am continuing to
investigate and will file a bug report with Sun.

(5) Good luck.

Joe O'Neil