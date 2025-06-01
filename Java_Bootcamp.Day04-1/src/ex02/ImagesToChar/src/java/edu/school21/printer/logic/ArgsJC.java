package edu.school21.printer.logic;


import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ArgsJC {


    @Parameter(description = "file name")
    public String filename = "./target/resources/image.bmp";

    @Parameter(names = "--black", description = "black color", required = true)
    public String black;

    @Parameter(names = "--white", description = "white color", required = true)
    public String white;

}
